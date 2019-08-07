package ioc.com;

import ioc.com.model.User;
import ioc.com.service.UserService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserServiceTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
        UserService user = (UserService) ctx.getBean("userService");

        User u = new User();
        u.setUsername("zhangsan");
        user.add(u);
    }


}
