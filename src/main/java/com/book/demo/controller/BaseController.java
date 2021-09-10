package com.book.demo.controller;

import com.book.demo.Database;
import com.book.demo.RegisterCountThread;
import com.book.demo.scheduler.NumberAccessScheduler;
import com.book.demo.vo.Count;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Slf4j
@Controller
@RequiredArgsConstructor

public class BaseController {
    private final NumberAccessScheduler numberAccessScheduler;

    @ResponseBody
    @PostMapping("/plus")
    public String plus(){
//        ObjectMapper mapper = new ObjectMapper();
        Count count = new Count(null, null, "plus", 1);
        Database.addQueue(count);
        RegisterCountThread registerCountThread = new RegisterCountThread(count);
        registerCountThread.start();
        numberAccessScheduler.execute();

        return "plus";
    }

    @ResponseBody
    @PostMapping("/minus")
    public String minus(){
        Database.addQueue(new Count(null, null, "minus", 1));
        return "minus";
    }

}
