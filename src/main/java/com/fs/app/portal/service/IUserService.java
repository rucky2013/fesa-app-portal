package com.fs.app.portal.service;

import com.fs.app.portal.entity.RenderData;
import com.fs.app.portal.pojo.UserInfoPojo;

public interface IUserService {

	public UserInfoPojo Login(String uname,String pwd);
	
	public RenderData Register(String uname,String pwd,String email,String phone);
	
	public RenderData ExistEmail(String email);
	
	public RenderData FindPwd(String email);
	
	public RenderData verifyExpired(String m, String u);
	
	public RenderData ResetPwd(String m,String u,String newPwd);
}
