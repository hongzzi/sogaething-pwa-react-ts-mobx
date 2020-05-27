package com.ssafy.market.domain.post.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.ssafy.market.domain.file.repository.FileRepository;
import com.ssafy.market.domain.hashtag.domain.Hashtag;
import com.ssafy.market.domain.hashtag.repository.HashtagRepository;
import com.ssafy.market.domain.post.domain.Post;
import com.ssafy.market.domain.post.dto.PostOutput;
import com.ssafy.market.domain.post.repository.PostRepository;
import com.ssafy.market.domain.product.domain.Product;
import com.ssafy.market.domain.product.repository.ProductRepository;
import com.ssafy.market.domain.user.repository.UserRepository;
import com.ssafy.market.global.exception.SelectNotDataException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.HandshakeHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
//            Product product = productRepository.findByPost(post);
//            Hashtag hashtag = hashtagRepository.findByProduct(product);
            outputs.add(new PostOutput(post.getPostId(),post.getUser().getUserId(), post.isBuy(),post.getTitle(),post.getContents(),post.getViewCount(),post.getDeal()
            ,post.getDealState()
//                    ,product.getCategory(),product.getName(),hashtag.getHashtag()
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
        PostOutput output = new PostOutput(id,post.getUser().getUserId(),post.isBuy(),post.getTitle(),post.getContents(),post.getViewCount(),post.getDeal(),post.getDealState()
                // ,product.getCategory(),product.getName(),hashtag.getHashtag()
        );
        return output;
    }

    public List<Post> findRecentPosts(){
        return postRepository.findTop6ByOrderByCreatedDateDesc();
    }
}