package com.book.demo;

public class MinusThread extends Thread{

    @Override
    public void run() {
        // 큐에 데이터 빼줌
        Database.popQueue();
    }
}
