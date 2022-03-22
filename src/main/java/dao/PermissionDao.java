package dao;

import java.util.List;

import model.Pager;
import model.Permission;

public interface PermissionDao extends BaseDao<Permission>{

	public List<Permission> getAllPermissions();
	
	public Pager<Permission> getAllPagerPermissions();
}
