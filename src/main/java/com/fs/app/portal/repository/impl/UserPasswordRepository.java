package com.fs.app.portal.repository.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fs.app.portal.pojo.UserPasswordPojo;
import com.fs.app.portal.repository.IUserPasswordRepository;

@Service
public class UserPasswordRepository implements IUserPasswordRepository {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Boolean addPassword(UserPasswordPojo userpswd) {
		try {
			sessionFactory.getCurrentSession().save(userpswd);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public UserPasswordPojo getUserPassword(String mail) {
		Query hquery = sessionFactory.getCurrentSession().createQuery(
				"from userPassword pwd where pwd.userMail=:mail");
		hquery.setString("mail", mail);
		List<UserPasswordPojo> list_pwds = (List<UserPasswordPojo>) hquery.list();
		if (list_pwds != null && list_pwds.size() > 0)
			return (UserPasswordPojo) list_pwds.get(0);
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public UserPasswordPojo getUserPasswordByUrl(String sendurl) {
		Query hquery = sessionFactory.getCurrentSession().createQuery(
				"from userPassword pwd where pwd.mark=:mark and pwd.status=1");
		hquery.setString("mark", sendurl);
		List<UserPasswordPojo> list_pwds = (List<UserPasswordPojo>) hquery.list();
		if (list_pwds != null && list_pwds.size() > 0)
			return (UserPasswordPojo) list_pwds.get(0);
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
		List<UserPasswordPojo> list_pwds = (List<UserPasswordPojo>) hquery.list();
		Transaction tx = session.beginTransaction();
		if (list_pwds != null) {
			try {
				for (UserPasswordPojo up : list_pwds) {
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