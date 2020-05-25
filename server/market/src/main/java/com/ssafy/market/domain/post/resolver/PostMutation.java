package com.ssafy.market.domain.post.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.ssafy.market.domain.post.dto.CreatePostInput;
import com.ssafy.market.domain.post.domain.Post;
import com.ssafy.market.domain.post.dto.PostOutput;
import com.ssafy.market.domain.post.dto.UpdatePostInput;
import com.ssafy.market.domain.post.repository.PostRepository;
import com.ssafy.market.domain.user.domain.User;
import com.ssafy.market.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class PostMutation implements GraphQLMutationResolver {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public PostOutput createPost(CreatePostInput input){
        User user = (userRepository.findById(input.getUploaderId()).get());
        Post post = postRepository.save(new Post(null, user, false, input.getTitle(), input.getSaleDate(), input.getContents(), (long) 0, input.getDeal(), "진행중"));
        PostOutput output = new PostOutput(post.getPostId(),user.getUserId(),post.isBuy(),post.getTitle(),post.getContents(),post.getViewCount(),post.getDeal());
        return  output;
    }
    @Transactional
    public PostOutput updatePost(UpdatePostInput input){
        Post post = postRepository.findByPostId(input.getPostId()).get();
        post.update(input.getTitle(),input.getContents(),input.getDeal());
        PostOutput output = new PostOutput(post.getPostId(),post.getUser().getUserId(),post.isBuy(),post.getTitle(),post.getContents(),post.getViewCount(),post.getDeal());
        return output;
    }
    @Transactional
    public int deletePost(Long id){
        return postRepository.deleteByPostId(id);
    }
}