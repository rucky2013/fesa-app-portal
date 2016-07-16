package com.fs.app.portal.repository;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.fs.app.portal.pojo.RecommendDetailPojo;

public interface IRecommendDetailRepository {

	public void Save(RecommendDetailPojo recommend);
	
	public void update(RecommendDetailPojo recommend);
	
	public void delete(Integer id);
	
	public List<RecommendDetailPojo> getreDetails(Integer speid);
}
