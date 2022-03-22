package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.RoleDao;
import model.Pager;
import model.Role;

@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService{

	@Autowired
	private RoleDao roleDao;
	
	@Override
	public List<Role> getAllRoles() {
		return roleDao.getAllRoles();
	}

	@Override
	public Pager<Role> getAllPagerRoles() {
		return roleDao.getAllPagerRoles();
	}

}
