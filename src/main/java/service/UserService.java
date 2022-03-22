package service;

import java.util.List;

import javax.naming.ldap.PagedResultsControl;

import model.Pager;
import model.User;

public interface UserService extends BaseService<User>{

	/**
	 * 获取所有用户，不支持分页
	 * @return
	 */
	public List<User> getAllUsers();
	
	/**
	 * 获取所有用户，支持分页
	 * @return
	 */
	public Pager<User> getAllPagerUsers();

	public User login(String username, String password);
}
