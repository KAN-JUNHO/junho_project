package com.book.demo.config.database;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

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

    public String ToString(){
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
