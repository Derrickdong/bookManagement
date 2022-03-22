package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.PermissionDao;
import model.Pager;
import model.Permission;

@Service("permissionService")
public class PermissionServiceImpl extends BaseServiceImpl<Permission> implements PermissionService{

	@Autowired
	private PermissionDao permissionDao;
	
	@Override
	public List<Permission> getAllPermissions() {
		return permissionDao.getAllPermissions();
	}

	@Override
	public Pager<Permission> getAllPagerPermissions() {
		return permissionDao.getAllPagerPermissions();
	}

}
