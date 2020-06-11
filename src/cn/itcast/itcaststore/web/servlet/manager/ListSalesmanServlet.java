package cn.itcast.itcaststore.web.servlet.manager;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.itcast.itcaststore.domain.Product;
import cn.itcast.itcaststore.domain.User;
import cn.itcast.itcaststore.exception.ListProductException;
import cn.itcast.itcaststore.service.ProductService;
import cn.itcast.itcaststore.service.UserService;
/**
 * 后台系统
 * 查询所有商品信息的销售人员
 */
@WebServlet("/ListSalesman")
public class ListSalesmanServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.创建service层的对象
		UserService uService =new UserService();
		try {
			// 2.调用service层用于查询所有商品的方法
			List<User> uList = uService.listAll();
			// 3.将查询出的所有商品放进request域中
			request.setAttribute("uList", uList);
			// 4.重定向到list.jsp页面
			request.getRequestDispatcher("/admin/salesmen/salesmenlist.jsp").forward(request, response);
			return;
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write(e.getMessage());
			return;
		}
	}
}
