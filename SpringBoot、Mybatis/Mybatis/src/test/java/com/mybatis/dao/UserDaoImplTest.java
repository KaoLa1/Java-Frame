package com.mybatis.dao;

import com.mybatis.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * <h3>Mybatis</h3>
 * <p>测试原始Dao方式的持久化方式</p>
 *
 * @author : gwh
 * @date : 2019-10-22 14:21
 **/
public class UserDaoImplTest {
    SqlSessionFactory sqlSessionFactory;
    @Before
    public void setUp() throws IOException {
        String resource = "SqlMapConfig.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
    }

    @Test
    public void testFindUserById(){
        UserDao userDao = new UserDaoImpl(sqlSessionFactory);
        User user = userDao.findUserById(24);
        System.out.println(user);
    }
}
