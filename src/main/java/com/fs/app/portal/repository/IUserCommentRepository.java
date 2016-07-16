package com.fs.app.portal.repository;

import java.util.List;
import java.util.Map;
import com.fs.app.portal.pojo.UserCommentPojo;

public interface IUserCommentRepository {
    Boolean addUserComment(UserCommentPojo usercomment);  
    Boolean removeUserComment(UserCommentPojo usercomment);
    List<UserCommentPojo> getUserComments(int uid,int speid,int fedid);
    List<UserCommentPojo> getUserCommentsbyUid(int uid);
    Map<String, String> getmostUserComment();//获取阅读最多的频道
    Map<String,String> getmostregUserComment();//获取订阅最多的站点
}
