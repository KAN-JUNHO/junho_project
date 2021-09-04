package com.book.demo;

import com.book.demo.vo.Count;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Queue;

@Slf4j
public class Database {

    private static Integer singletonNumber = 0;
    private static Queue<Count> queue = new ArrayDeque<>(100);

    public static void addQueue(Count count){
        queue.add(count);
    }

    public static Count peekQueue(){
        return queue.peek();
    }
    public static Count popQueue(){
        return queue.remove();
    }
    public static void plusNumber(int count){
        singletonNumber += count;
    }

    public static void minusNumber(int count){
        singletonNumber -= count;
    }


}