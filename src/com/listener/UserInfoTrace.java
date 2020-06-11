package com.listener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
//监听用户上下线
@WebListener
public class UserInfoTrace implements HttpSessionBindingListener {
	private String user;
	private UserInfolist container=UserInfolist.getInstance();
	private String datetime;
	private String week;
	private Calendar calendar=Calendar.getInstance();
	//private Log logger = LogFactory.getLog(this.getClass());
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日  hh时mm分ss秒");
	java.util.Date Logintime;  //登陆时间
	java.util.Date Logouttme; //退出时间
	
	
	public String getDatetime() {
		//获取当前时间
		java.util.Date currDate=Calendar.getInstance().getTime();
		//实例化SimpleDateFormat
		//格式化日期时间
		datetime=sdf.format(currDate);
		return datetime;
	}
	public String getWeek() {
		String[] weeks={"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
		//获取一星期的某天
		int index=calendar.get(Calendar.DAY_OF_WEEK);
		//获取星期几
		week=weeks[index-1];
		return week;
	}
    public UserInfoTrace() {
    	user="";
    }

    public void valueBound(HttpSessionBindingEvent arg0)  { 
    	try {
			Logintime=sdf.parse(getDatetime()); //获取登陆时间
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	System.out.println(this.user+"上线   + 时间："+getDatetime()+" "+getWeek());
    	//logger.info(this.user+"上线   + 时间："+getDatetime()+" "+getWeek());
    }

    public void valueUnbound(HttpSessionBindingEvent arg0) { 
    	System.out.println(this.user+"下线   + 时间："+getDatetime()+" "+getWeek());
    	//logger.info(this.user+"上线   + 时间："+getDatetime()+" "+getWeek());
    	
    	try {
			Logouttme=sdf.parse(getDatetime());//获取退出时间
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	int ttime=(int) ((Logouttme.getTime()-Logintime.getTime())/1000);  //本次登录时长/秒
    	if (ttime<0) {
    		ttime=ttime+43200;//小时12小时制的时间差
			if (ttime>7200) {
				ttime=7200;
			}
		}
	    System.out.println("本次登录时长为：  "+ttime+" 秒");

    	if(user!=""){
    		container.removeUser(user);
    	}
    }

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
    
}
