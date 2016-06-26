package cn.feisa.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;

import cn.lyf.bean.baseFeed;
import cn.lyf.bean.baseFeedDetail;
import cn.lyf.bean.baseSpecies;
import cn.lyf.bean.userFeed;
import cn.lyf.bean.userInfo;
import cn.lyf.service.baseFeedService;
import cn.lyf.service.baseSpeciesService;
import cn.lyf.service.feedDetailService;
import cn.lyf.service.userFeedService;
import cn.lyf.service.userReadService;

public class FeedDetailAction {
	@Resource
	private feedDetailService feedservice;
	@Resource
	private userFeedService userfeed_service;
	@Resource
	private baseSpeciesService specieservice;
	@Resource
	private baseFeedService basefeedservice;
	@Resource
	private userReadService userredservice;

	private List<baseFeedDetail> list_feed;// feed的详细信息
	private List<userFeed> list_userfeed;// 用户订阅的所有的feed
	private List<baseSpecies> list_species;// 种类的排名
	private baseFeed currentfeed;// 当前feed

	private String message;

	private int feedid;// 页面传递过来的参数

	public String execute() {
		userInfo user = (userInfo) ServletActionContext.getRequest()
				.getSession().getAttribute("user");
		String fedidtemp = ServletActionContext.getRequest().getParameter(
				"feedid");
		feedid = Integer.valueOf(fedidtemp);
		list_userfeed = null;
		if (user != null) {
			List<userFeed> list_temp = userfeed_service.getUserFeedsbyUid(user
					.getId());
			if (list_temp != null) {
				for (userFeed uFeed : list_temp) {
					if (uFeed.getFeedId().getId().equals(feedid)) {
						list_userfeed = new ArrayList<userFeed>();
						list_userfeed.add(uFeed);
					}
				}
			}
		}
		list_species = specieservice.getTopBaseSpeceies(0, 5);
		currentfeed = basefeedservice.getBaseFeed(getFeedid());
		list_feed = feedservice.getFeedDetailLimit(
				currentfeed.getFeedSpecies(), currentfeed.getId(), 0, 8);
		return "success";
	}

	public List<baseFeedDetail> getList_feed() {
		return list_feed;
	}

	public void setList_feed(List<baseFeedDetail> list_feed) {
		this.list_feed = list_feed;
	}

	public List<userFeed> getList_userfeed() {
		return list_userfeed;
	}

	public void setList_userfeed(List<userFeed> list_userfeed) {
		this.list_userfeed = list_userfeed;
	}

	public List<baseSpecies> getList_species() {
		return list_species;
	}

	public void setList_species(List<baseSpecies> list_species) {
		this.list_species = list_species;
	}

	public int getFeedid() {
		return feedid;
	}

	public void setFeedid(int feedid) {
		this.feedid = feedid;
	}

	public baseFeed getCurrentfeed() {
		return currentfeed;
	}

	public void setCurrentfeed(baseFeed currentfeed) {
		this.currentfeed = currentfeed;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
