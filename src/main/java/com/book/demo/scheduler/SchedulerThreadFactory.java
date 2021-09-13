package com.book.demo.scheduler;

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
    private long limit;

    public SchedulerThreadFactory(int i, boolean b) {
    }

    public String createThread(int time, boolean flag){
        SchedulerThread thread = new SchedulerThread(time,true);

        thread.run();
        String threadKey = UUID.randomUUID().toString();
        schedulerThreads.put(threadKey, thread);
        return threadKey;
    }

    public void removeThread(){
        Thread.interrupted();
    }

}
