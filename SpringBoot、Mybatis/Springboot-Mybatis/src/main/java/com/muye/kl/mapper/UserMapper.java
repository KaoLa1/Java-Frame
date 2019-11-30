package com.muye.kl.mapper;


import com.muye.kl.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : gwh
 * @date : 2019-11-26 11:31
 **/
@Mapper
public interface UserMapper {

    /**
     * 查询所有的User
     * @param user
     * @return
     */
    List<User> query(@Param("user") User user);

    User findById(String id);
}
