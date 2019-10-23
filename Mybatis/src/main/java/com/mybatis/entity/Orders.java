package com.mybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * <h3>Mybatis</h3>
 * <p>订单表</p>
 *
 * @author : gwh
 * @date : 2019-10-23 10:22
 **/

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    private  Integer id;

    private Integer userId;

    private String number;

    private Date createTime;

    private String note;

    /**
     * 用户信息
     */
    private User user;

    /**
     * 订单明细
     */
    private List<Orderdetail> orderDetails;
}
