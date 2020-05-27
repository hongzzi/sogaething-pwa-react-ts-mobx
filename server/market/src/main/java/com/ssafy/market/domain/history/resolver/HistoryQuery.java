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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        List<History> histories = historyRepository.findTop7ByUserIdOrderByCreatedDateDesc(userId);

        List<UserHistoryResponse> userHistoryResponses = new ArrayList<>();
        for (int i = 0; i < histories.size(); i++) {
            UserHistoryResponse response = new UserHistoryResponse();
            Post post = postRepository.findByPostId(histories.get(i).getPostId()).get();
            Product product = productRepository.findByPost(post);

            response.setUser(userRepository.findByUserId(userId));
            response.setPostId(post.getPostId());
            response.setIsBuy(post.isBuy());
            response.setTitle(post.getTitle());
            response.setSaleDate(post.getSaleDate());
            response.setContents(post.getContents());
            response.setViewCount(post.getViewCount());
            response.setDeal(post.getDeal());
            response.setCreatedDate(post.getCreatedDate());
            response.setModifiedDate(post.getModifiedDate());
            response.setHashTags(hashtagRepository.findByProduct(product));
            response.setPrice((long)123);
            response.setImgUrls(fileRepository.findByProduct(product));
            userHistoryResponses.add(response);
        }
        return userHistoryResponses;
    }
}