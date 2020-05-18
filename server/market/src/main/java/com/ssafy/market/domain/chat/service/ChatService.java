package com.ssafy.market.domain.chat.service;

import com.ssafy.market.domain.chat.domain.ChatMessage;
import com.ssafy.market.domain.chat.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChatService {
    private final ChatRepository chatRepository;

    public Boolean saveMessage(ChatMessage chatMessage){
        try {
            ChatMessage chatMessage2 = chatRepository.save(chatMessage);
            System.out.println(chatMessage2);
            return true;
        } catch (RuntimeException e) {
            throw e;
        }
    }
}
