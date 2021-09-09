package com.book.demo.scheduler;

import com.book.demo.Database;
import com.book.demo.singleton.Singleton;
import com.book.demo.vo.Count;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
@Slf4j
public class NumberAccessScheduler {

    @Scheduled(fixedDelay = 1000)
    public void execute(){
        log.info("### 큐 감시중###");
        // 큐에서 데이터르 꺼내서 더하는 로직
        Count count = null;
        try {
            count = Database.popQueue();
        } catch (NoSuchElementException ignore) {

        }
        //큐가 잘들어 가있는니 몇개 들어가니?
        //
        if (count == null)
            return;
        else {
            log.info("### COUNT : {}", count);
            if (count.getContent().equals("plus"))
                Singleton.getInstance().plusNumber(count.getCnt());
            else if (count.getContent().equals("minus")){
                Singleton.getInstance().minusNumber(count.getCnt());
            }
        }

        log.info(String.valueOf(Database.getSingletonInstance().getCnt()));

    }
}
