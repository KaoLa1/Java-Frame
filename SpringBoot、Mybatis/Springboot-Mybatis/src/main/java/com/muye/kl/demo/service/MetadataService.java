package com.muye.kl.demo.service;


import com.muye.kl.demo.entity.BO.Metadata;
import com.muye.kl.demo.entity.DataSourceInfo;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author gwh
 */
public interface MetadataService {


    /**
     * 测试数据源连接
     *
     * @param dataSourceInfo
     * @return
     */
    Boolean testConn(DataSourceInfo dataSourceInfo);

    /**
     * 获取数据库下所有的表名
     * @return
     */
    List<String> getAllTable(DataSourceInfo dataSourceInfo) throws SQLException;


    /**
     * 获取表相关信息
     *
     * @param dataSourceInfo
     * @return
     * @throws SQLException
     */
    Map<String,List<Metadata>> getTableInfo(DataSourceInfo dataSourceInfo) throws SQLException;

}
