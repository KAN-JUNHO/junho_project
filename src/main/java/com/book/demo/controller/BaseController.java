package com.book.demo.controller;

import com.book.demo.Database;
import com.book.demo.MinusThread;
import com.book.demo.RegisterCountThread;
import com.book.demo.vo.Count;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Slf4j
@Controller
public class BaseController {

    @ResponseBody
    @PostMapping("/plus")
    public String plus(){
//        ObjectMapper mapper = new ObjectMapper();
        Database.addQueue(new Count(null, null, "plus", 1));
        return "plus";
    }

    @ResponseBody
    @PostMapping("/minus")
    public String minus(){
        Database.addQueue(new Count(null, null, "minus", 1));
        return "minus";
    }

}
