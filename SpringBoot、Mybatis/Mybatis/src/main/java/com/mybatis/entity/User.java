package com.mybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private int id;
    /**
     * 用户姓名
     */
    private String username;
    /**
     * 性别
     */
    private String sex;
    /**
     * 生日
     */
    private Date birthday;
    /**
     * 地址
     */
    private String address;

    //用户创建的订单列表
    private List<Orders> ordersList;
}
