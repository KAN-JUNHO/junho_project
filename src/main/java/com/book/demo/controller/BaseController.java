package com.book.demo.controller;

import com.book.demo.Database;


import com.book.demo.scheduler.SchedulerThread;
import com.book.demo.scheduler.SchedulerThreadFactory;
import com.book.demo.vo.Count;
import com.sun.xml.internal.ws.api.ha.StickyFeature;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Slf4j
@Controller
@RequiredArgsConstructor

public class BaseController{
    private final SchedulerThreadFactory schedulerThreadFactory;


    // username=junho&password=1213&
    // { }


    @PostMapping("/plus")
    public String plus(@RequestParam String username){
        Count count = new Count(null, username , "plus", 1);
        log.info("input plus = "+count);
        Database.addQueue(count);

        return "plus";
    }

    @ResponseBody
    @PostMapping("/minus")
    public String minus(@RequestParam(value = "username") String username){
        Count count = new Count(null, username, "minus", 1);
        Database.addQueue(count);
        log.info("input minus = "+ count);

        return "minus";
    }


    @ResponseBody
    @PostMapping("/thread/create")
    public String createSchedulerThread(){
        schedulerThreadFactory.createThread(3000,true);
        return "create";
    }

    @ResponseBody
    @PostMapping("/thread/all")
    public String  removeSchedulerThread(){
        schedulerThreadFactory.removeThread();
        return "all";
    }

}
