package cn.lyf.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.lyf.bean.userFeed;

@Service
public interface userFeedService {
    Boolean addUserFeed(userFeed userfed);  
    Boolean removeUserFeed(userFeed userfed);
    List<userFeed> getUserFeeds(int uid,int speid,int fedid);
    List<userFeed> getUserFeedsbyUid(int uid);
    Map<String, String> getmostUserFeed();//获取阅读最多的频道
    Map<String,String> getmostregUserFeed();//获取订阅最多的站点
}
