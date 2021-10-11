package com.book.demo.config.database;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.*;

public class ConnectionRunnable implements Runnable{
    private static final Logger logger = LoggerFactory.getLogger(ConnectionRunnable.class);

    private Connection connection;
    private Object waitObject;
    private ConnectionCallBack callBack;

    public ConnectionRunnable(Connection connection, Object waitObject, ConnectionCallBack callBack) {
        this.connection = connection;
        this.waitObject = waitObject;
        this.callBack = callBack;
    }

    @Override
    public void run() {
        // 데이터를 계속 가져옵니다. 만약 가져오지 않으면 wait
        while (!Thread.currentThread().isInterrupted()){
            ExecuteSqlBean sqlBean = callBack.getExecuteSqlBean();
            while (sqlBean == null){
                synchronized (waitObject){
                    try {
                        logger.info(Thread.currentThread().getName() + " 시작 "); //시작
                        waitObject.wait();
                        logger.info(Thread.currentThread().getName() + " 마무리 "); //마무리
                        sqlBean = callBack.getExecuteSqlBean();

                        logger.info("{} sqlbean 가지고 옴 : {}", Thread.currentThread().getName(), sqlBean );//sqlbean 가지고 옴
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            logger.info(Thread.currentThread().getName()); //sql빈 실행됨

            // 실행 시작합니다
            List<Map<String,String>> result = doExecuteJob(sqlBean.getSql());
            sqlBean.setResult(result);
            logger.info(Thread.currentThread().getName()); // run 종료

            // 실행이 완료되면, 연결 풀의 리셋이 완료됩니다.
            callBack.onExecuteSuccess(sqlBean);
        }
        // 연결을 닫습니다.
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private List<Map<String, String>> doExecuteJob(String sql)  {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            List<Map<String, String>> executeResult = new LinkedList<>();
            while (resultSet.next()) {
                Map<String, String> columnMap = new HashMap<>();
                ResultSetMetaData metaData =  resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();
                for (int i = 1; i < columnCount + 1 ; i++) {
                    columnMap.put(metaData.getColumnName(i), resultSet.getString(i));
                }
                executeResult.add(columnMap);
            }
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            statement.close();
            return executeResult;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }
    public interface ConnectionCallBack{
        ExecuteSqlBean getExecuteSqlBean();
        void onExecuteSuccess(ExecuteSqlBean executeSqlBean);
    }

}
