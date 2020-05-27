package com.ssafy.market.domain.post.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.ssafy.market.domain.detaildeal.dto.FileArr;
import com.ssafy.market.domain.file.domain.File;
import com.ssafy.market.domain.file.repository.FileRepository;
import com.ssafy.market.domain.hashtag.domain.Hashtag;
import com.ssafy.market.domain.hashtag.repository.HashtagRepository;
import com.ssafy.market.domain.post.domain.Post;
import com.ssafy.market.domain.post.dto.PostOutput;
import com.ssafy.market.domain.post.dto.RecentPostResponse;
import com.ssafy.market.domain.post.repository.PostRepository;
import com.ssafy.market.domain.product.domain.Product;
import com.ssafy.market.domain.product.repository.ProductRepository;
import com.ssafy.market.domain.user.repository.UserRepository;
import com.ssafy.market.global.exception.SelectNotDataException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;


@Component
@RequiredArgsConstructor
public class PostQuery implements GraphQLQueryResolver {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final HashtagRepository hashtagRepository;
    private final FileRepository fileRepository;

    public List<PostOutput> findAllPost() {
        List<PostOutput> outputs = new ArrayList<>();
        List<Post> postList = postRepository.findAll();
        if(postList.size()==0){
            throw new SelectNotDataException("post 조회 결과 : ");
        }
        for(int i = 0; i<postList.size();i++){
            Post post = postList.get(i);
            Product product = productRepository.findByPost(post);
            List<Hashtag> hashtagList = hashtagRepository.findByProduct(product);
            String hashtag = "";
            for (int j = 0; j<hashtagList.size();j++){
                hashtag = hashtag +hashtagList.get(j).getHashtag()+" ";
            }
            List<File> files = fileRepository.findByProduct(product);
            List<FileArr> fileArr = new ArrayList<>();
            for (int j = 0; j < files.size(); j++) {
                fileArr.add(new FileArr(files.get(j).getImgPath()));
            }
            outputs.add(new PostOutput(post.getPostId(),post.getUser().getUserId(), post.isBuy(),post.getTitle(),post.getContents(),post.getDeal()
            ,post.getDealState(),product.getCategory(),product.getName(),product.getPrice(),hashtag,fileArr
            ));
        }
        return outputs;
    }

    public Iterable<Post> findAllPosts(){
        return postRepository.findAll();
    }


    public PostOutput findPostByPostId(Long id) {
        Post post = postRepository.findByPostId(id);
//        Product product = productRepository.findByPost(post);
//        Hashtag hashtag = hashtagRepository.findByProduct(product);
        if(post==null){
            throw new SelectNotDataException("post 조회 결과 : ");
        }
        Product product = productRepository.findByPost(post);
        List<Hashtag> hashtagList = hashtagRepository.findByProduct(product);
        String hashtag = "";
        for (int j = 0; j<hashtagList.size();j++){
            hashtag = hashtag +hashtagList.get(j).getHashtag()+" ";
        }
        List<File> files = fileRepository.findByProduct(product);
        List<FileArr> fileArr = new ArrayList<>();
        for (int j = 0; j < files.size(); j++) {
            fileArr.add(new FileArr(files.get(j).getImgPath()));
        }
        PostOutput output = new PostOutput(post.getPostId(),post.getUser().getUserId(), post.isBuy(),post.getTitle(),post.getContents(),post.getDeal()
                ,post.getDealState(),product.getCategory(),product.getName(),product.getPrice(),hashtag,fileArr
                );
        return output;
    }

    public List<RecentPostResponse> findRecentPosts(){
        List<RecentPostResponse> recentPostResponses = new ArrayList<>();
        List<Post> posts = postRepository.findTop6ByOrderByCreatedDateDesc();

        for (int i = 0; i < posts.size(); i++) {
            Post post = posts.get(i);
            Product product = productRepository.findByPost(post);

            RecentPostResponse response = new RecentPostResponse();
            response.setPostId(post.getPostId());
            response.setUser(userRepository.findByUserId(post.getUserId()));
            response.setHashTags(hashtagRepository.findByProduct(product));
            response.setIsBuy(post.isBuy());
            response.setPrice((long)12345);
            response.setSaleDate(post.getSaleDate());
            response.setImgUrls(fileRepository.findByProduct(product));
            response.setCategory(product.getCategory());
            response.setDeal(post.getDeal());
            response.setCreatedDate(post.getCreatedDate());
            response.setSaleDate(post.getSaleDate());

            recentPostResponses.add(response);
        }

        return recentPostResponses;
    }

}