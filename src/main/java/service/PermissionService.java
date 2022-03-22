package service;

import java.util.List;

import model.Pager;
import model.Permission;

public interface PermissionService {

	public List<Permission> getAllPermissions();
	
	public Pager<Permission> getAllPagerPermissions();
	
//	public boolean isExistingResource(String resource);
}
