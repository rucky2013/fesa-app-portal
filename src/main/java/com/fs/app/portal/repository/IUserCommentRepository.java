package com.fs.app.portal.repository;

import java.util.List;
import com.fs.app.portal.pojo.UserCommentPojo;

public interface IUserCommentRepository {
	
    Boolean updateUserComment(UserCommentPojo usercomment);
    
    List<UserCommentPojo> getUserComments(int newsid);
}
