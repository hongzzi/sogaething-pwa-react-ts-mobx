package com.ssafy.market.domain.matching.respository;

import com.ssafy.market.domain.matching.domain.Matching;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchingRepository extends JpaRepository<Matching, Long> {
    List<Matching> findTop5ByUserId(Long userId);
    int deleteByMatchingId(Long matchingId);
    Matching findByMatchingId(Long matchingId);
}
