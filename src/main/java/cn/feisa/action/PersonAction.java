package cn.feisa.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;

import cn.lyf.bean.baseFeed;
import cn.lyf.bean.baseFeedDetail;
import cn.lyf.bean.baseSpecies;
import cn.lyf.bean.userFeed;
import cn.lyf.bean.userInfo;
import cn.lyf.bean.userRead;
import cn.lyf.service.baseFeedService;
import cn.lyf.service.baseSpeciesService;
import cn.lyf.service.feedDetailService;
import cn.lyf.service.userFeedService;
import cn.lyf.service.userReadService;

public class PersonAction {
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

	private String message;
	private Map<String, Object> jsonRoot = new HashMap<String, Object>();

	private int parafid;// 记录读取详细信息时的feedid;切换频道的feedid
	private int detailid;// 记录读取详细信息时的detailid

	private int maxindex;// 翻页时的索引值
	private String specid;// 翻页时的speciesid
	private int feedid;// 翻页时的feedid

	public String getMessage() {
		return message;
	}

	public String execute() {
		userInfo user = (userInfo) ServletActionContext.getRequest()
				.getSession().getAttribute("user");
		list_userfeed = userfeed_service.getUserFeedsbyUid(user.getId());
		list_species = specieservice.getTopBaseSpeceies(0, 5);
		String paratemp = ServletActionContext.getRequest().getParameter(
				"parafid");
		if (paratemp != null)
			parafid = Integer.valueOf(paratemp);
		if (getParafid() == 0) {
			if (list_userfeed != null && list_userfeed.size() > 0) {
				list_feed = feedservice.getFeedDetailLimit(list_userfeed.get(0).getSpeciesId(),list_userfeed.get(0).getFeedId().getId(), 0, 8);
				specid = list_userfeed.get(0).getSpeciesId();
				feedid = list_userfeed.get(0).getFeedId().getId();
				maxindex = 8;
			}else{
				list_feed = null;
				specid = "";
				feedid = -1;
				maxindex = 0;
			}
		} else {
			for (userFeed ufed : list_userfeed) {
				if (ufed.getFeedId().getId() == getParafid()) {
					list_feed = feedservice.getFeedDetailLimit(
							ufed.getSpeciesId(), ufed.getFeedId().getId(), 0, 8);
					specid = ufed.getSpeciesId();
					feedid = ufed.getFeedId().getId();
					maxindex = 8;
					break;
				}
			}
		}
		return "success";
	}

	/*
	 * 获取更多的信息
	 */
	public String getMoreinfo() {
		list_feed = feedservice.getFeedDetailLimit(specid, feedid,
				maxindex, 8);
		jsonRoot.put("getmoreResult", list_feed);
		JSONObject jo = JSONObject.fromObject(jsonRoot);
		this.message = jo.toString();
		return Action.SUCCESS;
	}

	/**
	 * 暂时不用
	 * 
	 * @return
	 */
	public String refresh() {
		System.out.println("开始刷新");

		userInfo user = (userInfo) ServletActionContext.getRequest()
				.getSession().getAttribute("user");
		list_userfeed = userfeed_service.getUserFeedsbyUid(user.getId());
		list_species = specieservice.getTopBaseSpeceies(0, 5);
		list_feed = feedservice.getFeedDetail(list_userfeed.get(1)
				.getSpeciesId(), list_userfeed.get(1).getFeedId().getId());
		jsonRoot.put("refreshResult", true);
		JSONObject jo = JSONObject.fromObject(jsonRoot);
		this.message = jo.toString();
		System.out.println(this.message);
		return Action.SUCCESS;
	}

	public String read() {
		userInfo user = (userInfo) ServletActionContext.getRequest()
				.getSession().getAttribute("user");
		if (user == null) {
			JSONObject jo = JSONObject.fromObject(jsonRoot);
			this.message = jo.toString();
			return Action.SUCCESS;
		}
		String paratemp = ServletActionContext.getRequest().getParameter(
				"parafid");
		String detailtemp = ServletActionContext.getRequest().getParameter(
				"detailid");
		if (paratemp != null)
			parafid = Integer.valueOf(parafid);
		if (detailtemp != null)
			detailid = Integer.valueOf(detailtemp);
		baseFeed basefed = basefeedservice.getBaseFeed(parafid);
		List<userRead> list_reads = userredservice.getUserReds(user.getId(),
				Integer.valueOf(basefed.getFeedSpecies()), basefed.getId(),
				detailid);
		if (list_reads == null || list_reads.size() == 0) {
			userRead userred = new userRead();
			userred.setUserId(user.getId());
			userred.setSpeciesId(basefed.getFeedSpecies());
			userred.setFeedId(parafid);
			userred.setFeedName(basefed.getFeedName());
			userred.setFeedAddress(basefed.getFeedAddress());
			userred.setDetailId(detailid);
			userred.setReadCount(1);
			userred.setAddTime(new Date());
			userred.setUpdateTime(new Date());
			userred.setRemark("");
			Boolean addresult = userredservice.addUserRead(userred);
			jsonRoot.put("readResult", addresult);
		} else {
			userRead read_temp = (userRead) list_reads.get(0);
			read_temp.setReadCount(read_temp.getReadCount() + 1);
			read_temp.setUpdateTime(new Date());
			Boolean updateresult = userredservice.updateUserRead(read_temp);
			jsonRoot.put("readResult", updateresult);
		}
		JSONObject jo = JSONObject.fromObject(jsonRoot);
		this.message = jo.toString();
		return Action.SUCCESS;
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

	public int getParafid() {
		return parafid;
	}

	public void setParafid(int parafid) {
		this.parafid = parafid;
	}

	public int getDetailid() {
		return detailid;
	}

	public void setDetailid(int detailid) {
		this.detailid = detailid;
	}

	public int getMaxindex() {
		return maxindex;
	}

	public void setMaxindex(int maxindex) {
		this.maxindex = maxindex;
	}

	public String getSpecid() {
		return specid;
	}

	public void setSpecid(String specid) {
		this.specid = specid;
	}

	public int getFeedid() {
		return feedid;
	}

	public void setFeedid(int feedid) {
		this.feedid = feedid;
	}
}
