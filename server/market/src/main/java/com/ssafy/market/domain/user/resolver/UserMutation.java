package com.ssafy.market.domain.user.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.ssafy.market.domain.user.domain.User;
import com.ssafy.market.domain.user.dto.*;
import com.ssafy.market.domain.user.repository.UserRepository;
import com.ssafy.market.domain.user.security.TokenProvider;
import com.ssafy.market.domain.user.util.CookieUtils;
import com.ssafy.market.global.apis.KakaoApi;
import com.ssafy.market.global.apis.NewImageApi;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class UserMutation implements GraphQLMutationResolver {

    private final UserRepository userRepository;
    private final KakaoApi kakaoApi;
    private final TokenProvider tokenProvider;
    private final NewImageApi apis;

    @Transactional
    public LoginUserOutput loginUser(LoginUserInput input) {
        String Provider = input.getProvider();
        String Token = input.getToken();
        String Jwt = "ERROR";
        if (StringUtils.hasText(Provider) && Provider.equals("Kakao")) {

            User user = kakaoApi.getUserInfo(Token);

            User selected = userRepository.findByProviderId(user.getProviderId());
            if (selected == null) {
                userRepository.save(user);
            }
            user = userRepository.findByProviderId(user.getProviderId());
            Jwt = tokenProvider.createJwtToken(user);

            RequestAttributes attrs = RequestContextHolder.getRequestAttributes();
            HttpServletResponse res = ((ServletRequestAttributes) attrs).getResponse();
            CookieUtils.addCookie(res, "token", Jwt, 3600);
        }

        LoginUserOutput output = new LoginUserOutput(Jwt);
        return output;
    }

    @Transactional
    public UserOutput updateImg(UpdateImgInput input, DataFetchingEnvironment env) {
        UserOutput output = null;
        try {
            Long userId = tokenProvider.getUserIdFromHeader(env);
            User user = userRepository.findByUserId(userId);
            String[] image = input.getImageUrl().split(",");
            String img = apis.uploadImg(image[1]);
            user.updateimg(img);
            output = new UserOutput(userId, user.getName(), user.getEmail(), user.getImageUrl(), user.getProvider(), user.getProviderId(), user.getPhone(), user.getAddress(), user.getTrust());
        } catch (Exception e) {
            System.out.println(e+"에러");
            e.printStackTrace();
        }

        return output;
    }

    @Transactional
    public UserOutput updateUser(UpdateUserInput input, DataFetchingEnvironment env) {
        Long userId = tokenProvider.getUserIdFromHeader(env);
        User user = userRepository.findByUserId(userId);
        String[] image = input.getImageUrl().split(",");
        String img = null;
        try {
            img = apis.uploadImg(image[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }

        user.update(img, input.getPhone(), input.getAddress(), input.getTrust());
        UserOutput output = new UserOutput(userId, user.getName(), user.getEmail(), user.getImageUrl(), user.getProvider(), user.getProviderId(), user.getPhone(), user.getAddress(), user.getTrust());
        return output;
    }

    @Transactional
    public int deleteUser(Long id) {
        User user = userRepository.findByUserId(id);
        return userRepository.deleteByUserId(id);
    }

    public UserLogout logoutUser(Long userId) {

        RequestAttributes attrs = RequestContextHolder.getRequestAttributes();
        HttpServletResponse res = ((ServletRequestAttributes) attrs).getResponse();
        HttpServletRequest req = ((ServletRequestAttributes) attrs).getRequest();
        CookieUtils.deleteCookie(req, res, "token");
        UserLogout output = null;
        Cookie cookie = CookieUtils.getCookie(req, "token");

        if (cookie == null) {
            output = new UserLogout("SUCCESS", userId);
            return output;
        } else {
            output = new UserLogout("FAIL", userId);
            return output;
        }

    }
}

