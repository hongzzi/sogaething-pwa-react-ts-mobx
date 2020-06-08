package com.ssafy.market.domain.history.respository;

import com.ssafy.market.domain.history.domain.History;
import com.ssafy.market.domain.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface HistoryRepository extends JpaRepository<History, Long> {
//    List<History> findByUserIdByOrderByCreatedDateDesc(Long UserId);
    List<History> findByUserIdOrderByCreatedDateDesc(Long UserId);

    @Query(value = "SELECT DISTINCT post_id FROM history WHERE user_id = :userId order by created_date desc", nativeQuery = true)
    List<Long> findDistinctByUserIdOrderByCreatedDateDesc(@Param("userId")Long userId);

//    @Query(value = "SELECT DISTINCT post_id FROM history WHERE user_id = :userId order by created_date desc", nativeQuery = true)
//    List<Long> findPostIdByCategory(@Param("userId")Long userId);
}
