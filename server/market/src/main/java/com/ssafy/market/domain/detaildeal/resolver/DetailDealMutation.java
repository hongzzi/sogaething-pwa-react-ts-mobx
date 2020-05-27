package com.ssafy.market.domain.detaildeal.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.ssafy.market.domain.detaildeal.domain.DetailDeal;
import com.ssafy.market.domain.detaildeal.dto.CreateDetailDealInput;
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
import com.ssafy.market.domain.user.security.TokenProvider;
import com.ssafy.market.global.exception.DomainNotFoundException;
import com.ssafy.market.global.exception.SelectNotDataException;
import graphql.schema.DataFetchingEnvironment;
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
    private final TokenProvider tokenProvider;

    @Transactional
    public DetailOutput createDetailDeal(CreateDetailDealInput input, DataFetchingEnvironment env){
        Long userId = tokenProvider.getUserIdFromHeader(env);
        Post post = postRepository.findByPostId(input.getPostId());
        User writer = userRepository.findByUserId(post.getUser().getUserId()); // post 작성자
        Long numOfPosts = postRepository.countPostByUserId(writer.getUserId());
        User user = userRepository.findByUserId(userId); // 현재 작성자
        Product pro = productRepository.findByPost(post);
        if(post == null || user == null || pro == null){
            throw new DomainNotFoundException("Post or user or hashtag, product not found");
        }
        List<File> files = fileRepository.findByProduct(pro);
        List<FileArr> fileArr = new ArrayList<>();
        for (int j = 0; j < files.size(); j++) {
            fileArr.add(new FileArr(files.get(j).getImgPath()));
        }
        DetailDeal detailDeal = detailDealRepository.save(new DetailDeal(null, post,user));
        List<Hashtag> hashtagList = hashtagRepository.findByProduct(pro);
        String hashtag = "";
        for (int j = 0; j<hashtagList.size();j++){
            hashtag = hashtag +hashtagList.get(j).getHashtag()+" ";
        }
        UserInfoOutput userInfoOutput = new UserInfoOutput(writer.getName(),writer.getAddress(),writer.getTrust(),numOfPosts);
        DetailOutput output = new DetailOutput(detailDeal.getDealId(),
                input.getPostId(),fileArr,post.getTitle(),pro.getCategory(),hashtag,post.getContents(), pro.getPrice(),post.getUser().getUserId(), userId,userInfoOutput);
        return output;
    }
    @Transactional
    public int deleteDetailDeal(Long id){
        DetailDeal detailDeal = detailDealRepository.findBydealId(id);
        if(detailDeal==null){
            throw new DomainNotFoundException("detailId " , id);
        }
        return detailDealRepository.deleteByDealId(id);
    }
}
