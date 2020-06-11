package cn.itcast.itcaststore.web.servlet.salesman;

import java.util.List;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.itcaststore.domain.BrowseRecord;
import cn.itcast.itcaststore.domain.User;
import cn.itcast.itcaststore.service.BrowseRecordService;
@WebServlet("/fingBrowseRecordByCategory")
//用户购买其所负责销售商品的  浏览  记录
public class FingBrowseRecordByCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		String category=user.getProduct_category();
		BrowseRecordService bRecordService =new BrowseRecordService();
		List<BrowseRecord> bList= bRecordService.fingBrowseRecordByCategory(category);
		request.setAttribute("bList", bList);
	    request.getRequestDispatcher("/salesman/custom/browserecord.jsp").forward(request, response);
	}
}
