package com.muye.kl.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bonc.industry.core.exception.IndustryException;
import com.muye.kl.demo.entity.DataSourceInfo;
import com.muye.kl.demo.service.PoiService;
import com.muye.kl.demo.utils.DateUtils;
import com.muye.kl.demo.utils.RDBMSUtil;
import com.muye.kl.demo.utils.StringUtils;
import com.muye.kl.demo.utils.excel.ReadExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * @author : gwh
 * @date : 2020-01-06 15:07
 **/
@Slf4j
@Service
public class PoiServiceImpl implements PoiService {

    @Override
    public int importExcel(Map<String, Object> importMsg) throws SQLException {
        Connection conn = null;
        try {
            String filePath = importMsg.get("filePath").toString();
            String tableName = importMsg.get("tableName").toString();
            String dataSourceInfoObj = JSONObject.toJSON(importMsg.get("dataSourceInfo")).toString();
            Map dataSourceInfoObjMap = JSONObject.parseObject(dataSourceInfoObj, Map.class);
            JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(dataSourceInfoObjMap));
            DataSourceInfo dataSourceInfo = JSONObject.toJavaObject(jsonObject, DataSourceInfo.class);
            conn = RDBMSUtil.getConn(dataSourceInfo);
            //读取Excel中的数据，第1行为表头，第二行为数据
            List<List<String>> excelDataList = ReadExcelUtil.readExcelInfo(filePath);
            List<String> lists = excelDataList.subList(0, 1).get(0);
            List<String> headList = new ArrayList<>();
            List<String> tempList = new ArrayList<>();
            for (int i = 0; i < lists.size(); i++) {
                headList.add(lists.get(i));
                tempList.add("?");
            }
            String headers = StringUtils.asString(headList);
            String tempString = StringUtils.asString(tempList);
            String insetSql = "insert into " + tableName + "(" + headers + ") values( " + tempString + " ) ";
            PreparedStatement ps = conn.prepareStatement(insetSql);
            //从第二行开始取数
            for (int i = 1; i < excelDataList.size(); i++) {
                // 每一行,数据按列存放
                for (int j = 0; j < headList.size(); j++) {
                    ps.setObject(j + 1, excelDataList.get(i).get(j));
                }
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (Exception e) {
            throw new IndustryException("导入Excel失败" + e.getCause());
        } finally {
            conn.close();
        }
        return 1;
    }


    @Override
    public int exportExcel(HttpServletResponse response, Map<String, Object> exportMsg) throws IOException, SQLException {
        boolean flag = (boolean) exportMsg.get("flag");
        String tableInfo = JSONObject.toJSON(exportMsg.get("tableInfo")).toString();
        String dataSourceInfoObj = JSONObject.toJSON(exportMsg.get("dataSourceInfo")).toString();
        Map tableInfoMap = JSONObject.parseObject(tableInfo, Map.class);
        Map dataSourceInfoObjMap = JSONObject.parseObject(dataSourceInfoObj, Map.class);
        JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(dataSourceInfoObjMap));
        DataSourceInfo dataSourceInfo = JSONObject.toJavaObject(jsonObject, DataSourceInfo.class);
        ResultSet rs = null;
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
        try {
            Connection conn = RDBMSUtil.getConn(dataSourceInfo);
            //创建statement类对象，用来执行SQL语句
            Statement statement = conn.createStatement();
            for (Object key : tableInfoMap.keySet()) {
                String sql = "select " + tableInfoMap.get(key).toString() + " from " + key.toString();
                String headers = tableInfoMap.get(key).toString().replace(",", "\t");
                String fileName = key.toString() + "_" + DateUtils.dateToString(new Date(), DateUtils.YYYYMMDDHHMMSS);
                File file = new File(fileName + ".xls");
                fos = new FileOutputStream(file);
                osw = new OutputStreamWriter(fos, "GB2312");
                bw = new BufferedWriter(osw);
                //创建表头
                bw.write(headers + "\r\n");
                rs = statement.executeQuery(sql);
                if (flag == true) {
                    String[] columns = tableInfoMap.get(key).toString().split(",");
                    List<String> columnList = Arrays.asList(columns);
                    StringBuffer stringBuffer = new StringBuffer();
                    while (rs.next()) {
                        for (String column : columnList) {
                            stringBuffer.append(rs.getString(column) + "\t");
                        }
                        stringBuffer.append("\r\n");
                        bw.write(stringBuffer.toString());
                        //清空StringBuffer
                        stringBuffer.setLength(0);
                    }
                } else {
                    bw.write(" ");
                    log.info("---此处用于导出空模板---");
                }
            }
        } catch (Exception e) {
            throw new IndustryException("导出失败" + e.getCause());
        } finally {
            bw.close();
            osw.close();
            fos.close();
            rs.close();
            RDBMSUtil.closeConn(dataSourceInfo);
        }
        return 1;
    }

}
