package com.fs.app.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fs.app.portal.pojo.BaseFeedDetailPojo;
import com.fs.app.portal.pojo.BaseFeedPojo;
import com.fs.app.portal.pojo.BaseSpeciesPojo;
import com.fs.app.portal.repository.IBaseFeedRepository;
import com.fs.app.portal.repository.IBaseSpeciesRepository;
import com.fs.app.portal.repository.IFeedDetailRepository;

@Controller
@RequestMapping("/news")
public class NewsHandlers extends BaseHandlers{

	@Autowired
	private IFeedDetailRepository feedService;
	@Autowired
	private IBaseFeedRepository basefeedService;
	@Autowired
	private IBaseSpeciesRepository specieService;
	
	@ResponseBody
	@RequestMapping(value="/getBaseFeed",method=RequestMethod.GET)
	public void getBaseFeed(HttpServletRequest request, HttpServletResponse response){
		int feedid=Integer.valueOf(request.getParameter("feedid"));
		BaseFeedPojo currentfeed=basefeedService.getBaseFeed(feedid);
		WriteJson(response,currentfeed);
	}
	
	@ResponseBody
	@RequestMapping(value="/getFeedDetail",method=RequestMethod.GET)
	public void getFeedDetail(HttpServletRequest request, HttpServletResponse response){
		int detailid=Integer.valueOf(request.getParameter("detailid"));
		BaseFeedDetailPojo currentdetail=feedService.getFeedDetail(detailid);
		WriteJson(response,currentdetail);
	}
	
	@ResponseBody
	@RequestMapping(value="/getTopBaseSpeceies",method=RequestMethod.GET)
	public void getTopBaseSpeceies(HttpServletRequest request, HttpServletResponse response){
		List<BaseSpeciesPojo> list_species = specieService.getTopBaseSpeceies(0, 5);
		WriteJson(response,list_species);
	}
	
	@ResponseBody
	@RequestMapping(value="/getFeedDetailLimit",method=RequestMethod.GET)
	public void getFeedDetailLimit(HttpServletRequest request, HttpServletResponse response){
		String feedspecies=request.getParameter("feedspecies").toString();
		int feedid=Integer.valueOf(request.getParameter("feedid"));
		List<BaseFeedDetailPojo> list_feed = feedService.getFeedDetailLimit(feedspecies, feedid, 0, 8);
		WriteJson(response,list_feed);
	}
}
