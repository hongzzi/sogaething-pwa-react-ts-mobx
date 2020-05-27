package com.ssafy.market.domain.post.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
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
import com.ssafy.market.global.exception.DomainNotFoundException;
import com.ssafy.market.global.exception.UserNotFoundException;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
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

    @Transactional
    public PostOutput createPost(CreatePostInput input, DataFetchingEnvironment env ){
        Long userId = tokenProvider.getUserIdFromHeader(env);
        User user = (userRepository.findByUserId(userId));
        if(user==null){
            throw new UserNotFoundException("User");
        }
        // hashtag를 따로 나누어서 집어넣기
        Post post = postRepository.save(new Post(null, user, false, input.getTitle(), null, input.getContents(), (long) 0, input.getDeal(), "진행중"));
        Product product = productRepository.save(new Product(null,post,input.getProductname(), input.getPrice(),input.getCategory(),input.getProductState()));
        String[] hashtagarr = input.getHashtag().split(" ");
        for (int i = 0; i< hashtagarr.length; i++){
            Hashtag hashtag = hashtagRepository.save(new Hashtag(null,product,hashtagarr[i]));
        }
        // 링크는 url 받아서 넣어주기???
        File file = fileRepository.save(new File(null,product,input.getImgPath()));
        PostOutput output = new PostOutput(post.getPostId(),userId,post.isBuy(),post.getTitle(),post.getContents(),post.getViewCount(),post.getDeal(),post.getDealState()
//                ,product.getCategory(),product.getName(),hashtag.getHashtag()
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
        product.update(post,input.getProductname(),input.getPrice(),input.getCategory(),input.getProductState());
//        List<Hashtag> hashtagList = hashtagRepository.findByProduct(product);
//        String hashtag = input.getHashtag();
//        String[] hashtagarr = hashtag.split(" ");
//        for (int j = 0; j<hashtagarr.length;j++){
//            hashtagList.get(j).update(hashtagarr[j]);
////            hashtag = hashtag +" "+hashtagList.get(j);
//        }
        // 해시태그 관련 추가하기
//        hashtag.update(input.getHashtag());
        PostOutput output = new PostOutput(post.getPostId(),userId,post.isBuy(),post.getTitle(),post.getContents(),post.getViewCount(),post.getDeal(),post.getDealState()
//                ,product.getCategory(),product.getName(),hashtag.getHashtag()
        );
        return output;
    }
    @Transactional
    public int deletePost(Long id){
        Post post = postRepository.findByPostId(id);
        if(post==null){
            throw new DomainNotFoundException("postId " , id);
        }
        Product product = productRepository.findByPost(post);
//        Hashtag hashtag = hashtagRepository.findByProduct(product);
        List<File> fileList = fileRepository.findByProduct(product);
        productRepository.deleteByProductId(product.getProductId());
//        hashtagRepository.deleteByHashtagId(hashtag.getHashtagId());
        for (int i = 0; i<fileList.size();i++){
            fileRepository.deleteByFileId(fileList.get(i).getFileId());
        }
        return postRepository.deleteByPostId(id);
    }
}