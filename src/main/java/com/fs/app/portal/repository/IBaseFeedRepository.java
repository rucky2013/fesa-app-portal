package com.fs.app.portal.repository;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.fs.app.portal.pojo.BaseFeedPojo;

@Transactional
public interface IBaseFeedRepository {

	public void save(BaseFeedPojo baseFeed);

	public void update(BaseFeedPojo baseFeed);

	public BaseFeedPojo getBaseFeed(Integer feedid);
	
	public BaseFeedPojo getBaseFeed(String fedname);

	public BaseFeedPojo getBaseFeedbyURL(String address);
	
	public void delete(Integer feedid);

	public List<BaseFeedPojo> getbaseFeeds();
	
	public List<BaseFeedPojo> getbaseFeeds(String species);

}