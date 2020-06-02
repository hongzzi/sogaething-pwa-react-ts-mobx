package com.ssafy.market.domain.post.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.ssafy.market.domain.file.domain.File;
import com.ssafy.market.domain.file.repository.FileRepository;
import com.ssafy.market.domain.hashtag.domain.Hashtag;
import com.ssafy.market.domain.hashtag.domain.MatchingHashtag;
import com.ssafy.market.domain.hashtag.dto.HashtagInput;
import com.ssafy.market.domain.hashtag.repository.HashtagRepository;
import com.ssafy.market.domain.hashtag.repository.MatchingHashtagRepository;
import com.ssafy.market.domain.matching.domain.Matching;
import com.ssafy.market.domain.matching.dto.MatchInput;
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

    public Long countPostByUserId(Long userId){
        return postRepository.countPostByUserId(userId);
    }

    public PostOutput findPostByPostId(Long id) {

        Post post = postRepository.findByPostId(id);
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
        User user = userRepository.findByUserId(userId);
        List<Post> postList = postRepository.findByUserIdOrderByPostIdDesc(userId);

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
                    ,hash,post.isBuy(),post.getViewCount(),post.getDeal(), post.getDealState(),post.getSaleDate().toString(),post.getTransaction(),post.getCreatedDate().toString(), post.getModifiedDate().toString()));
        }
        return metaOutputList;
    }

    public List<PostMetaOutput> searchThings(HashtagInput input) {
        String[] hashtags = input.getHashtag();
        List<PostMetaOutput> outputs = new ArrayList<>();
        HashMap<Long, Post> hashMap = new HashMap<>();
        long[] postIdArr = new long[(int) postRepository.count() + 1];
        for (int i = 0; i < hashtags.length; i++) {
            List<Hashtag> hashtagList = hashtagRepository.findDistinctByHashtagStartingWith(hashtags[i]);
            for (int j = 0; j < hashtagList.size(); j++) {
                long PostId = hashtagList.get(j).getProduct().getPost().getPostId();
                hashMap.put(PostId, hashtagList.get(j).getProduct().getPost());
                postIdArr[(int) PostId]++;
            }
        }
        long[] temp = postIdArr.clone();
        Arrays.sort(temp);
        boolean[] visited = new boolean[postIdArr.length];
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] == 0) continue;
            for (int j = 0; j < postIdArr.length; j++) {
                if (!visited[j] && temp[i] == postIdArr[j]) {
                    visited[j] = true;
                    Post post = hashMap.get((long) j);
                    Product product = productRepository.findByPost(post);
                    File files = fileRepository.findByProduct(product).get(0);
                    List<Hashtag> hashtagList = hashtagRepository.findByProduct(product);
                    HashSet<String> hs = new HashSet<>();
                    for (int k = 0; k < hashtagList.size(); k++) {
                        hs.add(hashtagList.get(k).getHashtag());
                    }
                    List<String> hash = new ArrayList<>(hs);
                    outputs.add(new PostMetaOutput(post.getPostId(), post.getTitle(), product.getCategory(), files.getImgPath(), product.getPrice()
                            , hash,post.isBuy(),post.getViewCount(),post.getDeal(),post.getDealState(),post.getSaleDate().toString(),post.getTransaction(), post.getCreatedDate().toString(), post.getModifiedDate().toString()));
                    break;
                }
            }
        }
        return outputs;
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

}