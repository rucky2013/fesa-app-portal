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
import com.fs.app.portal.pojo.UserFeedPojo;
import com.fs.app.portal.repository.IUserFeedRepository;
import com.fs.app.portal.repository.IUserInfoRepository;
import com.fs.app.portal.repository.IUserReadRepository;

@Controller
@RequestMapping("/user")
public class UserHandlers extends BaseHandlers{

	@Autowired
	private IUserInfoRepository userService;
	
	@Autowired
	private IUserReadRepository userredService;
	
	@Autowired
	private IUserFeedRepository userfeedService;
	
	@ResponseBody
	@RequestMapping(value="/getHotNews",method=RequestMethod.GET)
	public void getHotNews(HttpServletRequest request, HttpServletResponse response){
		int pageindex=Integer.valueOf(request.getParameter("pageindex"));
		List<BaseFeedDetailPojo> mostnews=userredService.getmostReads(pageindex);
		WriteJson(response,mostnews);
	}
	
	@ResponseBody
	@RequestMapping(value="/getUserFeedsbyUid",method=RequestMethod.GET)
	public void getUserFeedsbyUid(HttpServletRequest request, HttpServletResponse response){
		int userid=Integer.valueOf(request.getParameter("userid"));
		List<UserFeedPojo> list_feeds = userfeedService.getUserFeedsbyUid(userid);
		WriteJson(response,list_feeds);
	}
}
