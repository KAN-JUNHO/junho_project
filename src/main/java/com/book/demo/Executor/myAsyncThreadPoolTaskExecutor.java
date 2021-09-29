package com.book.demo.Executor;


import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

public class myAsyncThreadPoolTaskExecutor {

    private static final int THREAD_POOL_SIZE = 0;

    @Bean(name = "myAsyncThreadPoolTaskExecutor")
    public Executor myAsyncThreadPoolTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(THREAD_POOL_SIZE);
        taskExecutor.setMaxPoolSize(THREAD_POOL_SIZE);
        taskExecutor.setQueueCapacity(Integer.MAX_VALUE);
        taskExecutor.setThreadNamePrefix("myAsyncThreadPoolTaskExecutor-");
        taskExecutor.initialize();
        return taskExecutor;
    }
}