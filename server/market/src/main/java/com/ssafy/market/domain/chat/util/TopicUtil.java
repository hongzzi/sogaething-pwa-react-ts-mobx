package com.ssafy.market.domain.chat.util;

import org.springframework.data.redis.listener.ChannelTopic;

import java.util.HashMap;
import java.util.Map;

public class TopicUtil {
    private static Map<Long, ChannelTopic> topics = new HashMap<>();
    private TopicUtil(){}
    public static Map<Long, ChannelTopic> getTopicUtil() {
        return topics;
    }
}
