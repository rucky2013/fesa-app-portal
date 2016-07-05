package com.fs.app.portal.repository;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.fs.app.portal.pojo.UserFeedPojo;

@Transactional
public interface IUserFeedRepository {
    Boolean addUserFeed(UserFeedPojo userfed);  
    Boolean removeUserFeed(UserFeedPojo userfed);
    List<UserFeedPojo> getUserFeeds(int uid,int speid,int fedid);
    List<UserFeedPojo> getUserFeedsbyUid(int uid);
    Map<String, String> getmostUserFeed();//获取阅读最多的频道
    Map<String,String> getmostregUserFeed();//获取订阅最多的站点
}
