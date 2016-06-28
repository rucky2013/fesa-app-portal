package cn.lyf.service;

import cn.lyf.bean.userPassword;

public interface userPasswordService {
    Boolean addPassword(userPassword userpswd);  
    userPassword getUserPassword(String mail);
    userPassword getUserPasswordByUrl(String sendurl);
    Boolean updatePwd(Integer uid,String newPwd);
}
