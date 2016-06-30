package com.fs.app.portal.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeHandlers extends BaseHandlers{

	@RequestMapping(value="/main",method=RequestMethod.GET)
	public String homepage(HttpServletRequest request, HttpServletResponse response){
		return "/front/main";
	}
	
	@RequestMapping(value="/person",method=RequestMethod.GET)
	public String person(HttpServletRequest request, HttpServletResponse response){
		return "/front/person";
	}
	
	@RequestMapping(value="/feedmanage",method=RequestMethod.GET)
	public String feedmanage(HttpServletRequest request, HttpServletResponse response){
		return "/front/feedmanage";
	}
	
	@RequestMapping(value="/reader",method=RequestMethod.GET)
	public String reader(HttpServletRequest request, HttpServletResponse response){
		return "/front/reader";
	}
	
	@RequestMapping(value="/download",method=RequestMethod.GET)
	public String download(HttpServletRequest request, HttpServletResponse response){
		return "/front/download";
	}
	
	@RequestMapping(value="/about",method=RequestMethod.GET)
	public String description(HttpServletRequest request, HttpServletResponse response){
		return "/front/about";
	}
	
	//站点相关
	@RequestMapping(value="/feeddescription",method=RequestMethod.GET)
	public String feeddescription(HttpServletRequest request, HttpServletResponse response){
		return "/front/feeddescription";
	}
	
	@RequestMapping(value="/feeddetail",method=RequestMethod.GET)
	public String feeddetail(HttpServletRequest request, HttpServletResponse response){
		return "/front/feeddetail";
	}
	
	//登陆相关
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(HttpServletRequest request, HttpServletResponse response){
		return "/front/login";
	}
	
	@RequestMapping(value="/register",method=RequestMethod.GET)
	public String register(HttpServletRequest request, HttpServletResponse response){
		return "/front/register";
	}
	
	@RequestMapping(value="/missingpwd",method=RequestMethod.GET)
	public String missingpwd(HttpServletRequest request, HttpServletResponse response){
		return "/front/missingpwd";
	}
	
	@RequestMapping(value="/resetpwd",method=RequestMethod.GET)
	public String resetpwd(HttpServletRequest request, HttpServletResponse response){
		return "/front/resetpwd";
	}
	
	@RequestMapping(value="/error",method=RequestMethod.GET)
	public String error(HttpServletRequest request, HttpServletResponse response){
		return "/front/error";
	}
	
	//后台配置
	@RequestMapping(value="/feedlist",method=RequestMethod.GET)
	public String feedlist(HttpServletRequest request, HttpServletResponse response){
		return "/page/feedlist";
	}

	@RequestMapping(value="/addfeed",method=RequestMethod.GET)
	public String addfeed(HttpServletRequest request, HttpServletResponse response){
		return "/page/addfeed";
	}
	
	@RequestMapping(value="/editfeed",method=RequestMethod.GET)
	public String editfeed(HttpServletRequest request, HttpServletResponse response){
		return "/page/editfeed";
	}
	
	@RequestMapping(value="/recommend",method=RequestMethod.GET)
	public String recommend(HttpServletRequest request, HttpServletResponse response){
		return "/page/recommend";
	}
}
