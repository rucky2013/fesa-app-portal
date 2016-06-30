package com.fs.app.portal.service;

public interface IEmailService {

	public void RegisterMessage(String to_mail,String user_name);
	
	public void FindPassWord(String to_mail);
}
