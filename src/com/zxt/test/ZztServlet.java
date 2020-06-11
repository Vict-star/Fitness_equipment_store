package com.zxt.test;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.servlet.ServletUtilities;

@WebServlet("/Zzt")
public class ZztServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void  doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 在业务层获取创建的柱状图（此时柱状图已经创建完成的）
		ColumnaService columnarService=new ColumnaService();
	    JFreeChart chart = columnarService.createColumnarTools();
	    // 将图形转换为图片传到dateSet.jsp
	    String fileName = ServletUtilities.saveChartAsJPEG(chart, 700, 400,null, request.getSession());
	    String chartURL = request.getContextPath() + "/chart?filename="+ fileName;
	    request.setAttribute("chartColumnURL", chartURL);
		// 4.重定向到list.jsp页面
		request.getRequestDispatcher("/dataSet.jsp").forward(request, response);
	}
}
