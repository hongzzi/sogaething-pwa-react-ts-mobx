package com.ssafy.market.domain.detaildeal.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.ssafy.market.domain.detaildeal.domain.DetailDeal;
import com.ssafy.market.domain.detaildeal.dto.CreateDetailDealInput;
import com.ssafy.market.domain.detaildeal.dto.DetailDealOutput;
import com.ssafy.market.domain.detaildeal.dto.FileArr;
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

import java.util.ArrayList;
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

    @Transactional
    public DetailDealOutput createDetailDeal(CreateDetailDealInput input){
        Post post = postRepository.findById(input.getPostId()).get();
        User user = userRepository.findById(input.getUserId()).get();
        Hashtag hashtag = hashtagRepository.findById(input.getHashtagId()).get();
        List<FileArr> fileArr = new ArrayList<>();
        Product pro = productRepository.findByPost(post);
        List<File> files = fileRepository.findByProduct(pro);
        for(int j = 0; j<files.size(); j++){
            fileArr.add(new FileArr(files.get(j).getImgPath()));
        }
        DetailDeal detailDeal = detailDealRepository.save(new DetailDeal(null, post,user,hashtag));
        DetailDealOutput output = new DetailDealOutput(detailDeal.getDealId(),input.getPostId(),fileArr,post.getTitle(),pro.getCategory(),hashtag.getHashtag(),post.getContents(), pro.getPrice(),post.getUser().getUserId(), input.getUserId(),user.getAddress());
        return output;


    }

    @Transactional
    public int deleteDetailDeal(Long id){
        return detailDealRepository.deleteByDealId(id);
    }
}
