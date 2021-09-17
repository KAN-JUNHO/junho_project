package com.book.demo.controller;

import com.book.demo.Database;


import com.book.demo.scheduler.SchedulerThreadFactory;
import com.book.demo.vo.Count;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.security.DeclareRoles;
import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Stream;


@Slf4j
@Controller
@RequiredArgsConstructor
@EnableAsync
public class BaseController{
    private final SchedulerThreadFactory schedulerThreadFactory;

    @PostMapping("/plus")
    @ResponseBody
    public Count plus(@RequestBody String jsonString) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> params = mapper.readValue(jsonString, Map.class);

        Count count = new Count(null, params.get("username") , "plus", 1);
        log.info("input = "+count);
        Database.addQueue(count);
        return count;
    }

    @ResponseBody
    @PostMapping("/minus")
    public Count minus(@RequestBody String jsonString) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> params = mapper.readValue(jsonString, Map.class);
        Count count = new Count(null, params.get("username") , "minus", 1);
        log.info("input = "+count);
        Database.addQueue(count);
        return count;
    }


    @ResponseBody
    @PostMapping("/thread/create")
    public String  createSchedulerThread(){
        schedulerThreadFactory.createThread(3000,true);
        return "ok";
    }

    @ResponseBody
    @PostMapping("/thread/all")
    public String  removeSchedulerThread(){
        schedulerThreadFactory.removeThread();
        return "all";
    }

    @ResponseBody
    @GetMapping(value = "/view" )
    public Flux<ServerSentEvent<String>> intervalStream() {
        return  Flux.interval(Duration.ofSeconds(3))
                .map(i -> ServerSentEvent.builder("data " + Database.getSingletonInstance().getCnt()).build());
    }
    @ResponseBody
    @PostMapping(value = "/view"){
    public Flux<ServerSentEvent<String>> echo(@RequestBody Mono<String> body) {
        return
    }
//    @ResponseBody
//    @RequestMapping(value = "/view" ,method = {RequestMethod.GET, RequestMethod.POST}, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//    public Mono<ServerSentEvent<String>> intervalStream() {
//        return  Mono.delay(Duration.ofSeconds(3))
//                .map(i -> ServerSentEvent.builder("data " + Database.getSingletonInstance().getCnt()).build());
//    }

}
