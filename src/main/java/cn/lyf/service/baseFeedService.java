package cn.lyf.service;

import java.util.List;
import cn.lyf.bean.baseFeed;

public interface baseFeedService {

	public void save(baseFeed baseFeed);

	public void update(baseFeed baseFeed);

	public baseFeed getBaseFeed(Integer feedid);
	
	public baseFeed getBaseFeed(String fedname);

	public baseFeed getBaseFeedbyURL(String address);
	
	public void delete(Integer feedid);

	public List<baseFeed> getbaseFeeds();
	
	public List<baseFeed> getbaseFeeds(String species);

}