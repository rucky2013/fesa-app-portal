package cn.feisa.utils;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import org.apache.struts2.ServletActionContext;
import cn.feisa.action.LoginAction;
import cn.lyf.bean.userInfo;
import cn.lyf.service.userInfoService;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@SuppressWarnings("serial")
public class CheckLoginInterceptor extends AbstractInterceptor {

	@Resource
	private userInfoService userservice;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Object action = invocation.getAction();
		if (action instanceof LoginAction) {
			return invocation.invoke();
		}
		Map<String, Object> session = invocation.getInvocationContext()
				.getSession();
		userInfo user = (userInfo) session.get("user");
		// Map<String,Object>
		// paras=invocation.getInvocationContext().getParameters();
		// System.out.println("参数:"+JSONObject.fromObject(paras).toString());
		if (null != user && !user.getUserName().equals("")) {
			System.out.println("拦截器:合法用户登录一");
			return invocation.invoke();
		} else {
			Cookie[] reqcookies = ServletActionContext.getRequest()
					.getCookies();
			if (reqcookies != null) {
				for (Cookie cookie : reqcookies) {
					if (cookie.getName().toString().equals("feisa")) {
						System.out.println("保存了用的信息，可以自动登陆了:"
								+ cookie.getName() + ":" + cookie.getValue());
						String cookievalue = cookie.getValue().toString();
						String[] temp = cookievalue.split("\\^");
						if (temp.length == 2) {
							userInfo tempuser = userservice.getUserInfoBymark(
									temp[0].toString(), temp[1].toString());
							if (tempuser != null) {
								ServletActionContext.getRequest().getSession()
										.setMaxInactiveInterval(900);
								ServletActionContext.getRequest().getSession()
										.setAttribute("user", tempuser);
								return invocation.invoke();
							}
						}
					}
				}
			}
			System.out.println("拦截器:未登录用户");
			return Action.LOGIN;
		}
	}
}
