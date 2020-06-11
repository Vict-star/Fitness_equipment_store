package com.zxt.yonghuyuce;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import cn.itcast.itcaststore.domain.Product;
import cn.itcast.itcaststore.exception.FindProductByIdException;
import cn.itcast.itcaststore.service.ProductService;
import cn.itcast.itcaststore.service.UserService;

public class ZxtyonghuyuceService {
	public JFreeChart createFoldLineTools(int id) {
	    //获取折线图数据源
	    DefaultCategoryDataset dataset=getDataSet(id);
	    //从折线图工具类中获取创建完成的折线图
	    JFreeChart chart=ZxtyonghuyuceTools.createFoldLine(dataset);
	    return chart;
	}
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
	public  DefaultCategoryDataset getDataSet(int id){
	    //创建数据集
	    DefaultCategoryDataset dataset=new DefaultCategoryDataset();
	    //添加数据
	    //获取当前时间
	  	String logintime=getDatetime();
	  	String[] time=new String[3];
	  	try {
			time=URLDecoder.decode(logintime, "UTF-8").split("-");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	  //将月份和年变成int再减，再化为string
	  	int yue=Integer.parseInt(time[1]);
	  	int nian=Integer.parseInt(time[0]);
	  	int[] yue1=new int[6];
	  	int[] nian1=new int[6];
	  	String[] yue2=new String[6];
	  	String[] nian2=new String[6];
	  	for (int i = 0; i < 6; i++) {
	  		yue1[i]=yue-i-1;
	  		nian1[i]=nian;
	  		if (yue1[i]<1) {
	  			yue1[i]=yue1[i]+12;
	  			nian1[i]--;
			}
	  		yue2[i]= String.valueOf(yue1[i]);
	  		nian2[i]= String.valueOf(nian1[i]);
		}
		double[] moneys = new double[10];  //各月购买金额列表
		//获取商品每月销量
		UserService userService=new UserService();
		moneys[6]=userService.FindYearMonthMoney(time[0],time[1],id); //当前月份销量
		moneys[5]=userService.FindYearMonthMoney(nian2[0],yue2[0],id); //前1月份销量
		moneys[4]=userService.FindYearMonthMoney(nian2[1],yue2[1],id); //前2月份销量
		moneys[3]=userService.FindYearMonthMoney(nian2[2],yue2[2],id); //前3月份销量
		moneys[2]=userService.FindYearMonthMoney(nian2[3],yue2[3],id); //前4月份销量
		moneys[1]=userService.FindYearMonthMoney(nian2[4],yue2[4],id); //前5月份销量
		moneys[0]=userService.FindYearMonthMoney(nian2[5],yue2[5],id); //前6月份销量	
	  //预测下月和下下月
	  	int[] x=new int[10];
		for (int i = 0; i < 10; i++) {  //给x赋值
			x[i]=i+1;
		}
		int n=7;  //当前预测的y的下标
		for (int j = n; j < 10; j++) {  //预测y=a+bx
			double a=1;  //参数a
			double b=1;  //参数b
			double b1=0;  //b的除数
			double b2=1;  //b的被除数
			double sumx=0;  //x的和
			double sumy=0;  //moneys的和
			double averagex=0;  //x的均值
			double averagey=0;  //moneys的均值
			//i=n-m:从有值的月份算起     i<n-1:得到5个x的和，本月还没结束所以不能算
			for (int i = n-6; i < n-1; i++) {
				sumx=sumx+x[i];
			}
			for (int i = n-6; i < n-1; i++) {//得到5个y的和 
				sumy=sumy+moneys[i];
				}
				averagex=sumx/5;  //得到x的均值
				averagey=sumy/5; //得到y均值
				for (int i = n-6; i < n-1; i++) {  //得到b2
				double mm=(x[i]-averagex)*(x[i]-averagex);
				b2=b2+mm;
			}
				for (int i = n-6; i < n-1; i++) {  //得到b1
				double mm=(x[i]-averagex)*(moneys[i]-averagey);
				b1=b1+mm;
				}
			b=b1/b2;  //得到b
			a=averagey-b*averagex;  //得到a
			moneys[n]=a+b*x[n];
			if (moneys[n]<0) {
				moneys[n]=0;
			}
			n++;
		}
	    dataset.addValue(moneys[0], "金额/元", nian2[5]+"-"+yue2[5]);
	    dataset.addValue(moneys[1], "金额/元", nian2[4]+"-"+yue2[4]);
	    dataset.addValue(moneys[2], "金额/元", nian2[3]+"-"+yue2[3]);
	    dataset.addValue(moneys[3], "金额/元", nian2[2]+"-"+yue2[2]);
	    dataset.addValue(moneys[4], "金额/元", nian2[1]+"-"+yue2[1]);
	    dataset.addValue(moneys[5], "金额/元", nian2[0]+"-"+yue2[0]);
	    dataset.addValue(moneys[6], "金额/元", "本月目前");
	    dataset.addValue(moneys[7], "金额/元", "下月预测");
	    dataset.addValue(moneys[8], "金额/元", "下下月预测");
	    return dataset;
	}
}
