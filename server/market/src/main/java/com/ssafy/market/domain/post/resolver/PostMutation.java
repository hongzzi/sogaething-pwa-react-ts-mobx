package com.ssafy.market.domain.post.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.ssafy.market.domain.post.dto.CreatePostInput;
import com.ssafy.market.domain.post.domain.Post;
import com.ssafy.market.domain.post.respository.PostRepository;
import com.ssafy.market.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class PostMutation implements GraphQLMutationResolver {

    private final PostRepository postRepository;

    @Transactional
    public Post createPost(CreatePostInput input){
        return postRepository.save(new Post(null, new User(input.getUploaderId()), false, input.getTitle(), input.getSaleDate(), input.getContents(), (long) 0, input.getDeal(), "진행중"));
    }
}