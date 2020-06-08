package com.ssafy.market.domain.post.repository;

import com.ssafy.market.domain.post.domain.Post;
import com.ssafy.market.domain.post.dto.PostMetaOutput;
import com.ssafy.market.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    Post findByPostId(Long id);
    List<Post> findByUser(User user);
    List<Post> findPostByUser(User user);
    List<Post> findAllByOrderByPostIdDesc();
    List<Post> findByUserIdOrderByPostIdDesc(Long userId);
    int deleteByPostId(Long id);
    long countPostByUserId(Long UserId);
    List<Post> findTop6ByOrderByCreatedDateDesc();
//    Post findTop1ByOrderByPostIdDesc();
    List<Post> findByTitleContaining(String title);


}
