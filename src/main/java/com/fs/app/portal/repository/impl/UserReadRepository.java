package com.fs.app.portal.repository.impl;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.fs.app.portal.pojo.BaseFeedDetailPojo;
import com.fs.app.portal.pojo.UserReadPojo;
import com.fs.app.portal.repository.IUserReadRepository;

@Repository
@Transactional
public class UserReadRepository implements IUserReadRepository {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Boolean addUserRead(UserReadPojo userred) {
		try {
			sessionFactory.getCurrentSession().save(userred);
			return true;
		} catch (Exception ex) {
			System.out.println(ex.toString());
			return false;
		}
	}

	@Override
	public Boolean removeUserRead(UserReadPojo userred) {
		try {
			sessionFactory.getCurrentSession().delete(userred);
			return true;
		} catch (Exception ex) {
			System.out.println(ex.toString());
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserReadPojo> getUserReds(int uid, int speid, int fedid,int detailid) {
		try {
			Query hquery = sessionFactory
					.getCurrentSession()
					.createQuery(
							"from userRead ufed where ufed.userId=:uid and ufed.speciesId=:speid and ufed.feedId=:fid and ufed.detailId=:detid");
			hquery.setInteger("uid", uid);
			hquery.setInteger("speid", speid);
			hquery.setInteger("fid", fedid);
			hquery.setInteger("detid", detailid);
			List<UserReadPojo> list_result = hquery.list();
			if (list_result != null && list_result.size() > 0)
				return list_result;
			else
				return null;
		} catch (Exception ex) {
			System.out.println(ex.toString());
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<UserReadPojo> getUserRedsbyUid(int uid) {
		try {
			Query hquery = sessionFactory.getCurrentSession().createQuery(
					"from userRead ufed where ufed.userId=:uid");
			hquery.setInteger("uid", uid);
			List<UserReadPojo> list_result = hquery.list();
			if (list_result != null && list_result.size() > 0)
				return list_result;
			else
				return null;
		} catch (Exception ex) {
			System.out.println(ex.toString());
			return null;
		}
	}

	@Override
	public Boolean updateUserRead(UserReadPojo userred) {
		try {
			sessionFactory.getCurrentSession().update(userred);
            return true;
		} catch (Exception ex) {
			System.out.println(ex.toString());
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BaseFeedDetailPojo> getmostReads(int index) {
		//Query hquery= sessionFactory.getCurrentSession().createQuery("select d from userRead as r,baseFeedDetail as d where r.detailId=d.Id group by r.detailId order by readCount desc");
		Query hquery= sessionFactory.getCurrentSession().createQuery("from baseFeedDetail");
		hquery.setFirstResult(index);
		hquery.setMaxResults(20);
		List<BaseFeedDetailPojo> list_temp= hquery.list();
		return list_temp;
	}
}
