package com.zxt.xiaoshouyuce;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.servlet.ServletUtilities;
//后台管理员商品分类销售情况
@WebServlet("/ZxtYuce")
public class ZxtYuceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	protected void  doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String category = request.getParameter("category"); // 商品类别
		if ("1".equals(category)) {
			category="蛋白粉";
		}else if ("2".equals(category)) {
			category="跑步机";
		}else if ("3".equals(category)) {
			category="哑铃";
		}else if ("4".equals(category)) {
			category="动感单车";
		}else if ("5".equals(category)) {
			category="仰卧起坐椅";
		}else if ("6".equals(category)) {
			category="力量训练器";
		}else if ("7".equals(category)) {
			category="自行车";
		}else{
			category="运动服";
		}
		// 在业务层获取创建的柱状图（此时柱状图已经创建完成的）
		FoldLineService foldLineService=new FoldLineService();
	    JFreeChart chart = foldLineService.createFoldLineTools(category);
	    // 将图形转换为图片传到dateSet.jsp
	    String fileName = ServletUtilities.saveChartAsJPEG(chart, 1100, 550,null, request.getSession());
	    String chartURL = request.getContextPath() + "/chart?filename="+ fileName;
	    request.setAttribute("chartLineURL", chartURL);
		// 3.重定向到list.jsp页面
	    request.getRequestDispatcher("/admin/products/xiaoshouyuce.jsp").forward(request, response);
	}
}
