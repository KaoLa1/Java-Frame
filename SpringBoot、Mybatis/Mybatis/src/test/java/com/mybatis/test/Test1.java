package com.mybatis.test;

import com.mybatis.entity.User;
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
 * <p>简单直接的测试</p>
 *
 * @author : gwh
 * @date : 2019-10-22 16:54
 **/
public class Test1 {
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

    @Test
    public void findUserById() throws IOException {
        //通过工厂得到sqlSession,线程不安全，所以不能放到外面;作为局部变量
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //通过sqlSession操作数据库
        //第一个参数：映射文件中statement的id,等于=namespace+"."+statement的id
        //第二个参数：指定和映射文件中所匹配的parameterType类型的参数
        //sqlSession.selectOne结果是映射文件中所匹配的resultType类型的对象
        User user = sqlSession.selectOne("test.findUserById", 24);
        System.out.println(user);
        //释放资源
        sqlSession.close();
    }


    // 根据用户名称模糊查询用户列表
    @Test
    public void findUserByNameTest() throws IOException {
        // 通过工厂得到SqlSession,线程不安全，所以不能放到外面;作为局部变量
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // list中的user和映射文件中resultType所指定的类型一致
        List<User> list = sqlSession.selectList("test.findUserByUsername", "小明");
        System.out.println(list);
        sqlSession.close();
    }
}
