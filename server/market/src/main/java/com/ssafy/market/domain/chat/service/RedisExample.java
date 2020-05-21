package com.ssafy.market.domain.chat.service;

import com.ssafy.market.domain.chat.domain.ChatMessage;
import com.ssafy.market.domain.chat.domain.MessageType;
import com.ssafy.market.global.config.RedisConfig;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class RedisExample {
    public void setGetValuesExam() {
        ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(RedisConfig.class);
        try {
            @SuppressWarnings("unchecked")
            RedisTemplate<String, Object> redisTemplate = (RedisTemplate<String, Object>)ctx.getBean("redisTemplate");
            // value operation
            ValueOperations<String, Object> values = redisTemplate.opsForValue();

            // set
            values.set("chatMessage", new ChatMessage(MessageType.ENTER, "id01", "박동준", "테스트", LocalDateTime.now().toString()));
//            values.set("chatMessage", new ChatMessage(MessageType.ENTER, "id01", "박동준", "테스트"));

            // get
            System.out.println("ChatMessaged added : " + values.get("chatMessage"));
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            ctx.close();
        }
    }

    public void hashExam() {
        ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(RedisConfig.class);
        try {
            @SuppressWarnings("unchecked")
            RedisTemplate<String, Object> redisTemplate = (RedisTemplate<String, Object>)ctx.getBean("redisTemplate");

            Map<String, String> empBobMap = new HashMap<>();
            empBobMap.put("name", "Bob");
            empBobMap.put("age", "26");
            empBobMap.put("id", "02");

            Map<String, String> empJohnMap = new HashMap<>();
            empJohnMap.put("name", "John");
            empJohnMap.put("age", "16");
            empJohnMap.put("id", "03");

            // Hash Operation
            HashOperations<String, String, String> hash = redisTemplate.opsForHash();
            String empBobKey = "emp:Bob";
            String empJohnKey = "emp:John";

            hash.putAll(empBobKey, empBobMap);
            hash.putAll(empJohnKey, empJohnMap);

            System.out.println("Get emp Bob : " + hash.entries(empBobKey));
            System.out.println("Get emp John : " + hash.entries(empJohnKey));

        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            ctx.close();
        }

    }
}
