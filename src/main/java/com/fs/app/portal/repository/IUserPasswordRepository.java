package com.fs.app.portal.repository;

import com.fs.app.portal.pojo.UserPasswordPojo;

public interface IUserPasswordRepository {
    Boolean addPassword(UserPasswordPojo userpswd);  
    UserPasswordPojo getUserPassword(String mail);
    UserPasswordPojo getUserPasswordByUrl(String sendurl);
    Boolean updatePwd(Integer uid,String newPwd);
}
