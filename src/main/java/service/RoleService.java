package service;

import java.util.List;

import model.Pager;
import model.Role;

public interface RoleService extends BaseService<Role>{

	public List<Role> getAllRoles();
	
	public Pager<Role> getAllPagerRoles();
}
