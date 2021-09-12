package com.book.demo.scheduler;

import com.book.demo.vo.Count;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor

public class SchedulerThreadFactory{

    private Map<String, SchedulerThread> schedulerThreads = new HashMap<>();

    Count count;

    public String createThread(int time){
        SchedulerThread thread = new SchedulerThread(time);
        thread.run();
        String threadKey = UUID.randomUUID().toString();
        schedulerThreads.put(threadKey, thread);
        return threadKey;
    }

    public void removeThread(int reset){
        SchedulerThread thread = new SchedulerThread(reset);
        thread.run();

    }




}
