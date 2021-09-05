package com.book.demo.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Count {

    private static String command;
    private static int count;
    //빌더가 안먹힘?

    public static Count of(String command, int count){
        return Count.builder()
                .command(command)
                .count(count)
                .build();
    }




}
