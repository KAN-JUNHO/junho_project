package com.book.demo.controller;

import com.book.demo.Database;


import com.book.demo.scheduler.SchedulerThreadFactory;
import com.book.demo.vo.Count;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Slf4j
@Controller
@RequiredArgsConstructor

public class BaseController{
    private final SchedulerThreadFactory schedulerThreadFactory;

    @ResponseBody
    @PostMapping("/plus")
    public String plus(){

        Count count = new Count(null, null, "plus", 1);
        Database.addQueue(count);

//        Count count = new Count(null, null, "plus", 1);
//        Database.addQueue(count);
//        RegisterCountThread registerCountThread = new RegisterCountThread(count);
//        registerCountThread.start();
//        numberAccessScheduler.execute();
        return "plus";
    }

    @ResponseBody
    @PostMapping("/minus")
    public String minus(){
        Database.addQueue(new Count(null, null, "minus", 1));
        return "minus";
    }


    @ResponseBody
    @PostMapping("/thread/create")
    public String createSchedulerThread(){
        return "SUCCESS : " + schedulerThreadFactory.createThread(1000);
    }

    @ResponseBody
    @PostMapping("/thread/all")
    public void removeSchedulerThread(){
        schedulerThreadFactory.removeThread(5000);
    }

}
