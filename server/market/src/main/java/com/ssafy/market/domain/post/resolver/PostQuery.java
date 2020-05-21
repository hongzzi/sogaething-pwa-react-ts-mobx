package com.ssafy.market.domain.post.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.ssafy.market.domain.post.domain.Post;
import com.ssafy.market.domain.post.respository.PostRepository;
import jdk.nashorn.internal.runtime.options.Option;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PostQuery implements GraphQLQueryResolver {
    private final PostRepository postRepository;

    public Iterable<Post> findAllPosts() {
        return postRepository.findAll();
    }

//    public Optional<Post> findPostByPostId(Long id) {
//        return postRepository.findByPost_id(id);
//    }

    public Optional<Post> findPostByPostId(Long id) {
        return postRepository.findByPostId(id);
    }

//    public Iterable<Post> findAllPostsByUploaderId() {
//        return postRepository.findAll();
//    }
}