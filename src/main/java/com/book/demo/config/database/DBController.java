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
public class DBController {

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
    @RequestMapping("/query2")
    public String queryDb2() {
        String querySql = "SELECT * FROM counts2";
        List<Map<String, String>> result = new ArrayList<>();
        try {
            result = ConnectionPool.executeSql(querySql);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result.toString();
    }
    @RequestMapping("/query3")
    public String queryDb3() {
        String querySql = "SELECT * FROM counts3";
        List<Map<String, String>> result = new ArrayList<>();
        try {
            result = ConnectionPool.executeSql(querySql);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result.toString();
    }


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}

