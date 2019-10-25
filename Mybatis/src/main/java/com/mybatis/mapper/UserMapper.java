package com.mybatis.mapper;

import com.mybatis.entity.User;
import com.mybatis.entity.UserCustom;
import com.mybatis.entity.UserQueryVo;

import java.util.List;

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
     * 查询用户个数
     *
     * @param userQueryVo
     * @return
     */
    int findUserCount(UserQueryVo userQueryVo);


    /**
     * 用户信息综合查询
     *
     * @param userQueryVo
     * @return
     */
    List<UserCustom> findUserList(UserQueryVo userQueryVo);


    /**
     * 更新用户
     * @param user
     * @throws Exception
     */
    void updateUser(User user);


}
