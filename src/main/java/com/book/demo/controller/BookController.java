package com.book.demo.controller;

import com.book.demo.websoketdemo.model.BookMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class BookController {

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public BookMessage sendMessage(@Payload BookMessage boookMessage) {
        return boookMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public BookMessage addUser(@Payload BookMessage boookMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", boookMessage.getSender());
        return boookMessage;
    }
}
