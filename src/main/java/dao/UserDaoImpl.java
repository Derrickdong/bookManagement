package dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import model.Pager;
import model.User;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao{

	@Override
	public List<User> getAllUsers() {
		String hql = "from User";
		return super.list(hql, null, null);
	}

	@Override
	public Pager<User> getAllPagerUsers() {
		String hqlString = "from User";
		return find(hqlString, null, null);
	}

	@Override
	public User loadUserByUserName(String username) {
		String hql = "select u from User u where u.username=?";
		return (User) super.queryByHql(hql, null, null);
	}

}
