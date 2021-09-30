package com.book.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import java.sql.SQLException;

@Slf4j
@Profile("h2")
@Configuration
public class H2ServerConfig {
    private Server webServer;

    @Value("${webclient.h2-console-port}")
    Integer h2ConsolePort;

    // ApplicationContext : Bean 생성, Dependency Injection, ResourceLoader, ApplicationEventPublisher등 역할
    // ContextRefreshedEvent: ApplicationContext가 초기화 시에 발생하는 이벤트
    @EventListener(ContextRefreshedEvent.class)
    public void start() throws SQLException {
        log.info("starting h2 console at port {}", h2ConsolePort);
        this.webServer = Server.createWebServer("-webPort", h2ConsolePort.toString());
        this.webServer.start();
    }

    // ContextClosedEvent: ApplicationContext가 종료시에 발생하는 이벤트
    @EventListener(ContextClosedEvent.class)
    public void stop() {
        log.info("stopping h2 console at port {}", h2ConsolePort);
        this.webServer.stop();
    }
}
