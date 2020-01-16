package com.muye.kl.demo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.muye.kl.demo.entity.po.Person;
import com.muye.kl.demo.mapper.PersonMapper;
import com.muye.kl.demo.service.PersonService;
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
public class PersonServiceImpl implements PersonService {

    private static final Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);

    @Autowired
    PersonMapper userMapper;

    @Override
    public PageInfo<Person> query(Person user, Integer pageSize, Integer pageNum) {
        //开启分页
        PageHelper.startPage(pageNum, pageSize);
        List<Person> list = userMapper.query(user);
        logger.info("queryPerson=> effect line count is " + list.size());
        return new PageInfo<>(list);
    }

    @Override
    public int insertBatch(List<Person> userList) {
        return userMapper.insertBatch(userList);
    }
}
