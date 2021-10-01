package com.book.demo.DB;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDriver;
import org.apache.commons.pool.impl.GenericObjectPool;

import java.sql.*;

@Slf4j
public class DBManager {

    private static DBManager instance;

    synchronized public static DBManager getInstance() {

        try {
            if (instance == null) {
                instance = new DBManager();
                log.info("DBManager initialize: {}", instance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return instance;
    }

    private DBManager() {

        // Connection을 초기화
        initFirstConnection();

        // TODO : 다른 DB 사용 시 해당 DB 초기화.
    }

    private void initFirstConnection(){
        // Config Setting
        try {
            setupFirstDriver();
        } catch (Exception ex) {
            log.error("Exception : {}", ex.getMessage());
        }
        log.info("FirstConnection Created");
    }

    /**
     * Comeback Player Connection을 리턴함
     *
     * @return DB Connection
     */
    public Connection getConnection() {
        Connection con = null;

        try {
            con = DriverManager.getConnection("jdbc:apache:commons:dbcp:first_connection");
        } catch (SQLException ex) {
            log.error("SQLException : {}", ex.getMessage());
        }
        // TODO : Connection Type에 따라 분기.

        return con;
    }

    /**
     *  Connection Pool 설정
     *
     * @throws Exception
     */
    public void setupFirstDriver() throws Exception {
        // JDBC 드라이버 로딩(MSSQL 드라이버를 가져옴. 사용하는 jdbc 드라이버를 로드하면 됨
        Class.forName(Configuration.getProperty("com.microsoft.sqlserver.jdbc.SQLServerDriver"));

        // Connection Pool 생성, 옵션세팅
        GenericObjectPool connectionPool = new GenericObjectPool(null);
        connectionPool.setMaxActive(45);
        connectionPool.setMinIdle(4);
        connectionPool.setMaxWait(15000);
        connectionPool.setTimeBetweenEvictionRunsMillis(3600000);
        connectionPool.setMinEvictableIdleTimeMillis(1800000);
        connectionPool.setMaxIdle(45);
        connectionPool.setTestOnBorrow(true);

        // 실제 DB와의 커넥션을 연결해주는 팩토리 생성
        ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(
                Configuration.getProperty("Database Remote URL"), // JDBC URL
                Configuration.getProperty("gompang"), // 사용자
                Configuration.getProperty("gompang_password"));

        // Connection Pool이 PoolableConnection 객체를 생성할 때 사용할
        // PoolableConnectionFactory 생성
        PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(
                connectionFactory,
                connectionPool,
                null, // statement pool
                "SELECT 1", // 커넥션 테스트 쿼리: 커넥션이 유효한지 테스트할 때 사용되는 쿼리.
                false, // read only 여부
                false); // auto commit 여부

        // Pooling을 위한 JDBC 드라이버 생성 및 등록
        PoolingDriver driver = new PoolingDriver();

        // JDBC 드라이버에 커넥션 풀 등록
        driver.registerPool("first_connection", connectionPool);
    }

    /*
        Connection Pool에 free 및 객체 소멸 함수들
     */
    public void freeConnection(Connection con, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            freeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void freeConnection(Connection con, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            freeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void freeConnection(Connection con, PreparedStatement pstmt) {
        try {
            if (pstmt != null) pstmt.close();
            freeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void freeConnection(Connection con, Statement stmt) {
        try {
            if (stmt != null) stmt.close();
            freeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void freeConnection(Connection con) {
        try {
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void freeConnection(Statement stmt) {
        try {
            if (stmt != null) stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void freeConnection(PreparedStatement pstmt) {
        try {
            if (pstmt != null) pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void freeConnection(ResultSet rs) {
        try {
            if (rs != null) rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
