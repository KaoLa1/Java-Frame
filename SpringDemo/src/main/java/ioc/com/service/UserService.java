package ioc.com.service;


import ioc.com.dao.UserDao;
import ioc.com.model.User;

public class UserService {
	private UserDao userdao;

	public void add(User user) {
		userdao.save(user);
	}

	// 设值注入
	public UserDao getUserdao() {
		return userdao;
	}

	public void setUserdao(UserDao userdao) {
		this.userdao = userdao;
	}

	// 构造注入
	public UserService(UserDao userdao) {
		super();
		this.userdao = userdao;
	}
	
	

}
