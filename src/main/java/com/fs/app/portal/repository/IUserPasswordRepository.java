package com.fs.app.portal.repository;

import org.springframework.transaction.annotation.Transactional;

import com.fs.app.portal.pojo.UserPasswordPojo;

@Transactional
public interface IUserPasswordRepository {
    Boolean addPassword(UserPasswordPojo userpswd);  
    UserPasswordPojo getUserPassword(String mail);
    UserPasswordPojo getUserPasswordByUrl(String sendurl);
    Boolean updatePwd(Integer uid,String newPwd);
}
