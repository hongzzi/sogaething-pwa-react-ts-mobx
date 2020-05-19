package com.ssafy.market.domain.post.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.ssafy.market.domain.post.dto.CreatePostInput;
import com.ssafy.market.domain.post.domain.Post;
import com.ssafy.market.domain.post.respository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class PostMutation implements GraphQLMutationResolver {

    private final PostRepository postRepository;

    @Transactional
    public Post createPost(CreatePostInput input){
        System.out.println(input.toString());
        return postRepository.save(new Post(null, input.getUploader_id(), false, input.getTitle(), input.getSale_date(), input.getContents(), (long) 0, input.getDeal(), "진행중"));
//        return postRepository.save(new Post(Long uploader_id, String title, Date sale_date, String contents, String deal));
//        return postRepository.save(new Post(Long post_id, Long uploader_id, boolean is_buy, String title, Date sale_date, String contents, Long view_count, Date enroll_date, Date modify_date, String deal, String deal_state));
    }
}