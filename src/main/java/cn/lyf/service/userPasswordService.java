package cn.lyf.service;

import org.springframework.stereotype.Service;
import cn.lyf.bean.userPassword;

@Service
public interface userPasswordService {
    Boolean addPassword(userPassword userpswd);  
    userPassword getUserPassword(String mail);
    userPassword getUserPasswordByUrl(String sendurl);
    Boolean updatePwd(Integer uid,String newPwd);
}
