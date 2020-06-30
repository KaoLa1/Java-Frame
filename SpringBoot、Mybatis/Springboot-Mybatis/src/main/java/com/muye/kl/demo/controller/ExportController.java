package com.muye.kl.demo.controller;

import com.muye.kl.demo.service.ExportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

/**
 * @author : gwh
 * @date : 2020-02-19 11:44
 **/
@RestController
@RequestMapping("export")
@Api(value = "导出到Excel", tags = "导出到Excel")
public class ExportController {

    @Autowired
    ExportService exportService;

    @PostMapping(value = "/export/excel", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ApiOperation("导出文件")
    public void exportExcel(HttpServletResponse response, @RequestBody Map<String, Object> exportMsg) throws IOException, SQLException {
        exportService.exportExcel(response, exportMsg);
    }

    @PostMapping(value = "/export/excel1", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ApiOperation("导出文件")
    public void exportExcel1(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String, Object> exportMsg) throws Exception {
        exportService.export(request, response, exportMsg);
    }

}
