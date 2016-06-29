package com.fs.app.portal.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import com.alibaba.fastjson.JSON;

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
}
