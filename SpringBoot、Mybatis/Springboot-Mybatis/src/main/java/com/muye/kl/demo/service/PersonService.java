package com.muye.kl.demo.service;

import com.github.pagehelper.PageInfo;
import com.muye.kl.demo.entity.po.Person;

import java.util.List;

/**
 * @author : gwh
 * @date : 2019-11-26 11:28
 **/
public interface PersonService {

    /**
     * 查询用户列表List
     * @param person
     * @param pageSize
     * @param pageNum
     * @return
     */
    PageInfo<Person> query(Person person, Integer pageSize, Integer pageNum);

    /**
     * 批量插入
     * @param userList
     * @return
     */
    int insertBatch(List<Person> userList);
}
