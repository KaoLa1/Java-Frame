package com.muye.kl.demo.entity.BO;

import lombok.*;

import java.io.Serializable;

/**
 * <p>
 *     元数据
 * </p>
 *
 * @author : wdj
 * @version 1.0
 * @date : 2019/12/21 9:16
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Metadata implements Serializable  {


    private static final long serialVersionUID = -8816119880600979078L;

    /**
     * 主键：// 是否主键：0：是；1不是
     */
    private int columnPrimaryKey;

    /**
     * 字段精度：小数部分的位数
     */
    private Integer decimalDigits;
    /**
     * 列名
     */
    private String columnName;
    /**
     * 字段类型名称
     */
    private String typeName;
    /**
     * 字段长度
     */
    private Integer columnSize;
    /**
     * 能否为空（1：可以为空，0不可以为空）
     */
    private Integer nullable;
    /**
     *  字段注释信息
     */
    private String remarks;

}
