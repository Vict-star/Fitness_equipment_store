package cn.itcast.itcaststore.web.servlet.client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.Spring;

import cn.itcast.itcaststore.service.OrderService;
import cn.itcast.itcaststore.service.ProductService;
//商品评价
@WebServlet("/evaluate")
public class EvaluateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] productids=new String[50];
		String[] evaluations=new String[50];
		ProductService pService=new ProductService();
		try {
			for(int i=1;i<50;i++){//得到所有的评价
				evaluations[i]=request.getParameter( String.valueOf(i));
			}
			for(int i=1;i<50;i++){//得到所有的商品id
				productids[i]=request.getParameter( String.valueOf(i+50));
			}
			for(int i=0;i<50;i++){//评价和商品id由null转为"a"，以免空指针异常
				if (evaluations[i]==null) {
					evaluations[i]="a";
				}
				if (productids[i]==null) {
					productids[i]="a";
				}
			}
			for(int i=1;i<50;i++){//改写商品评价数
				if (evaluations[i].endsWith("好评")) {
					pService.increaseevaluate(productids[i]);
				}else if (evaluations[i].endsWith("差评")) {
					pService.reduceevaluate(productids[i]);
				}
			}
			//修改评价状态
			String orderid=request.getParameter("orderid");
			OrderService oService=new OrderService();
			oService.updateEvaluateState(orderid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//重定向到操作成功页面，5秒后返回主页
		//response.sendRedirect(request.getContextPath() + "/client/updateusersuccess.jsp");
		//重定向到订单列表
		request.getRequestDispatcher("/findOrderByUser").forward(request, response);
	}

}
