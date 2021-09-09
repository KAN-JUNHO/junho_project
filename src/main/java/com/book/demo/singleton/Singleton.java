package com.book.demo.singleton;

import com.book.demo.vo.Count;

public class Singleton {

    private int cnt;
    private volatile static Singleton instance;

    private Singleton () {
    } //생성자를 private로

    public static Singleton getInstance() {
        if (instance == null) {
            //synchroized를 활용하여 여러 인스턴스를 생성하는 것을 방지
            synchronized (Singleton.class) {
                if (instance == null)
                    instance = new Singleton();
            }
        }
        return instance;
    }
//큐에 들어가는 값들이 잘못들어가룻 있다
    //
    public Integer getCnt() {
        return cnt;
    }

    public void plusNumber(int count){
        cnt += count;
//        return Count;
    }

    public void minusNumber(int count){
        cnt -= count;
//        return Count;
    }

//    public static Integer num = 1;
//
//    public static void main(String[] args) {
//        Runnable run = () -> {
//            num++;
//            Singleton singleton = Singleton.getInstance(num);
//            System.out.println("instance : " + singleton.getCnt());
//        };
//        for (int i = 0; i < 10; i++) {
//            Thread thread = new Thread(run);
//            thread.start();
//        }
//    }

}
