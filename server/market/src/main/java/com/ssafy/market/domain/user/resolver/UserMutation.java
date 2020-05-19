package com.ssafy.market.domain.user.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.ssafy.market.domain.post.domain.Post;
import com.ssafy.market.domain.post.dto.CreatePostInput;
import com.ssafy.market.domain.post.respository.PostRepository;
import com.ssafy.market.domain.user.dto.LoginOutput;
import com.ssafy.market.domain.user.dto.LoginUserInput;
import com.ssafy.market.domain.user.repository.UserRepository;
import graphql.schema.DataFetchingEnvironment;
import graphql.servlet.GraphQLContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class UserMutation implements GraphQLMutationResolver {

    private final UserRepository userRepository;

    @Transactional
    public LoginOutput loginUser(LoginUserInput input, DataFetchingEnvironment env){
        System.out.println(input.toString());
        GraphQLContext context = env.getContext();
        HttpServletRequest request = context.getHttpServletRequest().get();
        String token = request.getHeader("Authorization");
        LoginOutput output = new LoginOutput(token);
        return output;
    }
}