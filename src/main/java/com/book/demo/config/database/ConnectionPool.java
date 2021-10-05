package com.book.demo.config.database;

import com.book.demo.vo.Count;
import com.book.demo.vo.MessageType;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ConnectionPool {

    private List<Connection> cp;

    public ConnectionPool(String dburl,String dbUser,String dbpasswd) throws SQLException {
        cp = new ArrayList<>();
        for ( int i = 0 ; i < 20 ; i ++){
            cp.add(DriverManager.getConnection(dburl,dbUser,dbpasswd));
        }
    }

    public Connection getIdleConnection(){
        return cp.get(0);
    }

//    public List<Count> getCount(){
//        List<Count> list = new LinkedList<>();
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        String sql = "SELECT * FROM counts";
//        try(Connection conn = DriverManager.getConnection(connectionManager.getConnection().toString());
//            PreparedStatement ps  = conn.prepareStatement(sql) ) {
//
//            try(ResultSet rs = ps.executeQuery()){
//                while(rs.next()) {
//                    String sender = rs.getString("sender");
//                    MessageType type = MessageType.valueOf(rs.getString("type"));
//                    String content = rs.getString(null);
//                    int cnt = rs.getInt("cnt");
//                    Count count = new Count(type,sender,content,cnt);
//                    list.add(count);
//                }
//            }
//            catch(Exception e) {
//                e.printStackTrace();
//            }
//        }
//        catch(Exception ex){
//            ex.printStackTrace();
//        }
//        return list;
//    }
//
//    public int addCount(Count count) {
//        int insertCount = 0;
//
//        Connection conn = null ;
//        PreparedStatement ps = null;
//
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//
//            conn = DriverManager.getConnection();
//            String sql =  "INSERT INTO role (role_id,description) VALUES (?,?)";
//
//            ps = conn.prepareStatement(sql);
//
//            ps.setInt(1, role.getRoleId()); //1st ?
//            ps.setString(2, role.getDescription()); // 2nd ?
//
//            insertCount = ps.executeUpdate();
//
//
//        }catch(Exception ex){
//            ex.printStackTrace();
//        }finally {
//            if(ps!=null) {
//                try {
//                    ps.close();
//                }catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//            if(conn!=null) {
//                try {
//                    conn.close();
//                }catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//        }
//        return insertCount;
//    }



}
