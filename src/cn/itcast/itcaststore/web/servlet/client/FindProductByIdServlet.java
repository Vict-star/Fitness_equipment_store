package cn.itcast.itcaststore.web.servlet.client;

import java.io.IOException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.itcast.itcaststore.domain.Product;
import cn.itcast.itcaststore.exception.FindProductByIdException;
import cn.itcast.itcaststore.service.ProductService;
/**
 * 根据商品id查找指定商品信息的servlet
 */
public class FindProductByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public String getDatetime() {
		//获取当前时间
		String datetime;
		java.util.Date currDate=Calendar.getInstance().getTime();
		//实例化SimpleDateFormat
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		//格式化日期时间
		datetime=sdf.format(currDate);
		return datetime;
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 得到商品的id
		String id = request.getParameter("id");
		// 获取type参数值，此处的type用于区别普通用户和超级用户
		String type = request.getParameter("type");		
		ProductService service = new ProductService();		
		try {
			// 调用service层方法，通过id查找商品
			Product p = service.findProductById(id);
			request.setAttribute("p", p);

			//获取当前时间
			String logintime=getDatetime();
			String[] time=new String[3];
			time=URLDecoder.decode(logintime, "UTF-8").split("-");
			//获取商品当月销量
			List<Object[]> number = service.salesnumber(time[0],time[1],id);
			String num="0";
			Object[] arr=null;
			for (int i = 0; i < number.size(); i++) {
				arr=number.get(i);
				num=String.valueOf(arr[0]);			
			}
			request.setAttribute("num", num);
			
			// 普通用户默认不传递type值，会跳转到info.jsp页面
			if (type == null) {
				request.getRequestDispatcher("/client/info.jsp").forward(request,response);
				return;
			}						
			request.getRequestDispatcher("/admin/products/edit.jsp").forward(request, response);
			return;
		} catch (FindProductByIdException e) {
			e.printStackTrace();
		}
	}
}
