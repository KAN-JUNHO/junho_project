package com.book.demo.controller;

import com.book.demo.Database;


import com.book.demo.scheduler.SchedulerThreadFactory;
import com.book.demo.vo.Count;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Map;


@Slf4j
@Controller
@RequiredArgsConstructor
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


    @PostMapping("/minus")
    @ResponseBody
    public Count minus(@RequestBody String jsonString) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> params = mapper.readValue(jsonString, Map.class);

        Count count = new Count(null, params.get("username") , "minus", 1);
        log.info("input = "+count);
        Database.addQueue(count);
        return count;
    }


    @PostMapping("/thread/create")
    @ResponseBody
    public String  createSchedulerThread(){
        schedulerThreadFactory.createThread(3000,true);
        return "ok";
    }

    @PostMapping("/thread/all")
    @ResponseBody
    public String  removeSchedulerThread(){
        schedulerThreadFactory.removeThread();
        return "all";
    }

    @GetMapping(value = "/view",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ServerSentEvent<String> intervalStream() {
        log.info(String.valueOf(Flux.interval(Duration.ofSeconds(3)).take(10).map(i -> ServerSentEvent.builder("" + Database.getSingletonInstance().getCnt()).build())));

        return Flux.interval(Duration.ofSeconds(1)).take(100).map(i -> ServerSentEvent.builder("" + Database.getSingletonInstance().getCnt()).build()).blockFirst(Duration.ofSeconds(100));

//        return Flux.interval(Duration.ofSeconds(3))
//                .map(i -> ServerSentEvent.builder("" + Database.getSingletonInstance().getCnt()).build());
    }
}
