//package com.ssafy.market.domain.post.service;
//
//import com.google.common.collect.Lists;
//import com.ssafy.market.domain.post.domain.Post;
//import com.ssafy.market.domain.post.domain.PostEl;
//import com.ssafy.market.domain.post.repository.PostElRepository;
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class PostElService {
//    private final PostElRepository postElRepository;
//
////    public PostElService(PostElRepository postElRepository){
////        this.postElRepository = postElRepository;
////    }
//    public PostEl save(PostEl postEl){
//        return postElRepository.save(postEl);
//    }
//
//    public List<PostEl> findAll() {
//        return Lists.newArrayList(postElRepository.findAll());
//    }
//
//    public PostEl findByPostId(Long PostId) {
//        return postElRepository.findByPostId(PostId);
//    }
//}
