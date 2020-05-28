package com.ssafy.market.domain.hashtag.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.ssafy.market.domain.hashtag.domain.Hashtag;
import com.ssafy.market.domain.hashtag.dto.CreateHashtagInput;
import com.ssafy.market.domain.hashtag.dto.HashtagOutput;
import com.ssafy.market.domain.hashtag.dto.UpdateHashtagInput;
import com.ssafy.market.domain.hashtag.repository.HashtagRepository;
import com.ssafy.market.domain.product.domain.Product;
import com.ssafy.market.domain.product.repository.ProductRepository;
import com.ssafy.market.global.exception.DomainNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class HashtagMutation implements GraphQLMutationResolver {
    private final HashtagRepository hashtagRepository;
    private final ProductRepository productRepository;

    @Transactional
    public HashtagOutput createHashtag(CreateHashtagInput input){
        Product product = productRepository.findByProductId(input.getProductId());
        if(product == null) {
            throw new DomainNotFoundException("productId", input.getProductId());
        }
        Hashtag hashtag = hashtagRepository.save(new Hashtag(null, product, input.getHashtag()));
        HashtagOutput output = new HashtagOutput(hashtag.getHashtagId(), product.getProductId(),input.getHashtag());
        return output;
    }
    @Transactional
    public HashtagOutput updateHashtag(UpdateHashtagInput input){
        Hashtag hashtag = hashtagRepository.findByHashtagId(input.getHashtagId());
        if(hashtag==null){
            throw new DomainNotFoundException("hashtagId " , input.getHashtagId());
        }
        hashtag.update(input.getHashtag());
        HashtagOutput output = new HashtagOutput(hashtag.getHashtagId(),hashtag.getProduct().getProductId(),hashtag.getHashtag());
        return output;
    }

    @Transactional
    public int deleteHashtag(Long id){
        Hashtag hashtag = hashtagRepository.findByHashtagId(id);
        if(hashtag==null){
            throw new DomainNotFoundException("hashtagId " , id);
        }
        return hashtagRepository.deleteByHashtagId(id);
    }
}
