package ioc.service;


import ioc.dao.UserDao;
import ioc.model.User;

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
