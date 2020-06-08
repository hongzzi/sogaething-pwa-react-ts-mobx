package com.ssafy.market.domain.product.repository;


import com.ssafy.market.domain.post.domain.Post;
import com.ssafy.market.domain.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByPost(Post post);
    int deleteByProductId(Long id);
    Product findByProductId(Long productId);

    @Query(value = "SELECT SUM(price) FROM product WHERE post_id = :post", nativeQuery = true)
    Long totalPriceByPostId(@Param("post")Post post);

    @Query(value = "SELECT DISTINCT post_id FROM product WHERE category = :category", nativeQuery = true)
    List<Long> findPostIdByCategory(@Param("category")String category);

    @Query(value = "SELECT * FROM product WHERE post_id IN :postIds and price >= :startPrice and price <= :endPrice ", nativeQuery = true)
    List<Product> findPostByPrice(@Param("startPrice")int startPrice, @Param("endPrice")int endPrice, @Param("postIds")List<Long> postIds);

    @Query(value = "SELECT * FROM product WHERE post_id in (select distinct post_id from post where category = :category) and price >= :startPrice and price <= :endPrice", nativeQuery = true)
    List<Product> findPostByCategoryAndPrice(@Param("startPrice")int startPrice, @Param("endPrice")int endPrice, @Param("category")String category);
}
