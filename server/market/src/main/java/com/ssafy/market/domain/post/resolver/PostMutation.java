package com.ssafy.market.domain.post.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.ssafy.market.domain.detaildeal.dto.FileArr;
import com.ssafy.market.domain.file.domain.File;
import com.ssafy.market.domain.file.repository.FileRepository;
import com.ssafy.market.domain.hashtag.domain.Hashtag;
import com.ssafy.market.domain.hashtag.repository.HashtagRepository;
import com.ssafy.market.domain.post.dto.CreateOutput;
import com.ssafy.market.domain.post.dto.CreatePostInput;
import com.ssafy.market.domain.post.domain.Post;
import com.ssafy.market.domain.post.dto.PostOutput;
import com.ssafy.market.domain.post.dto.UpdatePostInput;
import com.ssafy.market.domain.post.repository.PostRepository;
import com.ssafy.market.domain.product.domain.Product;
import com.ssafy.market.domain.product.repository.ProductRepository;
import com.ssafy.market.domain.user.domain.User;
import com.ssafy.market.domain.user.repository.UserRepository;

import com.ssafy.market.domain.user.security.TokenProvider;
import com.ssafy.market.global.apis.ImgurApi;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
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
    public CreateOutput createPost(CreatePostInput input, DataFetchingEnvironment env ) throws Exception {

        Long userId = tokenProvider.getUserIdFromHeader(env);
        User user = (userRepository.findByUserId(userId));

        CreateOutput output = null;
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
                output = new CreateOutput("SUCCESS", post.getPostId());
            }else{
                output = new CreateOutput("FAIL",null);
            }
        } catch (Exception e) {
//            System.out.println(e);
             output = new CreateOutput("FAIL",null);
        }
        return  output;
    }

    @Transactional
    public PostOutput updatePost(UpdatePostInput input, DataFetchingEnvironment env ){
        Long userId = tokenProvider.getUserIdFromHeader(env);
        Post post = postRepository.findByPostId(input.getPostId());
        post.update(input.getTitle(),input.getContents(),input.getDeal(),input.getDealState());
        Product product = productRepository.findByPost(post);
        product.update(post,input.getProductname(),input.getPrice(),input.getCategory());
        List<Hashtag> hashtagList = hashtagRepository.findByProduct(product);
        String[] hashtagarr = input.getHashtag().split(" ");
        List<String> hash = new ArrayList<>();
        for (int i = 0; i< hashtagarr.length; i++){
            Hashtag hashtag = hashtagRepository.save(new Hashtag(null,product,hashtagarr[i]));
            hash.add(hashtagarr[i]);
        }
        List<File> files = fileRepository.findByProduct(product);
        List<FileArr> fileArr = new ArrayList<>();

//        String hashtag = input.getHashtag();
//        String[] hashtagarr = hashtag.split(" ");
//        for (int j = 0; j<hashtagarr.length;j++){
//            hashtagList.get(j).update(hashtagarr[j]);
////            hashtag = hashtag +" "+hashtagList.get(j);
//        }
        // 해시태그 관련 추가하기
//        hashtag.update(input.getHashtag());
        PostOutput output = new PostOutput(post.getPostId(),userId,post.isBuy(),post.getTitle(),post.getContents(),post.getDeal(),post.getDealState()
                ,product.getCategory(),product.getName(),product.getPrice(),hash,
                files
        );
        return output;
    }

    @Transactional
    public Long updateView(Long postId){
        Post post = postRepository.findByPostId(postId);
        System.out.println(post.getPostId());
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
    public int deletePost(Long postId){ // 해시태그 삭제 추가 해야함.
        Post post = postRepository.findByPostId(postId);
        Product product = productRepository.findByPost(post);
        List<File> fileList = fileRepository.findByProduct(product);
        productRepository.deleteByProductId(product.getProductId());
        for (int i = 0; i<fileList.size();i++){
            fileRepository.deleteByFileId(fileList.get(i).getFileId());
        }
        return postRepository.deleteByPostId(postId);
    }
}