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

// 플러스로직은 큐에 넣는 작업으로 대체, 마이너스 로직은 큐에서 뺴는 작업으로 대체
1. 플러스 로직
            1. 큐에 넣는 작업
            2. 큐에
2. 마이너스 로직
            1. 큐에 넣는 작업
            2. 큐에
3. 웹소켓으로 싱글턴 넘버를 실시간으로 조회