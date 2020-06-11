package cn.itcast.itcaststore.web.servlet.client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.itcaststore.service.OrderService;
//确认收货
@WebServlet("/Receive")
public class ReceiveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String orderid=request.getParameter("orderid");
		OrderService service = new OrderService();
		try {
			service.updateReceiveState(orderid);
			response.sendRedirect(request.getContextPath() + "/client/receivesuccess.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}

}
