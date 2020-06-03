package com.ssafy.market.domain.matching.resolver;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.ssafy.market.domain.hashtag.domain.Hashtag;
import com.ssafy.market.domain.hashtag.domain.MatchingHashtag;
import com.ssafy.market.domain.hashtag.repository.MatchingHashtagRepository;
import com.ssafy.market.domain.matching.domain.Matching;
import com.ssafy.market.domain.matching.respository.MatchingRepository;
import com.ssafy.market.domain.matching.dto.MatchInput;
import com.ssafy.market.domain.user.domain.User;
import com.ssafy.market.domain.user.security.TokenProvider;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MatchingMutation implements GraphQLMutationResolver {
    private final MatchingRepository matchingRepository;
    private final TokenProvider tokenProvider;
    private final MatchingHashtagRepository matchingHashtagRepository;

    @Transactional
    public Matching createMatching(MatchInput input, DataFetchingEnvironment env) {
        Long userId = tokenProvider.getUserIdFromHeader(env);
        Matching matching = matchingRepository.save(new Matching(new User(userId), input.getCategory(), input.getMinPrice(), input.getMaxPrice(), input.getTransaction()));
        List<MatchingHashtag> matchingHashtags = new ArrayList<>();
        for (int i = 0; i < input.getHashtag().length; i++) {
            matchingHashtags.add(new MatchingHashtag(matching, input.getHashtag()[i]));
        }
        matchingHashtagRepository.saveAll(matchingHashtags);
        return matching;
    }

    @Transactional
    public int deleteMatching(Long matchingId) {
        matchingHashtagRepository.deleteByMatchingId(matchingId);
        return matchingRepository.deleteByMatchingId(matchingId);
    }
}