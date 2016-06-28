package cn.lyf.service;

import java.util.List;
import cn.lyf.bean.baseSpecies;

public interface baseSpeciesService {

	public void save(baseSpecies basespecies);

	public void update(baseSpecies basespecies);

	public baseSpecies getBaseSpecies(Integer Speciesid);
	
	public List<baseSpecies> getTopBaseSpeceies(int start,int end);

	public void delete(Integer Speciesid);

	public List<baseSpecies> getbaseSpeciess();

}