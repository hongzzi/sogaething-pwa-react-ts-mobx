package com.ssafy.market.domain.hashtag.repository;

import com.ssafy.market.domain.hashtag.domain.Hashtag;
import com.ssafy.market.domain.post.domain.Post;
import com.ssafy.market.domain.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.socket.server.HandshakeHandler;

import java.util.List;
import java.util.Optional;

public interface HashtagRepository extends JpaRepository<Hashtag, Long> {
    Hashtag findByHashtagId(Long hashtagId);
    int deleteByHashtagId(Long id);
    List<Hashtag> findByProduct(Product product);
    long countByHashtag(String Hashtag);

    @Query(value = "SELECT DISTINCT hashtag FROM hashtag WHERE hashtag LIKE :hashtag%", nativeQuery = true)
    List<String> findAutocompleteHashtag(@Param("hashtag") String hashtag);

    List<Hashtag> findDistinctByHashtagStartingWith(String hashtag);
}