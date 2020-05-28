package com.ssafy.market.domain.chat.service;

import com.ssafy.market.domain.chat.domain.ChatMessage;
import com.ssafy.market.domain.chat.redis.RedisPublisher;
import com.ssafy.market.domain.chat.repository.ChatMongoRepository;
import com.ssafy.market.domain.chat.util.TopicUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class ChatService {
    private static final Logger logger = LoggerFactory.getLogger(ChatService.class);

    private final ChatMongoRepository chatMongoRepository;


//     채팅방(topic)에 발행되는 메시지를 처리할 Listener
//    private final RedisMessageListenerContainer redisMessageListener;
    // 게시 처리 서비스
    private final RedisPublisher redisPublisher;
    private Map<String, ChannelTopic> topics = TopicUtil.getTopicUtil();

    public ChannelTopic getTopic(String roomId) {
        return topics.get(roomId);
    }

    public Boolean sendMessage(ChatMessage chatMessage) {
        ChannelTopic channelTopic = getTopic(chatMessage.getRoomId());
        redisPublisher.publish(channelTopic, chatMessage);
        ChatMessage result = chatMongoRepository.insertChatMessage(chatMessage);
        if (result != null){
            return true;
        } else {
            return false;
        }
    }

    public List<ChatMessage> findChatMessagesByRoomId(String roomId) {
        List<ChatMessage> result = chatMongoRepository.getChatMessagesByRoomId(roomId);
        return result;
    }
}
