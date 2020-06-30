package springdata;

import springdata.Dao.UserDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author gwh
 */
public class App {
    public static void main(String[] args) {
        //启动IOC容器
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        //获取IOC中JdbcTemplate中的容器
//        JdbcTemplate jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");
//        Utils.insertData(jdbcTemplate);
//        Utils.batchInsertDate(jdbcTemplate);
//        Utils.query(jdbcTemplate);
//        Utils.queryMany(jdbcTemplate);
        UserDao userDao = (UserDao) ctx.getBean("userDao");
        System.out.println(userDao.get(2));
    }

}
