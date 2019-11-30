package com.muye.kl.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.muye.kl.entity.User;
import com.muye.kl.mapper.UserMapper;
import com.muye.kl.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : gwh
 * @date : 2019-11-26 11:29
 **/
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserMapper userMapper;

    @Override
    public PageInfo<User> query(User user, Integer pageSize, Integer pageNum) {
        //开启分页
        PageHelper.startPage(pageNum, pageSize);
        List<User> list = userMapper.query(user);
        logger.info("queryUser=> effect line count is " + list.size());
        return new PageInfo<>(list);
    }
}
