package cn.lyf.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.json.JSONException;
import org.json.JSONObject;

import cn.lyf.bean.baseFeed;
import cn.lyf.bean.recommendDetail;
import cn.lyf.bean.recommendSpecies;
import cn.lyf.service.baseFeedService;
import cn.lyf.service.recommendDetailService;
import cn.lyf.service.recommendSpeciesService;
import com.opensymphony.xwork2.Action;

public class recommendAction {

	@Resource
    recommendSpeciesService speciesService;
	@Resource
	recommendDetailService detailService;
	@Resource
	baseFeedService basefeedService;
	private List<recommendSpecies> list_recomms;
	private List<recommendDetail> list_details;
	private Integer reid;//recommendSpeciesid
	private Integer spid;//根据feedid获取所有recommendDetail
	private Integer add_speid;//需要添加的speid
	private String feedname;//需要添加的detail的名称 
	private String message;
	private String para;
	public String list(){
		list_recomms= speciesService.getrecommends();
		list_details=null;
		return Action.SUCCESS;
	}
	public String details()
	{
		list_recomms= speciesService.getrecommends();
		if(add_speid!=null){
			spid=add_speid;
			List<recommendDetail> details= detailService.getreDetails(spid);
			list_details=(details==null?new ArrayList<recommendDetail>():details);
		}
		return Action.SUCCESS;
	}
	public String Save(){
		if(this.para!=""){
			try {
				JSONObject temp=new JSONObject(this.para);
				recommendSpecies addrecommend=new recommendSpecies();
				addrecommend.setName(temp.getString("name"));
				addrecommend.setIcon(temp.getString("icon"));
				addrecommend.setRestatus(temp.getBoolean("restatus"));
				addrecommend.setRemark(temp.getString("remark"));
				speciesService.Save(addrecommend);
				this.message="添加成功";
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}else
			this.message="添加失败";
		return Action.SUCCESS;
	}
	public String Update(){
		if(this.para!=""){
			try {
				JSONObject temp=new JSONObject(this.para);
				recommendSpecies updatecommend=new recommendSpecies();
				updatecommend.setId(temp.getInt("id"));
				updatecommend.setName(temp.getString("name"));
				updatecommend.setIcon(temp.getString("icon"));
				updatecommend.setRestatus(temp.getBoolean("restatus"));
				updatecommend.setRemark(temp.getString("remark"));
				speciesService.update(updatecommend);
				this.message="更新成功";
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}else
			this.message="更新失败";
		return Action.SUCCESS;
	}
	public String Delete(){
		if(this.reid!=0){
			speciesService.delete(reid);
			this.message="删除成功";
		}else
			this.message="删除失败";
		return Action.SUCCESS;
	}
	public String existfeed()
	{
		baseFeed temp=null;
		if(feedname!=""){
			temp= basefeedService.getBaseFeed(feedname);
		}
		Map<String, Object> jsonRoot = new HashMap<String, Object>();
		jsonRoot.put("result", temp);
		net.sf.json.JSONObject jo =net.sf.json.JSONObject.fromObject(jsonRoot);
		this.message = jo.toString();
		return Action.SUCCESS;
	}
	public String AddDetail(){
		if(this.para!=""){
			try {
				JSONObject temp=new JSONObject(this.para);
				recommendDetail add_detail=new recommendDetail();
				add_detail.setSpeid(temp.getInt("speid"));
				add_detail.setFeedid(temp.getInt("feedid"));
				add_detail.setFeedname(temp.getString("feedname"));
				add_detail.setFeedphoto(temp.getString("feedphoto"));
				add_detail.setRemark(temp.getString("remark"));
				detailService.Save(add_detail);
				this.message="添加成功";
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}else
			this.message="添加失败";
		return Action.SUCCESS;
	}
	public String RemoveDetail(){
		if(para!=""){
			detailService.delete(Integer.valueOf(para));
			this.message="删除成功";
		}else
			this.message="删除失败";
		return Action.SUCCESS;
	}
	public Integer getReid() {
		return reid;
	}
	public void setReid(Integer reid) {
		this.reid = reid;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<recommendSpecies> getList_recomms() {
		return list_recomms;
	}
	public void setList_recomms(List<recommendSpecies> list_recomms) {
		this.list_recomms = list_recomms;
	}
	public String getPara() {
		return para;
	}
	public void setPara(String para) {
		this.para = para;
	}
	public String getFeedname() {
		return feedname;
	}
	public void setFeedname(String feedname) {
		this.feedname = feedname;
	}
	public Integer getAdd_speid() {
		return add_speid;
	}
	public void setAdd_speid(Integer add_speid) {
		this.add_speid = add_speid;
	}
	public List<recommendDetail> getList_details() {
		return list_details;
	}
	public void setList_details(List<recommendDetail> list_details) {
		this.list_details = list_details;
	}
}
