package com.ssafy.market.domain.history.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.ssafy.market.domain.history.domain.History;
import com.ssafy.market.domain.history.dto.HistoryOutput;
import com.ssafy.market.domain.history.respository.HistoryRepository;
import com.ssafy.market.domain.post.domain.Post;
import com.ssafy.market.domain.post.dto.CreatePostInput;
import com.ssafy.market.domain.post.repository.PostRepository;
import com.ssafy.market.domain.user.domain.User;
import com.ssafy.market.domain.user.security.TokenProvider;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class HistoryMutation implements GraphQLMutationResolver {
    private final HistoryRepository historyRepository;
    private final TokenProvider tokenProvider;
    private final PostRepository postRepository;

    @Transactional
    public HistoryOutput createHistory(long postId, DataFetchingEnvironment env) {
        Long userId = tokenProvider.getUserIdFromHeader(env);
        History history = historyRepository.save(new History( new User(userId), new Post(postId)));
        HistoryOutput output = HistoryOutput.builder()
                .userId(history.getUser().getUserId())
                .postId(history.getPost().getPostId())
                .createdDate(history.getCreatedDate())
                .modifiedDate(history.getModifiedDate())
                .build();
        Post post = postRepository.findByPostId(postId);
        post.updateViewCount();
        return output;
    }
}