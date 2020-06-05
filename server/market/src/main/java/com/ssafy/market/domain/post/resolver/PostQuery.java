package com.ssafy.market.domain.post.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.ssafy.market.domain.file.domain.File;
import com.ssafy.market.domain.file.repository.FileRepository;
import com.ssafy.market.domain.hashtag.domain.Hashtag;

import com.ssafy.market.domain.hashtag.dto.HashtagInput;
import com.ssafy.market.domain.hashtag.repository.HashtagRepository;
import com.ssafy.market.domain.hashtag.repository.MatchingHashtagRepository;
import com.ssafy.market.domain.matching.domain.Matching;
import com.ssafy.market.domain.matching.respository.MatchingRepository;
import com.ssafy.market.domain.post.domain.Post;
import com.ssafy.market.domain.post.dto.*;
import com.ssafy.market.domain.post.repository.PostRepository;
import com.ssafy.market.domain.product.domain.Product;
import com.ssafy.market.domain.product.repository.ProductRepository;
import com.ssafy.market.domain.user.domain.User;
import com.ssafy.market.domain.user.dto.UserInfoResponse;
import com.ssafy.market.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PostQuery implements GraphQLQueryResolver {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final HashtagRepository hashtagRepository;
    private final FileRepository fileRepository;
    private final MatchingRepository matchingRepository;
    private final MatchingHashtagRepository matchingHashtagRepository;

    public List<PostOutput> findAllPost() {
        List<PostOutput> outputs = new ArrayList<>();
        List<Post> postList = postRepository.findAllByOrderByPostIdDesc();
        for(int i = 0; i<postList.size();i++){
            Post post = postList.get(i);
            Product product = productRepository.findByPost(post);
            List<String> list = hashtagRepository.findHashtagDistinctByProduct(product.getProductId());

            List<File> files = fileRepository.findByProduct(product);

            outputs.add(new PostOutput(post.getPostId(),post.getUser().getUserId(), post.isBuy(),post.getTitle(),post.getContents(),post.getDeal()
            ,post.getDealState(),product.getCategory(),product.getName(),product.getPrice(),list,files
            ));
        }
        return outputs;
    }

    public Iterable<Post> findAllPosts(){
        return postRepository.findAll();
    }

    public Long countPostByUserId(Long userId){
        return postRepository.countPostByUserId(userId);
    }

    public PostOutput findPostByPostId(Long id) {

        Post post = postRepository.findByPostId(id);
        Product product = productRepository.findByPost(post);
        List<String> list = hashtagRepository.findHashtagDistinctByProduct(product.getProductId());

        List<File> files = fileRepository.findByProduct(product);

        PostOutput output = new PostOutput(post.getPostId(),post.getUser().getUserId(), post.isBuy(),post.getTitle(),post.getContents(),post.getDeal()
                ,post.getDealState(),product.getCategory(),product.getName(),product.getPrice(),list,files                );
        return output;
    }

    public List<RecentPostResponse> findRecentPosts() {
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
        List<Post> postList = postRepository.findByUserIdOrderByPostIdDesc(userId);
        for (int i = 0; i< postList.size(); i++){
            Post post = postList.get(i);
            Product product = productRepository.findByPost(post);
            File file = fileRepository.findTopByProduct(product);
            List<String> list = hashtagRepository.findHashtagDistinctByProduct(product.getProductId());
            metaOutputList.add(new PostMetaOutput(post.getPostId(),post.getTitle(),product.getCategory(),file.getImgPath(),product.getPrice()
                    ,list,post.isBuy(),post.getViewCount(),post.getDeal(), post.getDealState(),post.getSaleDate().toString(),post.getTransaction(),post.getCreatedDate().toString(), post.getModifiedDate().toString()));
        }
        return metaOutputList;
    }

    public List<PostMetaOutput> searchThings(HashtagInput input) {
        String[] hashtags = input.getHashtag();
        List<Long> hashtagCountOutputs = hashtagRepository.findHashtagCount(Arrays.asList(hashtags));
        List<PostMetaOutput> outputs = new ArrayList<>();
        for (int i = 0; i < hashtagCountOutputs.size(); i++) {
            Product product = productRepository.findByProductId(hashtagCountOutputs.get(i));
            Post post = product.getPost();
            File file = fileRepository.findTop1ByProduct(product);

            PostMetaOutput postMetaOutput = PostMetaOutput.builder()
                    .postId(post.getPostId())
                    .title(post.getTitle())
                    .category(product.getCategory())
                    .imgPath(file.getImgPath())
                    .price(productRepository.totalPriceByPostId(post))
                    .hashtag(hashtagRepository.findDistinctByHashtag(product.getProductId()))
                    .isBuy(post.isBuy())
                    .viewCount(post.getViewCount())
                    .deal(post.getDeal())
                    .dealState(post.getDealState())
                    .saleDate(post.getSaleDate().toString())
                    .transaction(post.getTransaction())
                    .createdDate(post.getCreatedDate().toString())
                    .modifiedDate(post.getModifiedDate().toString())
                    .build();
            outputs.add(postMetaOutput);
        }
        return outputs;
    }

    public PostDetailOutput findByDetailPost(Long postId){

        Post post = postRepository.findByPostId(postId);
        Product product = productRepository.findByPost(post);
        User writer = userRepository.findByUserId(post.getUser().getUserId());
        List<String> list = hashtagRepository.findHashtagDistinctByProduct(product.getProductId());
        List<String> imgPath = fileRepository.findFilePathByProduct(product.getProductId());
        Long numOfPosts = postRepository.countPostByUserId(writer.getUserId());
        UserInfoResponse user = new UserInfoResponse(writer.getUserId(),writer.getName(),writer.getAddress(),writer.getTrust(),
                numOfPosts,writer.getImageUrl());
        PostDetailOutput detailOutput = new PostDetailOutput(
                postId,post.getTitle(),product.getCategory(),imgPath,list,
                post.getContents(),product.getPrice(),user,post.getViewCount(),post.isBuy(),post.getDeal(),post.getDealState(),post.getSaleDate().toString(),post.getTransaction(), post.getCreatedDate().toString(),post.getModifiedDate().toString()
        );
        return detailOutput;
    }


    public List<PostDetailOutput> matchThings(Long matchingId) {
        List<PostDetailOutput> postDetailOutputs = new ArrayList<>();
        Matching matching = matchingRepository.findByMatchingId(matchingId);

        String category = matching.getCategory();
        String[] hashtag = matchingHashtagRepository.findHashtagByMatching(matching).toArray(new String[0]);
        String transaction = matching.getTransaction();

        List<Long> postIdList = productRepository.findPostIdByCategory(category);
        List<Product> products = productRepository.findPostByPrice(matching.getMinPrice(), matching.getMaxPrice(), postIdList);
        for (int i = 0; i < products.size(); i++) {
            if(products.get(i).getPost().getTransaction()!=null && !products.get(i).getPost().getTransaction().equals(transaction)) continue;

            List<Hashtag> hashtagList = hashtagRepository.findByProduct(products.get(i));
            HashSet<String> hs = new HashSet<>();
            for (int j = 0; j<hashtagList.size(); j++){
                hs.add(hashtagList.get(j).getHashtag());
            }
            List<String> hash = new ArrayList<>(hs);
            int flag = 0;
            for (int j = 0; j < hash.size(); j++) {
                if(Arrays.toString(hashtag).contains(hash.get(j)))
                    flag++;
            }
            if(flag == hashtag.length){
                postDetailOutputs.add(findByDetailPost(products.get(i).getPost().getPostId()));
            }
        }
        return postDetailOutputs;
    }

    public List<PostMetaOutput> searchThingsByTitle(String title){
//        List<PostMetaOutput> outputs = new ArrayList<>();
        List<Post> posts = postRepository.findByTitleContaining(title);
        List<PostMetaOutput> outputs = posts.stream().map( post -> {
            Product product = productRepository.findByPost(post);
            File file = fileRepository.findTop1ByProduct(product);

            PostMetaOutput postMetaOutput = PostMetaOutput.builder()
                    .postId(post.getPostId())
                    .title(post.getTitle())
                    .category(product.getCategory())
                    .imgPath(file.getImgPath())
                    .price(productRepository.totalPriceByPostId(post))
                    .hashtag(hashtagRepository.findDistinctByHashtag(product.getProductId()))
                    .isBuy(post.isBuy())
                    .viewCount(post.getViewCount())
                    .deal(post.getDeal())
                    .dealState(post.getDealState())
                    .saleDate(post.getSaleDate().toString())
                    .transaction(post.getTransaction())
                    .createdDate(post.getCreatedDate().toString())
                    .modifiedDate(post.getModifiedDate().toString())
                    .build();
            return postMetaOutput;
        }).collect(Collectors.toList());
        return outputs;
    }

}