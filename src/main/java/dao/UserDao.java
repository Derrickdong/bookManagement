package dao;

import java.util.List;

import model.Pager;
import model.User;

public interface UserDao extends BaseDao<User> {

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

	public User loadUserByUserName(String username);
}
