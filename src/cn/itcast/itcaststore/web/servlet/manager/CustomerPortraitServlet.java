package cn.itcast.itcaststore.web.servlet.manager;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.servlet.ServletUtilities;
import com.zxt.yonghuyuce.ZxtyonghuyuceService;

import cn.itcast.itcaststore.domain.BrowseRecord;
import cn.itcast.itcaststore.domain.User;
import cn.itcast.itcaststore.service.UserService;
@WebServlet("/CustomerPortrait")
public class CustomerPortraitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.创建service层的对象
		UserService uService =new UserService();
		try {
			String uid =request.getParameter("id");
			int id = Integer.parseInt(uid);
			// 2.调用service层用于查询用户的方法
			User user = uService.findUserById(id);
			//查找某个用户购买总数和购买总金额
			int TolNum=uService.FindTolNum(id);
			double TolMoney=uService.FindTolMoney(id);
			String arr[]=new String[]{"0","0.0","0.0"};  //将结果封装为一个数组
			arr[0]= String.valueOf(TolNum);
			arr[1]= String.valueOf(TolMoney);
			if (TolNum!=0) {
				arr[2]= String.valueOf(TolMoney/TolNum);
			}
			//查找某个用户最常购买商品种类(嵌套查询)
			String MostBuyCategory=uService.findMostBuy(id);
			//查找某个用户最常浏览商品种类
			String MostBrowserCategory=uService.findMostBrowser(id);
			//查找某个用户最近一周最常浏览商品种类
			String WeekMostBrowserCategory=uService.findWeekMostBrowser(id);
			//近两周登录总时长
			String time="0";
			String a=uService.find2WeekLogin(id);
			if (a!=null&&a!="null") {
				time=a;
			}
			//购买商品平均浏览次数count
			int count1=0;
			int browsercount=uService.findTolBrowser(id);
			int buycount=TolNum;
			if (buycount!=0) {
				count1=browsercount/buycount+1;
			}
			int score=30;  //评分
			score=score-count1;
			if (score<15) {
				score=15;
			}
			score=score+(int)TolMoney/100;
			if (score>85) {
				score=85;
			}score=score+Integer.parseInt(time)/500;
			if (score>100) {
				score=100;
			}
			uService.uppdatescore(score,user.getId());
			String count=String.valueOf(count1);
			//获取用户最常上线IP地址
			String IP=uService.findMostIP(id);
			//获取用户  浏览  记录列表
			List<BrowseRecord> bList= uService.fingBrowseRecordByUser(id);
			//获取用户  购买  记录列表
			List<Object[]> objects= uService.fingBuyRecordByUser(id);
			String arr2[][]=new String[objects.size()][6];  //将结果封装为一个二维数组
			for (int i = 0; i < objects.size(); i++) {
				Object[] object=objects.get(i);   //得到某一行的数据
				arr2[i][0]= String.valueOf(object[0]);
				arr2[i][1]= String.valueOf(object[1]);
				arr2[i][2]= String.valueOf(object[2]);
				arr2[i][3]= String.valueOf(object[3]);		
				arr2[i][4]= String.valueOf(object[4]);
				arr2[i][5]= String.valueOf(object[5]);
			}
			//创建购买金额折线图
			// 在业务层获取创建的折线图（此时柱状图已经创建完成的）
			ZxtyonghuyuceService zService=new ZxtyonghuyuceService();
		    JFreeChart chart = zService.createFoldLineTools(id);
		    // 将图形转换为图片传到dateSet.jsp
		    String fileName = ServletUtilities.saveChartAsJPEG(chart, 1100, 550,null, request.getSession());
		    String chartURL = request.getContextPath() + "/chart?filename="+ fileName;
		    request.setAttribute("ChartLineURL", chartURL);
		    
			// 3.将查询出的所有结果放进request域中
		    request.setAttribute("user", user);
			request.setAttribute("score", score);
			request.setAttribute("arr", arr);
			request.setAttribute("MostBuyCategory", MostBuyCategory);
			request.setAttribute("MostBrowserCategory", MostBrowserCategory);
			request.setAttribute("WeekMostBrowserCategory", WeekMostBrowserCategory);
			request.setAttribute("time", time);
			request.setAttribute("count", count);
			request.setAttribute("IP", IP);
			request.setAttribute("bList", bList);
			request.setAttribute("arr2", arr2);  
			request.setAttribute("objects", objects);  //结果集在arr2，传objects是为了在c:forEach时遍历
			// 4.重定向到list.jsp页面
			request.getRequestDispatcher("/admin/custom/customerportrait.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
