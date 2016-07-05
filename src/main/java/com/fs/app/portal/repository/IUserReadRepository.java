package com.fs.app.portal.repository;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.fs.app.portal.pojo.BaseFeedDetailPojo;
import com.fs.app.portal.pojo.UserReadPojo;

@Transactional
public interface IUserReadRepository {
	Boolean addUserRead(UserReadPojo userred);  
	Boolean removeUserRead(UserReadPojo userred);
	Boolean updateUserRead(UserReadPojo userred);
	List<UserReadPojo> getUserReds(int uid,int speid,int fedid,int detailid);
	List<UserReadPojo> getUserRedsbyUid(int uid);
    List<BaseFeedDetailPojo> getmostReads(int index);//读的最多的文章
}
