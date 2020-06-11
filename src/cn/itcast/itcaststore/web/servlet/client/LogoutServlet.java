package cn.itcast.itcaststore.web.servlet.client;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.listener.UserInfolist;

import cn.itcast.itcaststore.domain.User;
import cn.itcast.itcaststore.service.UserLoginService;

public class LogoutServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 获取session对象.
		HttpSession session = request.getSession();
		User user=(User) session.getAttribute("user");
		if (user!=null) {
			//插入登录时长到数据库
			UserLoginService uLoginService=new UserLoginService();
			try {
				uLoginService.updateLoginTime(user, request, response);
			} catch (SQLException e) {e.printStackTrace();}
			//在用户在线列表中移除该用户
	        UserInfolist List=UserInfolist.getInstance();
	        String username=user.getUsername();
	        List.removeUser(username);
		}
		
		// 销毁session
		session.invalidate();
		Cookie cookie = new Cookie("activeCode", "");
        response.addCookie(cookie);

        // 重定向到首页
        response.sendRedirect(request.getContextPath() + "/index.jsp");

	}
}