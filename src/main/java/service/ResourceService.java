package service;

import java.util.List;

import model.Resource;

public interface ResourceService extends BaseService<Resource>{

	void initPathes(List<String> pathes);
}
