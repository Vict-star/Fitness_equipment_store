package cn.itcast.itcaststore.web.servlet.client;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.itcast.itcaststore.domain.Order;
import cn.itcast.itcaststore.service.OrderService;
/**
 * 通过id查询订单
 * @author admin
 *
 */
public class FindOrderByIdServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.获取用户类型，看是前端的查询还是后端的查询
		String type=request.getParameter("type");		
		//2.得到要查询的订单的id（传过来的是id：orderlist.jsp点击查看和后台页面查找商品，传过来的是orderid：orderInfo.jsp点击评价）
		String id = request.getParameter("id");
		String orderid = request.getParameter("orderid");
		//3.根据id查找订单
		OrderService service = new OrderService();
		if (id!=null) {//不是去评价页面
			Order order = service.findOrderById(id);
	        //4.将查询出的订单信息添加到request作用域中
			request.setAttribute("order", order);
			//5.如果用户类型不为null，则请求转发到view.jsp页面（后端），否则转发到orderInfo.jsp页面（前端）
			if(type!=null){
				request.getRequestDispatcher("/admin/orders/view.jsp").forward(request, response);
				return;
			}
			request.getRequestDispatcher("/client/orderInfo.jsp").forward(request, response);
			return;
		}
		//去评价页面
		Order order1 = service.findOrderById(orderid);
		request.setAttribute("order", order1);
		request.getRequestDispatcher("/client/evaluate.jsp").forward(request, response);
	}
}
