package cn.lyf.service.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.lyf.bean.baseSpecies;
import cn.lyf.service.baseSpeciesService;

@Service
public class baseSpeciesServiceBean implements baseSpeciesService {
	@Autowired 
	private SessionFactory sessionFactory;

	public void save(baseSpecies basespecies){
		sessionFactory.getCurrentSession().persist(basespecies);
	}
	
	public void update(baseSpecies basespecies){
		sessionFactory.getCurrentSession().merge(basespecies);
	}
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public baseSpecies getBaseSpecies(Integer speciesid){
		return (baseSpecies)sessionFactory.getCurrentSession().get(baseSpecies.class, speciesid);
	}

	public void delete(Integer speciesid){
		sessionFactory.getCurrentSession().delete(
				sessionFactory.getCurrentSession().load(baseSpecies.class, speciesid));
	}
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	@SuppressWarnings("unchecked")
	public List<baseSpecies> getbaseSpeciess(){		
		return sessionFactory.getCurrentSession().createQuery("from baseSpecies where Status=true").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<baseSpecies> getTopBaseSpeceies(int start, int end) {
		Query hquery=sessionFactory.getCurrentSession().createQuery("from baseSpecies spe where spe.status=1 order by spe.count desc");
		hquery.setFirstResult(start);
		hquery.setMaxResults(end);
		
		return (List<baseSpecies>)hquery.list();
	}
}
