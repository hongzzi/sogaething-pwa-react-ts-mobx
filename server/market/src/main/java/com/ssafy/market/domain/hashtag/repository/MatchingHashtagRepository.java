package com.ssafy.market.domain.hashtag.repository;

import com.ssafy.market.domain.hashtag.domain.Hashtag;
import com.ssafy.market.domain.hashtag.domain.MatchingHashtag;
import com.ssafy.market.domain.matching.domain.Matching;
import com.ssafy.market.domain.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MatchingHashtagRepository extends JpaRepository<MatchingHashtag, Long> {
    List<MatchingHashtag> findByMatching(Matching matching);
//    int deleteByMatchingId(Long matchingId);
}