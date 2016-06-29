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
import com.fs.app.portal.pojo.RecommendDetailPojo;
import com.fs.app.portal.pojo.RecommendSpeciesPojo;
import com.fs.app.portal.pojo.UserFeedPojo;
import com.fs.app.portal.repository.IBaseFeedRepository;
import com.fs.app.portal.repository.IBaseSpeciesRepository;
import com.fs.app.portal.repository.IFeedDetailRepository;
import com.fs.app.portal.repository.IRecommendDetailRepository;
import com.fs.app.portal.repository.IRecommendSpeciesRepository;
import com.fs.app.portal.repository.IUserFeedRepository;
import com.fs.app.portal.repository.IUserInfoRepository;
import com.fs.app.portal.repository.IUserReadRepository;

@Controller
public class HomeHandlers extends BaseHandlers{

	@Autowired
	private IUserReadRepository userredService;
	@Autowired
	private IRecommendSpeciesRepository recommService;
	@Autowired
	private IRecommendDetailRepository detailService;
	@Autowired
	private IFeedDetailRepository feedService;
	@Autowired
	private IBaseFeedRepository basefeedService;
	@Autowired
	private IUserFeedRepository userfeedService;
	@Autowired
	private IBaseSpeciesRepository specieService;
	@Autowired
	private IUserInfoRepository userService;
	
	@RequestMapping(value="/homepage",method=RequestMethod.GET)
	public String homepage(HttpServletRequest request, HttpServletResponse response){
		return "/front/main";
	}
	
	@RequestMapping(value="/feeddescription",method=RequestMethod.GET)
	public String feeddescription(HttpServletRequest request, HttpServletResponse response){
		return "/front/feeddescription";
	}
	
	@RequestMapping(value="/feeddetail",method=RequestMethod.GET)
	public String feeddetail(HttpServletRequest request, HttpServletResponse response){
		return "/front/feeddetail";
	}
	
	@RequestMapping(value="/feedmanage",method=RequestMethod.GET)
	public String feedmanage(HttpServletRequest request, HttpServletResponse response){
		return "/front/feedmanage";
	}
	
	@RequestMapping(value="/person",method=RequestMethod.GET)
	public String person(HttpServletRequest request, HttpServletResponse response){
		return "/front/person";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(HttpServletRequest request, HttpServletResponse response){
		return "/front/login";
	}
	
	@RequestMapping(value="/register",method=RequestMethod.GET)
	public String register(HttpServletRequest request, HttpServletResponse response){
		return "/front/register";
	}
	
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
	
	@RequestMapping(value="/missingpwd",method=RequestMethod.GET)
	public String missingpwd(HttpServletRequest request, HttpServletResponse response){
		return "/front/missingpwd";
	}
	
	@RequestMapping(value="/resetpwd",method=RequestMethod.GET)
	public String resetpwd(HttpServletRequest request, HttpServletResponse response){
		return "/front/resetpwd";
	}
	
	@ResponseBody
	@RequestMapping(value="/getHotNews",method=RequestMethod.GET)
	public void getHotNews(HttpServletRequest request, HttpServletResponse response){
		int pageindex=Integer.valueOf(request.getParameter("pageindex"));
		List<BaseFeedDetailPojo> mostnews=userredService.getmostReads(pageindex);
		WriteJson(response,mostnews);
	}
	
	@ResponseBody
	@RequestMapping(value="/getRecommendSpecies",method=RequestMethod.GET)
	public void getRecommendSpecies(HttpServletRequest request, HttpServletResponse response){
		List<RecommendSpeciesPojo> recommends=recommService.getrecommends();
		WriteJson(response,recommends);
	}
	
	@ResponseBody
	@RequestMapping(value="/getRecommendDetail",method=RequestMethod.GET)
	public void getRecommendDetail(HttpServletRequest request, HttpServletResponse response){
		int speid=Integer.valueOf(request.getParameter("speid"));
		List<RecommendDetailPojo> recommendchilds=detailService.getreDetails(speid);
		WriteJson(response,recommendchilds);
	}
	
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
	
	@ResponseBody
	@RequestMapping(value="/getUserFeedsbyUid",method=RequestMethod.GET)
	public void getUserFeedsbyUid(HttpServletRequest request, HttpServletResponse response){
		int userid=Integer.valueOf(request.getParameter("userid"));
		List<UserFeedPojo> list_feeds = userfeedService.getUserFeedsbyUid(userid);
		WriteJson(response,list_feeds);
	}
}
