package com.example.demo.utils;

import com.muye.kl.demo.utils.StringUtils;
import com.muye.kl.demo.utils.excel.ReadExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 给定表名和字段，导出Excel
 * @author : gwh
 * @date : 2020-01-13 11:48
 *
 **/
@Slf4j
public class ExcelUtilsTest {

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

    @Test
    public void testInsert2() {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rt = null;
        String tableName = "user";
        String filePath = "H:\\TestSpace\\My_Test\\My_Test1574818080068.xlsx";
        try {
            Long startTime = System.currentTimeMillis();
            List<List<String>> excelList = ReadExcelUtil.readExcelInfo(filePath);
            List<String> columnList = excelList.subList(0, 1).get(0);
            List<String> replaceList = new ArrayList<>();
            for (int i = 0; i < columnList.size(); i++) {
                replaceList.add("?");
            }
            String replaces = StringUtils.asString(replaceList);
            String columns = StringUtils.asString(columnList);
            List<List<String>> dataList = excelList.subList(1, excelList.size());
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            String sql = "INSERT INTO " + tableName + "(" + columns + ")" + " values " + "(" + replaces + ")";
            pstm = conn.prepareStatement(sql);
            for (List<String> list : dataList) {
                log.info("----------");
                for (int i = 0; i < list.size(); i++) {
                    pstm.setObject(i + 1, list.get(i));
                }
                pstm.addBatch();
            }
            pstm.executeBatch();
            Long endTime = System.currentTimeMillis();
            System.out.println("用时：" + (endTime - startTime));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (pstm != null) {
                try {
                    pstm.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        }
    }

}

