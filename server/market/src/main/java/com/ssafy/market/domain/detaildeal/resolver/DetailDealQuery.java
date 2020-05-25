package com.ssafy.market.domain.detaildeal.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.ssafy.market.domain.detaildeal.domain.DetailDeal;
import com.ssafy.market.domain.detaildeal.dto.DetailDealOutput;
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


    public List<DetailDealOutput> findDetailDealByPost(Long postId) {
        List<DetailDealOutput> outputList = new ArrayList<>();
        // postId, hashtageId, UserId
        Post post = postRepository.findById(postId).get();
        List<DetailDeal> dealList = detailDealRepository.findByPost(post);
        for(int i = 0; i<dealList.size();i++){
            Product product = productRepository.findByPost(post);
            File file = fileRepository.findByProduct(product);
            Hashtag hashtag = hashtagRepository.findById(dealList.get(i).getHashtag().getHashtagId()).get();
            User user = userRepository.findById(dealList.get(i).getUser().getUserId()).get();
            outputList.add(new DetailDealOutput(postId,file.getImgPath(),post.getTitle(),product.getCategory(),
                    hashtag.getHashtag(),post.getContents(),product.getPrice(),
                    post.getUser().getUserId(),user.getUserId(),user.getAddress()));
        }

        return outputList;
    }

//    public Iterable<Post> findAllPostsByUploaderId() {
//        return postRepository.findAll();
//    }
}
