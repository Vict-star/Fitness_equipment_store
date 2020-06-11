package cn.itcast.itcaststore.tag;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import cn.itcast.itcaststore.domain.User;
import cn.itcast.itcaststore.exception.ActiveUserException;
import cn.itcast.itcaststore.service.UserService;
//过滤器，未登录不许操作，可用于购物车等页面
public class PrivilegeTag extends SimpleTagSupport {
	@Override
	public void doTag() throws JspException, IOException {
		PageContext context = (PageContext) this.getJspContext();
		HttpServletRequest request = (HttpServletRequest) context.getRequest();
		HttpServletResponse response = (HttpServletResponse) context.getResponse();
		
		User user = (User) context.getSession().getAttribute("user");
		if (user == null) {
			Cookie[] cooks=request.getCookies();
		    if(cooks!=null){
		    	UserService userService=new UserService();
		    	for(int i=0;i<cooks.length;i++){
		        	if(cooks[i].getName().endsWith("activeCode")){
		        		try {
		        			String activeCode=cooks[i].getValue();
		        			user = userService.findUserByActiveCode(activeCode);
							request.getSession().setAttribute("user", user);
						} catch (ActiveUserException e) {
							e.printStackTrace();
						}
		        	}
		        }
			}
		}
		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/client/error/privilege.jsp");
		}
	}
}
