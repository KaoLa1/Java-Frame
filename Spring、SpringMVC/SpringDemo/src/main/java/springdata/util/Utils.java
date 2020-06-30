package springdata.util;

import springdata.domain.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gwh
 */
public class Utils {

    /**
     * 修改数据
     * @param jdbcTemplate
     */
    public static void updateData(JdbcTemplate jdbcTemplate) {
        String sql = "update user set name = ?,deptid = ? where id = ?";
        jdbcTemplate.update(sql, new Object[]{"gaoweihua", 2, 1});
    }


    /**
     * 删除数据
     * @param jdbcTemplate
     */
    public static void deleteData(JdbcTemplate jdbcTemplate) {
        String sql = "delete from user where id =?";
        jdbcTemplate.update(sql, 1);
    }

    /**
     * 插入数据
     */
    public static void insertData(JdbcTemplate jdbcTemplate) {
        String sql = "insert into user (id,name,deptid) values (?,?,?)";
        jdbcTemplate.update(sql, new Object[]{3, "hua", 33});
    }

    /**
     * 批量插入数据
     * @param jdbcTemplate
     */
    public static void batchInsertDate(JdbcTemplate jdbcTemplate){
        String sql = "insert into user (id,name,deptid) values (?,?,?)";
        List<Object[]> batchArgs = new ArrayList<Object[]>();
        batchArgs.add(new Object[]{1, "zhao", 11});
        batchArgs.add(new Object[]{2, "qian", 22});
        batchArgs.add(new Object[]{4, "sun", 44});
        jdbcTemplate.batchUpdate(sql, batchArgs);
    }

    //读取单个对象
    //使用BeanProperytRowMapper要求sql数据查询出来的列和实体属性需要一一对应。如果数据中列明和属性名不一致，在sql语句中需要用as重新取一个别名
    public static void query(JdbcTemplate jdbcTemplate){
        String sql="select id,name,deptid from user where id=?";
        RowMapper<User> rm = new BeanPropertyRowMapper<User>(User.class);
        User user = jdbcTemplate.queryForObject(sql, rm, 2);
        System.out.println(user);
    }

    public static void queryMany(JdbcTemplate jdbcTemplate){
        String sql="select id,name,deptid from user";
        RowMapper<User> mapper = new BeanPropertyRowMapper<User>(User.class);
        List<User> users = jdbcTemplate.query(sql, mapper);
        for(User user:users){
            System.out.println(user);
        }
    }





}
