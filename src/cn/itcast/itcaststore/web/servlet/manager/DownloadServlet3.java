package cn.itcast.itcaststore.web.servlet.manager;
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
import cn.itcast.itcaststore.service.UserService;
import cn.itcast.itcaststore.utils.DownloadUtils;
@WebServlet("/Download3")
//管理员下载当月商品分类榜单
public class DownloadServlet3 extends HttpServlet {
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
		UserService uService=new UserService();
		//获取全部类别的商品
		List<Product> ps1 = service.findProductByManyCondition(null, null,"蛋白粉", null, null);
		List<Product> ps2 = service.findProductByManyCondition(null, null,"跑步机", null, null);
		List<Product> ps3 = service.findProductByManyCondition(null, null,"哑铃", null, null);
		List<Product> ps4 = service.findProductByManyCondition(null, null,"动感单车", null, null);
		List<Product> ps5 = service.findProductByManyCondition(null, null,"仰卧起坐椅", null, null);
		List<Product> ps6 = service.findProductByManyCondition(null, null,"力量训练器", null, null);
		List<Product> ps7 = service.findProductByManyCondition(null, null,"自行车", null, null);
		List<Product> ps8 = service.findProductByManyCondition(null, null,"运动服", null, null);
		String[] ProductCategory=new String[]{"蛋白粉","跑步机","哑铃","动感单车",
				"仰卧起坐椅","力量训练器","自行车","运动服"};//商品类名
		
		//获取商品对应的销售人员
		User[] salesmans=new User[8];
		salesmans[0]=uService.findUserByCategory("蛋白粉");
		salesmans[1]=uService.findUserByCategory("跑步机");
		salesmans[2]=uService.findUserByCategory("哑铃");
		salesmans[3]=uService.findUserByCategory("动感单车");
		salesmans[4]=uService.findUserByCategory("仰卧起坐椅");
		salesmans[5]=uService.findUserByCategory("力量训练器");
		salesmans[6]=uService.findUserByCategory("自行车");
		salesmans[7]=uService.findUserByCategory("运动服");
		String[] salesnames=new String[8];//销售人员名字
		int[] salesIds=new int[8]; //销售人员id
		for(int i=0; i<8;i++){
			if (salesmans[i]!=null) {
				salesnames[i]=salesmans[i].getUsername();
				salesIds[i]=salesmans[i].getId();
			}	
		}
		
		//获取当前时间
		String logintime=getDatetime();
		String[] time=new String[3];
		time=URLDecoder.decode(logintime, "UTF-8").split("-");
		//获取商品当月销量
		List<Object[]> numbers1 = service.salescategorynumber(time[0],time[1],"蛋白粉");
		List<Object[]> numbers2 = service.salescategorynumber(time[0],time[1],"跑步机");
		List<Object[]> numbers3 = service.salescategorynumber(time[0],time[1],"哑铃");
		List<Object[]> numbers4 = service.salescategorynumber(time[0],time[1],"动感单车");
		List<Object[]> numbers5 = service.salescategorynumber(time[0],time[1],"仰卧起坐椅");
		List<Object[]> numbers6 = service.salescategorynumber(time[0],time[1],"力量训练器");
		List<Object[]> numbers7 = service.salescategorynumber(time[0],time[1],"自行车");
		List<Object[]> numbers8 = service.salescategorynumber(time[0],time[1],"运动服");
		
		int[] nums = new int[8];  //各类销售数量列表
		double[] moneys = new double[8];  //各类销售额列表
		
		for (int i = 0; i < numbers1.size(); i++) {
			Object[] arr=numbers1.get(i);
			nums[0]+=Integer.parseInt(String.valueOf(arr[1]));		
			moneys[0]+=nums[i]*ps1.get(i).getPrice();
		}
		for (int i = 0; i < numbers2.size(); i++) {
			Object[] arr=numbers2.get(i);
			nums[1]+=Integer.parseInt(String.valueOf(arr[1]));		
			moneys[1]+=nums[i]*ps2.get(i).getPrice();
		}
		for (int i = 0; i < numbers3.size(); i++) {
			Object[] arr=numbers3.get(i);
			nums[2]+=Integer.parseInt(String.valueOf(arr[1]));		
			moneys[2]+=nums[i]*ps3.get(i).getPrice();
		}
		for (int i = 0; i < numbers4.size(); i++) {
			Object[] arr=numbers4.get(i);
			nums[3]+=Integer.parseInt(String.valueOf(arr[1]));		
			moneys[3]+=nums[i]*ps4.get(i).getPrice();
		}
		for (int i = 0; i < numbers5.size(); i++) {
			Object[] arr=numbers5.get(i);
			nums[4]+=Integer.parseInt(String.valueOf(arr[1]));		
			moneys[4]+=nums[i]*ps5.get(i).getPrice();
		}
		for (int i = 0; i < numbers6.size(); i++) {
			Object[] arr=numbers6.get(i);
			nums[5]+=Integer.parseInt(String.valueOf(arr[1]));		
			moneys[5]+=nums[i]*ps6.get(i).getPrice();
		}
		for (int i = 0; i < numbers7.size(); i++) {
			Object[] arr=numbers7.get(i);
			nums[6]+=Integer.parseInt(String.valueOf(arr[1]));		
			moneys[6]+=nums[i]*ps7.get(i).getPrice();
		}
		for (int i = 0; i < numbers8.size(); i++) {
			Object[] arr=numbers8.get(i);
			nums[7]+=Integer.parseInt(String.valueOf(arr[1]));		
			moneys[7]+=nums[i]*ps8.get(i).getPrice();
		}
		//准备文件名称
		String fileName=time[0]+"年"+time[1]+"月"+"商品销售总榜单.csv";	
		//获取文件的类型
		String fileType=this.getServletContext().getMimeType(fileName);
		//设置文件下载的响应头
		response.setContentType(fileType);
		//设置下载框弹出方式的响应头
		response.setHeader("Content-Disposition", "attachement;filename="+DownloadUtils.filenameEncoding(fileName, request));//或者+new String(fileName.getBytes("GBK"),"iso8859-1"));		
		response.setCharacterEncoding("UTF-8");
		//把查询出来的数据写到文件里
		PrintWriter out = response.getWriter();
		String[][] arr=new String[8][5];
		out.println("商品类别,销售人员编号,销售人员姓名,本月销售总数量,本月销售总金额");	
		for (int i = 0; i < 8; i++) {
			arr[i][0]=ProductCategory[i];
			arr[i][1]=String.valueOf(salesIds[i]);
			arr[i][2]=salesnames[i];
			arr[i][3]=String.valueOf(nums[i]);
			arr[i][4]=String.valueOf(moneys[i]);
			out.println(arr[i][0]+","+arr[i][1]+","+arr[i][2]+","+arr[i][3]+","+arr[i][4]);			
		}
		out.flush();
		out.close();
	}
}
