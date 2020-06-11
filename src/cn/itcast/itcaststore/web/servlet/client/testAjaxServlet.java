package cn.itcast.itcaststore.web.servlet.client;

import java.io.IOException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.itcaststore.domain.BrowseRecord;
import cn.itcast.itcaststore.domain.Product;
import cn.itcast.itcaststore.domain.User;
import cn.itcast.itcaststore.service.BrowseRecordService;
import cn.itcast.itcaststore.service.ProductService;
@WebServlet("/testAjax")
//通过前端的js获取用户停留在某个商品的时长
public class testAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	public String getDatetime() {
		//获取当前时间
		String datetime;
		java.util.Date currDate=Calendar.getInstance().getTime();
		//实例化SimpleDateFormat
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		//格式化日期时间
		datetime=sdf.format(currDate);
		return datetime;
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 //获取页面传过来的url和停留时长time
		 String url = request.getParameter("url");
		 String time = request.getParameter("time");
		 //从url提取出当前浏览商品的id
		 String[] info=URLDecoder.decode(url, "UTF-8").split("=");
		 ProductService productService=new ProductService();
		 BrowseRecordService bRecordService=new BrowseRecordService();
		 User user = (User) request.getSession().getAttribute("user");
		 BrowseRecord bRecord=new BrowseRecord();
		 if (info.length>1&&user!=null) {
			try {
				//根据商品id获得商品
				String product_id=info[1]; //从url提取出当前浏览商品的id
				Product product=productService.findProductById(product_id);
				//获取商品的种类
				String product_category=product.getCategory();  
				//将用户id，商品id，商品类别，停留时长插入到浏览记录表
				int user_id=user.getId();
				bRecord.setUser_id(user_id);
				bRecord.setProduct_id(product_id);
				bRecord.setProduct_category(product_category);
				bRecord.setStay_time(time);
				String currenttime =getDatetime();  //当前时间
				System.out.println(bRecord+"  浏览时间: "+currenttime);
				bRecordService.addBrowseRecor(bRecord);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
