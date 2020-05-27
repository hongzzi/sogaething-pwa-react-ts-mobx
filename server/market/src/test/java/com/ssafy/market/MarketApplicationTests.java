package com.ssafy.market;

import com.ssafy.market.domain.post.domain.PostEl;
import com.ssafy.market.domain.post.repository.PostElRepository;
import com.ssafy.market.domain.post.service.PostElService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class MarketApplicationTests {
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    PostElService postElService;

    @Autowired
    PostElRepository postElRepository;

    @Before
    public void setup() {
//        elasticsearchTemplate.delete
//        postElService = new PostElService(postElRepository);
    }

    @Test
    public void SuccessFindByPostId() {
        PostEl post = postElService.findByPostId((long) 1);
        assertThat(post.getPostId(), is((long)1));
//        System.out.println(post.toString());
        assertTrue(true);
    }


}
