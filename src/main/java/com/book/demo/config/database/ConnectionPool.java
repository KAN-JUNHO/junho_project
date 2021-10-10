package com.book.demo.config.database;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicLong;


public class ConnectionPool {
    private static final Logger logger = LoggerFactory.getLogger(ConnectionPool.class);

    private List<Connection> cp;

    private int coreNum;
    private final Object executeRunnableWaitObject = new Object();
    private Vector<ExecuteSqlBean> sqlBeanVector = new Vector<>();
    private AtomicLong maxSqlId = new AtomicLong();

    public ConnectionPool(int coreNum, ConnectionFactory connectionFactory) {
        this.coreNum = coreNum;
        initConnectionThread(connectionFactory);
    }

    private ConnectionRunnable.ConnectionCallBack connectionCallBack = new ConnectionRunnable.ConnectionCallBack() {
        @Override
        public synchronized ExecuteSqlBean getExecuteSqlBean() {

            logger.info(Thread.currentThread().getName() + " getExecuteSqlBean 실행된다");

            if (sqlBeanVector.size() == 0) {
                logger.info(Thread.currentThread().getName() + " getExecuteSqlBean 비어져있습니다.");
                return null;
            }

            ExecuteSqlBean executeSqlBean = sqlBeanVector.get(0);
            sqlBeanVector.remove(executeSqlBean);
            logger.info(Thread.currentThread().getName() + " executeSqlBean 가져옴");
            return executeSqlBean;
        }

        @Override
        public void onExecuteSuccess(ExecuteSqlBean executeSqlBean) {

            System.out.println(Thread.currentThread().getName() + " executeId: " + executeSqlBean.getId() + " " + executeSqlBean.getResult());
            // 실행이 완료되면, 해당 waitObject에서 wait의 스레드를 notifyAll로 설정해야 합니다.
            Object executeWaitObject = executeSqlBean.getExecuteWaitObject();
            synchronized (executeWaitObject) {

                System.out.println(Thread.currentThread().getName() + " executeWaitObject 알림을 시작합니다");
                executeWaitObject.notifyAll();
                System.out.println(Thread.currentThread().getName() + " executeWaitObject 종료 알림입니다.");
            }
        }
    };

    private void initConnectionThread(ConnectionFactory connectionFactory) {
        logger.info("initConnectionThread 시작");
        for (int i = 1; i < coreNum + 1; i++) {
            Connection connection = connectionFactory.makeConnection();
            Thread thread = new Thread(new ConnectionRunnable(connection, executeRunnableWaitObject, connectionCallBack), "db-connection-thread-" + i);
            thread.start();
        }
        logger.info("initConnectionThread 끝");
    }

    public List<Map<String, String>> executeSql(String sql) throws InterruptedException {

        logger.info("executeSql 실행");

        maxSqlId.addAndGet(1);
        final Object executeWaitObject = new Object();
        logger.info("executeSql maxSqlId 도움 : " + maxSqlId);

        // 막히기 시작합니다 Object를 기다리는 중
        ExecuteSqlBean sqlBean = new ExecuteSqlBean(maxSqlId.get(), sql, executeWaitObject);
        sqlBeanVector.add(sqlBean);
        synchronized (executeRunnableWaitObject) {
            logger.info("executeSql notifyAll before");
            executeRunnableWaitObject.notify();
            logger.info("executeSql notifyAll end");
        }

        // 실행 중 Object를 실행하는 중 막히기를 시작합니다.
        synchronized (executeWaitObject) {
            logger.info("executeSql executeWaitObject wait 시작합니다");
            executeWaitObject.wait();
            logger.info("executeSql executeWaitObject wait을 종료합니다.");
        }

        // wait 후, 실행이 완료되었습니다. sqlBean에서 직접 가져오면 됩니다.
        logger.info(String.valueOf(sqlBean.getResult()));
        return sqlBean.getResult();
    }


}