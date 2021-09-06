package com.book.demo.scheduler;

import com.book.demo.Database;
import com.book.demo.vo.Count;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class NumberAccessScheduler {

    @Scheduled(fixedDelay = 1000)
    public void execute(){
        // 큐에서 데이터르 꺼내서 더하는 로직
        Count count = Database.peekQueue();
        if ( count == null)
            return;
        else {
            Database.plusNumber(count.getCnt());
        }
//        if (count.getCommand().equals("plus"))
//            Database.plusNumber(count.getCount());

    }
}
