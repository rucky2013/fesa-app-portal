package cn.lyf.service.impl;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.lyf.bean.recommendDetail;
import cn.lyf.service.recommendDetailService;

@Service
public class recommendDetailServiceBean implements recommendDetailService {
	@Autowired 
	private SessionFactory sessionFactory;
	@Override
	public void Save(recommendDetail recommend) {
		sessionFactory.getCurrentSession().save(recommend);
	}
	@Override
	public void update(recommendDetail recommend) {
		sessionFactory.getCurrentSession().update(recommend);
	}
	@Override
	public void delete(Integer id) {
		sessionFactory.getCurrentSession().delete(
				sessionFactory.getCurrentSession().load(recommendDetail.class, id));
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<recommendDetail> getreDetails(Integer feid) {
		Query hquery = sessionFactory.getCurrentSession().createQuery("from recommendDetail de where de.speid=:spe");
		hquery.setInteger("spe", feid);
		List<recommendDetail> lists= hquery.list();
		return lists;
	}
}
