package com.mybatis.dao;

import com.mybatis.entity.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * <h3>Mybatis</h3>
 * <p>Dao的实现类</p>
 *
 * @author : gwh
 * @date : 2019-10-22 14:13
 **/
public class UserDaoImpl implements UserDao{

    private SqlSessionFactory sqlSessionFactory;

    //通过构造方法注入
    public UserDaoImpl (SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public User findUserById(int i) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = sqlSession.selectOne("test.findUserById", 24);
        sqlSession.close();
        return user;
    }
}
