package com.muye.kl.demo.utils.excel;

import com.alibaba.excel.EasyExcel;
import com.muye.kl.demo.entity.po.Person;
import com.muye.kl.demo.utils.DataUtils;
import org.junit.Test;

import java.io.*;
import java.util.List;

/**
 * @author : gwh
 * @date : 2019-11-26 17:56
 **/
public class ExcelUtilTest {

    /**
     * easyexcel 写入Excel
     */
    @Test
    public void simpleWrite() {
        // 写法1
        String path = "H:\\TestSpace\\My_Test\\";
        String extend = System.currentTimeMillis() + ".xlsx";
        String fileName = path+extend;
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        List<Person> data = DataUtils.data();
        EasyExcel.write(fileName, Person.class).sheet("模板").doWrite(data);
    }

    /**
     * 以流的方式写入Excel
     * @throws IOException
     */
    @Test
    public void writeWithStream() throws IOException {
        File file = new File("H:\\TestSpace\\My_Test\\streamExcel.xls");
        String userxlsinfo = "序号\t用户ID\t姓名\t手机\t留言信息";
        FileOutputStream fileOutputStream  = new FileOutputStream(file);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
        // 创建表头
        String sheader = userxlsinfo;
        sheader += "\r\n";
        bufferedWriter.write(sheader);
        for(int i = 0;i<5;i++){
            StringBuffer mess = new StringBuffer();
            mess.append((i + 1) + "\t");
            mess.append((i + 1) + "\t");
            mess.append((i + 1) + "\t");
            mess.append((i + 1) + "\t");
            mess.append((i + 1) + "\t");

            mess.append("\r\n");
            bufferedWriter.write(mess.toString());

        }
        bufferedWriter.close();
        outputStreamWriter.close();
        fileOutputStream.close();
        }


}
