package com.fs.app.portal.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import com.alibaba.fastjson.JSON;
import com.fs.app.portal.entity.RenderData;

public abstract class BaseHandlers {

	public void WriteJson(HttpServletResponse response,Object data){
		response.setCharacterEncoding("UTF-8");  
	    response.setContentType("application/json; charset=utf-8");
	    PrintWriter out=null;
		try {
			out = response.getWriter();
			out.append(JSON.toJSONString(data));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void WriteErrorJson(HttpServletResponse response,RenderData data){
		response.setCharacterEncoding("UTF-8");  
	    response.setContentType("application/json; charset=utf-8");
	    data.setCode(40001);
	    data.setMessage("请求错误！");
	    PrintWriter out=null;
		try {
			out = response.getWriter();
			out.append(JSON.toJSONString(data));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
