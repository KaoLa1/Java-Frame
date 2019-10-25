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
    public void testFindOrdersUser() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrdersCustomMapper mapper = sqlSession.getMapper(OrdersCustomMapper.class);
        List<OrdersCustom> ordersUser = mapper.findOrdersUser();
        System.out.println(ordersUser);
        sqlSession.close();
    }

    //一对一，ResultMap
    @Test
    public void testFindOrdersUserResultMap() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrdersCustomMapper mapper = sqlSession.getMapper(OrdersCustomMapper.class);
        List<Orders> orders = mapper.findOrdersUserResultMap();
        System.out.println(orders);
        sqlSession.close();
    }

    //一对多
    @Test
    public void testFindOrdersAndOrderDetailResultMap() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrdersCustomMapper mapper = sqlSession.getMapper(OrdersCustomMapper.class);
        List<Orders> orders = mapper.findOrdersAndOrderDetailResultMap();
        System.out.println(orders);
        sqlSession.close();
    }

    //多对多
    @Test
    public void testFindUserAndItemsResultMap() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrdersCustomMapper mapper = sqlSession.getMapper(OrdersCustomMapper.class);
        List<User> users = mapper.findUserAndItemsResultMap();
        System.out.println(users);
        sqlSession.close();
    }

    //懒加载
    @Test
    public void testFindOrdersUserLazyLoading() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrdersCustomMapper mapper = sqlSession.getMapper(OrdersCustomMapper.class);
        //查询订单信息（单标）
        List<Orders> orderList = mapper.findOrdersUserLazyLoading();
        //遍历上边的订单列表
        for (Orders orders : orderList) {
            User user = orders.getUser();
            System.out.println(user);
        }
        sqlSession.close();
    }


    //一级缓存测试
    @Test
    public void testCache1() {
        SqlSession sqlSession = sqlSessionFactory.openSession();// 创建代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //第一次发起请求，查询id为1的用户
        User user1 = userMapper.findUserById(1);
        System.out.println(user1);

        // 如果sqlSession去执行commit操作（执行插入、更新、删除），清空SqlSession中的一级缓存，
        // 这样做的目的为了让缓存中存储的是最新的信息，避免脏读。

        // 更新user1的信息
        // user1.setUsername("测试用户22");
        // userMapper.updateUser(user1);
        // //执行commit操作去清空缓存
        // sqlSession.commit();
//        user1.setUsername("测试用户1");
//        userMapper.updateUser(user1);
        //执行commit操作，清空缓存
//        sqlSession.commit();

        //第二次发起请求，查询id为1的用户
        User user2 = userMapper.findUserById(1);
        System.out.println(user2);
        sqlSession.close();
    }

    //二级缓存测试
    @Test
    public void testCache2() {
        SqlSession sqlSession1 = sqlSessionFactory.openSession();// 创建代理对象
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        SqlSession sqlSession3 = sqlSessionFactory.openSession();

        UserMapper userMapper1 = sqlSession1.getMapper(UserMapper.class);
        //第一次发起请求，查询id为1的用户
        User user1 = userMapper1.findUserById(1);
        System.out.println(user1);
        //这里执行关闭操作，将sqlSession中的数据写到二级缓存中
        sqlSession1.close();
        UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);
        //第二次发起请求，查询id为1的用户
        User user2 = userMapper2.findUserById(1);
        System.out.println(user2);
        sqlSession2.close();

      /*  UserMapper userMapper3 = sqlSession3.getMapper(UserMapper.class);
        //第二次发起请求，查询id为1的用户
        User user3 = userMapper3.findUserById(1);
        user3.setUsername("张明志");
        userMapper3.updateUser(user3);
        //执行提交操作
        sqlSession3.commit();
        sqlSession3.close();*/

    }

}
