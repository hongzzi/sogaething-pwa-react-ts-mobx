package com.ssafy.market.domain.matching.resolver;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.ssafy.market.domain.history.domain.History;
import com.ssafy.market.domain.history.dto.HistoryOutput;
import com.ssafy.market.domain.history.respository.HistoryRepository;
import com.ssafy.market.domain.matching.domain.Matching;
import com.ssafy.market.domain.matching.respository.MatchingRepository;
import com.ssafy.market.domain.post.domain.Post;
import com.ssafy.market.domain.post.dto.CreatePostInput;
import com.ssafy.market.domain.post.dto.MatchInput;
import com.ssafy.market.domain.post.repository.PostRepository;
import com.ssafy.market.domain.user.domain.User;
import com.ssafy.market.domain.user.security.TokenProvider;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MatchingMutation implements GraphQLMutationResolver {
    private final MatchingRepository matchingRepository;
    private final TokenProvider tokenProvider;

    @Transactional
    public Matching createMatching(MatchInput input, DataFetchingEnvironment env) {
        Long userId = tokenProvider.getUserIdFromHeader(env);
        return matchingRepository.save(new Matching(new User(userId), input.getCategory(), Arrays.toString(input.getHashtag()), input.getPrice()[0], input.getPrice()[1], input.getTransaction()));
    }
}