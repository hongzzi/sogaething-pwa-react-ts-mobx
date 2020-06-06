package com.ssafy.market.domain.matching.respository;

import com.ssafy.market.domain.history.domain.History;
import com.ssafy.market.domain.matching.domain.Matching;
import com.ssafy.market.domain.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface MatchingRepository extends JpaRepository<Matching, Long> {
    List<Matching> findTop5ByUserId(Long userId);
    int deleteByMatchingId(Long matchingId);
    Matching findByMatchingId(Long matchingId);
}
