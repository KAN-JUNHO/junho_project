package com.book.demo.repository;

import com.book.demo.vo.Count;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface CounterRepository extends ReactiveCrudRepository<Count,String > {
}
