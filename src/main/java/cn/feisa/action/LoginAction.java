package cn.feisa.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import cn.lyf.bean.userInfo;
import cn.lyf.service.userInfoService;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {
	private static final long serialVersionUID = 3321845277376234101L;
	private userInfo userinfo;
	private String userId;
	private String username;
	private String password;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String login() {
		if (this.getUsername() != null && this.getPassword() != null) {
			this.userinfo=userService.getUserInfo(this.username,this.password);
			Boolean res = false;
			if(this.userinfo!=null&&this.userinfo.getId()>0)
				res=true;
			jsonRoot.put("logeResult", res);
			JSONObject jo = JSONObject.fromObject(jsonRoot);
			this.result = jo.toString();
			if (res) {
				ServletActionContext.getRequest().getSession()
						.setMaxInactiveInterval(900);
				ServletActionContext.getRequest().getSession()
						.setAttribute("user", this.userinfo);
				//设置session的过期时间是为了让服务器的连接性能提高，即把那些长时间没有响应的用户断掉连接
				//设置cookies的过期时间是为了让在一段时间范围内用户访问网站时能够自动登陆
				Boolean existcookies=false;
				Cookie[] reqcookies= ServletActionContext.getRequest().getCookies();
				for (Cookie cookie : reqcookies) {
					if(cookie.getName().toString().equals("feisa")){
						existcookies=true;
					}
				}
				if (!existcookies) {
					Cookie userCookie = new Cookie("feisa", this.username+"^"+this.userinfo.getUserPassword());
					userCookie.setMaxAge(60 * 60 * 24);
					ServletActionContext.getResponse().addCookie(userCookie);
					System.out.println("Cookies保存成功");
				}
			}
		}
		return Action.SUCCESS;
	}

	public String loginout() {
		try {
			ServletActionContext.getRequest().getSession().invalidate();
			Cookie userCookie = new Cookie("feisa","");
			userCookie.setMaxAge(0);
			ServletActionContext.getResponse().addCookie(userCookie);
			jsonRoot.put("loginoutResult", true);
		} catch (Exception ex) {
			jsonRoot.put("loginoutResult", false);
		}
		JSONObject jo = JSONObject.fromObject(jsonRoot);
		this.result = jo.toString();
		return Action.SUCCESS;
	}
	public String loginpage(){
		return "loginpage";
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

}
