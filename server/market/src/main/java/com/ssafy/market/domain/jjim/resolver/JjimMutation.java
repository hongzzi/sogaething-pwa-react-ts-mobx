package com.ssafy.market.domain.jjim.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.ssafy.market.domain.jjim.domain.Jjim;
import com.ssafy.market.domain.jjim.dto.CreateJjimInput;
import com.ssafy.market.domain.jjim.repository.JjimRepository;
import com.ssafy.market.domain.post.domain.Post;
import com.ssafy.market.domain.post.repository.PostRepository;
import com.ssafy.market.domain.user.domain.User;
import com.ssafy.market.domain.user.repository.UserRepository;
import com.ssafy.market.domain.user.security.TokenProvider;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class JjimMutation implements GraphQLMutationResolver {

    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;
    private final JjimRepository jjimRepository;
    private final PostRepository postRepository;

    @Transactional
    public Jjim createJjim(CreateJjimInput input, DataFetchingEnvironment env){
        Long userId = tokenProvider.getUserIdFromHeader(env);
        User user = userRepository.findByUserId(userId);
        Post post = postRepository.findByPostId(input.getPostId());
        Jjim jjim = jjimRepository.save(new Jjim(null,user,post));
        return jjim;
    }
    @Transactional
    public int deleteJjim(Long jjimId){
        Jjim jjim = jjimRepository.findByJjimId(jjimId);
        return jjimRepository.deleteByJjimId(jjimId);
    }
}
