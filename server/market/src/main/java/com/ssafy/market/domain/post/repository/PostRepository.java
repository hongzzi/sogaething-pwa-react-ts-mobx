package com.ssafy.market.domain.post.repository;

import com.ssafy.market.domain.post.domain.Post;
import com.ssafy.market.domain.post.dto.SearchByCategoryOutput;
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

    @Query(value = "select post.post_id as postId, post.title as title, product.created_date as createdDate, product.modified_date as modifiedDate, file.img_path as imgPath, hashtags.hashtags hashtag, product.price as price, " +
            "post.is_buy as isBuy, post.view_count as viewCount, post.deal as deal, post.deal_state as dealState, post.sale_date as saleDate, post.transaction as transaction, product.category as category from post\n" +
            "join product product on post.post_id = product.post_id \n" +
            "join file file on product.product_id = file.product_id \n" +
            "join (SELECT product_id, group_concat(distinct hashtag separator ',') as hashtags FROM hashtag group by product_id) hashtags on hashtags.product_id = product.product_id\n" +
            "where product.category = :category " +
            "order by createdDate desc", nativeQuery = true)
    List<SearchByCategoryOutput> findPostByCategory(@Param("category")String category);

    @Query(value = "select post.post_id as postId, post.title as title, product.created_date as createdDate, product.modified_date as modifiedDate, file.img_path as imgPath, hashtags.hashtags as hashtag, " +
            "product.price as price, post.is_buy as isBuy, post.view_count as viewCount, post.deal as deal, post.deal_state as dealState, post.sale_date as saleDate, post.transaction as transaction  from post\n" +
            "join product product on post.post_id = product.post_id \n" +
            "join file file on product.product_id = file.product_id \n" +
            "join (SELECT product_id, group_concat(distinct hashtag separator ',') as hashtags FROM hashtag group by product_id) hashtags on hashtags.product_id = product.product_id\n" +
            "where product.product_id in (select product_id from hashtag where hashtag in :hashtags group by product_id order by count(hashtag) desc)", nativeQuery = true)
    List<SearchByCategoryOutput> findByHashTags(@Param("hashtags")List<String> hashtags);

    @Query(value = "select post.post_id as postId, post.title, post.contents, product.category ,product.created_date as createdDate, product.modified_date as modifiedDate, file.img_path as imgPath, hashtags.hashtags, product.price, post.is_buy as isBuy, post.view_count as viewCount, post.deal, post.deal_state as dealState, post.sale_date as saleDate, post.transaction from post\n" +
            "join product product on post.post_id = product.post_id \n" +
            "join file file on product.product_id = file.product_id \n" +
            "join (SELECT product_id, group_concat(distinct hashtag separator ',') as hashtags FROM hashtag group by product_id) hashtags on hashtags.product_id = product.product_id\n" +
            "where product.product_id in (SELECT product_id FROM product WHERE post_id in (select distinct post_id from post where category = \"디지털/가전\") and price >= 3000000 and price <= 3100000)", nativeQuery = true)
    List<SearchByCategoryOutput> findByOptions(@Param("hashtags")List<String> hashtags);
}
