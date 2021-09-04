package com.book.demo.vo;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Count {

    private final String command;
    private final int count;

    public static Count of(String command, int count){
        return Count.builder()
                .command(command)
                .count(count)
                .build();
    }




}
