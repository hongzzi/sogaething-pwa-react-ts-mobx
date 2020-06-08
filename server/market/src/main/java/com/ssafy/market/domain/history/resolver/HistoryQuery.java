package com.ssafy.market.domain.history.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.ssafy.market.domain.file.domain.File;
import com.ssafy.market.domain.file.repository.FileRepository;
import com.ssafy.market.domain.hashtag.domain.Hashtag;
import com.ssafy.market.domain.hashtag.repository.HashtagRepository;
import com.ssafy.market.domain.history.domain.History;
import com.ssafy.market.domain.history.dto.UserHistoryResponse;
import com.ssafy.market.domain.history.respository.HistoryRepository;
import com.ssafy.market.domain.post.domain.Post;
import com.ssafy.market.domain.post.repository.PostRepository;
import com.ssafy.market.domain.product.domain.Product;
import com.ssafy.market.domain.product.repository.ProductRepository;
import com.ssafy.market.domain.user.domain.User;
import com.ssafy.market.domain.user.dto.UserInfoOutput;
import com.ssafy.market.domain.user.repository.UserRepository;
import com.ssafy.market.domain.user.security.TokenProvider;
import graphql.schema.DataFetchingEnvironment;
import graphql.servlet.GraphQLContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Component
@RequiredArgsConstructor
public class HistoryQuery implements GraphQLQueryResolver {
    private final HistoryRepository historyRepository;
    private final PostRepository postRepository;
    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;
    private final HashtagRepository hashtagRepository;
    private final ProductRepository productRepository;
    private final FileRepository fileRepository;

    public List<UserHistoryResponse> findUserHistoryByUserId(DataFetchingEnvironment env){
        Long userId = tokenProvider.getUserIdFromHeader(env);
        List<History> histories = historyRepository.findByUserIdOrderByCreatedDateDesc(userId);
        List<Long> postIds = new ArrayList<>();
        for (int i = 0; i < histories.size(); i++) {
            if(!postIds.contains(histories.get(i).getPostId())) {
                postIds.add(histories.get(i).getPostId());
            }
        }
        List<UserHistoryResponse> userHistoryResponses = new ArrayList<>();

        for (int i = 0; i < postIds.size(); i++) {
            Long postId = postIds.get(i);
            Post post = postRepository.findByPostId(postId);
            if(userId == post.getUserId()) continue;

            Product product = productRepository.findByPost(post);
            UserHistoryResponse response = UserHistoryResponse.builder()
                    .user(userRepository.findByUserId(userId))
                    .postId(post.getPostId())
                    .isBuy(post.isBuy())
                    .title(post.getTitle())
                    .saleDate(post.getSaleDate())
                    .contents(post.getContents())
                    .viewCount(post.getViewCount())
                    .deal(post.getDeal())
                    .createdDate(post.getCreatedDate())
                    .modifiedDate(post.getModifiedDate())
                    .hashTags(hashtagRepository.findByProduct(product))
                    .price(productRepository.totalPriceByPostId(post))
                    .imgUrls(fileRepository.findByProduct(product))
                    .build();
            userHistoryResponses.add(response);
            if(userHistoryResponses.size()==7) break;
        }
        return userHistoryResponses;
    }
}
