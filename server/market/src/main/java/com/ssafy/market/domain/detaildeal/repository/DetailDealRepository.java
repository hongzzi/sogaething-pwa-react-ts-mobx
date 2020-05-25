package com.ssafy.market.domain.detaildeal.repository;

import com.ssafy.market.domain.detaildeal.domain.DetailDeal;
import com.ssafy.market.domain.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DetailDealRepository extends JpaRepository<DetailDeal, Long> {

    Optional<DetailDeal> findBydealId(Long dealId);
    List<DetailDeal> findByPost(Post post);

}
