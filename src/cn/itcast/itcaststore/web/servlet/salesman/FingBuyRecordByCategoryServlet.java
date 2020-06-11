package cn.itcast.itcaststore.web.servlet.salesman;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.itcaststore.domain.BrowseRecord;
import cn.itcast.itcaststore.domain.User;
import cn.itcast.itcaststore.service.BrowseRecordService;
@WebServlet("/fingBuyRecordByCategory")
//用户购买其所负责销售商品的  购买  记录
public class FingBuyRecordByCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		String category=user.getProduct_category();
		BrowseRecordService bRecordService =new BrowseRecordService();
		List<Object[]> oList= bRecordService.fingBuyRecordByCategory(category);
		String arr[][]=new String[oList.size()][6];  //将结果封装为一个二维数组
		for (int i = 0; i < oList.size(); i++) {
			Object[] object=oList.get(i);   //得到某一行的数据
			arr[i][0]= String.valueOf(object[0]);
			arr[i][1]= String.valueOf(object[1]);
			arr[i][2]= String.valueOf(object[2]);
			arr[i][3]= String.valueOf(object[3]);		
			arr[i][4]= String.valueOf(object[4]);
			arr[i][5]= String.valueOf(object[5]);
		}
		request.setAttribute("arr", arr);
		request.setAttribute("oList", oList);//结果集在arr，传oList是为了在c:forEach时遍历
	    request.getRequestDispatcher("/salesman/custom/buyrecord.jsp").forward(request, response);
	}
}
