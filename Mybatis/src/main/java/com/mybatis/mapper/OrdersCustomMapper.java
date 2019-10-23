package com.mybatis.mapper;

import com.mybatis.entity.OrdersCustom;
import com.mybatis.entity.User;
import com.mybatis.entity.UserQueryVo;

import java.util.List;

public interface OrdersCustomMapper {
    /**
     * 查询订单关联查询用户信息
     *
     * @return
     */
    List<OrdersCustom> findOrdersUser();

    /**
     * 根据用户名查询用户
     *
     * @return
     */
    User findUserByUsername(String username);

    /**
     *  查询用户个数
     * @param userQueryVo
     * @return
     */
    int findUserCount(UserQueryVo userQueryVo);



}
