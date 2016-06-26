package cn.feisa.action;

import javax.annotation.Resource;
import org.apache.struts2.ServletActionContext;
import cn.lyf.bean.baseFeed;
import cn.lyf.bean.baseFeedDetail;
import cn.lyf.service.baseFeedService;
import cn.lyf.service.feedDetailService;

public class FeedDescriptionAction {
	@Resource
	private feedDetailService feedservice;
	@Resource
	private baseFeedService basefeedservice;

	private baseFeedDetail currentdetail;// feed的详细信息
	private baseFeed currentfeed;// 当前feed

	private String message;

	private int feedid;// 页面传递过来的参数
	private int detailid;// 页面传递过来的参数

	public String execute() {
		//userInfo user = (userInfo) ServletActionContext.getRequest().getSession().getAttribute("user");
		String fedidtemp = ServletActionContext.getRequest().getParameter("feedid");
		String deidtemp = ServletActionContext.getRequest().getParameter("detailid");
		feedid=Integer.valueOf(fedidtemp);
		detailid = Integer.valueOf(deidtemp);
		currentfeed = basefeedservice.getBaseFeed(getFeedid());
		currentdetail = feedservice.getFeedDetail(getDetailid());
		return "success";
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

	public int getDetailid() {
		return detailid;
	}

	public void setDetailid(int detailid) {
		this.detailid = detailid;
	}

	public int getFeedid() {
		return feedid;
	}

	public void setFeedid(int feedid) {
		this.feedid = feedid;
	}
	
	public baseFeedDetail getCurrentdetail() {
		return currentdetail;
	}
	public void setCurrentdetail(baseFeedDetail currentdetail) {
		this.currentdetail = currentdetail;
	}
}
