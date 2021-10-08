package com.book.demo.config.database;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {

    @Value("${myapplication.datasource.dbdriver}")
    String dbdriver;

    @Value("${myapplication.datasource.dburl}")
    String dburl;

    @Value("${myapplication.datasource.dbUser}")
    String dbUser;

    @Value("${myapplication.datasource.dbpasswd}")
    String dbpasswd;

    @Bean
    public ConnectionFactory getFactory() {
        return new ConnectionFactory(dbdriver,dburl,dbUser,dbpasswd);
    }
    @Bean
    public ConnectionPool getConnectionPool(ConnectionFactory factory) {
        return new ConnectionPool(0, factory);
    }
}
