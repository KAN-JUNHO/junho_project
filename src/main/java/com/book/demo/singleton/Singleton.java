package com.book.demo.singleton;

public class Singleton {

    private Integer cnt;
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

    public Integer getCnt() {
        return cnt;
    }

    public void plusNumber(int count){
        cnt += count;
    }

    public void minusNumber(int count){
        cnt -= count;
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
