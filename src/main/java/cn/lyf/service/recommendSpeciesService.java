package cn.lyf.service;

import java.util.List;
import cn.lyf.bean.recommendSpecies;

public interface recommendSpeciesService {

	public void Save(recommendSpecies recommend);
	
	public void update(recommendSpecies recommend);
	
	public void delete(Integer id);
	
    public recommendSpecies getRecommendSpecies(Integer reid);
	
	public List<recommendSpecies> getrecommends();
}
