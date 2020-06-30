package com.muye.kl.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.muye.kl.demo.entity.BO.Metadata;
import com.muye.kl.demo.entity.DataSourceInfo;
import com.muye.kl.demo.service.ExportService;
import com.muye.kl.demo.service.MetadataService;
import com.muye.kl.demo.utils.DateUtils;
import com.muye.kl.demo.utils.ExcelExporter;
import com.muye.kl.demo.utils.RDBMSUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * @author : gwh
 * @date : 2020-02-19 10:48
 **/
@Slf4j
@Service
public class ExportServiceImpl implements ExportService {

    @Autowired
    MetadataService metadataService;

    @Override
    public void exportExcel(HttpServletResponse response, Map<String, Object> exportMsg) throws IOException, SQLException {
        String dataSourceInfoObj = JSONObject.toJSON(exportMsg.get("dataSourceInfo")).toString();
        Map dataSourceInfoObjMap = JSONObject.parseObject(dataSourceInfoObj, Map.class);
        JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(dataSourceInfoObjMap));
        DataSourceInfo dataSourceInfo = JSONObject.toJavaObject(jsonObject, DataSourceInfo.class);
        Map<String, List<Metadata>> tableInfoMap = metadataService.getTableInfo(dataSourceInfo);
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        OutputStream output = null;
        try {
            int n= 0;
            for (String key : tableInfoMap.keySet()) {
                workbook.createSheet(key);
                SXSSFSheet sheet = workbook.getSheetAt(n);
                n++;
                CellStyle style = workbook.createCellStyle();
                Row row = sheet.createRow(0);
                //声明列对象
                String[] title = {"英文名称", "中文名称", "数据类型", "是否主键", "是否为空"};
                Cell cell;
                for (int i = 0; i < title.length; i++) {
                    cell = row.createCell(i);
                    cell.setCellValue(title[i]);
                    cell.setCellStyle(style);
                }
                List<String> columnList = Arrays.asList(title);
                List<Metadata> metadataList = tableInfoMap.get(key);
                List<List<String>> tableList = new ArrayList<>();
                for (Metadata metadata : metadataList) {
                    List<String> dataList = new ArrayList<>();
                    String columnName = metadata.getColumnName().toUpperCase();
                    String remarks = metadata.getRemarks();
                    String typeName = metadata.getTypeName();
                    Integer columnSize = metadata.getColumnSize();
                    String dataType = typeName + "(" + columnSize + ")";
                    int columnPrimaryKey = metadata.getColumnPrimaryKey();
                    int nullable = metadata.getNullable().intValue();
                    dataList.add(columnName);
                    dataList.add(remarks);
                    dataList.add(dataType);
                    dataList.add(String.valueOf(columnPrimaryKey == 0 ? true : false));
                    dataList.add(String.valueOf(nullable == 0 ? true : false));
                    tableList.add(dataList);
                }
                int i = 0;
                for (int m = 0; m < tableList.size(); m++) {
                    row = sheet.createRow(i + 1);
                    for (int j = 0; j < columnList.size(); j++) {
                        row.createCell(j).setCellValue(tableList.get(m).get(j));
                    }
                    i++;
                }
                output = response.getOutputStream();
                response.reset();
                response.setHeader("Access-Control-Allow-Origin", "*");
                response.setHeader("Cache-Control", "no-cache");
                response.setContentType("application/octet-stream");
                response.addHeader("Content-Disposition", "attachment;filename=" + "hh" + ".xlsx");
                workbook.write(output);
                output.flush();
            }

        } catch (Exception e) {
            log.error("...");
        } finally {
            output.close();
        }
    }

    @Override
    public void export(HttpServletRequest request, HttpServletResponse response, Map<String, Object> exportMsg) throws Exception {
        ExcelExporter excelExporter = new ExcelExporter();
        excelExporter.export(request, response,metadataService ,exportMsg);
    }
}
