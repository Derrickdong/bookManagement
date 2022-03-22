package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.UserDao;
import model.Pager;
import model.User;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}

	@Override
	public Pager<User> getAllPagerUsers() {
		return userDao.getAllPagerUsers();
	}

	@Override
	public User login(String username, String password) {
		User user = userDao.loadUserByUserName(username);
		if (user == null) throw new RuntimeException("Wrong username or password");
		if (!user.getPassword().equals(password)) throw new RuntimeException("Wrong username or password");
		if (user.getState() != 1) throw new RuntimeException("User got denied");
		return user;
	}



}
