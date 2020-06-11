package cn.itcast.itcaststore.web.servlet.manager;
import java.io.IOException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.itcast.itcaststore.domain.Product;
import cn.itcast.itcaststore.domain.User;
import cn.itcast.itcaststore.service.ProductService;
public class FindProductByManyConditionServlet extends HttpServlet {
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
		//1.获取表单数据
		String id = request.getParameter("id"); // 商品id
		String name = request.getParameter("name"); // 商品名称
		String minprice = request.getParameter("minprice"); // 最小价格
		String maxprice = request.getParameter("maxprice"); // 最大价格    
		String category = request.getParameter("category"); // 商品类别
		// 2.创建ProductService对象
		ProductService service = new ProductService();
		User user=(User)request.getSession().getAttribute("user");
		String role =" ";
		if (user!=null) {
			role =user.getRole();
		}
		if ("销售人员".equals(role)) {//销售人员的查询
			String product_category = user.getProduct_category();
			List<Product> ps = service.findProductByManyCondition(id, name,product_category, minprice, maxprice);
			request.setAttribute("ps", ps);
			String[] productIds=new String[ps.size()];  //获得当类商品的所有id
			for (int i = 0; i < ps.size(); i++) {
				productIds[i]=ps.get(i).getId();
			}
			//获取当前时间
			String logintime=getDatetime();
			String[] time=new String[3];
			time=URLDecoder.decode(logintime, "UTF-8").split("-");
			
			//获取商品当月销量
			List<Object[]> numbers ;
			int[] nums = new int[ps.size()];
			double[] moneys = new double[ps.size()];
			for (int i = 0; i < ps.size(); i++) {
				numbers=service.salesnumber(time[0],time[1],productIds[i]);
				if (numbers!=null) {
					for (int j = 0; j < numbers.size(); j++) {
						Object[] arr=numbers.get(0);
						nums[i]=Integer.parseInt(String.valueOf(arr[0]));
						moneys[i]=nums[i]*ps.get(i).getPrice();
					}
				}
			}			
			
			request.setAttribute("moneys", moneys);
			request.setAttribute("nums", nums);
			request.getRequestDispatcher("/salesman/products/list.jsp").forward(request, response);
		}else {//管理员的查询
			// 3.调用service层用于条件查询的方法
			List<Product> ps = service.findProductByManyCondition(id, name,category, minprice, maxprice);
			String[] productIds=new String[ps.size()];  //获得当类商品的所有id
			for (int i = 0; i < ps.size(); i++) {
				productIds[i]=ps.get(i).getId();
			}
			//获取当前时间
			String logintime=getDatetime();
			String[] time=new String[3];
			time=URLDecoder.decode(logintime, "UTF-8").split("-");
			
			//获取商品当月销量
			List<Object[]> numbers ;
			int[] nums = new int[ps.size()];
			double[] moneys = new double[ps.size()];
			for (int i = 0; i < ps.size(); i++) {
				numbers=service.salesnumber(time[0],time[1],productIds[i]);
				if (numbers!=null) {
					for (int j = 0; j < numbers.size(); j++) {
						Object[] arr=numbers.get(0);
						nums[i]=Integer.parseInt(String.valueOf(arr[0]));
						moneys[i]=nums[i]*ps.get(i).getPrice();
					}
				}
			}
			// 4.将条件查询的结果放进request域中
			request.setAttribute("ps", ps);
			request.setAttribute("moneys", moneys);
			request.setAttribute("nums", nums);
			// 5.请求重定向到商品管理首页list.jsp页面
			request.getRequestDispatcher("/admin/products/list.jsp").forward(request, response);
		}
	}
}
