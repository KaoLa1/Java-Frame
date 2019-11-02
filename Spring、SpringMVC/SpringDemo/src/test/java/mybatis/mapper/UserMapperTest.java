package mybatis.mapper;

import mybatis.pojo.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserMapperTest {

    private ApplicationContext applicationContext;

    //在setUp这个方法得到spring容器
    @Before
    public void setUp() {
        applicationContext = new ClassPathXmlApplicationContext("classpath:mybatis/applicationContext.xml");
    }

    @Test
    public void testFindUserById() {
        UserMapper userMapper = (UserMapper) applicationContext.getBean("userMapper");
        User user = userMapper.findUserById(1);
        System.out.println(user);
    }

}
