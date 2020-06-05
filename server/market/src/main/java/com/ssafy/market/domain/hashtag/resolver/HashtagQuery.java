package com.ssafy.market.domain.hashtag.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.ssafy.market.domain.hashtag.domain.Hashtag;
import com.ssafy.market.domain.hashtag.dto.Autocomplete;
import com.ssafy.market.domain.hashtag.dto.HashtagCountOutput;
import com.ssafy.market.domain.hashtag.dto.HashtagOutput;
import com.ssafy.market.domain.hashtag.repository.HashtagRepository;
import com.ssafy.market.domain.hashtag.repository.MatchingHashtagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class HashtagQuery implements GraphQLQueryResolver {
    private final HashtagRepository hashtagRepository;
    private final MatchingHashtagRepository matchingHashtagRepository;

    public List<HashtagOutput> findAllHashtag()
    {
        List<HashtagOutput> outputs = new ArrayList<>();
        List<Hashtag> hashtagList = hashtagRepository.findAll();
        for (int i = 0; i<hashtagList.size();i++){
            outputs.add(new HashtagOutput(hashtagList.get(i).getHashtagId(),hashtagList.get(i).getProduct().getProductId(),hashtagList.get(i).getHashtag()));
        }
        return outputs;
    }

    public Iterable<Hashtag> findAllHashtags(){
        return hashtagRepository.findAll();
    }

    public HashtagOutput findByHashtagId(Long id) {
        Hashtag hashtag = hashtagRepository.findByHashtagId(id);
        HashtagOutput hashtagOutput = new HashtagOutput(id,hashtag.getProduct().getProductId(),hashtag.getHashtag());
        return hashtagOutput;
    }

    public List<Autocomplete> autocomplete(String hashTag) {
        if(hashTag == null|| hashTag.equals("")){
            return new ArrayList<Autocomplete>();
        }
        List<HashtagCountOutput> list = hashtagRepository.countAllByHashtag(hashTag);
        List<Autocomplete> autocompletes = list.stream().map(temp -> {
            return new Autocomplete(temp.getHashtag(), temp.getCount());
        }).collect(Collectors.toList());

        return autocompletes;
    }

    public List<String> findAllMatchingHashtagByMatchingId(Long matchingId) {
        return matchingHashtagRepository.findHashtagByMatchingId(matchingId);
    }
}