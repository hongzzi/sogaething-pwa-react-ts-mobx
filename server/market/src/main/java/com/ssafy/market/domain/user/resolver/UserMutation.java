package com.ssafy.market.domain.user.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.ssafy.market.domain.post.domain.Post;
import com.ssafy.market.domain.post.dto.CreatePostInput;
import com.ssafy.market.domain.post.respository.PostRepository;
import com.ssafy.market.domain.user.domain.User;
import com.ssafy.market.domain.user.dto.LoginUserOutput;
import com.ssafy.market.domain.user.dto.LoginUserInput;
import com.ssafy.market.domain.user.repository.UserRepository;
import com.ssafy.market.domain.user.security.TokenProvider;
import com.ssafy.market.global.apis.KakaoApi;
import graphql.schema.DataFetchingEnvironment;
import graphql.servlet.GraphQLContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class UserMutation implements GraphQLMutationResolver {

    private final UserRepository userRepository;
    private final KakaoApi kakaoApi;
    private final TokenProvider tokenProvider;

    @Transactional
    public LoginUserOutput loginUser(LoginUserInput input,DataFetchingEnvironment env){
        String Provider = input.getProvider();
        String Token = input.getToken();
        String Jwt = "ERROR";
        if(StringUtils.hasText(Provider) && Provider.equals("Kakao")){
            System.out.println("kakao");
            User user = kakaoApi.getUserInfo(Token);

            User selected = userRepository.findByProviderId(user.getProviderId());
            if(selected == null){
                userRepository.save(user);
            }
            user = userRepository.findByProviderId(user.getProviderId());
            Jwt = tokenProvider.createJwtToken(user);
        }

        GraphQLContext context = env.getContext();
        LoginUserOutput output = new LoginUserOutput(Jwt);
        return output;
    }
}

