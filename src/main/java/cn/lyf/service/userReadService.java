package cn.lyf.service;

import java.util.List;
import cn.lyf.bean.baseFeedDetail;
import cn.lyf.bean.userRead;

public interface userReadService {
	Boolean addUserRead(userRead userred);  
	Boolean removeUserRead(userRead userred);
	Boolean updateUserRead(userRead userred);
	List<userRead> getUserReds(int uid,int speid,int fedid,int detailid);
	List<userRead> getUserRedsbyUid(int uid);
    List<baseFeedDetail> getmostReads(int index);//读的最多的文章
}
