package testyuce;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/yuce")
public class YuceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int[] x=new int[25];
		double[] y=new double[25];  //y=a+bx
		int n=7;  //当前预测的y的下标
		y[0]=20;
		y[1]=30;
		y[2]=35;
		y[3]=45;
		y[4]=41;
		y[5]=36;
		y[6]=30;
		for (int i = 0; i < 25; i++) {  //给x赋值
			x[i]=i+1;
		}
		for (int j = n; j < 24; j++) {
			double a=1;  //参数a
			double b=1;  //参数b
			double b1=1;  //b的除数
			double b2=1;  //b的被除数
			double sumx=0;  //x的和
			double sumy=0;  //y的和
			double averagex=0;  //x的均值
			double averagey=0;  //y的均值
			
			for (int i = n-5; i < n; i++) {//得到5个x的和
				sumx=sumx+x[i];
			}
			averagex=sumx/5;  //得到x的均值
			for (int i = n-5; i < n; i++) {//得到5个y的和 
				sumy=sumy+y[i];
				}
			averagey=sumy/5; //得到y均值
			for (int i = n-5; i < n; i++) {  //得到b2
				double m=(x[i]-averagex)*(x[i]-averagex);
				b2=b2+m;
			}
			for (int i = n-5; i < n; i++) {  //得到b1
				double m=(x[i]-averagex)*(y[i]-averagey);
				b1=b1+m;
				}
			b=b1/b2;  //得到b
			a=averagey-b*averagex;  //得到a
			y[n]=a+b*x[n];
			if (y[n]<0) {
				y[n]=0;
			}
			System.out.println(y[n]+",   "+b+",   "+a+",   "+b1+",   "+b2+",   "+averagey+",   "+averagex);
			n++;
		}
	}
}
