package cn.itcast.itcaststore.web.servlet.salesman;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.itcaststore.domain.Product;
import cn.itcast.itcaststore.domain.User;
import cn.itcast.itcaststore.service.ProductService;
import cn.itcast.itcaststore.utils.DownloadUtils;
@WebServlet("/Download2")
//销售人员下载商品榜单
public class DownloadServlet2 extends HttpServlet {
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
		// 2.创建ProductService对象
		ProductService service = new ProductService();
		User user=(User)request.getSession().getAttribute("user");
		String product_category = user.getProduct_category();
		List<Product> ps = service.findProductByManyCondition(null, null,product_category, null, null);
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
			
		//准备文件名称
		String fileName=time[0]+"年"+time[1]+"月"+product_category+"类"+"商品销售榜单.csv";	
		//获取文件的类型
		String fileType=this.getServletContext().getMimeType(fileName);
		//设置文件下载的响应头
		response.setContentType(fileType);
		//设置下载框弹出方式的响应头
		response.setHeader("Content-Disposition", "attachement;filename="+DownloadUtils.filenameEncoding(fileName, request));//或者+new String(fileName.getBytes("GBK"),"iso8859-1"));		
		response.setCharacterEncoding("UTF-8");
		//把查询出来的数据写到文件里
		PrintWriter out = response.getWriter();
		String[][]arr=new String[25][6]; 
		out.println("商品类别,商品编号,商品名称,商品价格,本月销量,本月销售额");
		for (int i = 0; i < ps.size(); i++) {
			arr[i][0]=ps.get(i).getCategory();
			arr[i][1]=ps.get(i).getId();
			arr[i][2]=ps.get(i).getName();
			arr[i][3]=String.valueOf(ps.get(i).getPrice());
			arr[i][4]=String.valueOf(nums[i]);
			double n=nums[i]*ps.get(i).getPrice();
			arr[i][5]=(String.valueOf(n));
			out.println(arr[i][0]+","+arr[i][1]+","+arr[i][2]+","+arr[i][3]+","+arr[i][4]+","+arr[i][5]);			
		}
		out.flush();
		out.close();
	}
}
