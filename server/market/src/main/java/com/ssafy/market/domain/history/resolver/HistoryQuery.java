package com.ssafy.market.domain.history.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.ssafy.market.domain.history.domain.History;
import com.ssafy.market.domain.history.respository.HistoryRepository;
import com.ssafy.market.domain.post.domain.Post;
import com.ssafy.market.domain.post.respository.PostRepository;
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


    public List<Post> findUserHistoryByUserId(DataFetchingEnvironment env){
        Long userId = tokenProvider.getUserIdFromHeader(env);

        List<History> histories = historyRepository.findTop7ByUserIdOrderByCreatedDateDesc(userId);
        List<Post> posts = new ArrayList<>();
        for (int i = 0; i < histories.size(); i++) {
            posts.add(postRepository.findByPostId(histories.get(i).getPostId()).get());
        }
        return posts;
    }
}