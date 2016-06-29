package com.fs.app.portal.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/about")
public class AboutHandlers extends BaseHandlers{
	
	@ResponseBody
	@RequestMapping(value="/description",method=RequestMethod.GET)
	public String description(HttpServletRequest request, HttpServletResponse response){
		return "/front/about";
	}

	@ResponseBody
	@RequestMapping(value="/download",method=RequestMethod.GET)
	public String download(HttpServletRequest request, HttpServletResponse response){
		return "/front/download";
	}
}
