package cn.itcast.itcaststore.web.servlet.client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.itcaststore.exception.ActiveUserException;
import cn.itcast.itcaststore.service.OrderService;
@WebServlet("/changeSendState")
public class ChangeSendStateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.获取订单id
		String orderid=request.getParameter("orderid");
		OrderService service = new OrderService();
		try {
			service.updateSendState(orderid);
			response.sendRedirect(request.getContextPath() + "/client/sendsuccess.jsp");
			return;
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
