package com.ssafy.market.domain.hashtag.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.ssafy.market.domain.file.domain.File;
import com.ssafy.market.domain.file.repository.FileRepository;
import com.ssafy.market.domain.hashtag.domain.Hashtag;
import com.ssafy.market.domain.hashtag.repository.HashtagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class HashtagQuery implements GraphQLQueryResolver {
    private final HashtagRepository hashtagRepository;

    public Iterable<Hashtag> findAllHashtags() {
        return hashtagRepository.findAll();
    }

//    public Optional<Post> findPostByPostId(Long id) {
//        return postRepository.findByPost_id(id);
//    }

    public Optional<Hashtag> findHashtagById(Long id) {
        return hashtagRepository.findById(id);
    }

//    public Iterable<Post> findAllPostsByUploaderId() {
//        return postRepository.findAll();
//    }
}