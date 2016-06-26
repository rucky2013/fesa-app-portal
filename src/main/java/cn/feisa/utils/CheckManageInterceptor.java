package cn.feisa.utils;

import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import org.apache.struts2.ServletActionContext;
import cn.lyf.bean.userInfo;
import cn.lyf.service.userInfoService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@SuppressWarnings("serial")
public class CheckManageInterceptor extends AbstractInterceptor {

	@Resource
	private userInfoService userservice;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Map<String, Object> session = invocation.getInvocationContext().getSession();
		userInfo user = (userInfo)session.get("manage");
		if (null != user && user.getUserName().equals("admin")&&user.getUserPassword().equals("PASSword")) {
			ActionContext tt= invocation.getInvocationContext();
			return invocation.invoke();
		} else {
			Cookie[] reqcookies = ServletActionContext.getRequest()
					.getCookies();
			if (reqcookies != null) {
				for (Cookie cookie : reqcookies) {
					if (cookie.getName().toString().equals("feisaadmin")) {
						System.out.println("保存了用的信息，可以自动登陆了:"
								+ cookie.getName() + ":" + cookie.getValue());
						String cookievalue = cookie.getValue().toString();
						String[] temp = cookievalue.split("\\^");
						if (temp.length == 2) {
							userInfo tempuser = new userInfo();
							tempuser.setUserName(temp[0]);
							tempuser.setUserPassword(temp[1]);
							if (tempuser != null) {
								ServletActionContext.getRequest().getSession()
										.setMaxInactiveInterval(900);
								ServletActionContext.getRequest().getSession()
										.setAttribute("manage", tempuser);
								return invocation.invoke();
							}
						}
					}
				}
			}
			System.out.println("拦截器:未登录用户");
			return "Managelogin";
		}
	}
}
