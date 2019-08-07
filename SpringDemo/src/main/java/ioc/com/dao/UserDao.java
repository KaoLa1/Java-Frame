package ioc.com.dao;


import ioc.com.model.User;

public class UserDao {
    public void save(User user) {
        System.out.println("User save!");
    }
}
