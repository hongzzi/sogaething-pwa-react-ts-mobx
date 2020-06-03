package com.ssafy.market.domain.user.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.ssafy.market.domain.post.domain.Post;
import com.ssafy.market.domain.post.repository.PostRepository;
import com.ssafy.market.domain.product.domain.Product;
import com.ssafy.market.domain.product.dto.ProductOutput;
import com.ssafy.market.domain.user.domain.User;
import com.ssafy.market.domain.user.dto.*;
import com.ssafy.market.domain.user.dto.LoginUserOutput;
import com.ssafy.market.domain.user.domain.User;
import com.ssafy.market.domain.user.repository.UserRepository;
import com.ssafy.market.domain.user.security.TokenProvider;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserQuery implements GraphQLQueryResolver {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final TokenProvider tokenProvider;

    @Transactional
    public Iterable<User> findAllUsers(){

        return userRepository.findAll();
    }

//    @Transactional
//    public LoginUserOutput loginUser(DataFetchingEnvironment env){
//        GraphQLContext context = env.getContext();
//        HttpServletRequest request = context.getHttpServletRequest().get();
//        String Jwt = (String)request.getAttribute("Jwt");
//        LoginUserOutput output = new LoginUserOutput(Jwt);
//        return output;
//    }

    @Transactional
    public List<UserOutput> findAllUser() {
        List<UserOutput> outputs = new ArrayList<>();
        List<User> userList = userRepository.findAll();
        for (int i = 0; i < userList.size(); i++) {
            outputs.add(new UserOutput(userList.get(i).getUserId(),
                    userList.get(i).getName(),
                    userList.get(i).getEmail(),
                    userList.get(i).getImageUrl(),
                    userList.get(i).getProvider(),
                    userList.get(i).getProviderId(),
                    userList.get(i).getPhone(),
                    userList.get(i).getAddress(),
                    userList.get(i).getTrust()));
        }
        return outputs;
    }
    @Transactional
    public UserInfoResponse findUserInfo(DataFetchingEnvironment env){
        Long userId = tokenProvider.getUserIdFromHeader(env);

        User user = userRepository.findById(userId).get();
        Long numOfPosts = postRepository.countPostByUserId(userId);

        UserInfoResponse output = new UserInfoResponse();
        output.setUserId(user.getUserId());
        output.setName(user.getName());
        output.setAddress(user.getAddress());
        output.setTrust(user.getTrust());
        output.setNumOfPosts(numOfPosts);
        output.setImgurl(user.getImageUrl());
        return output;
    }

}
