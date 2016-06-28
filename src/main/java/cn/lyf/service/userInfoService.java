package cn.lyf.service;

import cn.lyf.bean.userInfo;
import java.util.*;

public interface userInfoService {
    Boolean addUser(userInfo userinfo);  
    userInfo getUserInfo(String name,String pswd);
    userInfo getUserInfoBymark(String name,String pswd);
    userInfo getUserEmail(String email);
    Boolean changePassword(int uid,String newpwd);
    List<userInfo> getAllUsers();
}
