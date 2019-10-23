package com.mybatis.mapper;

import com.mybatis.entity.Orders;
import com.mybatis.entity.OrdersCustom;
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
 * <p>订单用户信息查询</p>
 *
 * @author : gwh
 * @date : 2019-10-23 11:09
 **/
public class OrdersMapperCustom {

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

    //一对一,resultType
  @Test
    public void testFindOrdersUser(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrdersCustomMapper mapper = sqlSession.getMapper(OrdersCustomMapper.class);
        List<OrdersCustom> ordersUser = mapper.findOrdersUser();
        System.out.println(ordersUser);
        sqlSession.close();
    }

    //一对一，ResultMap
    @Test
    public void testFindOrdersUserResultMap(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrdersCustomMapper mapper = sqlSession.getMapper(OrdersCustomMapper.class);
        List<Orders> orders = mapper.findOrdersUserResultMap();
        System.out.println(orders);
        sqlSession.close();
    }

    //一对多
    @Test
    public void testFindOrdersAndOrderDetailResultMap(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrdersCustomMapper mapper = sqlSession.getMapper(OrdersCustomMapper.class);
        List<Orders> orders = mapper.findOrdersAndOrderDetailResultMap();
        System.out.println(orders);
        sqlSession.close();
    }

    //多对多
    @Test
    public void testFindUserAndItemsResultMap(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrdersCustomMapper mapper = sqlSession.getMapper(OrdersCustomMapper.class);
        List<User> users = mapper.findUserAndItemsResultMap();
        System.out.println(users);
        sqlSession.close();
    }

}
