package com.ssafy.market.domain.post.respository;

import com.ssafy.market.domain.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
