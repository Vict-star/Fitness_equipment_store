package cn.itcast.itcaststore.web.servlet.client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.jasper.tagplugins.jstl.core.Out;

import cn.itcast.itcaststore.domain.User;
import cn.itcast.itcaststore.exception.RegisterException;
import cn.itcast.itcaststore.service.UserService;
import cn.itcast.itcaststore.utils.ActiveCodeUtils;


public class UpdateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		try {
			BeanUtils.populate(user, request.getParameterMap());  // 将表单提交的数据封装到javaBean
			User user1 = (User)request.getSession().getAttribute("user");
			int id=user1.getId();
			user.setId(id);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		UserService service = new UserService();
		try {
			service.updateuser(user);
		} catch (RegisterException e) {
			e.printStackTrace();
			response.getWriter().write(e.getMessage());//获取UserService更新失败的信息，再打印到后台
			return;
		}
		// 更新成功，跳转到updateusersuccess.jsp
		response.sendRedirect(request.getContextPath() + "/client/updateusersuccess.jsp");
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
