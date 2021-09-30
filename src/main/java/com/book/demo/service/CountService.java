package com.book.demo.service;

import com.book.demo.repository.CounterRepository;
import com.book.demo.vo.Count;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class CountService {

    private final CounterRepository counterRepository;

    public Mono<Long> count(){
        return counterRepository.count();
    }
    public Flux<Count> findAll() {
        return counterRepository.findAll();
    }

    public Mono<Count> addUser(Count count) {
        return counterRepository.save(count);
    }

    public Flux<Count> addUsers(Flux<Count> count) {
        return counterRepository.saveAll(count);
    }

    public Mono<Void> deleteUser(String sender) {
        return counterRepository.deleteById(sender);
    }

    public Mono<Void> deleteAll() {
        return counterRepository.deleteAll();
    }
}
