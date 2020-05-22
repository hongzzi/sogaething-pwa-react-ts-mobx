package com.ssafy.market.domain.detaildeal.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.ssafy.market.domain.detaildeal.domain.DetailDeal;
import com.ssafy.market.domain.detaildeal.dto.CreateDetailDealInput;
import com.ssafy.market.domain.detaildeal.dto.UpdateDetailDealInput;
import com.ssafy.market.domain.detaildeal.repository.DetailDealRepository;
import com.ssafy.market.domain.hashtag.domain.Hashtag;
import com.ssafy.market.domain.hashtag.repository.HashtagRepository;
import com.ssafy.market.domain.post.domain.Post;
import com.ssafy.market.domain.post.respository.PostRepository;
import com.ssafy.market.domain.user.domain.User;
import com.ssafy.market.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@RequiredArgsConstructor
public class DetailDealMutation implements GraphQLMutationResolver {

    private final DetailDealRepository detailDealRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final HashtagRepository hashtagRepository;

    @Transactional
    public DetailDeal createDetailDeal(CreateDetailDealInput input){
        System.out.println(input.toString());
        Post post = postRepository.findByPostId(input.getPostId()).get();
        User user = userRepository.findById(input.getUserId()).get();
        Hashtag hashtag = hashtagRepository.findById(input.getHashtagId()).get();
        return  detailDealRepository.save(new DetailDeal(null, post,user,hashtag));
    }

//    @Transactional
//    public DetailDeal updateDetailDeal(UpdateDetailDealInput input){
//
//    }
}
