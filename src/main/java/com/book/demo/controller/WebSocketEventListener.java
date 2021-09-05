package com.book.demo.controller;

import com.book.demo.model.BookMessage;
import com.book.demo.model.MessageType;
import com.book.demo.vo.Count;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Slf4j
@Component //자바 클래스를 스프링 빈이라고 표시하는 역할을 합니다
public class WebSocketEventListener {

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    // SessionConnectedEvent 와 SessionDisconnectEvent 는 ApplicationEvent에 상속
    @EventListener//@EventListener 주석을 통해 관리되는 Bean의 모든 public 메소드에 등록 할 수 있습니다.
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        log.info("Received a new web socket connection");
    }
    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {

        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        String username = (String) headerAccessor.getSessionAttributes().get("username");
        if(username != null) {
            log.info("User Disconnected : " + username);

            //?외부패키 못불러온다? 왜지?
            Count bookMessage = new Count();

//            bookMessage.setType(MessageType.LEAVE);
            bookMessage.setSender(username);

            messagingTemplate.convertAndSend("/topic/public", bookMessage);
        }
    }
}
