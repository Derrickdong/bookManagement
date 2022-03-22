package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import dao.ResourceDao;
import model.Resource;

public class ResourceServiceImpl extends BaseServiceImpl<Resource> implements ResourceService{

	@Autowired
	private ResourceDao resourceDao;
	
	@Override
	public void initPathes(List<String> pathes) {
		int resCount = 0;
		for(String path:pathes) {
			resCount = resourceDao.selectCountResByPath(path);
			if(resCount == 0) {
				this.add(new Resource(path));
			}
		}
	}
}
