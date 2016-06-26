package cn.feisa.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;

import cn.lyf.bean.baseFeed;
import cn.lyf.bean.baseSpecies;
import cn.lyf.bean.userFeed;
import cn.lyf.bean.userInfo;
import cn.lyf.service.baseFeedService;
import cn.lyf.service.baseSpeciesService;
import cn.lyf.service.userFeedService;

public class FeedManageAction {
	@Resource
	private baseFeedService service;
	@Resource
	private baseSpeciesService species;
	@Resource
	private userFeedService userfeedservice;

	private List<baseFeed> feeds;
	private List<baseSpecies> list_species;
	private List<userFeed> list_ufeeds;
	private int chanel;
	private String message;

	private int speciesId;// 订阅的频道
	private int feedId;// 订阅的id

	public String getMessage() {
		return message;
	}

	public String execute() {
		String param = ServletActionContext.getRequest().getParameter("chanel");
		if (param != null)
			chanel = Integer.valueOf(param);
		list_species = species.getbaseSpeciess();
		if (chanel == 0)
			feeds = service.getbaseFeeds(String.valueOf(list_species.get(0)
					.getId()));
		else
			feeds = service.getbaseFeeds(String.valueOf(chanel));
		userInfo seuser = (userInfo) ServletActionContext.getRequest()
				.getSession().getAttribute("user");
		if (seuser != null)
			list_ufeeds = userfeedservice.getUserFeedsbyUid(seuser.getId());
		else
			list_ufeeds = new ArrayList<userFeed>();
		return "success";
	}

	public String adduserfeed() {
		String _spid = ServletActionContext.getRequest().getParameter(
				"speciesId");
		String _fedid = ServletActionContext.getRequest()
				.getParameter("feedId");
		Map<String, Object> jsonRoot = new HashMap<String, Object>();
		if (_spid != "" && _fedid != "") {
			userInfo seuser = (userInfo) ServletActionContext.getRequest()
					.getSession().getAttribute("user");
			if (seuser != null) {
				baseFeed fedinfo = (baseFeed) service.getBaseFeed(Integer
						.valueOf(_fedid));
				userFeed userfed = new userFeed();
				userfed.setUserId(seuser.getId());
				userfed.setSpeciesId(_spid);
				userfed.setFeedId(fedinfo);
				userfed.setAddTime(new Date());
				userfed.setRemark("");
				Boolean res = userfeedservice.addUserFeed(userfed);
				jsonRoot.put("addResult", res);
			}else{
				jsonRoot.put("addResult", "未登录");
			}
			JSONObject jo = JSONObject.fromObject(jsonRoot);
			this.message = jo.toString();
		}
		return Action.SUCCESS;
	}

	public String removeuserfeed() {
		String _spid = ServletActionContext.getRequest().getParameter(
				"speciesId");
		String _fedid = ServletActionContext.getRequest()
				.getParameter("feedId");
		Map<String, Object> jsonRoot = new HashMap<String, Object>();
		if (_spid != "" && _fedid != "") {
			userInfo seuser = (userInfo) ServletActionContext.getRequest()
					.getSession().getAttribute("user");
			if (seuser != null) {
				baseFeed fedinfo = (baseFeed) service.getBaseFeed(Integer
						.valueOf(_fedid));
				List<userFeed> list_temp = userfeedservice.getUserFeeds(
						seuser.getId(),
						Integer.valueOf(fedinfo.getFeedSpecies()),
						fedinfo.getId());
				userFeed userfed = list_temp.get(0);
				Boolean res = userfeedservice.removeUserFeed(userfed);
				jsonRoot.put("removeResult", res);
			}else{
				jsonRoot.put("removeResult", "未登录");
			}
			JSONObject jo = JSONObject.fromObject(jsonRoot);
			this.message = jo.toString();
		}
		return Action.SUCCESS;
	}

	public List<baseFeed> getFeeds() {
		return feeds;
	}

	public void setFeeds(List<baseFeed> feeds) {
		this.feeds = feeds;
	}

	public List<baseSpecies> getList_species() {
		return list_species;
	}

	public void setList_species(List<baseSpecies> list_species) {
		this.list_species = list_species;
	}

	public int getChanel() {
		return chanel;
	}

	public void setChanel(int chanel) {
		this.chanel = chanel;
	}

	public int getSpeciesId() {
		return speciesId;
	}

	public void setSpeciesId(int speciesId) {
		this.speciesId = speciesId;
	}

	public int getFeedId() {
		return feedId;
	}

	public void setFeedId(int feedId) {
		this.feedId = feedId;
	}

	public List<userFeed> getList_ufeeds() {
		return list_ufeeds;
	}

	public void setList_ufeeds(List<userFeed> list_ufeeds) {
		this.list_ufeeds = list_ufeeds;
	}
}
