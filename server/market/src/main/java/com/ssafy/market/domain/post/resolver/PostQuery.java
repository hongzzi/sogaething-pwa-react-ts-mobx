package com.ssafy.market.domain.post.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.ssafy.market.domain.post.domain.Post;
import com.ssafy.market.domain.post.dto.PostOutput;
import com.ssafy.market.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PostQuery implements GraphQLQueryResolver {
    private final PostRepository postRepository;

    public List<PostOutput> findAllPost() {
        List<PostOutput> outputs = new ArrayList<>();
        List<Post> postList = postRepository.findAll();
        for(int i = 0; i<postList.size();i++){
            outputs.add(new PostOutput(postList.get(i).getPostId(),postList.get(i).getUser().getUserId(), postList.get(i).isBuy()
            ,postList.get(i).getTitle(),postList.get(i).getContents(),postList.get(i).getViewCount(),postList.get(i).getDeal()));
        }
        return outputs;
//        return postRepository.findAll();
    }
    public Iterable<Post> findAllPosts(){
        return postRepository.findAll();
    }
//    public Optional<Post> findPostByPostId(Long id) {
//        return postRepository.findByPost_id(id);
//    }

    public PostOutput findPostByPostId(Long id) {
        Post post = postRepository.findByPostId(id).get();
        PostOutput output = new PostOutput(id,post.getUser().getUserId(),post.isBuy(),post.getTitle(),post.getContents(),post.getViewCount(),post.getDeal());
        return output;
//        return postRepository.findByPostId(id);
    }

//    public Iterable<Post> findAllPostsByUploaderId() {
//        return postRepository.findAll();
//    }
}