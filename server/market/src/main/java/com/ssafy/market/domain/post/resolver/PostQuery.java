package com.ssafy.market.domain.post.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.ssafy.market.domain.file.domain.File;
import com.ssafy.market.domain.file.repository.FileRepository;
import com.ssafy.market.domain.hashtag.domain.Hashtag;
import com.ssafy.market.domain.hashtag.repository.HashtagRepository;
import com.ssafy.market.domain.post.domain.Post;
import com.ssafy.market.domain.post.dto.PostDetailOutput;
import com.ssafy.market.domain.post.dto.PostMetaOutput;
import com.ssafy.market.domain.post.dto.PostOutput;
import com.ssafy.market.domain.post.dto.RecentPostResponse;
import com.ssafy.market.domain.post.repository.PostRepository;
import com.ssafy.market.domain.product.domain.Product;
import com.ssafy.market.domain.product.repository.ProductRepository;
import com.ssafy.market.domain.user.domain.User;
import com.ssafy.market.domain.user.dto.UserInfoResponse;
import com.ssafy.market.domain.user.repository.UserRepository;
import com.ssafy.market.global.exception.SelectNotDataException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


@Component
@RequiredArgsConstructor
public class PostQuery implements GraphQLQueryResolver {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final HashtagRepository hashtagRepository;
    private final FileRepository fileRepository;
//    private final ImgurUploader uploader;
//    private final UploadCallback callback;

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
            HashSet<String> hs = new HashSet<>();
            for (int j = 0; j<hashtagList.size(); j++){
                hs.add(hashtagList.get(j).getHashtag());
            }
            List<String> hash = new ArrayList<>(hs);
            List<File> files = fileRepository.findByProduct(product);

            outputs.add(new PostOutput(post.getPostId(),post.getUser().getUserId(), post.isBuy(),post.getTitle(),post.getContents(),post.getDeal()
            ,post.getDealState(),product.getCategory(),product.getName(),product.getPrice(),hash,files
            ));
        }
        return outputs;
    }

    public Iterable<Post> findAllPosts(){
        return postRepository.findAll();
    }


    public PostOutput findPostByPostId(Long id) {

        Post post = postRepository.findByPostId(id);
        if(post==null){
            throw new SelectNotDataException("post 조회 결과 : ");
        }
        Product product = productRepository.findByPost(post);
        List<Hashtag> hashtagList = hashtagRepository.findByProduct(product);
        HashSet<String> hs = new HashSet<>();
        for (int j = 0; j<hashtagList.size(); j++){
            hs.add(hashtagList.get(j).getHashtag());
        }
        List<String> hash = new ArrayList<>(hs);
        List<File> files = fileRepository.findByProduct(product);

        PostOutput output = new PostOutput(post.getPostId(),post.getUser().getUserId(), post.isBuy(),post.getTitle(),post.getContents(),post.getDeal()
                ,post.getDealState(),product.getCategory(),product.getName(),product.getPrice(),hash,files                );
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
            response.setPrice(productRepository.totalPriceByPostId(post));
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

    public List<PostMetaOutput> findPostListByUserId(Long userId){
        List<PostMetaOutput> metaOutputList = new ArrayList<>();
        User user = userRepository.findByUserId(userId);
        List<Post> postList = postRepository.findPostByUser(user);
        for (int i = 0; i< postList.size(); i++){
            Post post = postList.get(i);
            Product product = productRepository.findByPost(post);
            File files = fileRepository.findByProduct(product).get(0);
            List<Hashtag> hashtagList = hashtagRepository.findByProduct(product);
            HashSet<String> hs = new HashSet<>();
            for (int j = 0; j<hashtagList.size(); j++){
                hs.add(hashtagList.get(j).getHashtag());
            }
            List<String> hash = new ArrayList<>(hs);
            metaOutputList.add(new PostMetaOutput(post.getPostId(),post.getTitle(),product.getCategory(),files.getImgPath(),product.getPrice()
                    ,hash,post.getCreatedDate().toString(), post.getModifiedDate().toString()));
        }
        return metaOutputList;
    }
    public PostDetailOutput findByDetailPost(Long postId){

        Post post = postRepository.findByPostId(postId);
        Product product = productRepository.findByPost(post);
        User writer = userRepository.findByUserId(post.getUser().getUserId());
        List<Hashtag> hashtagList = hashtagRepository.findByProduct(product);
        HashSet<String> hs = new HashSet<>();
        for (int j = 0; j<hashtagList.size(); j++){
            hs.add(hashtagList.get(j).getHashtag());
        }
        List<String> hash = new ArrayList<>(hs);
        List<File> files = fileRepository.findByProduct(product);
        HashSet<String> fs = new HashSet<>();
        for (int j = 0; j<files.size(); j++){
            fs.add(files.get(j).getImgPath());
        }
        List<String> file = new ArrayList<>(fs);
        Long numOfPosts = postRepository.countPostByUserId(writer.getUserId());
        UserInfoResponse user = new UserInfoResponse(writer.getName(),writer.getAddress(),writer.getTrust(),
                numOfPosts,writer.getImageUrl());
        PostDetailOutput detailOutput = new PostDetailOutput(
                postId,post.getTitle(),product.getCategory(),file,hash,
                post.getContents(),product.getPrice(),user, post.getCreatedDate().toString(),post.getModifiedDate().toString()
        );
        return detailOutput;
    }
}