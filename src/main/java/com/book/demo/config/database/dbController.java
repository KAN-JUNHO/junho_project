package com.book.demo.config.database;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequestMapping("/db")
@RestController
public class dbController {

    @Autowired
    private ConnectionPool ConnectionPool;

    @RequestMapping("/query")
    public String queryDb() {

        String querySql = "SELECT * FROM counts";
        List<Map<String, String>> result = new ArrayList<>();
        try {
            result = ConnectionPool.executeSql(querySql);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result.toString();

    }
    @RequestMapping("/pool")
    public String pool(){
        return "2";
    }
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}

