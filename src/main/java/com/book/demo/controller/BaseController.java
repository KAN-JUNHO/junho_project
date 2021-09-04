package com.book.demo.controller;

import com.book.demo.MinusThread;
import com.book.demo.PlusThread;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Slf4j
@Controller
public class BaseController {

    @ResponseBody
    @GetMapping("/plus")
    public String plus(){
        PlusThread plusThread = new PlusThread();
        plusThread.start();
        return "OK";
    }

    @ResponseBody
    @GetMapping("/minus")
    public String minus(){
        MinusThread minusThread = new MinusThread();
        minusThread.start();
        return "OK";
    }

}
