package cn.itcast.itcaststore.web.servlet.client;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Vector;

import javax.security.auth.login.LoginException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.listener.UserInfoTrace;
import com.listener.UserInfolist;

import cn.itcast.itcaststore.domain.User;
import cn.itcast.itcaststore.service.UserLoginService;
import cn.itcast.itcaststore.service.UserService;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.获取登录页面输入的用户名与密码
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		// 2.调用service完成登录操作。
		UserService service = new UserService();
		UserLoginService uLoginService=new UserLoginService();
		try {
			User user = service.login(username, password);
			
			// 3.登录成功，将用户存储到session中.
			String activeCode=user.getActiveCode();
			String username1=user.getUsername();
			String PASSWORD1=user.getPassword();
			
			//监听用户上下线
			UserInfolist List=UserInfolist.getInstance();
			UserInfoTrace uTrace=new UserInfoTrace();
			uTrace.setUser(username1);                   //将username1设置为监听器的user
			request.getSession().setAttribute("list", uTrace);
			List.addUser(uTrace.getUser());         //将username1添加到用户列表
			String IP=request.getRemoteAddr();
			System.out.println("有新用户登录，IP地址为：  "+IP);
			 //插入用户登录时间和IP地址到数据库
			uLoginService.addLoginTime(user,IP,request, response);
			System.out.println("这里是LoginServlet,目前在线的用户有：");
			Vector vector=List.getList();  //输出目前在线的用户
			 if(vector!=null&&vector.size()>0){
				 for(int i=0;i<vector.size();i++){
					 System.out.println(vector.elementAt(i));
				 }
			 }
			
			//只要不关闭浏览器，每次重启服务器都能自动登录，或者关闭浏览器不超过1小时也能自动登录
			Cookie cookie1 = new Cookie("activeCode", activeCode);
			cookie1.setMaxAge(60*60);//cookie时间：1小时
            response.addCookie(cookie1);
            
            //自动填写登录表单,时间为1年，关闭浏览器也不消失
            Cookie cookie2 = new Cookie("activeCode2",username1+"#"+PASSWORD1);
            cookie2.setMaxAge(365*24*60*60);//cookie时间：1年
            response.addCookie(cookie2);
           
			request.getSession().setAttribute("user", user);
			request.getSession().setMaxInactiveInterval(30*60); //Session生命周期30分钟
			// 获取用户的角色，其中用户的角色分普通用户和超级用户两种
			String role = user.getRole();
			// 如果是超级用户，就进入到网上书城的后台管理系统；否则进入我的账户页面
			if ("超级用户".equals(role)) {
				response.sendRedirect(request.getContextPath() + "/admin/login/home.jsp");
				return;
			}else if ("销售人员".equals(role)) {
				response.sendRedirect(request.getContextPath() + "/salesman/login/home.jsp");
				return;
			} else {
				response.sendRedirect(request.getContextPath() + "/client/myAccount.jsp");
				return;
			}
		} catch (LoginException e) {
			// 如果出现问题，将错误信息存储到request范围，并跳转回登录页面显示错误信息
			e.printStackTrace();
			request.setAttribute("register_message", e.getMessage());
			request.getRequestDispatcher("/client/login.jsp").forward(request, response);
			return;
		} catch (SQLException e) {//抓住插入用户登录时间到数据库的错误
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
