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
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Stream;


@Slf4j
@RestController
@RequiredArgsConstructor
public class BaseController{
    private final SchedulerThreadFactory schedulerThreadFactory;

    @PostMapping("/plus")
    public Count plus(@RequestBody String jsonString) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> params = mapper.readValue(jsonString, Map.class);

        Count count = new Count(null, params.get("username") , "plus", 1);
        log.info("input = "+count);
        Database.addQueue(count);
        return count;
    }


    @PostMapping("/minus")
    public Count minus(@RequestBody String jsonString) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> params = mapper.readValue(jsonString, Map.class);

        Count count = new Count(null, params.get("username") , "minus", 1);
        log.info("input = "+count);
        Database.addQueue(count);
        return count;
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

//    @GetMapping(value = "/view",produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public Flux<ServerSentEvent<String>> intervalStream() {
//        log.info(String.valueOf(Flux.interval(Duration.ofSeconds(3)).map(i -> ServerSentEvent.builder("" + Database.getSingletonInstance().getCnt()).build())));
//
//        return Flux.interval(Duration.ofSeconds(1)).map(i -> ServerSentEvent.builder("" + Database.getSingletonInstance().getCnt()).build());
//
////        return Flux.interval(Duration.ofSeconds(3))
////                .map(i -> ServerSentEvent.builder("" + Database.getSingletonInstance().getCnt()).build());
//    }
    @GetMapping(value = "/stream")
    Flux<Map<String, Integer>> stream() {
        Stream<Integer> stream = Stream.iterate(Database.getSingletonInstance().getCnt(), i -> Database.getSingletonInstance().getCnt());
        return (Flux<Map<String, Integer>>) Flux.fromStream(stream.limit(1000))
                .map(tuple -> Flux.interval(Duration.ofSeconds(3)).zipWith(Collections.singletonMap("value", tuple)));
    }

    @PostMapping("/view")
    Flux<String> view(@RequestBody Flux<String> body) {
        return body.map(String::toUpperCase);
    }
}
