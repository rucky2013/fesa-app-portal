package com.fs.app.portal.api.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fs.app.portal.controller.BaseHandlers;
import com.fs.app.portal.entity.RenderData;
import com.fs.app.portal.pojo.UserInfoPojo;
import com.fs.app.portal.service.IUserService;

@Controller
@RequestMapping("/api/users")
public class UserApiHandlers extends BaseHandlers{

	@Autowired
	private IUserService userService;
	
	@ResponseBody
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public void login(HttpServletRequest request, HttpServletResponse response) {
		String username=request.getParameter("username").toString();
		String password=request.getParameter("password").toString();
		UserInfoPojo userinfo= userService.Login(username, password);
		RenderData result=new RenderData((userinfo==null?false:true));
		WriteJson(response,result);
	}
}
