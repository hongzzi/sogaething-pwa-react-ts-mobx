package com.ssafy.market.domain.post.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.ssafy.market.domain.detaildeal.dto.FileArr;
import com.ssafy.market.domain.file.domain.File;
import com.ssafy.market.domain.file.repository.FileRepository;
import com.ssafy.market.domain.hashtag.domain.Hashtag;
import com.ssafy.market.domain.hashtag.repository.HashtagRepository;
import com.ssafy.market.domain.post.dto.*;
import com.ssafy.market.domain.post.domain.Post;
import com.ssafy.market.domain.post.repository.PostRepository;
import com.ssafy.market.domain.product.domain.Product;
import com.ssafy.market.domain.product.repository.ProductRepository;
import com.ssafy.market.domain.user.domain.User;
import com.ssafy.market.domain.user.dto.UserInfoResponse;
import com.ssafy.market.domain.user.repository.UserRepository;

import com.ssafy.market.domain.user.security.TokenProvider;
import com.ssafy.market.global.apis.ImgurApi;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PostMutation implements GraphQLMutationResolver {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final HashtagRepository hashtagRepository;
    private final FileRepository fileRepository;
    private final TokenProvider tokenProvider;
    private final ImgurApi api;


    @Transactional
    public Output createPost(CreatePostInput input, DataFetchingEnvironment env ) throws Exception {

        Long userId = tokenProvider.getUserIdFromHeader(env);
        User user = (userRepository.findByUserId(userId));

        Output output = null;
        Boolean check = true;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
            Calendar cal = Calendar.getInstance();
            String today = null;
            today = formatter.format(cal.getTime());
            Timestamp ts = Timestamp.valueOf(today);
            if(input.getTitle().equals("") || input.getContents().equals("") ||input.getTransaction().equals("") || input.getPrice()==null || input.getCategory().equals("")){
                check = false;
            }
            if(input.getHashtag().length<0 || input.getImgPaths().length<0){
                check =false;
            }
            if(check) {
                Post post = postRepository.save(new Post(null, user, false, input.getTitle(), ts, input.getContents(), (long) 0, "판매", "진행중", input.getTransaction()));
                Product product = productRepository.save(new Product(null, post, input.getPrice(), input.getCategory(), (long) 0));
                String[] hashtagarr = input.getHashtag();
                List<String> hash = new ArrayList<>();
                if (hashtagarr.length > 0) {
                    for (int i = 0; i < hashtagarr.length; i++) {
                        Hashtag hashtag = hashtagRepository.save(new Hashtag(null, product, hashtagarr[i]));
                        hash.add(hashtagarr[i]);
                    }
                }
                String[] arr = input.getImgPaths();
                if (arr.length > 0) {
                    for (int k = 0; k < arr.length; k++) {
//                        String temp = arr[k].substring(22);
                        String[] temp = arr[k].split(",");
                        String imgur = api.uploadimgtest(temp[1]);
                        if(!imgur.equals("false")) {
                            File file = fileRepository.save(new File(null, product, imgur));
                        }
                        else{
                            break;
                        }
                    }
                }
                List<File> files = fileRepository.findByProduct(product);
                output = new Output("SUCCESS", post.getPostId());
            }else{
                output = new Output("FAIL",null);
            }
        } catch (Exception e) {
//            System.out.println(e);
             output = new Output("FAIL",null);
        }
        return  output;
    }

    @Transactional
    public PostMetaOutput updatePost(UpdatePostInput input){
        Post post = postRepository.findByPostId(input.getPostId());
        post.update(input.getTitle(),input.getContents(),input.getTransaction());
        Product product = productRepository.findByPost(post);
        product.update(post,input.getPrice(),input.getCategory());
        List<Hashtag> hashtagList = hashtagRepository.findByProduct(product);
        List<File> fileList = fileRepository.findByProduct(product);
        // 해시 태그 및 파일 삭제 하기
        for (int i = 0; i < fileList.size(); i++) {
            fileRepository.deleteByFileId(fileList.get(i).getFileId());
        }
        for (int j = 0; j < hashtagList.size(); j++) {
            hashtagRepository.deleteByHashtagId(hashtagList.get(j).getHashtagId());
        }
        // 해시 태그 및 파일 추가 하기
        String[] hashtagarr = input.getHashtag();
        HashSet<String> hs = new HashSet<>();
        if (hashtagarr.length > 0) {
            for (int i = 0; i < hashtagarr.length; i++) {
                Hashtag hashtag = hashtagRepository.save(new Hashtag(null, product, hashtagarr[i]));
                hs.add(hashtagarr[i]);
            }
        }
        String[] arr = input.getImgPaths();
        if (arr.length > 0) {
            try {
                for (int k = 0; k < arr.length; k++) {
                    String[] temp = arr[k].split(",");
                    String imgur = api.uploadimgtest(temp[1]);
                    if (!imgur.equals("false")) {
                        File file = fileRepository.save(new File(null, product, imgur));
                    } else {
                        break;
                    }
                }
            }catch (Exception e){
                System.out.println("Fail" + e);
            }
        }
        List<String> hash= new ArrayList<>(hs);

        List<File> files = fileRepository.findByProduct(product);
        String imgPath = files.get(0).getImgPath();
        PostMetaOutput output = new PostMetaOutput(
                input.getPostId(),post.getTitle(),product.getCategory(),imgPath,
                product.getPrice(),hash,post.isBuy(),post.getViewCount(),post.getDeal(),post.getDealState(),
                post.getSaleDate().toString(),post.getTransaction(),post.getCreatedDate().toString(),
                post.getModifiedDate().toString());

        return output;
    }

    @Transactional
    public Long updateView(Long postId){
        Post post = postRepository.findByPostId(postId);
        post.updateViewCount();
        return post.getViewCount();
    }
    @Transactional
    public Boolean updateIsBuy(Long postsId){
        Post post = postRepository.findByPostId(postsId);
        if(post.isBuy()== true){
            post.update(false);
        }else {
            post.update(true);
        }
        return post.isBuy();
    }
    @Transactional
    public Output deletePost(Long postId){
        Output output = null;

        Post post = postRepository.findByPostId(postId);
        Product product = productRepository.findByPost(post);
        List<File> fileList = fileRepository.findByProduct(product);
        List<Hashtag> hashtagList = hashtagRepository.findByProduct(product);

        try {
            for (int i = 0; i < fileList.size(); i++) {
                fileRepository.deleteByFileId(fileList.get(i).getFileId());
            }
            for (int j = 0; j < hashtagList.size(); j++) {
                hashtagRepository.deleteByHashtagId(hashtagList.get(j).getHashtagId());
            }
            productRepository.deleteByProductId(product.getProductId());
            postRepository.deleteByPostId(postId);
            output = new Output("SUCCESS", postId);
        }catch (Exception e){
            output = new Output("FAIL",null);
        }
        return output;
    }
}