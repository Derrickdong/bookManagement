package dao;

import model.Resource;

public interface ResourceDao{

	int selectCountResByPath(String path);

}
