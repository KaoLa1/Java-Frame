package com.muye.kl.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.muye.kl.entity.User;
import org.junit.Test;

import java.util.List;

/**
 * @author : gwh
 * @date : 2019-11-26 17:56
 **/
public class ExcleUtil {
    @Test
    public void simpleWrite() {
        // 写法1
        String path = "H:\\TestSpace\\My_Test\\";
        String extention = System.currentTimeMillis() + ".xlsx";
        String fileName = path+extention;
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        List<User> data = DataUtils.data();
        EasyExcel.write(fileName, User.class).sheet("模板").doWrite(data);
    }
}
