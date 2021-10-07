package com.book.demo.config.database;

import com.book.demo.vo.Count;
import com.book.demo.vo.MessageType;
import org.springframework.beans.factory.annotation.Value;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ConnectionManager {

    private final ConnectionPool connectionPool;
    @Value("${myapplication.datasource.dburl}")
    String dburl;

    @Value("${myapplication.datasource.dbUser}")
    String dbUser;

    @Value("${myapplication.datasource.dbpasswd}")
    String dbpasswd;

    public ConnectionPool getConnectionPool() {
        return connectionPool;
    }

    public ConnectionManager(int coreNum, ConnectionFactory connectionFactory){
        connectionPool = new ConnectionPool(coreNum, connectionFactory);
    }

//    public Connection getConnection(){
//        return connectionPool.getIdleConnection();
//    }

    //유저 검색
    public List<Count> getCounts(){
        List<Count> list = new LinkedList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String sql = "SELECT * FROM counts";

        try(Connection conn = DriverManager.getConnection( dburl, dbUser, dbpasswd);
            PreparedStatement ps  = conn.prepareStatement(sql) ) {

            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()) {
                    String sender = rs.getString("sender");
                    MessageType type = MessageType.valueOf(rs.getString("type"));
                    String content = rs.getString(null);
                    int cnt = rs.getInt("cnt");
                    Count count = new Count(type,sender,content,cnt);
                    list.add(count);
                }
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return list;
    }

    //유저 업데이트
    public int addCount(Count count) {
        int insertCount = 0;

        Connection conn = null ;
        PreparedStatement ps = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
            String sql =  "INSERT INTO counts(type,sender,content,cnt) VALUES (?,?,?,?)";

            ps = conn.prepareStatement(sql);

            ps.setObject(1,count.getType());
            ps.setString(2, count.getSender()); //1st ?
            ps.setString(3, count.getContent()); // 2nd ?
            ps.setInt(4, count.getCnt()); // 2nd ?

            insertCount = ps.executeUpdate();


        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            if(ps!=null) {
                try {
                    ps.close();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if(conn!=null) {
                try {
                    conn.close();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
        return insertCount;
    }

    public Count getCount(String name)  // 정보를 담아낼 객체
    {
        Count count  = null;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            //Driver Load
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(dburl,dbUser,dbpasswd); //커넥션객체 얻어옴
            String sql = "SELECT * FROM counts WHERE sender = ?";
            ps = conn.prepareStatement(sql);

            ps.setString(1,name); // 1번인자 = 몇번쨰? 2번쨰 인자 =
            rs = ps.executeQuery();

            if(rs.next())//roleID에해당 결과값이 없다면?
            {
                String sender = rs.getString("sender");
                //파라미터 인덱스로 가져오는 것도 가능하고 컬럼명으로 가져오는 것도 가능하다.

                count = new Count(null ,sender,"",1);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(rs!=null) {
                try {
                    rs.close();
                }catch (Exception e) {
                    // TODO: handle exception
                }
            }
            if(ps!=null) {
                try {
                    ps.close();
                }catch (Exception e) {
                    // TODO: handle exception
                }
            }
            if(conn!=null) {
                try {
                    conn.close();
                }catch (Exception e) {
                    // TODO: handle exception
                }
            }
        }

        return count;

    }
}
