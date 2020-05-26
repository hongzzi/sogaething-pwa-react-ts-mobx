package com.ssafy.market.domain.detaildeal.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.ssafy.market.domain.detaildeal.domain.DetailDeal;
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
import org.springframework.web.socket.server.HandshakeHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DetailDealQuery implements GraphQLQueryResolver {
    private final DetailDealRepository detailDealRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final HashtagRepository hashtagRepository;
    private final ProductRepository productRepository;
    private final FileRepository fileRepository;

    public Iterable<DetailDeal> findAllDetailDeals() {
        return detailDealRepository.findAll();
    }

//    public Optional<DetailDeal> findPostById(Long id) {
//        return detailDealRepository.findById(id);
//    }


    public List<DetailDealOutput> findAllDetailDeal(){
        List<DetailDealOutput> outputList = new ArrayList<>();
        List<DetailDeal> dealList = detailDealRepository.findAll();
        for(int i =0; i<dealList.size();i++){
            Long id = dealList.get(i).getDealId();
            Post po = dealList.get(i).getPost();
            User user = dealList.get(i).getUser();
            Hashtag hashtag = dealList.get(i).getHashtag();
            Product pro = productRepository.findByPost(po);
            List<File> files = fileRepository.findByProduct(pro);
            List<FileArr> fileArr = new ArrayList<>();
            for(int j = 0; j<files.size(); j++){
                fileArr.add(new FileArr(files.get(j).getImgPath()));
            }
            outputList.add(new DetailDealOutput(id,po.getPostId(),fileArr,po.getTitle(),pro.getCategory(),
                    hashtag.getHashtag(),po.getContents(),pro.getPrice(),
                    po.getUser().getUserId(),user.getUserId(),user.getAddress()));
        }
        return outputList;
    }

//    public List<DetailDealOutput> findDetailDealByPosts(Long postId) {
//        List<DetailDealOutput> outputList = new ArrayList<>();
//        Post post = postRepository.findById(postId).get();
//        List<DetailDeal> dealList = detailDealRepository.findByPost(post);
//        List<FileArr> fileArr = new ArrayList<>();
//        for(int i = 0; i<dealList.size();i++){
//            Long id = dealList.get(i).getDealId();
//            Product product = productRepository.findByPost(post);
//            List<File> files = fileRepository.findByProduct(product);
//            Hashtag hashtag = hashtagRepository.findById(dealList.get(i).getHashtag().getHashtagId()).get();
//            for(int j = 0; j<files.size(); j++){
//                fileArr.add(new FileArr(files.get(j).getImgPath()));
//            }
//            User user = userRepository.findById(dealList.get(i).getUser().getUserId()).get();
//            outputList.add(new DetailDealOutput(id,postId,fileArr,post.getTitle(),product.getCategory(),
//                    hashtag.getHashtag(),post.getContents(),product.getPrice(),
//                    post.getUser().getUserId(),user.getUserId(),user.getAddress()));
//        }
//
//        return outputList;
//    }

    public DetailDealOutput findDetailDealByPost(Long postId) {
        Post post = postRepository.findById(postId).get();
        DetailDeal deal = detailDealRepository.findByPost(post);
        Product product = productRepository.findByPost(post);
        Hashtag hashtag = hashtagRepository.findById(deal.getHashtag().getHashtagId()).get();
        User user = userRepository.findById(deal.getUser().getUserId()).get();
        List<FileArr> fileArr = new ArrayList<>();
        List<File> files = fileRepository.findByProduct(product);
        for (int j = 0; j < files.size(); j++) {
            fileArr.add(new FileArr(files.get(j).getImgPath()));
        }
        DetailDealOutput output = new DetailDealOutput(
                deal.getDealId(),postId,fileArr, post.getTitle(), product.getCategory(),
                hashtag.getHashtag(), post.getContents(), product.getPrice(),
                post.getUser().getUserId(), user.getUserId(), user.getAddress()
        );
        return output;
    }
}
