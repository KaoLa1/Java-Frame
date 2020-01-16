package com.muye.kl.demo.entity.po;

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
public class Person {
    /**
     * 主键
     */
    private String id;
    /**
     * 姓名
     */
    @ExcelProperty("姓名")
    private String name;
    /**
     * 年龄
     */
    @ExcelProperty("年龄")
    private String age;
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
