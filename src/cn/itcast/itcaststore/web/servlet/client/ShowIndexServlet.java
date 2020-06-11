package cn.itcast.itcaststore.web.servlet.client;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.itcast.itcaststore.domain.Notice;
import cn.itcast.itcaststore.service.NoticeService;
import cn.itcast.itcaststore.service.ProductService;
/**
 *	前台页面展示的servlet 
 *	1、展示最新添加或修改的一条公告
 *  2、展示本周热销商品
 */
public class ShowIndexServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//查询最近新增或更新的一条公告
		NoticeService nService=new NoticeService();
		Notice notice=nService.getRecentNotice();
		req.setAttribute("n", notice);
		//查询本周热卖商品
		ProductService productService=new ProductService();
		List<Object[]> pList=productService.getWeekHotProduct();
		req.setAttribute("pList", pList);
		//请求转发
		req.getRequestDispatcher("/client/index.jsp").forward(req, resp);
	}
}
