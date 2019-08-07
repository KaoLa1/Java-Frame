package hibernate.service;


import hibernate.beans.User;
import hibernate.dao.UserDAO;

public class UserService {
	
	public boolean valid(String username, String password) {
		UserDAO test = new UserDAO();
		User user = test.getUser("liyan");
		if(user.getPassword().equals(password)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void main(String[] args) {
		UserService service = new UserService();
		boolean login = service.valid("liyan", "ly123");
		System.out.println("验证结果："+login);
	}
}
