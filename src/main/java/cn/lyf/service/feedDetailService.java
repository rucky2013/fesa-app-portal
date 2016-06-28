package cn.lyf.service;

import java.util.List;
import cn.lyf.bean.baseFeedDetail;

public interface feedDetailService {

	public void save(baseFeedDetail feedDetail);

	public void update(baseFeedDetail feedDetail);
	
	public baseFeedDetail getFeedDetail(Integer detaid);
	
	public List<baseFeedDetail> getFeedDetail(String speid,Integer fedid); 
	
	public List<baseFeedDetail> getFeedDetailLimit(String speid,Integer fedid,int min,int max); 
}