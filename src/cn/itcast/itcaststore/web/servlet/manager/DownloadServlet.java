package cn.itcast.itcaststore.web.servlet.manager;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.itcaststore.service.ProductService;
import cn.itcast.itcaststore.utils.DownloadUtils;

public class DownloadServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		ProductService service = new ProductService();
		//查询已经支付的商品信息并排序
		List<Object[]> ps = service.download(year,month);
		//准备文件名称
		String fileName=year+"年"+month+"月销售榜单.csv";	
		//获取文件的类型
		String fileType=this.getServletContext().getMimeType(fileName);
		//设置文件下载的响应头
		response.setContentType(fileType);
		//设置下载框弹出方式的响应头
		response.setHeader("Content-Disposition", "attachement;filename="+DownloadUtils.filenameEncoding(fileName, request));//或者+new String(fileName.getBytes("GBK"),"iso8859-1"));		
		response.setCharacterEncoding("UTF-8");
		//把查询出来的数据写到文件里
		PrintWriter out = response.getWriter();
		out.println("商品名称,销售数量");
		for (int i = 0; i < ps.size(); i++) {
			Object[] arr=ps.get(i);
			out.println(arr[0]+","+arr[1]);			
		}
		out.flush();
		out.close();
	}
}
