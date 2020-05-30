package com.ssafy.market.domain.post.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.ssafy.market.domain.detaildeal.dto.FileArr;
import com.ssafy.market.domain.file.domain.File;
import com.ssafy.market.domain.file.repository.FileRepository;
import com.ssafy.market.domain.hashtag.domain.Hashtag;
import com.ssafy.market.domain.hashtag.repository.HashtagRepository;
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
import com.ssafy.market.global.exception.DomainNotFoundException;
import com.ssafy.market.global.exception.UserNotFoundException;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sun.misc.IOUtils;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
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
    public PostOutput createPost(CreatePostInput input, DataFetchingEnvironment env ) throws IOException {
//        String[] arr = input.getImgs();
//        byte[] byteData = (arr[0].getBytes());
//        String base64 = Base64.getEncoder().encodeToString(byteData);
//        System.out.println(base64);
//        String result = api.uploadimgtest(base64);
//        String result = api.uploadimgtest(arr[0]);
//        System.out.println(result);

        Long userId = tokenProvider.getUserIdFromHeader(env);
        User user = (userRepository.findByUserId(userId));
        if(user==null){
            throw new UserNotFoundException("User");
        }
        Post post = postRepository.save(new Post(null, user, false, input.getTitle(), null, input.getContents(), (long) 0, input.getDeal(), "진행중"));
        Product product = productRepository.save(new Product(null,post,input.getProductname(), input.getPrice(),input.getCategory(),(long)0));
        String[] hashtagarr = input.getHashtag().split(" ");
        List<String> hash = new ArrayList<>();
        for (int i = 0; i< hashtagarr.length; i++){
            Hashtag hashtag = hashtagRepository.save(new Hashtag(null,product,hashtagarr[i]));
            hash.add(hashtagarr[i]);
        }
        String[] arr = input.getImgs();
        for (int k = 0; k< arr.length; k++){
            String imgur = api.uploadimgtest(arr[k]);
            File file = fileRepository.save(new File(null,product,imgur));
        }
        List<File> files = fileRepository.findByProduct(product);
        PostOutput output = new PostOutput(post.getPostId(),userId,post.isBuy(),post.getTitle(),post.getContents()
                ,post.getDeal(),post.getDealState()
                ,product.getCategory(),product.getName(),input.getPrice(),hash,files
        );
        return  output;
    }

    @Transactional
    public PostOutput updatePost(UpdatePostInput input, DataFetchingEnvironment env ){
        Long userId = tokenProvider.getUserIdFromHeader(env);
        Post post = postRepository.findByPostId(input.getPostId());
        if(post==null){
            throw new DomainNotFoundException("postId" , input.getPostId());
        }
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
    public Long updateViewcount(Long postId){
        Post post = postRepository.findByPostId(postId);
        post.update(post.getViewCount()+1);
        return post.getViewCount();
    }
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
        if(post==null){
            throw new DomainNotFoundException("postId " , postId);
        }
        Product product = productRepository.findByPost(post);
        List<File> fileList = fileRepository.findByProduct(product);
        productRepository.deleteByProductId(product.getProductId());
        for (int i = 0; i<fileList.size();i++){
            fileRepository.deleteByFileId(fileList.get(i).getFileId());
        }
        return postRepository.deleteByPostId(postId);
    }
}