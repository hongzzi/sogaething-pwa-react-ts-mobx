package com.ssafy.market.domain.detaildeal.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.ssafy.market.domain.detaildeal.domain.DetailDeal;
import com.ssafy.market.domain.detaildeal.dto.CreateDetailDealInput;
import com.ssafy.market.domain.detaildeal.dto.DetailDealOutput;
import com.ssafy.market.domain.detaildeal.dto.UpdateDetailDealInput;
import com.ssafy.market.domain.detaildeal.repository.DetailDealRepository;
import com.ssafy.market.domain.file.domain.File;
import com.ssafy.market.domain.file.repository.FileRepository;
import com.ssafy.market.domain.hashtag.domain.Hashtag;
import com.ssafy.market.domain.hashtag.repository.HashtagRepository;
import com.ssafy.market.domain.post.domain.Post;
import com.ssafy.market.domain.post.repository.PostRepository;
import com.ssafy.market.domain.product.domain.Product;
import com.ssafy.market.domain.product.repository.ProductRepository;
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
    private final ProductRepository productRepository;
    private final FileRepository fileRepository;

    @Transactional
    public DetailDealOutput createDetailDeal(CreateDetailDealInput input){
        System.out.println(input.toString());

        Post post = postRepository.findById(input.getPostId()).get();
        User user = userRepository.findById(input.getUserId()).get();
        Hashtag hashtag = hashtagRepository.findById(input.getHashtagId()).get();

        Product product = productRepository.findByPost(post);
        File file = fileRepository.findByProduct(product);
        DetailDealOutput output = new DetailDealOutput(input.getPostId(),file.getImgPath(),post.getTitle(),product.getCategory(),hashtag.getHashtag(),post.getContents(), product.getPrice(),post.getUser().getUserId(), input.getUserId(),user.getAddress());
        detailDealRepository.save(new DetailDeal(null, post,user,hashtag));
        return output;
//      return  detailDealRepository.save(new DetailDeal(null, post,user,hashtag));

    }

//    @Transactional
//    public DetailDealOutput updateDetailDeal(UpdateDetailDealInput input){
//        System.out.println(input.toString());
//
//        DetailDeal detailDeal = detailDealRepository.findBydealId(input.getDealId()).get();
//        User user = detailDeal.getUser();
//        Post post = detailDeal.getPost();
//        Hashtag hashtag = hashtagRepository.findById(input.getHashtagId()).get();
//        post.update(input.getTitle(),input.getContents(),input.getDeal());
//        Product product = productRepository.findByPost(post);
//        product.update(post,product.getName(),input.getPrice(),product.getCategory(),product.getState());
//        File file = fileRepository.findByProduct(product);
//        DetailDealOutput output = new DetailDealOutput(input.getPostId(),file.getImgPath(),post.getTitle(),product.getCategory(),hashtag.getHashtag(),post.getContents(), product.getPrice(),post.getUser().getUserId(), input.getUserId(),user.getAddress());
//        detailDealRepository.save(new DetailDeal(null, post,user,hashtag));
//        return output;
////      return  detailDealRepository.save(new DetailDeal(null, post,user,hashtag));
//
//    }

    @Transactional
    public int deleteDetailDeal(Long id){
        return detailDealRepository.deleteByDealId(id);
    }
}
