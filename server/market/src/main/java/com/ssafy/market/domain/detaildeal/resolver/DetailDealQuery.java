package com.ssafy.market.domain.detaildeal.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.ssafy.market.domain.detaildeal.domain.DetailDeal;
import com.ssafy.market.domain.detaildeal.dto.DetailOutput;
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
import com.ssafy.market.domain.user.dto.UserInfoResponse;
import com.ssafy.market.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DetailDealQuery implements GraphQLQueryResolver {
    private final DetailDealRepository detailDealRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final HashtagRepository hashtagRepository;
    private final ProductRepository productRepository;
    private final FileRepository fileRepository;

    @Transactional
    public Iterable<DetailDeal> findAllDetailDeals() {
        return detailDealRepository.findAll();
    }

    @Transactional
    public List<DetailOutput> findAllDetailDeal(){
        List<DetailOutput> outputList = new ArrayList<>();
        List<DetailDeal> dealList = detailDealRepository.findAll();
        for(int i =0; i<dealList.size();i++){
            DetailDeal detailDeal = dealList.get(i);
            Long id = detailDeal.getDealId();
            Post po = detailDeal.getPost();
            User user = detailDeal.getUser();
            Product pro = productRepository.findByPost(po);
            User writer = userRepository.findByUserId(po.getUser().getUserId());
            Long numOfPosts = postRepository.countPostByUserId(writer.getUserId());
            List<Hashtag> hashtagList = hashtagRepository.findByProduct(pro);
            HashSet<String> hs = new HashSet<>();
            for (int j = 0; j<hashtagList.size(); j++){
                hs.add(hashtagList.get(j).getHashtag());
            }
            List<String> hash = new ArrayList<>(hs);
            List<File> files = fileRepository.findByProduct(pro);
            HashSet<String> fs = new HashSet<>();
            for (int j = 0; j<files.size(); j++){
                fs.add(files.get(j).getImgPath());
            }
            List<String> file = new ArrayList<>(fs);
            UserInfoResponse userInfoResponse = new UserInfoResponse(writer.getUserId(),writer.getName(),writer.getAddress(),writer.getTrust(),numOfPosts,writer.getImageUrl());

            outputList.add(new DetailOutput(id,po.getPostId(),file,po.getTitle(),pro.getCategory(),
                    hash,po.getContents(),pro.getPrice(),
                    po.getUser().getUserId(),user.getUserId(),userInfoResponse,detailDeal.getCreatedDate().toString(), detailDeal.getModifiedDate().toString() ));
        }
        return outputList;
    }

    @Transactional
    public DetailOutput findDetailDealByPost(Long postId) {

        Post post = postRepository.findByPostId(postId);
        DetailDeal deal = detailDealRepository.findByPost(post);
        System.out.println(deal.getDealId());
        Product product = productRepository.findByPost(post);
        List<Hashtag> hashtagList = hashtagRepository.findByProduct(product);

        HashSet<String> hs = new HashSet<>();
        for (int j = 0; j<hashtagList.size(); j++){
            hs.add(hashtagList.get(j).getHashtag());
        }
        List<String> hash = new ArrayList<>(hs);

        User user = userRepository.findByUserId(deal.getUser().getUserId());
        User writer = userRepository.findByUserId(post.getUser().getUserId());
        Long numOfPosts = postRepository.countPostByUserId(writer.getUserId());
        List<File> files = fileRepository.findByProduct(product);
        HashSet<String> fs = new HashSet<>();
        for (int j = 0; j<files.size(); j++){
            fs.add(files.get(j).getImgPath());
        }
        List<String> file = new ArrayList<>(fs);
        UserInfoResponse userInfoResponse = new UserInfoResponse(writer.getUserId(),writer.getName(),writer.getAddress(),writer.getTrust(),numOfPosts,writer.getImageUrl());
        DetailOutput output = new DetailOutput(
                deal.getDealId(),postId,file, post.getTitle(), product.getCategory(),
                hash, post.getContents(), product.getPrice(),
                post.getUser().getUserId(), user.getUserId(), userInfoResponse
                ,deal.getCreatedDate().toString(),deal.getModifiedDate().toString()
        );
        return output;
    }
}
