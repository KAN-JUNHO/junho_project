package com.book.demo.config.database;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;

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
        while (!Thread.currentThread().isInterrupted()){
            ExecuteSqlBean sqlBean = callBack.getExecuteSqlBean();
            while (sqlBean == null){
                synchronized (waitObject){

                    try {
                        logger.info(Thread.currentThread().getName()); //시작
                        waitObject.wait();
                        logger.info(Thread.currentThread().getName()); //마무리
                        sqlBean = callBack.getExecuteSqlBean();
                        logger.info(Thread.currentThread().getName());//sqlbean 가지고 옴

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        logger
    }

    public interface ConnectionCallBack{
        ExecuteSqlBean getExecuteSqlBean();
        void onExecuteSuccess(ExecuteSqlBean executeSqlBean);

    }
}
