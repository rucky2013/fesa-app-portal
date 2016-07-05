package com.fs.app.portal.repository.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fs.app.portal.pojo.UserFeedPojo;
import com.fs.app.portal.repository.IUserFeedRepository;

@Repository
public class UserFeedRepository implements IUserFeedRepository {
	@Autowired 
	private SessionFactory sessionFactory;
	
	@Override
	public Boolean addUserFeed(UserFeedPojo userfed) {
		try {
			sessionFactory.getCurrentSession().save(userfed);
			return true;
		} catch (Exception ex) {
			System.out.println(ex.toString());
			return false;
		}
	}
	public Boolean removeUserFeed(UserFeedPojo userfed){
		try {
			sessionFactory.getCurrentSession().delete(userfed);
			return true;
		} catch (Exception ex) {
			System.out.println(ex.toString());
			return false;
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<UserFeedPojo> getUserFeeds(int uid, int speid, int fedid) {
		try{
			Query hquery= sessionFactory.getCurrentSession().createQuery("from userFeed ufed where ufed.userId=:uid and ufed.speciesId=:speid and ufed.feedId=:fid");
			hquery.setInteger("uid", uid);
			hquery.setInteger("speid",speid);
			hquery.setInteger("fid",fedid);
			List<UserFeedPojo> list_result= hquery.list();
			if(list_result!=null&&list_result.size()>0)
				return list_result;
			else
			   return null;
		}catch(Exception ex){
			System.out.println(ex.toString());
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<UserFeedPojo> getUserFeedsbyUid(int uid) {
		try{
			Query hquery= sessionFactory.getCurrentSession().createQuery("from userFeed ufed where ufed.userId=:uid");
			hquery.setInteger("uid", uid);
			List<UserFeedPojo> list_result= hquery.list();
			if(list_result!=null&&list_result.size()>0)
				return list_result;
			else
			   return null;
		}catch(Exception ex){
			System.out.println(ex.toString());
			return null;
		}
	}
	@SuppressWarnings("rawtypes")
	@Override
	public Map<String, String> getmostUserFeed() {
		Query hquery= sessionFactory.getCurrentSession().createQuery("select uf.speciesId,spe.speciesName from userFeed as uf,baseSpecies spe where uf.speciesId=spe.Id group by speciesid");
		List list_temp= hquery.list();
		Map<String, String> mapvalue=new HashMap<String, String>();
		for(int i=0;i<list_temp.size();i++){
		   Object[] rowvalue=(Object[])list_temp.get(i);
		   mapvalue.put(rowvalue[0].toString(),rowvalue[1].toString());
		}
		return mapvalue;
	}
	@SuppressWarnings("rawtypes")
	@Override
	public Map<String, String> getmostregUserFeed() {
		Query hquery= sessionFactory.getCurrentSession().createQuery("select feedId.id,feedId.feedName,count(*) as regcount from userFeed group by feedId.id,feedId.feedName order by col_2_0_ desc");
		List list_temp= hquery.list();
		Map<String, String> mapvalue=new HashMap<String, String>();
		for(int i=0;i<list_temp.size();i++){
		   Object[] rowvalue=(Object[])list_temp.get(i);
		   mapvalue.put(rowvalue[0].toString(),rowvalue[1].toString());
		}
		return mapvalue;
	}
}
