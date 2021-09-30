package com.book.demo.controller;

import com.book.demo.service.CountService;
import com.book.demo.vo.Count;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
public class CountApiController {
    private final CountService countService;

    @GetMapping("counts")
    public Flux<Count> findAll(){
        return countService.findAll();
    }

    @PostMapping(value = "counts", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Count> addCount(@RequestBody Count count){
        return countService.addUser(count);
    }

    @DeleteMapping(value = "counts/{sender}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Void> deleteCount(@PathVariable String sender){
        return countService.deleteUser(sender);
    }


}
