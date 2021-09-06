package com.book.demo.controller;

import com.book.demo.vo.Count;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class BookController {

    ///app/chat.sendMessage" 인 메세지는 sendMessage()로 라우팅
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public Count sendMessage(@Payload Count count) {
        log.info("1111");

        return count;
    }

    //"/app/chat.addUser" 인 메시지는 addUser()로 라우팅
    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public Count addUser(@Payload Count count, SimpMessageHeaderAccessor headerAccessor){
        log.info("2222");
        headerAccessor.getSessionAttributes().put("username", count.getContent());
        return count;
    }
}
