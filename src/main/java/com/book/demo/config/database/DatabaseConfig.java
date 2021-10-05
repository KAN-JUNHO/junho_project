package com.book.demo.config.database;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

@Configuration
public class DatabaseConfig {

//    @Value("{myapplication.datasource.url}")
//    String url;
    String dburl = "jdbc:mysql://localhost:3306/counts?serverTimezone=Asia/Seoul&useSSL=false";
    String dbUser = "root";
    String dbpasswd = "1234qwer";
    @Bean
    public ConnectionManager connectionManager() throws SQLException {
        return new ConnectionManager(dburl, dbUser, dbpasswd);
    }
}
