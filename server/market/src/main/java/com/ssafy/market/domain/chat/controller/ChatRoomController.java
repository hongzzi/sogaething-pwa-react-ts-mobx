package com.ssafy.market.domain.chat.controller;

import com.ssafy.market.domain.chat.domain.ChatRoom;
import com.ssafy.market.domain.chat.dto.ChatRoomDto;
import com.ssafy.market.domain.chat.service.ChatRoomService;
import com.ssafy.market.domain.chat.util.BoolResult;
import com.ssafy.market.domain.chat.util.StringResult;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatRoomController {
    private static final Logger logger = LoggerFactory.getLogger(ChatRoomController.class);

    private final ChatRoomService chatRoomService;

    // 채팅방 생성
    @PostMapping("/room")
    @ApiOperation(value = "채팅방을 생성한다.", response= ChatRoom.class)
    public ResponseEntity<Object> createChatRoom(@RequestBody ChatRoom chatRoom) {
        StringResult stringResult = null;
        try {
            logger.debug("createChatRoom {}", chatRoom);
            ChatRoom result = chatRoomService.createChatRoom(chatRoom);
            if (result != null) {
                stringResult = new StringResult(String.valueOf(result.getRoomId()), "createChatRoom", "SUCCESS");
                return new ResponseEntity<Object>(stringResult, HttpStatus.CREATED);
            } else { // 실행 중 에러
                stringResult = new StringResult("-1", "createChatRoom", "SUCCESS");
                return new ResponseEntity<Object>(stringResult, HttpStatus.CONFLICT);
            }
        } catch (RuntimeException e) {
            logger.error("createChatRoom {}", e.getMessage());
            stringResult = new StringResult(e.getMessage(), "createChatRoom", "FAIL");
            return new ResponseEntity<Object>(stringResult, HttpStatus.BAD_REQUEST);
        }
    }

    // 모든 채팅방 목록 반환
    @GetMapping("/rooms")
    @ApiOperation(value = "모든 채팅방을 가져온다.", response=List.class)
    public ResponseEntity<Object> findAllRooms() {
        try {
            List<ChatRoom> result = chatRoomService.findAllRoom();
            return new ResponseEntity<Object>(result, HttpStatus.OK);
        } catch (RuntimeException e) {
            String errorMessage = e.getMessage();
            logger.error("findAllRooms {}", errorMessage);
            StringResult stringResult = new StringResult(errorMessage, "findAllRooms", "FAIL");
            return new ResponseEntity<Object>(stringResult, HttpStatus.BAD_REQUEST);
        }
    }

    // 특정 채팅방 조회
    @GetMapping("/room/{roomId}")
    @ApiOperation(value = "특정 방의 정보를 가져온다.", response= ChatRoom.class)
    public ResponseEntity<Object> findRoomByRoomId(@PathVariable Long roomId) {
        try {
            ChatRoom chatRoom = chatRoomService.findRoomByRoomId(roomId);
            return new ResponseEntity<Object>(chatRoom, HttpStatus.OK);
        } catch (RuntimeException e) {
            String errorMessage = e.getMessage();
            logger.error("findRoomByRoomId {}", errorMessage);
            StringResult stringResult = new StringResult(errorMessage, "findRoomById", "FAIL");
            return new ResponseEntity<Object>(stringResult, HttpStatus.BAD_REQUEST);
        }
    }

    // 유저별 채팅방 리스트 조회
    @GetMapping("/rooms/{userId}")
    @ApiOperation(value = "특정 유저의 채팅방 목록을 가져온다.", response=List.class)
    public ResponseEntity<Object> findRoomsByUserId(@PathVariable String userId) {
        try {
//            Map<String, List<ChatRoom>> result = chatRoomService.findRoomsPerPositionByUserId(userId);
            List<ChatRoomDto> result = chatRoomService.findRoomsByUserId(userId);
            return new ResponseEntity<Object>(result, HttpStatus.OK);
        } catch (RuntimeException e) {
            String errorMessage = e.getMessage();
            logger.error("findRoomsByUserId {}", errorMessage);
            StringResult stringResult = new StringResult(errorMessage, "findRoomsPerPositionByUserId", "FAIL");
            return new ResponseEntity<Object>(stringResult, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/room")
    @ApiOperation(value = "채팅방 정보를 수정한다.", response=Boolean.class)
    public ResponseEntity<Object> updateChatRoom(@RequestBody ChatRoom chatRoom) {
        BoolResult boolResult = null;
        try {
            logger.debug("updateChatRoom {}", chatRoom);
            Long result = chatRoomService.updateChatRoom(chatRoom);
            if (result > 0) {
                boolResult = new BoolResult(true, "updateChatRoom", "SUCCESS");
                return new ResponseEntity<Object>(boolResult, HttpStatus.OK);
            } else {
                boolResult = new BoolResult(false, "updateChatRoom", "SUCCESS");
                return new ResponseEntity<Object>(boolResult, HttpStatus.NO_CONTENT);
            }
        } catch (RuntimeException e) {
            boolResult = new BoolResult(false, "updateChatRoom", "FAIL");
            return new ResponseEntity<Object>(boolResult, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("room")
    @ApiOperation(value = "특정 채팅방을 삭제한다.", response = Boolean.class)
    public ResponseEntity<Object> deleteChatRoom(@RequestBody ChatRoom chatRoom) {
        BoolResult boolResult = null;
        try {
            logger.debug("deleteChatRoom {}", chatRoom);
            Long result = chatRoomService.deleteChatRoom(chatRoom);
            if (result > 0) {
                boolResult = new BoolResult(true, "deleteChatRoom", "SUCCESS");
                return new ResponseEntity<Object>(boolResult, HttpStatus.OK);
            } else {
                boolResult = new BoolResult(false, "deleteChatRoom", "SUCCESS");
                return new ResponseEntity<Object>(boolResult, HttpStatus.NO_CONTENT);
            }
        } catch (RuntimeException e) {
            boolResult = new BoolResult(false, "deleteChatRoom", "FAIL");
            return new ResponseEntity<Object>(boolResult, HttpStatus.BAD_REQUEST);
        }
    }
}
