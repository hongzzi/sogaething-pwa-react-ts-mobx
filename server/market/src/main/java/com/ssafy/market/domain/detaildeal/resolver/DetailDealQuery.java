package com.ssafy.market.domain.detaildeal.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.ssafy.market.domain.detaildeal.domain.DetailDeal;
import com.ssafy.market.domain.detaildeal.dto.DetailOutput;
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
import com.ssafy.market.domain.user.dto.UserInfoOutput;
import com.ssafy.market.domain.user.repository.UserRepository;
import com.ssafy.market.global.exception.DomainNotFoundException;
import com.ssafy.market.global.exception.SelectNotDataException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
        if(dealList.size()==0){
            throw new SelectNotDataException("");
        }
        for(int i =0; i<dealList.size();i++){
            DetailDeal detailDeal = dealList.get(i);
            Long id = detailDeal.getDealId();
            Post po = detailDeal.getPost();
            User user = detailDeal.getUser();
            Product pro = productRepository.findByPost(po);
            User writer = userRepository.findByUserId(po.getUser().getUserId());
            Long numOfPosts = postRepository.countPostByUserId(writer.getUserId());
            List<Hashtag> hashtagList = hashtagRepository.findByProduct(pro);
            String hashtag = "";
            for (int j = 0; j<hashtagList.size();j++){
                hashtag = hashtag +hashtagList.get(j).getHashtag()+" ";
            }
            List<File> files = fileRepository.findByProduct(pro);
            List<FileArr> fileArr = new ArrayList<>();
            for (int j = 0; j < files.size(); j++) {
                fileArr.add(new FileArr(files.get(j).getImgPath()));
            }
            UserInfoOutput userInfoOutput = new UserInfoOutput(writer.getName(),writer.getAddress(),writer.getTrust(),numOfPosts);

            outputList.add(new DetailOutput(id,po.getPostId(),fileArr,po.getTitle(),pro.getCategory(),
                    hashtag,po.getContents(),pro.getPrice(),
                    po.getUser().getUserId(),user.getUserId(),userInfoOutput));
        }
        return outputList;
    }

    @Transactional
    public DetailOutput findDetailDealByPost(Long postId) {
        Post post = postRepository.findByPostId(postId);
        DetailDeal deal = detailDealRepository.findByPost(post);
        System.out.println(deal.getDealId());
        if(deal==null){
            throw new SelectNotDataException("");
        }
        Product product = productRepository.findByPost(post);
        List<Hashtag> hashtagList = hashtagRepository.findByProduct(product);
        String hashtag = "";
        for (int j = 0; j<hashtagList.size();j++){
            hashtag = hashtag +hashtagList.get(j).getHashtag()+" ";
        }
        User user = userRepository.findByUserId(deal.getUser().getUserId());
        User writer = userRepository.findByUserId(post.getUser().getUserId());
        Long numOfPosts = postRepository.countPostByUserId(writer.getUserId());
        if(post == null){
            throw new DomainNotFoundException("post : ");
        }else if(product==null){
            throw new DomainNotFoundException("product : ");
        }else if(hashtag==null){
            throw new DomainNotFoundException("hashtag : ");
        }
        List<File> files = fileRepository.findByProduct(product);
        List<FileArr> fileArr = new ArrayList<>();
        for (int j = 0; j < files.size(); j++) {
            fileArr.add(new FileArr(files.get(j).getImgPath()));
        }
        UserInfoOutput userInfoOutput = new UserInfoOutput(writer.getName(),writer.getAddress(),writer.getTrust(),numOfPosts);
        DetailOutput output = new DetailOutput(
                deal.getDealId(),postId,fileArr, post.getTitle(), product.getCategory(),
                hashtag, post.getContents(), product.getPrice(),
                post.getUser().getUserId(), user.getUserId(), userInfoOutput
        );
        return output;
    }
}
