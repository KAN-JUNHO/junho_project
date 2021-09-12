package com.book.demo.scheduler;

import com.book.demo.Database;
import com.book.demo.singleton.Singleton;
import com.book.demo.vo.Count;
import lombok.extern.slf4j.Slf4j;

import java.util.NoSuchElementException;

@Slf4j
public class SchedulerThread extends Thread {

    private final int time;

    public SchedulerThread(int time) {
        this.time=time;
    }

    @Override
    public void run() {
        Count count = null;
        boolean check = true;
        while (check){
            log.info("### 큐 감시중###");
            while (true){
                try {
                    count = Database.popQueue();
                } catch (NoSuchElementException ignore) {
                    log.info("큐안에 아무것도 없습니다.");
                    break;
                }
            }
            // 큐에서 데이터르 꺼내서 더하는 로직

            //큐가 잘들어 가있는니 몇개 들어가니?
            //
            if (count == null) {
                check = false;
                return;
            }
            else {
                check=false;
                log.info("### COUNT : {}", count);
                if (count.getContent().equals("plus"))
                    Singleton.getInstance().plusNumber(count.getCnt());
                else if (count.getContent().equals("minus")){
                    Singleton.getInstance().minusNumber(count.getCnt());
                }

            }
            try {
                sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info(String.valueOf(Database.getSingletonInstance().getCnt()));



        }

    }


}
