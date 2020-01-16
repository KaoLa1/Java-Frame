package com.muye.kl.demo.utils;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.ExceptionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.DialectFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.DialectModel;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.muye.kl.demo.entity.BO.Information;
import com.muye.kl.demo.entity.BO.Metadata;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

/**
 * <p>
 * 关系数据库工具类
 * </p>
 *
 * @author : wdj
 * @version 1.0
 * @date : 2019/12/20 17:42
 */
@Slf4j
public class RDBMSUtil1 {

    /**
     * 测试连接数据源
     * @param driver
     * @param url
     * @param username
     * @param password
     * @return
     */
    public static Connection getConn(String driver, String url, String username, String password) {
        //声明Connection对象
        Connection conn = null;
        try {
            //加载驱动程序
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
            if (!conn.isClosed()) {
                log.info("Succeeded connecting to the Database!");
            }
            conn.close();
        } catch (Exception e) {
            //数据库驱动类异常处理
            log.error("Sorry,can`t find the Driver!");
            e.printStackTrace();
        } finally {
            log.info("数据库数据成功获取！！");
        }
        return conn;
    }


    /**
     * 获取数据源信息
     *
     * @param driver   驱动
     * @param url      url
     * @param username username
     * @param password password
     * @return DataSource
     */
    private static DataSource getDataSource(String driver, String url, String username, String password) {
        HikariDataSource dataSource = new HikariDataSource();
        Properties props = new Properties();
        // oracle 必须配置此参数.
        props.setProperty("remarksReporting", "true");
        dataSource.setDriverClassName(driver);
        dataSource.setJdbcUrl(url);
        dataSource.setDataSourceProperties(props);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }



    /**
     * 获取数据库下的所有表名
     *
     * @param driver   驱动
     * @param url      url
     * @param username username
     * @param password password
     * @return tableList
     */
    public static List<String> buildDatabaseMetaDataTables(String driver, String url, String username, String password) throws SQLException {
        DataSource dataSource = getDataSource(driver, url, username, password);
        Connection conn = dataSource.getConnection();
        DatabaseMetaData metaData = conn.getMetaData();

        // 获取库名称
        String catalog = conn.getCatalog();
        ResultSet resultSet = metaData.getTables(catalog, metaData.getUserName().toUpperCase(), "%", new String[]{"TABLE"});
        List<String> tableList = new ArrayList<>(resultSet.getRow());
        while (resultSet.next()) {
            tableList.add(resultSet.getString("TABLE_NAME"));
        }

        return tableList;
    }


    /**
     * 获取到表字段所有属性
     *
     * @param driver    驱动
     * @param url       url
     * @param username  username
     * @param password  password
     * @param tableName tableName
     * @return
     */
    public static List<Metadata> buildTableMetadata(String driver, String url, String username, String password, String tableName) throws SQLException {
        DataSource dataSource = getDataSource(driver, url, username, password);
        Connection conn = dataSource.getConnection();

        // 获取库名称
        String catalog = conn.getCatalog();

        // 获取数据库元数据
        DatabaseMetaData metaData = conn.getMetaData();

        // %表示所有.
        ResultSet resultSet = metaData.getColumns(catalog, "%", tableName, "%");

        ResultSet primaryKeyResultSet = metaData.getPrimaryKeys(catalog, metaData.getUserName().toUpperCase(), tableName);
        List<String> primaryKeyList = new ArrayList<>();
        while (primaryKeyResultSet.next()) {
            String primaryKeyColumnName = primaryKeyResultSet.getString("COLUMN_NAME");
            primaryKeyList.add(primaryKeyColumnName);
        }

        List<Metadata> metadataList = new ArrayList<>();
        while (resultSet.next()) {
            // 字段名称
            String columnName = resultSet.getString("COLUMN_NAME");
            // 字段类型
            String columnType = resultSet.getString("TYPE_NAME");
            // 字段长度
            int columnSize = resultSet.getInt("COLUMN_SIZE");
            // 字段注释信息
            String remarks = resultSet.getString("REMARKS");
            // 字段精度：小数部分的位数
//            int digits = resultSet.getInt("DECIMAL_DIGITS");
            // 是否为空（1：可以为空，0不可以为空）
            int nullable = resultSet.getInt("NULLABLE");

            Metadata metadata = new Metadata();
            metadata.setColumnName(columnName);
            metadata.setTypeName(columnType);
            metadata.setColumnSize(columnSize);
            metadata.setRemarks(remarks);
//            metadata.setDecimalDigits(digits);
            metadata.setNullable(nullable);

            if (primaryKeyList.contains(columnName)) {
                // 是否主键：0：是；1不是
                metadata.setColumnPrimaryKey(0);
            } else {
                metadata.setColumnPrimaryKey(1);
            }

            metadataList.add(metadata);
        }

        return metadataList;
    }


    /**
     * 查询分页结果集
     *
     * @param driver      驱动
     * @param url         url
     * @param username    username
     * @param password    password
     * @param originalSql 原始sql
     * @param pageNum     pagenum
     * @param pageSize    pagesize
     * @return list
     */
    public static List<Map<String, Object>> buildPaginationDialectSqlResult(String driver,
                                                                            String url,
                                                                            String username,
                                                                            String password,
                                                                            String originalSql,
                                                                            Integer pageNum,
                                                                            Integer pageSize) {

        PreparedStatement ps = null;
        List<Map<String, Object>> resultList = new ArrayList<>();

        try {

            DataSource dataSource = getDataSource(driver, url, username, password);
            Connection conn = dataSource.getConnection();
            IPage<?> page = new Page<>(pageNum, pageSize);
            DbType dbType = getDbType(url);
            // 继承DialectFactory；获取方言分页sql方法。参数说明：buildPaginationSql->参数说明：分页，原始sql，数据库类型，驱动.
            DialectModel dialectModel = DialectFactory.buildPaginationSql(page, originalSql, dbType, null);
            // 获取方言分页sql语句.
            String dialectSql = dialectModel.getDialectSql();
            ps = conn.prepareStatement(dialectSql);
            // preparedStatement预编译，防止sql注入. TODO parameterIndex下标从1开始，x从0开始。mysql,oracle下标对应值顺序不同,待验证。
            PreparedStatement preparedStatement = getPreparedStatement(ps, dbType, pageNum, pageSize);
            ResultSet rs = preparedStatement.executeQuery();

            //获得列集
            ResultSetMetaData rsMetaData = rs.getMetaData();

            while (rs.next()) {
                Map<String, Object> resultMap = new HashMap<>(16);
                for (int j = 0; j < rsMetaData.getColumnCount(); j++) {
                    resultMap.put(rsMetaData.getColumnName(j + 1), rs.getObject(j + 1));
                }
                resultList.add(resultMap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;

    }

    public static PreparedStatement paginationDialectSqlPreparedStatement(Connection connection,
                                                                          String jdbcUrl,
                                                                          String originalSql,
                                                                          Integer pageNum,
                                                                          Integer pageSize) {
        IPage<?> page = new Page<>(pageNum, pageSize);
        DbType dbType = getDbType(jdbcUrl);
        // 继承DialectFactory；获取方言分页sql方法。参数说明：buildPaginationSql->参数说明：分页，原始sql，数据库类型，驱动.
        DialectModel dialectModel = DialectFactory.buildPaginationSql(page, originalSql.toString(), dbType, null);
        // 获取方言分页sql语句.
        PreparedStatement tmp = null;
        try {
            tmp = connection.prepareStatement(dialectModel.getDialectSql());
            return getPreparedStatement(tmp, dbType, pageNum, pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tmp;
    }

    /**
     * 传入分页参数.
     *
     * @param preparedStatement ps
     * @param dbType            dbType
     * @param pageNum           current
     * @param pageSize          size
     * @return preparedStatement
     * @throws SQLException
     */
    private static PreparedStatement getPreparedStatement(PreparedStatement preparedStatement,
                                                          DbType dbType,
                                                          Integer pageNum,
                                                          Integer pageSize) throws SQLException {
        switch (dbType) {
            case MYSQL:
                preparedStatement.setLong(1, pageNum - 1);
                preparedStatement.setLong(2, pageSize);
                return preparedStatement;
            case ORACLE:
                preparedStatement.setLong(1, pageSize);
                preparedStatement.setLong(2, pageNum - 1);
                return preparedStatement;
            default:
                throw ExceptionUtils.mpe("The Database's ISQLDialect Not Supported!", new Object[0]);
        }

    }

    //这里先只做mysql和oracle两种数据类型的校验
    public static Information getInformationFromUrl(String url) {
        if (org.apache.commons.lang3.StringUtils.isEmpty(url)) {
            return null;
        }
        char charArray[] = url.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == '\u3000') {
                charArray[i] = ' ';
            } else if (charArray[i] > '\uFF00' && charArray[i] < '\uFF5F') {
                charArray[i] = (char) (charArray[i] - 65248);
            }
        }
        url = new String(charArray);
        Information result = new Information();
        switch (getDbType(url)) {
            //oracle 有三种url写法
            case MARIADB:
                break;
            case ORACLE:
                String[] information = url.split("jdbc:oracle:thin:");
                if (information[1].startsWith("@")) {
                    //RAC处理
                } else {
                    result.setDatabaseName(information[1].substring(0, information[1].indexOf("/")).trim().toUpperCase());
                    String[] tmp = information[1].split("@");
                    if (tmp[1].contains("/")) {
                        //serviceName处理
                        String[] ipPort = tmp[1].split("/")[2].split(":");
                        result.setIp(ipPort[0]);
                        result.setPort(ipPort[1]);
                    } else {
                        //SID处理
                        String[] ipPort = tmp[1].split(":");
                        result.setIp(ipPort[0]);
                        result.setPort(ipPort[1]);
                    }
                }
                break;
            case MYSQL:
                String[] urlArr = url.split("/");
                if (urlArr.length < 3){
                    return null;
                }
                String[] ipPortArr = urlArr[2].split(":");
                result.setIp(ipPortArr[0]);
                result.setPort(ipPortArr[1]);
                if (urlArr[3].contains("?")) {
                    result.setDatabaseName(urlArr[3].split("\\?")[0]);
                } else {
                    result.setDatabaseName(urlArr[3]);
                }
                break;
            case DB2:
                break;
            case H2:
                break;
            case HSQL:
                break;
            case SQLITE:
                break;
            case POSTGRE_SQL:
                break;
            case SQL_SERVER2005:
                break;
            case SQL_SERVER:
                break;
            case DM:
                break;
            case OTHER:
                break;
        }
        return result;
    }

    /**
     * 获取数据库类型枚举
     *
     * @param jdbcUrl url
     * @return DbType
     */
    public static DbType getDbType(String jdbcUrl) {
        Assert.isFalse(StringUtils.isEmpty(jdbcUrl), "Error: The jdbcUrl is Null, Cannot read database type", new Object[0]);
        if (!jdbcUrl.contains(":mysql:") && !jdbcUrl.contains(":cobar:")) {
            if (jdbcUrl.contains(":mariadb:")) {
                return DbType.MARIADB;
            } else if (jdbcUrl.contains(":oracle:")) {
                return DbType.ORACLE;
            } else if (!jdbcUrl.contains(":sqlserver:") && !jdbcUrl.contains(":microsoft:")) {
                if (jdbcUrl.contains(":sqlserver2012:")) {
                    return DbType.SQL_SERVER;
                } else if (jdbcUrl.contains(":postgresql:")) {
                    return DbType.POSTGRE_SQL;
                } else if (jdbcUrl.contains(":hsqldb:")) {
                    return DbType.HSQL;
                } else if (jdbcUrl.contains(":db2:")) {
                    return DbType.DB2;
                } else if (jdbcUrl.contains(":sqlite:")) {
                    return DbType.SQLITE;
                } else if (jdbcUrl.contains(":h2:")) {
                    return DbType.H2;
                } else if (jdbcUrl.contains(":dm:")) {
                    return DbType.DM;
                } else {
                    log.warn("The jdbcUrl is " + jdbcUrl + ", Mybatis Plus Cannot Read Database type or The Database's Not Supported!");
                    return DbType.OTHER;
                }
            } else {
                return DbType.SQL_SERVER2005;
            }
        } else {
            return DbType.MYSQL;
        }
    }

}
