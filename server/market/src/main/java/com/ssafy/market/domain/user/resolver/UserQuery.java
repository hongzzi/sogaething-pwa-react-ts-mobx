package com.ssafy.market.domain.user.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.ssafy.market.domain.post.domain.Post;
import com.ssafy.market.domain.user.domain.User;
import com.ssafy.market.domain.user.dto.LoginUserOutput;
import com.ssafy.market.domain.user.dto.LoginUserInput;
import com.ssafy.market.domain.user.dto.LoginUserOutput;
import com.ssafy.market.domain.user.repository.UserRepository;
import graphql.schema.DataFetchingEnvironment;
import graphql.servlet.GraphQLContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class UserQuery implements GraphQLQueryResolver {

    private final UserRepository userRepository;

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
}
