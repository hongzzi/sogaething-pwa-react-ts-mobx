package com.ssafy.market.domain.user.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.ssafy.market.domain.post.respository.PostRepository;
import com.ssafy.market.domain.user.domain.User;
import com.ssafy.market.domain.user.dto.UserInfoOutput;
import com.ssafy.market.domain.user.repository.UserRepository;
import com.ssafy.market.domain.user.security.TokenProvider;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

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

    @Transactional
    public UserInfoOutput findUserInfo(DataFetchingEnvironment env){
        Long userId = tokenProvider.getUserIdFromHeader(env);

        User user = userRepository.findById(userId).get();
        Long numOfPosts = postRepository.countPostByUserId(userId);

        UserInfoOutput output = new UserInfoOutput();
        output.setName(user.getName());
        output.setAddress(user.getAddress());
        output.setTrust(user.getTrust());
        output.setNumOfPosts(numOfPosts);

        return output;
    }
}