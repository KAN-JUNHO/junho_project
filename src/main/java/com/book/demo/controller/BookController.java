package com.book.demo.controller;

import com.book.demo.vo.Count;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class BookController {

    ///app/chat.sendMessage" 인 메세지는 sendMessage()로 라우팅
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public Count sendMessage(@Payload Count chatMessage) {
        return chatMessage;
    }

    //"/app/chat.addUser" 인 메시지는 addUser()로 라우팅
    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public Count addUser(@Payload Count chatMessage, SimpMessageHeaderAccessor headerAccessor){
        headerAccessor.getSessionAttributes().put("username", chatMessage.());
        return chatMessage;
    }
}
