package com.muye.kl.demo.utils.excel;

import com.alibaba.excel.event.AbstractIgnoreExceptionReadListener;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * @author : gwh
 * @date : 2020-01-14 17:39
 **/
@Slf4j
public class ReadExcel {
    private BufferedReader bReader;

    public static void main(String[] args) {
        ReadExcel readExcel = new ReadExcel();
        readExcel.execute("H:\\TestSpace\\My_Test\\My_Test1574818080068.xlsx");
    }


    /**
     * 执行文件入口
     */
    public  void execute(String path) {
        try {
            Long startTime = System.currentTimeMillis();
            log.info("------开始执行定时任务，时间=" + startTime);
            readExcel(path);
            Long endTime = System.currentTimeMillis();
            log.info("------结束定时任务，时间=" + endTime + "---耗时="
                    + (endTime - startTime));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取csv并处理数据
     * @param path
     * @throws Exception
     */
    private void readExcel(String path) throws Exception {
        File file = new File(path);
        try {
            bReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "gbk"));
            String line = "";
            //忽略第一行标题
            for (int i = 0; i < 1; i++) {
                line = bReader.readLine();
            }
            while ((line = bReader.readLine()) != null) {
                if (line.trim() != "") {
                    //分割开来的即是对应的每个单元格，注意空的情况
                    String[] result = line.split(",");
                }
            }
        } finally {
            if (bReader != null) {
                bReader.close();
            }
        }
    }
}
