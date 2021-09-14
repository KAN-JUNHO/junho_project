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
public class SchedulerThreadFactory {

    private Map<String , SchedulerThread> schedulerThreads = new HashMap<>();
    private int threadKey;

    public int getThreadKey() {
        return threadKey;
    }

    public SchedulerThreadFactory(int i, boolean b) {
    }

    public String  createThread(int time, boolean flag) {
        SchedulerThread thread = new SchedulerThread(time, true);
        thread.run();
        String threadKey = UUID.randomUUID().toString();
        schedulerThreads.put(threadKey, thread);
        return threadKey;
    }

    public void removeThread() {
        for ( String threadKey : schedulerThreads.keySet() ){
            SchedulerThread thread = schedulerThreads.get(threadKey);
            thread.setFlag(false);
        }

    }
}
