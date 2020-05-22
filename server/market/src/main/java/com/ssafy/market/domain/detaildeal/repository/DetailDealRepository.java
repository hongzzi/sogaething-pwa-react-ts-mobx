package com.ssafy.market.domain.detaildeal.repository;

import com.ssafy.market.domain.detaildeal.domain.DetailDeal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DetailDealRepository extends JpaRepository<DetailDeal, Long> {
    Optional<DetailDeal> findBydealId(Long dealId);
//    Optional<DetailDeal> findByPostId(Long postId);

}
