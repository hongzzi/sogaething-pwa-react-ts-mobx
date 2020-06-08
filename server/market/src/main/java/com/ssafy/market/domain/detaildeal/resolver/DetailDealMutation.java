package com.ssafy.market.domain.detaildeal.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.ssafy.market.domain.detaildeal.domain.DetailDeal;
import com.ssafy.market.domain.detaildeal.dto.CreateDetailDealInput;
import com.ssafy.market.domain.detaildeal.dto.DetailOutput;
import com.ssafy.market.domain.detaildeal.repository.DetailDealRepository;
import com.ssafy.market.domain.file.repository.FileRepository;
import com.ssafy.market.domain.hashtag.repository.HashtagRepository;
import com.ssafy.market.domain.post.domain.Post;
import com.ssafy.market.domain.post.repository.PostRepository;
import com.ssafy.market.domain.product.domain.Product;
import com.ssafy.market.domain.product.repository.ProductRepository;
import com.ssafy.market.domain.user.domain.User;
import com.ssafy.market.domain.user.dto.UserInfoResponse;
import com.ssafy.market.domain.user.repository.UserRepository;
import com.ssafy.market.domain.user.security.TokenProvider;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Component
@RequiredArgsConstructor
public class DetailDealMutation implements GraphQLMutationResolver {
    private final DetailDealRepository detailDealRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final HashtagRepository hashtagRepository;
    private final ProductRepository productRepository;
    private final FileRepository fileRepository;
    private final TokenProvider tokenProvider;

    @Transactional
    public DetailOutput createDetailDeal(CreateDetailDealInput input, DataFetchingEnvironment env){
        Long userId = tokenProvider.getUserIdFromHeader(env);
        Post post = postRepository.findByPostId(input.getPostId());
        User writer = userRepository.findByUserId(post.getUser().getUserId()); // post 작성자
        Long numOfPosts = postRepository.countPostByUserId(writer.getUserId());
        User user = userRepository.findByUserId(userId); // 현재 작성자
        Product pro = productRepository.findByPost(post);
        DetailDeal detailDeal = detailDealRepository.save(new DetailDeal(null, post,user));
        List<String> hash = hashtagRepository.findHashtagDistinctByProduct(pro.getProductId());
        List<String> file = fileRepository.findFilePathByProduct(pro.getProductId());
        UserInfoResponse userInfoResponse = new UserInfoResponse(writer.getUserId(),writer.getName(),writer.getAddress(),writer.getTrust(),numOfPosts,writer.getImageUrl());
        DetailOutput output = new DetailOutput(detailDeal.getDealId(),
                input.getPostId(),file,post.getTitle(),pro.getCategory(),hash,post.getContents(), pro.getPrice(),post.getUser().getUserId(), userId,userInfoResponse,detailDeal.getCreatedDate().toString(),detailDeal.getModifiedDate().toString());
        return output;
    }
    @Transactional
    public int deleteDetailDeal(Long id){
        DetailDeal detailDeal = detailDealRepository.findByDealId(id);
        return detailDealRepository.deleteByDealId(id);
    }
}
