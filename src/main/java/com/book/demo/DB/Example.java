package com.book.demo.DB;

import java.sql.*;

public class Example{
    private static void m3() {
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "delete from tblMemo";
            stat = conn.prepareStatement(sql);
            System.out.println(stat.executeUpdate());
            stat.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("JDBCTest.m2()" + e.toString());
        }
    }
}
