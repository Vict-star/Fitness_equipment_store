package com.zxt.shangpinyuce;

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

public class ZxtshangpinyuceService {
	public JFreeChart createFoldLineTools(String id) {
	    //获取折线图数据源
	    DefaultCategoryDataset dataset=getDataSet(id);
	    //从折线图工具类中获取创建完成的折线图
	    JFreeChart chart=ZxtshangpinyuceTools.createFoldLine(dataset);
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
	public  DefaultCategoryDataset getDataSet(String id){
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
	  	int[] nums = new int[10];  //各月销售数量列表
		double[] moneys = new double[10];  //各月销售额列表
		//获取商品每月销量
		ProductService service = new ProductService();
		List<Object[]> numbers0 = service.salesnumber(time[0],time[1],id); //当前月份销量
	  	List<Object[]> numbers1 = service.salesnumber(nian2[0],yue2[0],id); //前1月份销量
	  	List<Object[]> numbers2 = service.salesnumber(nian2[1],yue2[1],id); //前2月份销量
	  	List<Object[]> numbers3 = service.salesnumber(nian2[2],yue2[2],id); //前3月份销量
	  	List<Object[]> numbers4 = service.salesnumber(nian2[3],yue2[3],id); //前4月份销量
	  	List<Object[]> numbers5 = service.salesnumber(nian2[4],yue2[4],id); //前5月份销量
	  	List<Object[]> numbers6 = service.salesnumber(nian2[5],yue2[5],id); //前6月份销量	
	  	try {
			Product ps = service.findProductById(id);//得到商品
		   	for (int j = 0; j < numbers0.size(); j++) {  //当前月份销量
		   		Object[] arr=numbers0.get(0);
		   		nums[6]=Integer.parseInt(String.valueOf(arr[0]));
		   		moneys[6]=nums[6]*ps.getPrice();
		   		}
		   	for (int j = 0; j < numbers1.size(); j++) {  //前1月份销量
		   		Object[] arr=numbers1.get(0);
		   		nums[5]=Integer.parseInt(String.valueOf(arr[0]));
		   		moneys[5]=nums[5]*ps.getPrice();
		   		}
		   	for (int j = 0; j < numbers2.size(); j++) {  //前2月份销量
		   		Object[] arr=numbers2.get(0);
		   		nums[4]=Integer.parseInt(String.valueOf(arr[0]));
		   		moneys[4]=nums[4]*ps.getPrice();
		   		}
		   	for (int j = 0; j < numbers3.size(); j++) {  //前3月份销量
		   		Object[] arr=numbers3.get(0);
		   		nums[3]=Integer.parseInt(String.valueOf(arr[0]));
		   		moneys[3]=nums[3]*ps.getPrice();
		   		}
		   	for (int j = 0; j < numbers4.size(); j++) {  //前4月份销量
		   		Object[] arr=numbers4.get(0);
		   		nums[2]=Integer.parseInt(String.valueOf(arr[0]));
		   		moneys[2]=nums[2]*ps.getPrice();
		   		}
		   	for (int j = 0; j < numbers5.size(); j++) {  //前5月份销量
		   		Object[] arr=numbers5.get(0);
		   		nums[1]=Integer.parseInt(String.valueOf(arr[0]));
		   		moneys[1]=nums[1]*ps.getPrice();
		   		}
		   	for (int j = 0; j < numbers6.size(); j++) {  //前6月份销量
		   		Object[] arr=numbers6.get(0);
		   		nums[0]=Integer.parseInt(String.valueOf(arr[0]));
		   		moneys[0]=nums[0]*ps.getPrice();
		   		}
		   	for (int i = 0; i < 7; i++) { //换单位为xx百元
				moneys[i]=moneys[i]/100;   
			}
	  	} catch (FindProductByIdException e) {
			e.printStackTrace();
		}
	  //预测下月和下下月
	  	int[] x=new int[10];
		for (int i = 0; i < 10; i++) {  //给x赋值
			x[i]=i+1;
		}
		int n=7;  //当前预测的y的下标
		for (int j = n; j < 10; j++) {  //预测y=a+bx
			double a=1;  //参数a
			double b=1;  //参数b
			double b1=0;  //moneys的b的除数
			double b3=0;  //nums的b的除数
			double b2=1;  //b的被除数
			double sumx=0;  //x的和
			double sumy=0;  //moneys的和
			double sumy1=0;  //nums的和
			double averagex=0;  //x的均值
			double averagey=0;  //moneys的均值
			double averagey1=0;  //nums的均值
			//i=n-m:从有值的月份算起     i<n-1:得到5个x的和，本月还没结束所以不能算
			for (int i = n-6; i < n-1; i++) {
				sumx=sumx+x[i];
			}
			for (int i = n-6; i < n-1; i++) {//得到5个y的和 
				sumy=sumy+moneys[i];
				sumy1=sumy1+nums[i];
				}
				averagex=sumx/5;  //得到x的均值
				averagey=sumy/5; //得到y均值
				averagey1=sumy1/5;
				for (int i = n-6; i < n-1; i++) {  //得到b2
				double mm=(x[i]-averagex)*(x[i]-averagex);
				b2=b2+mm;
			}
				for (int i = n-6; i < n-1; i++) {  //得到b1
				double mm=(x[i]-averagex)*(moneys[i]-averagey);
				b1=b1+mm;
				double mm1=(x[i]-averagex)*(nums[i]-averagey1);
				b3=b3+mm1;
				}
			b=b1/b2;  //得到b
			a=averagey-b*averagex;  //得到a
			moneys[n]=a+b*x[n];
			if (moneys[n]<0) {
				moneys[n]=0;
			}
			b=b3/b2;  //得到b
			a=averagey1-b*averagex;  //得到a
			nums[n]=(int) (a+b*x[n]);
			if (nums[n]<0) {
				nums[n]=0;
			}
			n++;
		}
		dataset.addValue(nums[0], "销量/本", nian2[5]+"-"+yue2[5]);
	    dataset.addValue(nums[1], "销量/本", nian2[4]+"-"+yue2[4]);
	    dataset.addValue(nums[2], "销量/本", nian2[3]+"-"+yue2[3]);
	    dataset.addValue(nums[3], "销量/本", nian2[2]+"-"+yue2[2]);
	    dataset.addValue(nums[4], "销量/本", nian2[1]+"-"+yue2[1]);
	    dataset.addValue(nums[5], "销量/本", nian2[0]+"-"+yue2[0]);
	    dataset.addValue(nums[6], "销量/本", "本月目前");
	    dataset.addValue(nums[7], "销量/本", "下月预测");
	    dataset.addValue(nums[8], "销量/本", "下下月预测");
	    dataset.addValue(moneys[0], "销售额/百元", nian2[5]+"-"+yue2[5]);
	    dataset.addValue(moneys[1], "销售额/百元", nian2[4]+"-"+yue2[4]);
	    dataset.addValue(moneys[2], "销售额/百元", nian2[3]+"-"+yue2[3]);
	    dataset.addValue(moneys[3], "销售额/百元", nian2[2]+"-"+yue2[2]);
	    dataset.addValue(moneys[4], "销售额/百元", nian2[1]+"-"+yue2[1]);
	    dataset.addValue(moneys[5], "销售额/百元", nian2[0]+"-"+yue2[0]);
	    dataset.addValue(moneys[6], "销售额/百元", "本月目前");
	    dataset.addValue(moneys[7], "销售额/百元", "下月预测");
	    dataset.addValue(moneys[8], "销售额/百元", "下下月预测");
	    return dataset;
	}
}
