package dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import model.Pager;
import model.Permission;

@Repository("permissionDao")
public class PermissionDaoImpl extends BaseDaoImpl<Permission> implements PermissionDao{

	@Override
	public List<Permission> getAllPermissions() {
		String hql = "from Permission";
		return super.list(hql, null, null);
	}

	@Override
	public Pager<Permission> getAllPagerPermissions() {
		String hql = "from Permission";
		return super.find(hql, null, null);
	}

}
