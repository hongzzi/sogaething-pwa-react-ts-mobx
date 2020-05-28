package com.ssafy.market.domain.hashtag.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.ssafy.market.domain.file.domain.File;
import com.ssafy.market.domain.file.repository.FileRepository;
import com.ssafy.market.domain.hashtag.domain.Hashtag;
import com.ssafy.market.domain.hashtag.dto.HashtagOutput;
import com.ssafy.market.domain.hashtag.repository.HashtagRepository;
import com.ssafy.market.domain.post.domain.Post;
import com.ssafy.market.global.exception.SelectNotDataException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class HashtagQuery implements GraphQLQueryResolver {
    private final HashtagRepository hashtagRepository;

    public List<HashtagOutput> findAllHashtag()
    {
        List<HashtagOutput> outputs = new ArrayList<>();
        List<Hashtag> hashtagList = hashtagRepository.findAll();
        if(hashtagList.size()==0){
            throw new SelectNotDataException("hashtag 조회 결과 : ");
        }
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
        if(hashtag==null){
            throw new SelectNotDataException("hashtag 조회 결과 : ");
        }
        HashtagOutput hashtagOutput = new HashtagOutput(id,hashtag.getProduct().getProductId(),hashtag.getHashtag());
        return hashtagOutput;
    }

}