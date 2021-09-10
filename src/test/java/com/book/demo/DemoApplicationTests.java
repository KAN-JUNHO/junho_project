package com.book.demo;

import groovy.util.logging.Slf4j;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

class DemoApplicationTests {

	Logger log = LoggerFactory.getLogger(DemoApplicationTests.class);

	@Test
	void contextLoads() {
		log.info("1번 실행");
	}

	@Test
	void contextLoads2() {
		log.info("2번 실행");
	}

}