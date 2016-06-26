package cn.feisa.mail;

import java.security.spec.KeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import net.sf.json.JSONObject;

import cn.feisa.tools.encryptionAnddecryption;
import cn.lyf.bean.userPassword;
import cn.lyf.service.userInfoService;
import cn.lyf.service.userPasswordService;

import com.opensymphony.xwork2.Action;

public class missingPwdAction {
	private String tomail;
	private String frommail = "feilongyouyuan@sina.com";
	private String name = "feilongyouyuan@sina.com";
	private String password = "3302213";
	private Map<String, Object> jsonRoot = new HashMap<String, Object>();
	private String result;
	private String message;
	private String m;
	private String u;
	private String newPwd;
	@Resource
	private userPasswordService userPwd;
	@Resource
	private userInfoService userInfo;

	public String sendEmail() {
		cn.lyf.bean.userInfo uinfo = userInfo.getUserEmail(tomail);
		userPassword upassword = new userPassword();
		upassword.setUserId(uinfo.getId());
		encryptionAnddecryption jiami = new encryptionAnddecryption();
		SecretKey key = jiami.createSecretKey("DES");
		String str_mail = jiami.encryptToDES(key, tomail);
		String str_uid = jiami.encryptToDES(key, uinfo.getId().toString());
		upassword.setMark("http://localhost:8080/FS/feisa/resetPwd?m="
				+ str_mail + "&u=" + str_uid);
		byte[] keybyt = key.getEncoded();
		upassword.setEncryKey(keybyt);
		upassword.setStatus("1");
		upassword.setOldPassword(uinfo.getUserPassword());
		upassword.setAddTime(new Date());
		upassword.setUpdateTime(null);
		userPwd.addPassword(upassword);
		findPassWord(tomail, frommail, name, password, upassword.getMark(),
				uinfo.getUserName());
		jsonRoot.put("SentResult", true);
		JSONObject jo = JSONObject.fromObject(jsonRoot);
		this.result = jo.toString();
		return Action.SUCCESS;
	}
// 出现重复的方法，参考registerAction的 existemail方法
//	public String exitEmail() {
//		cn.lyf.bean.userInfo uinfo = userInfo.getUserEmail(tomail);
//		if (uinfo != null)
//			jsonRoot.put("exitPwd", true);
//		else
//			jsonRoot.put("exitPwd", false);
//		JSONObject jo = JSONObject.fromObject(jsonRoot);
//		this.result = jo.toString();
//		return Action.SUCCESS;
//	}

	/**
	 * 找回密码
	 */
	public void findPassWord(String to_mail, String from_mail,
			String user_name, String user_pswd, String send_url, String _name) {
		if (true) {
			//String toMail = tomail;// "244328187@qq.com"
			StringBuffer sb = new StringBuffer();
			sb.append("亲爱的用户" + _name + " ：您好！<br><br>");
			sb.append("        您收到这封这封电子邮件是因为您 (也可能是某人冒充您的名义) 申请了一个新的密码。假如这不是您本人所申请, 请不用理会<br>这封电子邮件, 但是如果您持续收到这类的信件骚扰, 请您尽快联络管理员。<br><br>");
			sb.append("        要使用新的密码, 请使用以下链接启用密码。<br><br>");
			sb.append("        <a href='" + send_url + "'>" + send_url + "</a>");
			sb.append("<br><br><br>获取最新资讯，请访问FS主页：<a href='http://localhost:8080/FS/main'>http://localhost:8080/FS/main</a><br>");
			sb.append("访问个人订阅信息,请访问个人中心：<a href='http://localhost:8080/FS/person'>http://localhost:8080/FS/person</a><br>");
			sb.append("获取更多的订阅信息,请访问：<a href='http://localhost:8080/FS/feedmanage'>http://localhost:8080/FS/feedmanage</a><br>");
			sb.append("下载手机客户端，请访问移动App：<a href='http://localhost:8080/FS/download'>http://localhost:8080/FS/download</a><br>");
			sb.append("联系我们,洽谈合作请访问：<a href='http://localhost:8080/FS/about'>http://localhost:8080/FS/about</a><br>");
			sb.append("<br><br><br>我们将一如既往、热忱的为您服务！");
			sb.append("<br><br>http://localhost:8080/FS/ - 打造最好的阅读平台！");
			sb.append("<br>用户服务支持：<a href='mailto:feilongyouyuan@sina.com'>feilongyouyuan@sina.com</a><br><br><br>");
			/** strm[1]第一个跟第二个@间内容,strm[strm.length - 1]最后一@内容 */
			String strm[] = tomail.split("@");
			// 创建邮件
			fsMail mail = new fsMail();
			mail.setTo(tomail);
			mail.setFrom(frommail);// 你的邮箱 feilongyouyuan@sina.com
			mail.setHost("smtp.sina.com.cn");
			mail.setUsername(name);// 用户 feilongyouyuan@sina.com
			mail.setPassword(password);// 密码 3302213
			mail.setSubject("[FS阅读]找回您的账户密码");
			mail.setContent(sb.toString());
			if (mail.sendMail()) {
				System.out.println("您的申请已提交成功，请查看您的******"
						+ strm[strm.length - 1] + "邮箱。");
			} else {
				System.out.println("操作失败，轻稍后重新尝试！");
			}
		}
	}

	public String missingpassword() {
		return "miss";
	}

	public String resetpassword() {
		encryptionAnddecryption jiami = new encryptionAnddecryption();
		String url_temp = "http://localhost:8080/FS/feisa/resetPwd?m=" + m
				+ "&u=" + u;
		userPassword userpwds_temp = userPwd.getUserPasswordByUrl(url_temp);
		if (userpwds_temp != null) {
			byte[] bts = userpwds_temp.getEncryKey();
			try {
				KeySpec keySpec = new DESKeySpec(bts);
				SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
				SecretKey key = factory.generateSecret(keySpec);
				// SecretKey key=new SecretKeySpec(bts, "DES");
				String email_temp = jiami.decryptByDES(key, m);
				String uid_temp = jiami.decryptByDES(key, u);
				m = email_temp;
				u = uid_temp;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "reset";
		}else{
			this.message="修改密码连接已过时，请重新点击发送！";
			return "error";
		}
	}

	public String changePwd() {
		if (newPwd != "") {
			Boolean charesult = userInfo.changePassword(Integer.valueOf(u),
					newPwd);
			if (charesult)
				userPwd.updatePwd(Integer.valueOf(u), newPwd);
			jsonRoot.put("chaneResult", charesult);
		} else
			jsonRoot.put("chaneResult", false);
		JSONObject jo = JSONObject.fromObject(jsonRoot);
		this.result = jo.toString();
		System.out.println(this.result);
		return Action.SUCCESS;
	}

	public String getTomail() {
		return tomail;
	}

	public void setTomail(String tomail) {
		this.tomail = tomail;
	}

	public String getFrommail() {
		return frommail;
	}

	public void setFrommail(String frommail) {
		this.frommail = frommail;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getM() {
		return m;
	}

	public void setM(String m) {
		this.m = m;
	}

	public String getU() {
		return u;
	}

	public void setU(String u) {
		this.u = u;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
