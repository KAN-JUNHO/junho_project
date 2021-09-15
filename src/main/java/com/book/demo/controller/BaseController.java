package com.book.demo.controller;

import com.book.demo.Database;


import com.book.demo.scheduler.SchedulerThreadFactory;
import com.book.demo.vo.Count;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;


@Slf4j
@Controller
@RequiredArgsConstructor

public class BaseController{
    private final SchedulerThreadFactory schedulerThreadFactory;


    // username=junho&password=1213&
    // { }

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
    public Integer createSchedulerThread(){
        return schedulerThreadFactory.createThread(3000,true);
    }

    @ResponseBody
    @PostMapping("/thread/all")
    public String  removeSchedulerThread(){
        schedulerThreadFactory.removeThread();
        return "all";
    }

}
