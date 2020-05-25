package com.ssafy.market.domain.hashtag.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.ssafy.market.domain.hashtag.domain.Hashtag;
import com.ssafy.market.domain.hashtag.dto.CreateHashtagInput;
import com.ssafy.market.domain.hashtag.repository.HashtagRepository;
import com.ssafy.market.domain.product.domain.Product;
import com.ssafy.market.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class HashtagMutation implements GraphQLMutationResolver {
    private final HashtagRepository hashtagRepository;
    private final ProductRepository productRepository;

    @Transactional
    public Hashtag createHashtag(CreateHashtagInput input){
        Product product = productRepository.findById(input.getProductId()).get();
        return hashtagRepository.save(new Hashtag(null, product, input.getHashtag()));
    }
}
