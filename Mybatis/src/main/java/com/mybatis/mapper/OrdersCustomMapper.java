package com.mybatis.mapper;

import com.mybatis.entity.Orders;
import com.mybatis.entity.OrdersCustom;
import com.mybatis.entity.User;

import java.util.List;

public interface OrdersCustomMapper {
    /**
     * 查询订单关联查询用户信息
     *
     * @return
     */
    List<OrdersCustom> findOrdersUser();

    /**
     * 查询订单关联查询用户信息(ResultMap)
     *
     * @return
     */
    List<Orders> findOrdersUserResultMap();

    /**
     *  查询订单关联用户及订单明细
     * @return
     */
    List<Orders> findOrdersAndOrderDetailResultMap();

    /**
     * 查询用户购买商品信息
     * @return
     */
    List<User> findUserAndItemsResultMap();

    /**
     * 查询订单关联查询用户信息(懒加载)
     *
     * @return
     */
    List<Orders> findOrdersUserLazyLoading();

}
