package com.book.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class moveController {

    @GetMapping("/test")
    public String move(){
        return "/test";
    }

}
