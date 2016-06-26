package cn.lyf.service;

import java.util.List;
import org.springframework.stereotype.Service;
import cn.lyf.bean.recommendSpecies;

@Service
public interface recommendSpeciesService {

	public void Save(recommendSpecies recommend);
	
	public void update(recommendSpecies recommend);
	
	public void delete(Integer id);
	
    public recommendSpecies getRecommendSpecies(Integer reid);
	
	public List<recommendSpecies> getrecommends();
}
