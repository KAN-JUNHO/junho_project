package com.book.demo.DB;

import lombok.extern.slf4j.Slf4j;

import java.sql.*;

@Slf4j
public class DBUtil {
    public static Connection getConnection() {
        Connection conn = null;
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String id = "hr";
        String pw = "java1234";
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(url, id, pw);
            return conn;
        } catch (Exception e) {
            System.out.println("DBUtil.getConnection() : " + e.toString());
        }
        return null;
    }
}
