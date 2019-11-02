package com.mybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author gwh
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Items {
    private Integer id;

    private String name;

    private Float price;

    private String pic;

    private Date createtime;

    private String detail;
}