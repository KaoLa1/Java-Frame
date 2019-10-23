package com.mybatis.mapper;

import com.mybatis.entity.User;
import com.mybatis.entity.UserQueryVo;

public interface UserMapper {
    /**
     * 根据用户ID查询用户
     *
     * @return
     */
    User findUserById(Integer id);

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
