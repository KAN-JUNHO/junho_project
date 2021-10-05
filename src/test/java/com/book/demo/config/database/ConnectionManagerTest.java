package com.book.demo.config.database;

import com.book.demo.Database;
import groovy.util.logging.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DatabaseConfig.class})
//@Import({DatabaseConfig.class})
class ConnectionManagerTest {

    @Autowired
    private ConnectionManager connectionManager;

    @Test
    void connectionTest(){
        Connection connection = connectionManager.getConnection();
    }

    @Test
    void retrieveAfterClose(){

    }



}