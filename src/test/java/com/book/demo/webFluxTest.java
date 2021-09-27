package com.book.demo;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.util.StopWatch;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;

import java.net.URI;
import java.time.Duration;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

public class webFluxTest {
    private static final URI THREE_SECOND_URL = URI.create("home");
    Logger log = LoggerFactory.getLogger(getClass());


//    @Test
//    public Flux<ServerSentEvent<String>> blocking() {
//        public Flux<ServerSentEvent<String>> intervalStream () {
//            return Flux.interval(Duration.ofSeconds(1))
//                    .map(i -> ServerSentEvent.builder("data " + i).build());
//        }
//    }

}
