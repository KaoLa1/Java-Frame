package ioc;

import ioc.model.User;
import ioc.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author gwh
 */
public class UserServiceTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
        UserService user = (UserService) ctx.getBean("userService");

        User u = new User();
        u.setUsername("zhangsan");
        user.add(u);
    }


}
