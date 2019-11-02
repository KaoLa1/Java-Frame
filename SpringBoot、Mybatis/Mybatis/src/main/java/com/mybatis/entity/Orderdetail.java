package com.mybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author gwh
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Orderdetail {
    private Integer id;

    private Integer ordersId;

    private Integer itemsId;

    private Integer itemsNum;

    /**
     * 明细对应的商品信息
     */
    private Items items;

}