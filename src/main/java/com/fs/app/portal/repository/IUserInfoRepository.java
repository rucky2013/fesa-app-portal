package com.fs.app.portal.repository;

import java.util.*;

import com.fs.app.portal.pojo.UserInfoPojo;

public interface IUserInfoRepository {
    Boolean addUser(UserInfoPojo userinfo);  
    UserInfoPojo getUserInfo(String name,String pswd);
    UserInfoPojo getUserInfoBymark(String name,String pswd);
    UserInfoPojo getUserEmail(String email);
    Boolean changePassword(int uid,String newpwd);
    List<UserInfoPojo> getAllUsers();
}
