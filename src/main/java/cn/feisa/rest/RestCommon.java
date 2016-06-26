package cn.feisa.rest;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.crypto.SecretKey;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;

import cn.feisa.tools.encryptionAnddecryption;
import cn.lyf.bean.baseFeed;
import cn.lyf.bean.baseFeedDetail;
import cn.lyf.bean.baseSpecies;
import cn.lyf.bean.userFeed;
import cn.lyf.bean.userInfo;
import cn.lyf.service.baseFeedService;
import cn.lyf.service.baseSpeciesService;
import cn.lyf.service.feedDetailService;
import cn.lyf.service.userFeedService;
import cn.lyf.service.userInfoService;

import com.opensymphony.xwork2.Action;
import net.sf.json.JSONObject;

public class RestCommon {
	@Resource
	private userInfoService userinfoservice;
	@Resource
	private baseSpeciesService basespecieservice;
	@Resource
	private baseFeedService basefeedservice;
	@Resource
	private userFeedService userfeedservice;
	@Resource
	private feedDetailService feeddetailservice;
	Map<String, Object> jsonRoot = new HashMap<String, Object>();
	private JSONObject jo;
	/**
	 * 获取欢迎界面信息
	 * @return /rest/welcome
	 */
	public String getwelcomeinfo(){
		jsonRoot=new HashMap<String, Object>();
		jsonRoot.put("message", "新的一天新的Idea!");
		jsonRoot.put("image","/photo/welcome.jpg");
		jo = JSONObject.fromObject(jsonRoot);
		return Action.SUCCESS;
	}
	private String uname,upwd;
	/**
	 * 获取用户信息
	 * @return /rest/userinfo?uname=lvyafei&&upwd=1
	 */
	public String getuserinfo(){
		userInfo user=userinfoservice.getUserInfo(uname,upwd);
		jsonRoot=new HashMap<String, Object>();
		jsonRoot.put("userinfo",user==null?"":user);
		jo = JSONObject.fromObject(jsonRoot);
		return Action.SUCCESS;
	}
	/**
	 * 验证用户邮箱是否存在
	 * @return /rest/validemail?email=22@qq.com
	 */
	public String existEmail(){
		userInfo exituser = userinfoservice.getUserEmail(email);
		if (exituser != null)
			jsonRoot.put("existResult", true);
		else
			jsonRoot.put("existResult", false);
		jo = JSONObject.fromObject(jsonRoot);
		return Action.SUCCESS;
	}
	/**
	 * 注册用户
	 * @return /rest/register?uname=lvyafei&&upwd=1&email=22@qq.com&phonenumber=151211
	 */
	private String email;
	private String phonenumber;
	public String createuserinfo(){
		userInfo newuser=new userInfo();
		newuser.setUserName(uname);
		newuser.setUserEmail(email);
		newuser.setUserPhone(phonenumber);
		newuser.setAuthLimit("1");
		newuser.setCreTime(new java.util.Date());
		newuser.setRegTime(new java.util.Date());
		encryptionAnddecryption jiami = new encryptionAnddecryption();
		SecretKey key = jiami.createSecretKey("DES");
		String str_password = jiami.encryptToDES(key,upwd);
		newuser.setUserPassword(str_password);
		newuser.setRemark(key.getEncoded());
		Boolean res = userinfoservice.addUser(newuser);
		if (res) {
			userInfo taguser = userinfoservice.getUserInfo(uname,upwd);
			jsonRoot.put("registerResult", res);
			jsonRoot.put("userinfo", taguser);
		} else {
			jsonRoot.put("registerResult", res);
			jsonRoot.put("userinfo", null);
		}
		jo = JSONObject.fromObject(jsonRoot);
		return Action.SUCCESS;
	}
	/*
	 * 获取公共频道信息 /rest/comchannel
	 */
	public String getcomchannel(){
		List<baseSpecies> list_species= basespecieservice.getbaseSpeciess();
		jsonRoot=new HashMap<String, Object>();
		jsonRoot.put("channelinfo", list_species);
		jo=JSONObject.fromObject(jsonRoot);
		return Action.SUCCESS;
	}
	private String speciesid;
	/*
	 * 获取频道下的站点 /rest/feedsbyspecies?speciesid=4
	 */
	public String getcomfeed(){
		List<baseFeed> list_feeds= basefeedservice.getbaseFeeds(speciesid);
		jsonRoot=new HashMap<String, Object>();
		jsonRoot.put("channelinfo", list_feeds);
		jo=JSONObject.fromObject(jsonRoot);
		return Action.SUCCESS;
	}
	private int uid;
	/*
	 * 获取个人订阅信息  /rest/personchannel?uid=22
	 */
	public String getpersonchannel(){
		List<userFeed> list_ufeeds= userfeedservice.getUserFeedsbyUid(uid);
		jsonRoot=new HashMap<String, Object>();
		jsonRoot.put("userfeeds", list_ufeeds);
		jo=JSONObject.fromObject(jsonRoot);
		return Action.SUCCESS;
	}
	/**
	 * 获取channel的详细信息 /rest/feeddetails?d_speid=3&&d_fedid=46&&d_start=1&&d_end=8
	 */
	private String d_speid;
	private int d_fedid,d_start,d_end;
	public String getfeeddetail(){
		List<baseFeedDetail> list_details=feeddetailservice.getFeedDetailLimit(d_speid, d_fedid, d_start, d_end);
		for (baseFeedDetail bDetail : list_details) {
			if(bDetail.getUpdatedDate()==null)
				bDetail.setUpdatedDate(bDetail.getCreateDate());
			if(bDetail.getPublishedDate()==null)
				bDetail.setPublishedDate(bDetail.getCreateDate());
		}
		jsonRoot = new HashMap<String, Object>();
		if (list_details != null && !list_details.isEmpty()) {
			jsonRoot.put("feeddetails", list_details);
		}else{
			jsonRoot.put("feeddetails", "null");
		}
		jo=JSONObject.fromObject(jsonRoot);
		return Action.SUCCESS;
	}
	/**
	 * /rest/subscribe?type=1&&uname=lvyafei&&upwd=1&&fedid=46
	 * @return
	 */
	private String fedid;
	private int type;
	public String getsubscribe(){
		if (uname != "" && upwd != "") {
			userInfo user=userinfoservice.getUserInfoBymark(uname,upwd);
			if(user!=null&&type==0){
				baseFeed fedinfo = (baseFeed)basefeedservice.getBaseFeed(Integer.valueOf(fedid));
				List<userFeed> list_temp = userfeedservice.getUserFeeds(user.getId(),Integer.valueOf(fedinfo.getFeedSpecies()),fedinfo.getId());
				userFeed userfed = list_temp.get(0);
				Boolean res = userfeedservice.removeUserFeed(userfed);
				jsonRoot.put("subResult", res);
			}else if(user!=null&&type==1){
				baseFeed fedinfo = (baseFeed)basefeedservice.getBaseFeed(Integer.valueOf(fedid));
				userFeed userfed = new userFeed();
				userfed.setUserId(user.getId());
				userfed.setSpeciesId(fedinfo.getFeedSpecies());
				userfed.setFeedId(fedinfo);
				userfed.setAddTime(new Date());
				userfed.setRemark("");
				Boolean res = userfeedservice.addUserFeed(userfed);
				jsonRoot.put("subResult", res);
			}else{
				jsonRoot.put("subResult", "false");
			}
			jo = JSONObject.fromObject(jsonRoot);
		}
		return Action.SUCCESS;
	}
	public JSONObject getJo() {
		return jo;
	}
	public void setJo(JSONObject jo) {
		this.jo = jo;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUpwd() {
		return upwd;
	}
	public void setUpwd(String upwd) {
		this.upwd = upwd;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getSpeciesid() {
		return speciesid;
	}
	public void setSpeciesid(String speciesid) {
		this.speciesid = speciesid;
	}
	public String getD_speid() {
		return d_speid;
	}
	public void setD_speid(String d_speid) {
		this.d_speid = d_speid;
	}
	public int getD_fedid() {
		return d_fedid;
	}
	public void setD_fedid(int d_fedid) {
		this.d_fedid = d_fedid;
	}
	public int getD_start() {
		return d_start;
	}
	public void setD_start(int d_start) {
		this.d_start = d_start;
	}
	public int getD_end() {
		return d_end;
	}
	public void setD_end(int d_end) {
		this.d_end = d_end;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getFedid() {
		return fedid;
	}
	public void setFedid(String fedid) {
		this.fedid = fedid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
}
