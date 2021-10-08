package com.book.demo.config.database;

import groovy.util.logging.Slf4j;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DatabaseConfig.class})
@Import({DatabaseConfig.class})
class ConnectionManagerTest {
//    @Value("${myapplication.datasource.url}")
//    String dburl;
//
//    @Value("${myapplication.datasource.dbUser}")
//    String dbUser;
//
//    @Value("${myapplication.datasource.dbpasswd}")
//    String dbpasswd;
//
//    @Autowired
//    private ConnectionManager connectionManager;
//
////    @Test
////    void connectionTest(){
////        Connection connection = connectionManager.getConnection();ㅌㅌ
////ㅌ   }
//
//    @Test
//    void retrieveAfterClose(){
//
//    }
//
//    @Test
//    void find_sender() throws SQLException {
//
//        ConnectionManager connectionManager = new ConnectionManager( coreNum,  connectionFactory);
//        Count count = connectionManager.getCount("junho");
//        if (count==null) {
//            System.out.println("################# 못찾음");
//        }else {
//            System.out.println("################# 찾음 :" + count);
//        }
//    }
//
//    @Test
//    void select() throws SQLException {
//
//        Count count = connectionManager.getCount("junho");
//        System.out.println(count);
//    }
//
//    @Test
//    void poolsize() throws SQLException {
//        List<Integer> cp;
//        cp = new ArrayList<>();
//
//        while (true) {
//            while (cp.size() < 5) {
////            cp.add(DriverManager.getConnection(dburl,dbUser,dbpasswd));
//                cp.add(1);
//            }
//            cp.remove(0);
//        }
//    }
}