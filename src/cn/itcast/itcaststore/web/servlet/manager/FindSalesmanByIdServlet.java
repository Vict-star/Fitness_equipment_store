package cn.itcast.itcaststore.web.servlet.manager;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.itcaststore.domain.Product;
import cn.itcast.itcaststore.domain.User;
import cn.itcast.itcaststore.exception.FindProductByIdException;
import cn.itcast.itcaststore.service.UserService;
@WebServlet("/FindSalesmanById")
public class FindSalesmanByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 得到销售人员的id
		String id =request.getParameter("id");
		int uid = Integer.parseInt(id);
		UserService uService=new UserService();
		try {
			// 调用service层方法，通过id查找销售人员
			User user=uService.findUserById(uid);
			request.setAttribute("u", user);	
			request.getRequestDispatcher("/admin/salesmen/updatesalesman.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
