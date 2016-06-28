package cn.lyf.service.impl;

import java.security.spec.KeySpec;
import java.util.ArrayList;
import java.util.List;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.feisa.tools.encryptionAnddecryption;
import cn.lyf.bean.userInfo;
import cn.lyf.service.userInfoService;

@Service
public class userInfoServiceBean implements userInfoService {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Boolean addUser(userInfo userinfo) {
		try {
			sessionFactory.getCurrentSession().save(userinfo);
			return true;
		} catch (Exception ex) {
			System.out.println(ex.toString());
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	/**
	 * 以明文方式传递的用户名和密码
	 */
	public userInfo getUserInfo(String name, String pswd) {
		try {
			Query hquery = sessionFactory
					.getCurrentSession()
					.createQuery(
							"from userInfo uinfo where uinfo.userName=:uname");
			hquery.setString("uname", name);
			List<userInfo> list = hquery.list();
			if (list != null && list.size()>0){
				encryptionAnddecryption jiami = new encryptionAnddecryption();
				userInfo result=null;
				for (userInfo uInfo : list) {
					byte[] bts = uInfo.getRemark();
					KeySpec keySpec = new DESKeySpec(bts);
					SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
					SecretKey key = factory.generateSecret(keySpec);
					String pwd_temp = jiami.decryptByDES(key, uInfo.getUserPassword());
					if(pwd_temp.equals(pswd))
						result=uInfo;
				}
				return result;
			}
			else
				return null;
		} catch (Exception ex) {
			System.out.println(ex.toString());
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	/**
	 * 以明文用户名称+密文密码
	 */
	public userInfo getUserInfoBymark(String name, String pswd) {
		try {
			Query hquery = sessionFactory
					.getCurrentSession()
					.createQuery(
							"from userInfo uinfo where uinfo.userName=:uname");
			hquery.setString("uname", name);
			List<userInfo> list = hquery.list();
			if (list != null && list.size()>0){
				encryptionAnddecryption jiami = new encryptionAnddecryption();
				userInfo result=null;
				for (userInfo uInfo : list) {
					if(uInfo.getUserPassword().equals(pswd))
						result=uInfo;
				}
				return result;
			}
			else
				return null;
		} catch (Exception ex) {
			System.out.println(ex.toString());
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public userInfo getUserEmail(String email) {
		try {
			Query hquery = sessionFactory.getCurrentSession().createQuery(
					"from userInfo uinfo where uinfo.userEmail=:email");
			hquery.setString("email", email);
			List<userInfo> list = hquery.list();
			if (list != null && list.size() == 1)
				return list.get(0);
			else
				return null;
		} catch (Exception ex) {
			System.out.println(ex.toString());
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public Boolean changePassword(int uid, String newpwd) {
		try {
			Query hquery = sessionFactory.getCurrentSession().createQuery(
					"from userInfo uinfo where uinfo.Id=:uid");
			hquery.setInteger("uid", uid);
			List<userInfo> list_users = (List<userInfo>)hquery.list();
			if (list_users != null && list_users.size() > 0) {
               userInfo temp_user=list_users.get(0);
               
               encryptionAnddecryption jiami = new encryptionAnddecryption();
   			   SecretKey key = jiami.createSecretKey("DES");
   			   String str_password = jiami.encryptToDES(key, newpwd);
               temp_user.setUserPassword(str_password);
               temp_user.setRemark(key.getEncoded());
               sessionFactory.getCurrentSession().update(temp_user);
               return true;
			}else
				return false;
		} catch (Exception ex) {
			System.out.println(ex.toString());
			return false;
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<userInfo> getAllUsers() {
		List<userInfo> list_result=new ArrayList<userInfo>();
		try {
			Query hquery = sessionFactory.getCurrentSession().createQuery(
					"from userInfo");
			list_result = hquery.list();
			if (list_result != null)
				return list_result;
			else
				return null;
		} catch (Exception ex) {
			System.out.println(ex.toString());
			return null;
		}
	}

}
