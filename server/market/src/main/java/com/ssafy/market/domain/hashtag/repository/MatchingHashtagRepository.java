package com.ssafy.market.domain.hashtag.repository;

import com.ssafy.market.domain.hashtag.domain.MatchingHashtag;
import com.ssafy.market.domain.matching.domain.Matching;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MatchingHashtagRepository extends JpaRepository<MatchingHashtag, Long> {
    List<MatchingHashtag> findByMatching(Matching matching);

    @Query(value = "SELECT DISTINCT hashtag FROM matching_hashtag WHERE matching_id = :matching", nativeQuery = true)
    List<String> findHashtagByMatching(@Param("matching") Matching matching);

    @Query(value = "SELECT DISTINCT hashtag FROM matching_hashtag WHERE matching_id = :matchingId", nativeQuery = true)
    List<String> findHashtagByMatchingId(@Param("matchingId") Long matchingId);

    int deleteByMatchingId(Long matchingId);
}