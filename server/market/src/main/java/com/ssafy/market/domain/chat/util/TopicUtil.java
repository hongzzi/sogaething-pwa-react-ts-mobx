package com.ssafy.market.domain.chat.util;

import org.springframework.data.redis.listener.ChannelTopic;

import java.util.HashMap;
import java.util.Map;

public class TopicUtil {
    private static Map<String, ChannelTopic> topics = new HashMap<>();
    private TopicUtil(){}
    public static Map<String, ChannelTopic> getTopicUtil() {
        return topics;
    }
}
