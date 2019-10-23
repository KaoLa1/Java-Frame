package com.mybatis.dao;

import com.mybatis.entity.User;

/**
 * <h3>Mybatis</h3>
 * <p>持久层编写</p>
 *
 * @author : gwh
 * @date : 2019-10-22 14:10
 **/
public interface UserDao {
    User findUserById(int i);
}
