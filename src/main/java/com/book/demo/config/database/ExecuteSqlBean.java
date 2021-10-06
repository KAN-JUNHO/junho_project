package com.book.demo.config.database;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ExecuteSqlBean {

    private long id;
    private String sql;
    private Object executeWaitObject;
    private List<Map<String, String>> result;

    public ExecuteSqlBean(long id, String sql, Object executeWaitObject) {
        this.id = id;
        this.sql = sql;
        this.executeWaitObject = executeWaitObject;
    }
}
