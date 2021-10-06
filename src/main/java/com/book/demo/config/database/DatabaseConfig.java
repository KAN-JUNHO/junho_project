package com.book.demo.config.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class DatabaseConfig {

    @Autowired
    Environment env;

    @Bean
    public ConnectionFactory getFactory() {
        return new ConnectionFactory(env.getProperty("db.driver"),
                env.getProperty("db.dbUrl"),
                env.getProperty("db.userName"),
                env.getProperty("db.password"));
    }
    @Bean
    public ConnectionPool getConnectionPool(ConnectionFactory factory) {

        return new ConnectionPool(2, factory);
    }

}
