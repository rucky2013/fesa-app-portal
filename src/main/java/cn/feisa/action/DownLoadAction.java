package cn.feisa.action;

import com.opensymphony.xwork2.ActionSupport;

public class DownLoadAction extends ActionSupport {
	private static final long serialVersionUID = 3321845277376234101L;
	private String message;
	
	public String getMessage(){
		return message;
	}
	public String execute(){
		return "success";
	}
}
