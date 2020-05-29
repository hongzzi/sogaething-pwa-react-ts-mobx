package com.ssafy.market.domain.post.repository;

import com.ssafy.market.domain.post.domain.Post;
import com.ssafy.market.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Post findByPostId(Long id);
    List<Post> findByUser(User user);
    List<Post> findPostByUser(User user);
    int deleteByPostId(Long id);
    long countPostByUserId(Long UserId);
    List<Post> findTop6ByOrderByCreatedDateDesc();
}
