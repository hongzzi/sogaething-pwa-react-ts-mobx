package com.ssafy.market.domain.post.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.ssafy.market.domain.post.dto.CreatePostInput;
import com.ssafy.market.domain.post.domain.Post;
import com.ssafy.market.domain.post.respository.PostRepository;
import com.ssafy.market.domain.user.domain.User;
import com.ssafy.market.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PostMutation implements GraphQLMutationResolver {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public Post createPost(CreatePostInput input){
        System.out.println(input.toString());
        User user = (userRepository.findById(input.getUploaderId()).get());
//        user = user.getUserByid(Long.valueOf(input.getUploaderId()));
//        System.out.println(user.toString());
//        if(user.equals(user)){
//            System.out.println("dasdas");
//        }
//        user = user.getUserByid(input.getUploaderId());

        System.out.println(user.toString());
        return postRepository.save(new Post(null, user, false, input.getTitle(), input.getSaleDate(), input.getContents(), (long) 0, input.getDeal(), "진행중"));

    }
}