package com.book.demo.scheduler;

import com.book.demo.Database;
import com.book.demo.singleton.Singleton;
import com.book.demo.vo.Count;
import lombok.extern.slf4j.Slf4j;

import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
public class SchedulerThread extends Thread {

    private int time;
    private boolean flag;
    private Count count;

    public SchedulerThread(int time, boolean flag) {
        this.time = time;
        this.flag = flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        while (flag){
            log.info("### 큐 감시중### {}", time/1000 +" 초 마다 진행중" );
            while (Database.peekQueue() != null) {
                count = Database.popQueue();
                log.info("output =  "+ count);
                if (count.getContent().equals("plus"))
                    Singleton.getInstance().plusNumber(count.getCnt());
                else if (count.getContent().equals("minus")) {
                    Singleton.getInstance().minusNumber(count.getCnt());
                }
            }
            log.info(String.valueOf(Database.getSingletonInstance().getCnt()));
//            log.info("### COUNT : {}", count);
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        log.info("멈춤");
    }


}
// 큐에서 데이터르 꺼내서 더하는 로직

//큐가 잘들어 가있는니 몇개 들어가니?
//
