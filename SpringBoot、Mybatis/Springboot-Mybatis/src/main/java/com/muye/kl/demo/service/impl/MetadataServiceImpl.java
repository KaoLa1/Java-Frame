package com.muye.kl.demo.service.impl;


import com.muye.kl.demo.entity.BO.Metadata;
import com.muye.kl.demo.entity.DataSourceInfo;
import com.muye.kl.demo.service.MetadataService;
import com.muye.kl.demo.utils.RDBMSUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : gwh
 **/
@Slf4j
@Service
public class MetadataServiceImpl implements MetadataService {


    @Override
    public Boolean testConn(DataSourceInfo dataSourceInfo) {
        boolean a = false;
        Connection conn = RDBMSUtil.getConn(dataSourceInfo);
        if (conn != null) {
            a = true;
            log.info("连接数据库成功！");
        } else {
            log.info("连接数据库失败！");
        }
        //关闭连接
        RDBMSUtil.closeConn(dataSourceInfo);
        return a;
    }

    @Override
    public List<String> getAllTable(DataSourceInfo dataSourceInfo) throws SQLException {
        return RDBMSUtil.buildDatabaseMetaDataTables(dataSourceInfo);
    }


    @Override
    public Map<String, List<Metadata>> getTableInfo(DataSourceInfo dataSourceInfo) throws SQLException {
//        List<Object> tableInfoList = new ArrayList<>();
        Map<String, List<Metadata>> tableMap = new HashMap<>();
//        List<Map<String,List<Metadata>>> tableInfoList = new ArrayList<>();
        //获取所有表名
        List<String> tableList = RDBMSUtil.buildDatabaseMetaDataTables(dataSourceInfo);
        List<Metadata> columnList;
        for (String table : tableList) {
            //获取表中所有字段
            columnList = RDBMSUtil.buildTableMetadata(dataSourceInfo, table);
            tableMap.put(table, columnList);
        }
//        tableInfoList.add(tableMap);
        //关闭连接
        RDBMSUtil.closeConn(dataSourceInfo);
        return tableMap;
    }

}
