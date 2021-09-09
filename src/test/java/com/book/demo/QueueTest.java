package com.book.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayDeque;
import java.util.Queue;

public class QueueTest {

    Logger log = LoggerFactory.getLogger(getClass());

    private Queue<String> queue;

    @BeforeEach
    void setUp(){
        queue = new ArrayDeque<>();
        queue.add("11");
        queue.add("22");
        queue.add("33");
        queue.add("44");
        queue.add("55");

    }

    @Test
    void peek(){
        log.info(queue.peek());
        log.info("{}",queue.size());

    }
}
