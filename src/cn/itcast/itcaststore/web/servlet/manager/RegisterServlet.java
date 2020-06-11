package cn.itcast.itcaststore.web.servlet.manager;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import cn.itcast.itcaststore.domain.User;
import cn.itcast.itcaststore.exception.RegisterException;
import cn.itcast.itcaststore.service.UserService;
import cn.itcast.itcaststore.utils.ActiveCodeUtils;
//销售人员注册
@WebServlet("/SalesmenRegister")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		UserService uService=new UserService();
		User user=new User();
		try {
			BeanUtils.populate(user, request.getParameterMap());  // 将表单提交的数据封装到javaBean
			uService.addSalesman(user);
			// 注册成功，跳转到registersuccess.jsp
			response.sendRedirect(request.getContextPath() + "/admin/salesmen/registersuccess.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
