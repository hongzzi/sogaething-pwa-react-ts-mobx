package com.ssafy.market.domain.hashtag.repository;

import com.ssafy.market.domain.hashtag.domain.Hashtag;
import com.ssafy.market.domain.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface HashtagRepository extends JpaRepository<Hashtag, Long> {
    Hashtag findByHashtagId(Long hashtagId);
    int deleteByHashtagId(Long id);
    List<Hashtag> findByProduct(Product product);
}
