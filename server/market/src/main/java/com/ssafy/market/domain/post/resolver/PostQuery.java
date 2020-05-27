package com.ssafy.market.domain.post.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.google.common.collect.Lists;
import com.ssafy.market.domain.file.repository.FileRepository;
import com.ssafy.market.domain.hashtag.repository.HashtagRepository;
import com.ssafy.market.domain.post.domain.Post;
import com.ssafy.market.domain.post.dto.PostOutput;
import com.ssafy.market.domain.post.dto.RecentPostResponse;
import com.ssafy.market.domain.post.repository.PostRepository;
import com.ssafy.market.domain.product.domain.Product;
import com.ssafy.market.domain.product.repository.ProductRepository;
import com.ssafy.market.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PostQuery implements GraphQLQueryResolver {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final HashtagRepository hashtagRepository;
    private final ProductRepository productRepository;
    private final FileRepository fileRepository;

    public List<PostOutput> findAllPost() {
        List<PostOutput> outputs = new ArrayList<>();
        List<Post> postList = postRepository.findAll();
        for(int i = 0; i<postList.size();i++){
            outputs.add(new PostOutput(postList.get(i).getPostId(),postList.get(i).getUser().getUserId(), postList.get(i).isBuy()
            ,postList.get(i).getTitle(),postList.get(i).getContents(),postList.get(i).getViewCount(),postList.get(i).getDeal()));
        }
        return outputs;
    }
    public Iterable<Post> findAllPosts(){
        return postRepository.findAll();
    }


    public PostOutput findPostByPostId(Long id) {
        Post post = postRepository.findByPostId(id).get();
        PostOutput output = new PostOutput(id,post.getUser().getUserId(),post.isBuy(),post.getTitle(),post.getContents(),post.getViewCount(),post.getDeal());
        return output;
    }

    public List<RecentPostResponse> findRecentPosts(){
        List<RecentPostResponse> recentPostResponses = new ArrayList<>();
        List<Post> posts = postRepository.findTop6ByOrderByCreatedDateDesc();

        for (int i = 0; i < posts.size(); i++) {
            Post post = posts.get(i);
            Product product = productRepository.findByPost(post);

            RecentPostResponse response = new RecentPostResponse();
            response.setPostId(post.getPostId());
            response.setUser(userRepository.findByUserId(post.getUserId()));
            response.setHashTags(hashtagRepository.findByProduct(product));
            response.setIsBuy(post.isBuy());
            response.setPrice((long)12345);
            response.setSaleDate(post.getSaleDate());
            response.setImgUrls(fileRepository.findByProduct(product));
            response.setCategory(product.getCategory());
            response.setDeal(post.getDeal());
            response.setCreatedDate(post.getCreatedDate());
            response.setSaleDate(post.getSaleDate());

            recentPostResponses.add(response);
        }

        return recentPostResponses;
    }

}