package com.muye.kl.demo.mapper;


import com.muye.kl.demo.entity.po.Person;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : gwh
 * @date : 2019-11-26 11:31
 **/
@Mapper
@Repository
public interface PersonMapper {

    /**
     * 查询所有的Person
     * @param user
     * @return
     */
    List<Person> query(@Param("user") Person user);

    /**
     * 根据Id查询详细信息
     * @param id
     * @return
     */
    Person findById(String id);

    /**
     * 批量插入
     *
     * @param userList
     * @return
     */
    int insertBatch(List<Person> userList);
}
