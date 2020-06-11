package cn.itcast.itcaststore.web.servlet.client;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import cn.itcast.itcaststore.domain.User;
import cn.itcast.itcaststore.exception.RegisterException;
import cn.itcast.itcaststore.service.UserService;
import cn.itcast.itcaststore.utils.ActiveCodeUtils;

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//校验验证码
		String userCode = request .getParameter ("userCode") ;    //获取用户输入的验证码
		String serverCode = (String) request .getSession() .getAttribute ("checkcode_session") ;   //获取系统生成的验证码
		if (! serverCode . equalsIgnoreCase (userCode) ) {   //校验验证码
			request . setAttribute ("codeError", "输入验证码错误");
			request .getRequestDispatcher ("/client/register.jsp").forward (request, response);   //回送错误信息
			return;  //不让下面的代码执行
		}
		// 将表单提交的数据封装到javaBean
		User user = new User();
		try {
			BeanUtils.populate(user, request.getParameterMap());  // 将表单提交的数据封装到javaBean
			// 封裝激活码
			user.setActiveCode(ActiveCodeUtils.createActiveCode());
			} catch (Exception e) {
				e.printStackTrace();
			} 
		// 调用service完成注册操作。
		UserService service = new UserService();
		try {
			service.register(user);
		} catch (RegisterException e) {
			e.printStackTrace();
			response.getWriter().write(e.getMessage());//获取UserService注册失败的信息，再打印到后台
			return;
		}
		// 注册成功，跳转到registersuccess.jsp
		response.sendRedirect(request.getContextPath() + "/client/registersuccess.jsp");
		return;
	}
}
