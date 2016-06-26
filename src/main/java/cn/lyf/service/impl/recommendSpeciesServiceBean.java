package cn.lyf.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import cn.lyf.bean.baseFeed;
import cn.lyf.bean.recommendSpecies;
import cn.lyf.service.recommendSpeciesService;

@Transactional
public class recommendSpeciesServiceBean implements recommendSpeciesService {
	@Autowired 
	private SessionFactory sessionFactory;
	@Override
	public void Save(recommendSpecies recommend) {
		sessionFactory.getCurrentSession().save(recommend);
	}
	@Override
	public void update(recommendSpecies recommend) {
		sessionFactory.getCurrentSession().update(recommend);
	}
	@Override
	public void delete(Integer id) {
		sessionFactory.getCurrentSession().delete(
				sessionFactory.getCurrentSession().load(recommendSpecies.class, id));
	}
	@Override
	public recommendSpecies getRecommendSpecies(Integer reid) {
		return (recommendSpecies)sessionFactory.getCurrentSession().get(recommendSpecies.class, reid);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<recommendSpecies> getrecommends() {
		return sessionFactory.getCurrentSession().createQuery("from recommendSpecies").list();
	}
}
