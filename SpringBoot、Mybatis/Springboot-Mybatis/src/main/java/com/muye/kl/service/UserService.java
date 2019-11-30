package com.muye.kl.service;

import com.github.pagehelper.PageInfo;
import com.muye.kl.entity.User;

import java.util.List;

/**
 * @author : gwh
 * @date : 2019-11-26 11:28
 **/
public interface UserService {

    /**
     * 查询用户列表List
     * @param user
     * @param pageSize
     * @param pageNum
     * @return
     */
    PageInfo<User> query(User user, Integer pageSize, Integer pageNum);
}
