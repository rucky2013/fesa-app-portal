package com.fs.app.portal.repository;

import java.util.List;
import com.fs.app.portal.pojo.UserReadPojo;

public interface IUserReadRepository {
	
	Boolean updateUserRead(UserReadPojo userred);
	
	List<UserReadPojo> getUserReads(int newsid,int type);
}
