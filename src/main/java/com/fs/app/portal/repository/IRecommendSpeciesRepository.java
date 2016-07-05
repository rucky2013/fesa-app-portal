package com.fs.app.portal.repository;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.fs.app.portal.pojo.RecommendSpeciesPojo;

public interface IRecommendSpeciesRepository {

	public void Save(RecommendSpeciesPojo recommend);
	
	public void update(RecommendSpeciesPojo recommend);
	
	public void delete(Integer id);
	
    public RecommendSpeciesPojo getRecommendSpecies(Integer reid);
	
	public List<RecommendSpeciesPojo> getrecommends();
}
