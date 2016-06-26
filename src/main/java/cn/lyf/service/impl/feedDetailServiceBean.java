package cn.lyf.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import cn.lyf.bean.baseFeed;
import cn.lyf.bean.baseFeedDetail;
import cn.lyf.service.feedDetailService;

@Transactional
public class feedDetailServiceBean implements feedDetailService {
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public void save(baseFeedDetail feedDetail) {
		Query hquery = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from baseFeedDetail detail where detail.speciesId=:speid and detail.feedId=:fedid and detail.title=:titl and detail.author=:auth and detail.link=:lin");
		hquery.setString("speid", feedDetail.getSpeciesId());
		hquery.setEntity("fedid", feedDetail.getFeedId());
		//hquery.setInteger("fedid", feedDetail.getFeedId().getId());
		hquery.setString("titl", feedDetail.getTitle());
		hquery.setString("auth", feedDetail.getAuthor());
		hquery.setString("lin", feedDetail.getLink());
		List<baseFeedDetail> list_query = hquery.list();
		if (list_query == null || list_query.size() == 0)
			sessionFactory.getCurrentSession().save(feedDetail);
	}

	public void update(baseFeedDetail feedDetail) {
		// sessionFactory.getCurrentSession().merge(baseFeed);
		sessionFactory.getCurrentSession().update(feedDetail);
	}
	@Override
	public baseFeedDetail getFeedDetail(Integer detaid) {
		Query hquery = sessionFactory.getCurrentSession().createQuery(
						"from baseFeedDetail detail where detail.id=:did");
		hquery.setInteger("did", detaid);
		if (hquery.list().size() > 0)
			return (baseFeedDetail) hquery.list().get(0);
		else
			return null;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<baseFeedDetail> getFeedDetail(String speid, Integer fedid) {
		Query hquery = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from baseFeedDetail detail where detail.speciesId=:speid and detail.feedId=:fedid order by publishedDate desc");
		hquery.setString("speid", speid);
		hquery.setInteger("fedid", fedid);
		if (hquery.list().size() > 0)
			return (List<baseFeedDetail>) hquery.list();
		else
			return null;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<baseFeedDetail> getFeedDetailLimit(String speid, Integer fedid,
			int min, int max) {
		Query hquery = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from baseFeedDetail detail where detail.speciesId=:speid and detail.feedId=:fedid order by publishedDate desc");
		hquery.setString("speid", speid);
		hquery.setInteger("fedid", fedid);
		hquery.setFirstResult(min);
		hquery.setMaxResults(max);
		if (hquery.list().size() > 0){
			List<baseFeedDetail> temp=(List<baseFeedDetail>)hquery.list();
			for (baseFeedDetail b : temp) {
			    if(b.getContent()!=null&&b.getContent().length()>0)
			    	b.setContent(parseHTML(b.getContent()));
			    if(b.getDescription()!=null&&b.getDescription().length()>0)
			    	b.setDescription(parseHTML(b.getDescription()));
			}
			return temp;
		}
		else
			return null;
	}
	public String parseHTML(String content){
		try{
		   org.jsoup.nodes.Document document =Jsoup.parse(content);
           return document.body().html();
        }catch(Exception e){
        	System.out.println(e.toString());
        	return content;
        }
	}
}
