//package com.ssafy.market.domain.post.resolver;
//
//import com.coxautodev.graphql.tools.GraphQLQueryResolver;
//import com.ssafy.market.domain.post.domain.Post;
//import com.ssafy.market.domain.post.respository.PostRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//public class PostQuery implements GraphQLQueryResolver {
//    private final PostRepository postRepository;
//
//    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
//    @Autowired
//    public PostQuery(PostRepository postRepository){
//        this.postRepository = postRepository;
//    }
//
//    public Iterable<Post> findAllPosts() {
//        return postRepository.findAll();
//    }
//}
