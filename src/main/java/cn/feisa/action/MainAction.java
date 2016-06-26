package cn.feisa.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import com.opensymphony.xwork2.Action;

import cn.lyf.bean.baseFeedDetail;
import cn.lyf.bean.recommendDetail;
import cn.lyf.bean.recommendSpecies;
import cn.lyf.service.recommendDetailService;
import cn.lyf.service.recommendSpeciesService;
import cn.lyf.service.userReadService;

public class MainAction {

	private int pageindex;
	private Map<String, Object> jsonRoot = new HashMap<String, Object>();
	@Resource
	private userReadService userredService;
	private List<baseFeedDetail> mostfile;//最多的文章
	@Resource
	private recommendSpeciesService recommService;
	@Resource
	private recommendDetailService detailService;
	private List<recommendSpecies> recommends;//推荐
	private List<recommendDetail> recommendchilds;//推荐的详细站点
	private Integer speid;//推荐类别id
	private String message;
	
	public String getMessage(){
		return message;
	}
	public String execute(){
		pageindex=0;
		mostfile=userredService.getmostReads(pageindex);
		recommends=recommService.getrecommends();
		return "success";
	}
	public String getMoretujian(){
		mostfile=userredService.getmostReads(pageindex);
		jsonRoot.put("getmoreResult", mostfile);
		JSONObject jo = JSONObject.fromObject(jsonRoot);
		this.message = jo.toString();
		return Action.SUCCESS;
	}
	public String getChildren(){
		recommendchilds=detailService.getreDetails(speid);
		jsonRoot=new HashMap<String, Object>();
		jsonRoot.put("getResult",recommendchilds);
		JSONObject jo=JSONObject.fromObject(jsonRoot);
		this.message=jo.toString();
		return Action.SUCCESS;
	}
	public List<baseFeedDetail> getMostfile() {
		return mostfile;
	}
	public void setMostfile(List<baseFeedDetail> mostfile) {
		this.mostfile = mostfile;
	}
	public int getPageindex() {
		return pageindex;
	}
	public void setPageindex(int pageindex) {
		this.pageindex = pageindex;
	}
	public List<recommendSpecies> getRecommends() {
		return recommends;
	}
	public void setRecommends(List<recommendSpecies> recommends) {
		this.recommends = recommends;
	}
	public List<recommendDetail> getRecommendchilds() {
		return recommendchilds;
	}
	public void setRecommendchilds(List<recommendDetail> recommendchilds) {
		this.recommendchilds = recommendchilds;
	}
	public Integer getSpeid() {
		return speid;
	}
	public void setSpeid(Integer speid) {
		this.speid = speid;
	}
}
