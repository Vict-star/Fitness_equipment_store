package cn.itcast.itcaststore.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.itcaststore.domain.User;
import cn.itcast.itcaststore.exception.ActiveUserException;
import cn.itcast.itcaststore.service.UserService;

public class AdminPrivilegeFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		// 1 强制转换
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		// 2判断是否具有权限
		User user = (User) request.getSession().getAttribute("user");
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
		if (user != null && "超级用户".equals(user.getRole())) {
			// 3.放行
			chain.doFilter(request, response);
			return;
		}else if (user == null) {
			response.sendRedirect(request.getContextPath() + "/error/privilege.jsp");
		}else {
			response.sendRedirect(request.getContextPath() + "/index.jsp");
		}
	}

	public void destroy() {
	}
}
