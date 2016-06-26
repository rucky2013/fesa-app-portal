package cn.lyf.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import cn.lyf.bean.userPassword;
import cn.lyf.service.userPasswordService;

@Transactional
public class userPasswordServiceBean implements userPasswordService {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Boolean addPassword(userPassword userpswd) {
		try {
			sessionFactory.getCurrentSession().save(userpswd);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public userPassword getUserPassword(String mail) {
		Query hquery = sessionFactory.getCurrentSession().createQuery(
				"from userPassword pwd where pwd.userMail=:mail");
		hquery.setString("mail", mail);
		List<userPassword> list_pwds = (List<userPassword>) hquery.list();
		if (list_pwds != null && list_pwds.size() > 0)
			return (userPassword) list_pwds.get(0);
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public userPassword getUserPasswordByUrl(String sendurl) {
		Query hquery = sessionFactory.getCurrentSession().createQuery(
				"from userPassword pwd where pwd.mark=:mark and pwd.status=1");
		hquery.setString("mark", sendurl);
		List<userPassword> list_pwds = (List<userPassword>) hquery.list();
		if (list_pwds != null && list_pwds.size() > 0)
			return (userPassword) list_pwds.get(0);
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Boolean updatePwd(Integer uid, String newPwd) {
		// Query hquery = sessionFactory.getCurrentSession().createQuery(
		// "from userPassword pwd where pwd.userId=:uid and pwd.status=1");
		// hquery.setInteger("uid", uid);
		Session session = sessionFactory.openSession();
		Query hquery = session
				.createQuery("from userPassword pwd where pwd.userId=:uid and pwd.status=1");
		hquery.setInteger("uid", uid);
		List<userPassword> list_pwds = (List<userPassword>) hquery.list();
		Transaction tx = session.beginTransaction();
		if (list_pwds != null) {
			try {
				for (userPassword up : list_pwds) {
					up.setUpdateTime(new Date());
					up.setStatus("2");
					sessionFactory.getCurrentSession().update(up);
				}
				tx.commit();
				return true;
			} catch (Exception ex) {
				tx.rollback();
				return false;
			} finally {
			   session.close();
			}
		} else{
			session.close();
			return false;
		}
	}
}
