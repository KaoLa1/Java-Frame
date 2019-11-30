package com.muye.kl.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : gwh
 * @date : 2019-11-26 11:11
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    /**
     * 主键
     */
    private int id;
    /**
     * 姓名
     */
    @ExcelProperty("姓名")
    private String name;
    /**
     * 年龄
     */
    @ExcelProperty("年龄")
    private int age;
    /**
     * 性别
     */
    @ExcelProperty("性别")
    private String sex;
    /**
     * 地址
     */
    @ExcelProperty("地址")
    private String addr;

}
