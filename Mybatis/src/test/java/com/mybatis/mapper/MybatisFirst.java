package com.mybatis.mapper;

import com.mybatis.entity.User;
import com.mybatis.entity.UserCustom;
import com.mybatis.entity.UserQueryVo;
import com.mybatis.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * <h3>Mybatis</h3>
 * <p>Mybatis的测试类</p>
 *
 * @author : gwh
 * @date : 2019-10-20 17:32
 **/
public class MybatisFirst {

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void setUp() throws IOException {
        //mybatis配置文件
        String resource = "SqlMapConfig.xml";
        // 得到配置文件流
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //创建会话工厂
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    //测试Mapper方式
    @Test
    public void testFindUserList() {
        // 通过工厂得到SqlSession,线程不安全，所以不能放到外面;作为局部变量
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.findUserById(24);
        System.out.println(user);
    }

    @Test
    public void testFindUserCount(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        UserQueryVo userQueryVo = new UserQueryVo();
        UserCustom userCustom = new UserCustom();
        userCustom.setSex("1");
        userCustom.setUsername("张三丰");
        userQueryVo.setUserCustom(userCustom);
        int userCount = userMapper.findUserCount(userQueryVo);
        System.out.println(userCount);
    }


}
