package com.muye.kl.demo.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.muye.kl.demo.entity.BO.Metadata;
import com.muye.kl.demo.entity.DataSourceInfo;
import com.muye.kl.demo.service.MetadataService;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * @describe 
 * @author gwh
 */
public class ExcelExporter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelExporter.class);

    /**
     * @param request
     * @param response
     * @throws Exception
     */
    public void export(HttpServletRequest request, HttpServletResponse response,MetadataService metadataService ,Map<String, Object> exportMsg) throws Exception {
        String dataSourceInfoObj = JSONObject.toJSON(exportMsg.get("dataSourceInfo")).toString();
        Map dataSourceInfoObjMap = JSONObject.parseObject(dataSourceInfoObj, Map.class);
        JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(dataSourceInfoObjMap));
        DataSourceInfo dataSourceInfo = JSONObject.toJavaObject(jsonObject, DataSourceInfo.class);
        Map<String, List<Metadata>> tableInfoMap = metadataService.getTableInfo(dataSourceInfo);
        setExcelHeader(response, request, "aa");
        // 创建一个工作簿
        // 处理07版本，但是适用于大数据量，导出之后数据不会占用内存
        SXSSFWorkbook workbook = new SXSSFWorkbook();

        String sheetName;
        SXSSFSheet sheet;
        String[] title = {"英文名称", "中文名称", "数据类型", "是否主键", "是否为空","说明"};
        List<String> columnList = Arrays.asList(title);
        for (Map.Entry<String, List<Metadata>> entry : tableInfoMap.entrySet()) {
            List<Metadata> metadataList = tableInfoMap.get(entry.getKey());
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
                dataList.add(String.valueOf(nullable == 0 ? false : true));
                dataList.add("");
                tableList.add(dataList);
            }
            //获取sheet名
            sheetName = entry.getKey();
            // 创建一个工作表sheet
            sheet = workbook.createSheet(sheetName);
            //获取列名头
//            headers = (List<Map<String, String>>) exportSheet.get(HEADERS);
            //初始化页签头
            initHeader(workbook, sheet, title);
            //获取各行数据
            createTableRows(tableList, columnList, sheet);
            autoAllSizeColumn(sheet, columnList.size());
//        }
        }
        exportExcel(workbook, response.getOutputStream());
    }

    /**
     * 初始化表头信息
     */
    private static void initHeader(SXSSFWorkbook sxssfWorkbook, Sheet sheet, String[] title) {
        // 创建第一行
        Row row = sheet.createRow(0);
        Cell cell;
        for (int i = 0; i < title.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(sxssfWorkbook.createCellStyle());
        }
    }


    /**
     * 设置单元格样式
     *
     * @param cell 单元格
     */

    private static void setCellStyle(Cell cell, SXSSFWorkbook sxssfWorkbook) {
        // 设置样式
        CellStyle cellStyle = sxssfWorkbook.createCellStyle();
        //cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 设置字体居中
        // 设置字体
        Font font = sxssfWorkbook.createFont();
        font.setFontName("宋体");
        // 字体加粗
        font.setBold(true);
        font.setFontHeightInPoints((short) 13);

        cellStyle.setFont(font);
        cell.setCellStyle(cellStyle);
    }

    /**
     * @param datas  数据,每一个map都是一行
     * @param header key[i]代表从map中获取keys[i]的值作为第i列的值,如果传的是null默认取表头
     */

    private static void createTableRows(List<List<String>> datas, List<String> header, Sheet sheet) {
        for (int i = 0 ; i < datas.size(); i++) {
            // 创建行(从第二行开始)
            Row row = sheet.createRow(i + 1);
            Cell cell;
            for (int j = 0; j < header.size(); j++) {
                // 单元格获取map中的key
                cell = row.createCell(j);
                cell.setCellValue(datas.get(i).get(j));
            }

        }
    }

    /**
     * 根据表头自动调整列宽度
     *
     * @param sheet
     * @param columnSzie 列数
     */
    private static void autoAllSizeColumn(SXSSFSheet sheet, int columnSzie) {
        sheet.trackAllColumnsForAutoSizing();
        for (int i = 0; i < columnSzie; i++) {
            sheet.autoSizeColumn(i);
        }
    }

    /**
     * 设置http请求报文为下载文件
     *
     * @param response
     * @param request
     * @param fileName
     * @throws UnsupportedEncodingException
     **/
    private static void setExcelHeader(HttpServletResponse response, HttpServletRequest request, String fileName)
            throws UnsupportedEncodingException {

        String name = fileName + ".xlsx";
        String userAgent = request.getHeader("User-Agent");
        if (userAgent.contains("Firefox")) {
            name = new String(name.getBytes("UTF-8"), "ISO8859-1");
        } else {
            name = URLEncoder.encode(name, "UTF-8");
        }
        response.addHeader("Content-Disposition", "attachment; filename=\"" + name + "\"");
        response.setContentType("application/octet-stream" + ";charset=" + "UTF-8");
        response.setHeader("Accept-Ranges", "bytes");
    }

    /**
     * 将数据写出到excel中
     *
     * @param outputStream
     */
    private static void exportExcel(SXSSFWorkbook workbook, OutputStream outputStream) {
        try {
            workbook.write(outputStream);
        } catch (IOException e) {
            LOGGER.error(" exportExcel error", e);
        } finally {
            IOUtils.closeQuietly(outputStream);
        }
    }
}