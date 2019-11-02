package ioc.dao;


import ioc.model.User;

public class UserDao {
    public void save(User user) {
        System.out.println("User save!");
    }
}
