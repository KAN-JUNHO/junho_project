package com.book.demo.config.database;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;

public class ConnectionManager {

    private final ConnectionPool connectionPool;

    public ConnectionManager(String dburl,String dbUser,String dbpasswd) throws SQLException {
        connectionPool = new ConnectionPool(dburl,dbUser,dbpasswd);
    }

    public Connection getConnection(){
        return connectionPool.getIdleConnection();
    }


}
