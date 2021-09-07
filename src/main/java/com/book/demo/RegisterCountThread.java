package com.book.demo;

import com.book.demo.vo.MessageType;
import com.book.demo.vo.Count;


public class RegisterCountThread extends Thread {

    private Count count;

    public RegisterCountThread(Count count){
        this.count = count;
    }

    @Override
    public void run() {

        // 큐에 데이터 넣어줌
        Database.addQueue(count);
    }
}
