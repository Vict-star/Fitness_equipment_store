package com.zxt.shangpinyuce;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.servlet.ServletUtilities;
//销售人员和管理员得到单个商品销售折线图
@WebServlet("/Zxtshangpinyuce")
public class ZxtshangpinyuceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	protected void  doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");  //商品id
		// 在业务层获取创建的柱状图（此时柱状图已经创建完成的）
		ZxtshangpinyuceService zxtshangpinyuceService=new ZxtshangpinyuceService();
	    JFreeChart chart = zxtshangpinyuceService.createFoldLineTools(id);
	    // 将图形转换为图片传到dateSet.jsp
	    String fileName = ServletUtilities.saveChartAsJPEG(chart, 1100, 550,null, request.getSession());
	    String chartURL = request.getContextPath() + "/chart?filename="+ fileName;
	    request.setAttribute("ChartLineURL", chartURL);
		// 3.重定向到list.jsp页面
	    request.getRequestDispatcher("/salesman/products/xiaoshouyuce.jsp").forward(request, response);
	}
}
