package cn.lyf.service.impl;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import cn.lyf.bean.baseFeed;
import cn.lyf.service.baseFeedService;

@Transactional
public class baseFeedServiceBean implements baseFeedService {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(baseFeed baseFeed) {
		// sessionFactory.getCurrentSession().persist(baseFeed);
		sessionFactory.getCurrentSession().save(baseFeed);
	}

	@Override
	public void update(baseFeed baseFeed) {
		// sessionFactory.getCurrentSession().merge(baseFeed);
		sessionFactory.getCurrentSession().update(baseFeed);
	}

	@Override
	public baseFeed getBaseFeed(Integer feedid) {
		return (baseFeed) sessionFactory.getCurrentSession().get(baseFeed.class, feedid);
	}

	@Override
	public baseFeed getBaseFeed(String fedname) {
		Query hquery = sessionFactory.getCurrentSession().createQuery("from baseFeed fed where fed.feedName=:name");
		hquery.setString("name", fedname);
		List<baseFeed> list_resuls = hquery.list();
		if (list_resuls != null && list_resuls.size() > 0)
			return list_resuls.get(0);
		else
			return null;
	}

	@Override
	public baseFeed getBaseFeedbyURL(String address) {
		Query hquery = sessionFactory.getCurrentSession()
				.createQuery("from baseFeed fed where fed.feedAddress=:address");
		hquery.setString("address", address);
		List<baseFeed> list_resuls = hquery.list();
		if (list_resuls != null && list_resuls.size() > 0)
			return list_resuls.get(0);
		else
			return null;
	}

	@Override
	public void delete(Integer feedid) {
		sessionFactory.getCurrentSession().delete(sessionFactory.getCurrentSession().load(baseFeed.class, feedid));
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	@Override
	public List<baseFeed> getbaseFeeds() {
		return sessionFactory.getCurrentSession().createQuery("from baseFeed").list();
	}

	@Override
	public List<baseFeed> getbaseFeeds(String species) {
		// TODO Auto-generated method stub
		Query hquery = sessionFactory.getCurrentSession()
				.createQuery("from baseFeed fed where fed.feedSpecies=:species");
		hquery.setString("species", species);
		return hquery.list();
	}
}
