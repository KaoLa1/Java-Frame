package com.muye.kl.demo.controller;

import com.muye.kl.demo.service.PoiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;


/**
 * @author : gwh
 **/
@RestController
@RequestMapping("template")
@Api(value = "用户信息", tags = "用户信息")
public class PoiController {

    @Autowired
    private PoiService poiService;

    @PostMapping("/export")
    @ApiOperation("导出文件")
    public ResponseEntity<Integer> exportExcel(HttpServletResponse response, @RequestBody Map<String, Object> exportMsg) throws IOException, SQLException {
        return ResponseEntity.ok(poiService.exportExcel(response, exportMsg));
    }

    @PostMapping("/import/excel")
    @ApiOperation("导入文件")
    public ResponseEntity importExcel(@RequestBody Map<String, Object> importMsg) throws Exception {
        return ResponseEntity.ok(poiService.importExcel(importMsg));
    }




}
