package com.book.demo;

import com.book.demo.vo.Count;


public class PlusThread extends Thread {

    @Override
    public void run() {
        // 큐에 데이터 넣어줌
        Database.addQueue(Count.of("plus", 1));

    }
}
