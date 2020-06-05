package com.ssafy.market.domain.hashtag.repository;

import com.ssafy.market.domain.hashtag.domain.Hashtag;
import com.ssafy.market.domain.hashtag.dto.HashtagCountOutput;
import com.ssafy.market.domain.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HashtagRepository extends JpaRepository<Hashtag, Long> {
    Hashtag findByHashtagId(Long hashtagId);
    int deleteByHashtagId(Long id);
    List<Hashtag> findByProduct(Product product);
    long countByHashtag(String Hashtag);

    @Query(value = "select hashtag, count(hashtag) count from hashtag where hashtag like :hashtag% group by hashtag order by count(hashtag) desc;", nativeQuery = true)
    List<HashtagCountOutput> countAllByHashtag(@Param("hashtag") String hashtag);

    @Query(value = "SELECT DISTINCT hashtag FROM hashtag WHERE product_id = :product", nativeQuery = true)
    List<String> findHashtagDistinctByProduct(@Param("product") Long productId);

    @Query(value = "SELECT DISTINCT hashtag FROM hashtag WHERE hashtag LIKE :hashtag%", nativeQuery = true)
    List<String> findAutocompleteHashtag(@Param("hashtag") String hashtag);

    @Query(value = "SELECT DISTINCT hashtag FROM hashtag WHERE product_id = :productId", nativeQuery = true)
    List<String> findDistinctByHashtag(@Param("productId") Long productId);

    @Query(value = "select product_id from hashtag where hashtag in :hashtags group by product_id order by count(hashtag) desc;", nativeQuery = true)
    List<Long> findHashtagCount(@Param("hashtags") List<String> hashtags);
}