package com.book.demo.controller;

import com.book.demo.Database;


import com.book.demo.scheduler.SchedulerThreadFactory;
import com.book.demo.service.CountService;
import com.book.demo.vo.Count;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

import java.util.Map;


@Slf4j
@RestController
@RequiredArgsConstructor
@EnableAsync
public class BaseController{
    private final SchedulerThreadFactory schedulerThreadFactory;
    private final CountService countService;
    private Integer a = 0;

    @PostMapping("/plus")
    public Mono<Count> plus(@RequestBody String jsonString) throws JsonProcessingException {
        log.info("######  : {}", Database.requestCount);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> params = mapper.readValue(jsonString, Map.class);
        Count count = new Count(null, params.get("username"), "plus", 1);
//        log.info("input = "+count);
        Database.addQueue(count);
        return countService.addUser(count);
    }


    @PostMapping("/minus")
    public Mono<Count> minus(@RequestBody String jsonString) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> params = mapper.readValue(jsonString, Map.class);

        Count count = new Count(null, params.get("username") , "minus", 1);
//        log.info("input = "+count);
        Database.addQueue(count);
        return countService.addUser(count);
    }


    @PostMapping("/thread/create")
    public String  createSchedulerThread(){
        schedulerThreadFactory.createThread(3000,true);
        return "ok";
    }

    @PostMapping("/thread/all")
    public String  removeSchedulerThread(){
        schedulerThreadFactory.removeThread();
        return "all";
    }

    @GetMapping("/queueSize")
    public Object test(){
        return Database.getQueue().size();
    }

    @GetMapping(value = "/view",produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<ServerSentEvent<String>> intervalStream() {
        log.info(String.valueOf(Flux.interval(Duration.ofSeconds(3)).map(i -> ServerSentEvent.builder("" + Database.getSingletonInstance().getCnt()).build())));
        return Flux.interval(Duration.ofSeconds(3))
                .map(i -> ServerSentEvent.builder("" + Database.getSingletonInstance().getCnt()).build());
    }

    @Async
    @PostMapping(value = "/sseview",produces = MediaType.APPLICATION_JSON_VALUE)
    public ListenableFuture<Flux<Object>> intervalStream1() {
        log.info(String.valueOf(Flux.interval(Duration.ofSeconds(3)).map(i -> ServerSentEvent.builder("" + Database.getSingletonInstance().getCnt()).build())));

        return new AsyncResult<>(Flux.interval(Duration.ofSeconds(3))
                .map(i -> ServerSentEvent.builder("" + Database.getSingletonInstance().getCnt()).build()));
    }

}

