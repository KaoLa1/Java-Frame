package com.muye.kl.demo.controller;

import com.muye.kl.demo.entity.DataSourceInfo;
import com.muye.kl.demo.service.MetadataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

/**
 * @author : gwh
 * @date : 2020-01-07 10:44
 **/
@RestController
@RequestMapping("metadata")
@Api(value = "元数据信息获取",tags = "元数据信息获取")
public class MetadataController {

    @Autowired
    MetadataService metadataService;

    @ApiOperation("测试连接数据库")
    @PostMapping("/testConn")
    public ResponseEntity<Boolean> testConn(DataSourceInfo dataSourceInfo) {
        return ResponseEntity.ok(metadataService.testConn(dataSourceInfo));
    }

    @ApiOperation("获取数据库下所有表名")
    @PostMapping("/getAllTable")
    public ResponseEntity<Object> getAllTable(DataSourceInfo dataSourceInfo) throws SQLException {
        return ResponseEntity.ok(metadataService.getAllTable(dataSourceInfo));
    }

    @ApiOperation("获取表的详细信息")
    @PostMapping("/getTableInfo")
    public ResponseEntity<Object> getTableInfo(DataSourceInfo dataSourceInfo) throws SQLException {
        return ResponseEntity.ok(metadataService.getTableInfo(dataSourceInfo));
    }


}
