package com.ssafy.market.domain.jjim.repository;

import com.ssafy.market.domain.history.domain.History;
import com.ssafy.market.domain.jjim.domain.Jjim;
import com.ssafy.market.domain.post.dto.PostOutput;
import com.ssafy.market.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JjimRepository extends JpaRepository<Jjim, Long> {
    List<Jjim> findByUser(User user);
    Jjim findByJjimId(Long JjimId);
    int deleteByJjimId(Long JjimId);
}
