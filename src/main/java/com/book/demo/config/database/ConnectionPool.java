package com.book.demo.config.database;

import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.util.List;

public class ConnectionPool {

    private List<Connection> cp;

    private int coreNum;
    public ConnectionPool(int coreNum, ConnectionFactory connectionFactory) {

        this.coreNum = coreNum;
        initConnectionThread(connectionFactory);
    }
    private void initConnectionThread(ConnectionFactory connectionFactory) {
        for (int i = 0; i< coreNum; i++) {
            Connection connection = connectionFactory.makeConnection();
            Thread thread = new Thread(new ConnectionRunnable(connection, executeRunnableWaitObject, connectionCallBack), "db-connection-thread-" + i);
            thread.start();
        }
    }
    //
//    public ConnectionPool(String dburl,String dbUser,String dbpasswd) throws SQLException {
//        cp = new ArrayList<>();
//        while (cp.size()<20){
//            cp.add(DriverManager.getConnection(dburl,dbUser,dbpasswd));
//            System.out.println("#############"+cp);
//        }
//    }
//    public Connection getIdleConnection(){
//        return cp.get(0);
//    }




}
