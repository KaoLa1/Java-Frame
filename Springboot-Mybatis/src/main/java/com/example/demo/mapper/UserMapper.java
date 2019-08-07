package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    List<User> findUserByName(String name);

    List<User> ListUser();

    int insertUser(User user);

    int delete(int id);

    int Update(User user);

    User findUserById(int id);

    int deleteUserById(int id);

    int updateUser(User user);

}
