package cn.feisa.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.crypto.SecretKey;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import cn.feisa.mail.fsMail;
import cn.feisa.tools.encryptionAnddecryption;
import cn.lyf.bean.userInfo;
import cn.lyf.service.userInfoService;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class RegisterAction extends ActionSupport {
	private static final long serialVersionUID = 3321845277376234101L;
	private userInfo userinfo;
	private String username;
	private String password;
	private String email;
	private String phone;
	private Map<String, Object> jsonRoot = new HashMap<String, Object>();
	private String result;
	@Resource
	private userInfoService userService;

	public userInfo getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(userInfo userinfo) {
		this.userinfo = userinfo;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String register() {
		return "reg";
	}

	public String existemail() {
		if (this.getEmail()!="") {
			userInfo exituser = userService.getUserEmail(this.email);
			if (exituser != null)
				jsonRoot.put("existResult", true);
			else
				jsonRoot.put("existResult", false);
			JSONObject jo = JSONObject.fromObject(jsonRoot);
			this.result = jo.toString();
		}
		return Action.SUCCESS;
	}

	public String registerinfo() {
		if (this.getUsername() != null && this.getPassword() != null) {
			this.userinfo = new userInfo();
			this.userinfo.setUserName(this.username);
			this.userinfo.setUserEmail(this.email);
			this.userinfo.setUserPhone(this.phone);
			this.userinfo.setAuthLimit("1");
			this.userinfo.setCreTime(new java.util.Date());
			this.userinfo.setRegTime(new java.util.Date());
			encryptionAnddecryption jiami = new encryptionAnddecryption();
			SecretKey key = jiami.createSecretKey("DES");
			String str_password = jiami.encryptToDES(key, this.password);
			this.userinfo.setUserPassword(str_password);
			this.userinfo.setRemark(key.getEncoded());
			Boolean res = userService.addUser(getUserinfo());
			jsonRoot.put("registerResult", res);
			JSONObject jo = JSONObject.fromObject(jsonRoot);
			this.result = jo.toString();
			if (res) {
				SendUserEmail(this.email,this.username);
				this.userinfo = userService.getUserInfo(this.username,
						this.password);
				ServletActionContext.getRequest().getSession()
						.setMaxInactiveInterval(600);
				ServletActionContext.getRequest().getSession()
						.setAttribute("user", this.userinfo);
			}
		}
		return Action.SUCCESS;
	}
	/**
	 * 发送用户到邮箱
	 * @param to_mail 目标邮箱
	 * @param user_name 用户名称
	 */
	public void SendUserEmail(String to_mail,String user_name) {
		if (true) {
			StringBuffer sb = new StringBuffer();
			sb.append("亲爱的用户" + user_name + " ：您好！<br><br>");
			sb.append("        欢迎使用FS阅读，收这封电子邮件, 首先感谢你对FS阅读的认可,我们将用最大的能力给你带来舒适的阅读体验。<br><br>");
			sb.append("        你的登录账号为:"+user_name+"<br><br>");
			sb.append("<br><br><br>获取最新资讯，请访问FS主页：<a href='http://localhost:8080/FS/main'>http://localhost:8080/FS/main</a><br>");
			sb.append("访问个人订阅信息,请访问个人中心：<a href='http://localhost:8080/FS/person'>http://localhost:8080/FS/person</a><br>");
			sb.append("获取更多的订阅信息,请访问：<a href='http://localhost:8080/FS/feedmanage'>http://localhost:8080/FS/feedmanage</a><br>");
			sb.append("下载手机客户端，请访问移动App：<a href='http://localhost:8080/FS/download'>http://localhost:8080/FS/download</a><br>");
			sb.append("联系我们,洽谈合作请访问：<a href='http://localhost:8080/FS/about'>http://localhost:8080/FS/about</a><br>");
			sb.append("<br><br><br>我们将一如既往、热忱的为您服务！");
			sb.append("<br><br>http://localhost:8080/FS/ - 打造最好的阅读平台！");
			sb.append("<br>用户服务支持：<a href='mailto:feilongyouyuan@sina.com'>feilongyouyuan@sina.com</a><br><br><br>");
			String strm[] = to_mail.split("@");
			// 创建邮件
			fsMail mail = new fsMail();
			mail.setTo(to_mail);
			mail.setFrom("feilongyouyuan@sina.com");// 你的邮箱 feilongyouyuan@sina.com
			mail.setHost("smtp.sina.com.cn");
			mail.setUsername("feilongyouyuan@sina.com");// 用户 feilongyouyuan@sina.com
			mail.setPassword("3302213");// 密码 3302213
			mail.setSubject("[FS阅读] 欢迎使用FS阅读");
			mail.setContent(sb.toString());
			if (mail.sendMail()) {
				System.out.println("您的申请已提交成功，请查看您的******"
						+ strm[strm.length - 1] + "邮箱。");
			} else {
				System.out.println("操作失败，轻稍后重新尝试！");
			}
		}
	}

	public Map<String, Object> getJsonRoot() {
		return jsonRoot;
	}

	public void setJsonRoot(Map<String, Object> jsonRoot) {
		this.jsonRoot = jsonRoot;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
