package com.ssafy.market.domain.detaildeal.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.ssafy.market.domain.detaildeal.domain.DetailDeal;
import com.ssafy.market.domain.detaildeal.repository.DetailDealRepository;
import com.ssafy.market.domain.post.respository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DetailDealQuery implements GraphQLQueryResolver {
    private final DetailDealRepository detaildealRepository;
    private final PostRepository postRepository;

//    public Iterable<DetailDeal> findAllDetailDeals() {
//        return detaildealRepository.findAll();
//    }

//    public Optional<Post> findPostByPostId(Long id) {
//        return postRepository.findByPost_id(id);
//    }

    public Optional<DetailDeal> findDetailDealByPostId(Long postId) {
        return detaildealRepository.findBydealId(postId);
    }

//    public Iterable<Post> findAllPostsByUploaderId() {
//        return postRepository.findAll();
//    }
}
