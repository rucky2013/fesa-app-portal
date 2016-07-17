package com.fs.app.portal.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import com.alibaba.fastjson.JSON;
import com.fs.commons.app.entity.RenderData;

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
	
	public void ValidParam(HttpServletResponse response,Object... params){
		Boolean isvalid=true;
		for (Object object : params) {
			if(object==null){
				isvalid=false;
				break;
			}
		}
		if(!isvalid){
			RenderData data=new RenderData();
			data.setCode(30001);
			data.setMessage("请求参数出错！");
			data.setDatas(params);
			response.setCharacterEncoding("UTF-8");  
		    response.setContentType("application/json; charset=utf-8");
		    PrintWriter out=null;
			try {
				out = response.getWriter();
				out.append(JSON.toJSONString(data));
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
	}
}
