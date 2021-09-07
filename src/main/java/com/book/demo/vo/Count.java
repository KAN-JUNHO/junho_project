package com.book.demo.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Getter
@Setter
@Slf4j
public class Count {

    private MessageType type;
    private String sender;
    private String content;
    private Integer cnt;

    public Count(MessageType type, String sender, String content, Integer cnt) {
        this.type = type;
        this.sender = sender;
        this.content = content;
        this.cnt = cnt;
    }

    public Count() {

    }

    public Count(Integer cnt){

    }

    public Integer getCnt(){
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

//    public void plusNumber(int count){
//        cnt += count;
//    }
//
//    public void minusNumber(int count){
//        cnt -= count;
//    }
    //객체로 만들어서 반환
//    public static Count of(MessageType type,String command, int count) {
//        return new Count(MessageType.CHAT,"asdf","123",1);
//    }


//    public void setType(String type){
//        if (this.type.getValue().equals(type));
//            this.type =
//    }
//
//    public void getType(){
//
//    }
//
////
//    public static void main(String[] args) {
//        Count ofTestInstance = Count.of("11", 121);
//        System.out.printf("");
//    }



//    public static Count of(String command, int count){
//        return Count.builder()
//                .command(command)
//                .count(count)
//                .build();
//    }

    public String toString(){
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
