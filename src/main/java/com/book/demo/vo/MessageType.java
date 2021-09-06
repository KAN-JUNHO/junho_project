package com.book.demo.vo;

import lombok.Getter;
import org.apache.logging.log4j.message.Message;

@Getter
public enum MessageType {
    CHAT,
    JOIN,
    LEAVE
}
//    CHAT("","",""),
//    JOIN("","",""),
//    LEAVE("","","");
//
//    private String value;
//    private String name;
//    private String nickname;
//
//    public MessageType(String value, String name, String nickname){
//        this.
//
//    }
//}
