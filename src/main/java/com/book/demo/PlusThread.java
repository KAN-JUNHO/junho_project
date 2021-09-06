package com.book.demo;

import com.book.demo.vo.MessageType;
import com.book.demo.vo.Count;


public class PlusThread extends Thread {

    @Override
    public void run() {
        Count count = new Count();
        // 큐에 데이터 넣어줌
//        Database.addQueue(count.getCnt());
//        Database.addQueue(Count.of(MessageType.CHAT,"plus",1));

    }
}
