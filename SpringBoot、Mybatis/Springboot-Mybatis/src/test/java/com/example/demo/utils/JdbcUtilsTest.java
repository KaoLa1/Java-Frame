package com.example.demo.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * @author : gwh
 * @date : 2020-01-16 15:58
 **/
@Slf4j
public class JdbcUtilsTest {
    private static final String url = "jdbc:mysql://localhost/db2?useUnicode=true&characterEncoding=utf8&useSSL=false&allowMultiQueries=true&useCursorFetch=true&defaultFetchSize=1000";
    private static final String user = "root";
    private static final String password = "123456";
    private static final String driver = "com.mysql.jdbc.Driver";

    @Test
    public void testInsert1(){
        Connection con;
        String tableName = "student1";
        String key = "id,name,age";
        String data = "1112,11,11";
        String insetSql = "insert into " + tableName + "(" + key + ") values (" + data + ")";
        try {
            //加载驱动程序
            Class.forName(driver);
            //1.getConnection()方法，连接MySQL数据库！！
            con = DriverManager.getConnection(url, user, password);
            if (!con.isClosed()) {
                System.out.println("Succeeded connecting to the Database!");
            }
            //2.创建statement类对象，用来执行SQL语句！！
            PreparedStatement preparedStatement = con.prepareStatement(insetSql);
            //3.ResultSet类，用来存放获取的结果集！！
            int i = preparedStatement.executeUpdate();
            preparedStatement.close();
            log.info("-------------------" + i);
            con.close();
        } catch (Exception e) {
            //数据库驱动类异常处理
            System.out.println("Sorry,can`t find the Driver!");
            e.printStackTrace();
        } finally {
            System.out.println("数据库数据成功获取！！");
        }
    }
}
