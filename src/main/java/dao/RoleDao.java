package dao;

import java.util.List;

import model.Pager;
import model.Role;

public interface RoleDao extends BaseDao<Role>{

	/**
	 * 获取所有角色信息
	 * @return
	 */
	public List<Role> getAllRoles();
	
	public Pager<Role> getAllPagerRoles();
}
