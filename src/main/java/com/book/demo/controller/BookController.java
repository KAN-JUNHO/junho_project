package com.book.demo.controller;

import com.book.demo.RegisterCountThread;
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
    //전에 채팅이 덮어 씌어지지 않음
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public Count sendMessage(@Payload Count count) {
        RegisterCountThread registerCountThread = new RegisterCountThread(count);
        registerCountThread.start();
        log.info("메세지 보내기");
        return count;
    }

    //"/app/chat.addUser" 인 메시지는 addUser()로 라우팅
    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public Count addUser(@Payload Count count, SimpMessageHeaderAccessor headerAccessor){

        log.info("가입");
        headerAccessor.getSessionAttributes().put("username", count.getSender());
        return count;
    }


}
