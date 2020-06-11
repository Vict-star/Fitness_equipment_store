package cn.itcast.itcaststore.service;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.itcaststore.dao.UserLoginDao;
import cn.itcast.itcaststore.domain.User;


public class UserLoginService {
	private UserLoginDao uLoginDao=new UserLoginDao();
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd  hh:mm:ss");
	java.util.Date Logintime;  //登陆时间
	java.util.Date Logouttme; //退出时间
	private String datetime;
	public String getDatetime() {
		//获取当前时间
		java.util.Date currDate=Calendar.getInstance().getTime();
		//实例化SimpleDateFormat
		//格式化日期时间
		datetime=sdf.format(currDate);
		return datetime;
	}
	//插入登录时间
	public void addLoginTime(User user,String IP,HttpServletRequest request, HttpServletResponse response) throws SQLException {
	    // 调用uLoginDao完成插入登录时间
		try {
			String logintime=getDatetime();//为了数据库按登录时间查找，多建一个String类型的登陆时间
			Logintime=Calendar.getInstance().getTime(); //获取登陆时间
			uLoginDao.addLoginTime(user, Logintime,IP,logintime);
			Cookie cookie3 = new Cookie("logintime",logintime );
			cookie3.setMaxAge(2*60*60);//cookie时间：2小时
            response.addCookie(cookie3);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	//更新在线时长
	public void updateLoginTime(User user,HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// 调用uLoginDao完成插入在线时长
		try {
			Logouttme=sdf.parse(getDatetime());//获取退出时间(12小时制)
			Cookie[] cooks=request.getCookies();
		    if(cooks!=null){
		    	for(int i=0;i<cooks.length;i++){
		        	if(cooks[i].getName().endsWith("logintime")){
		        		String logintime=cooks[i].getValue();
		        		Logintime=sdf.parse(logintime); //获取登陆时间
						int time=(int) ((Logouttme.getTime()-Logintime.getTime())/1000);  //本次登录时长/秒
						if (time<0) {
							time=time+43200;//小时12小时制的时间差
							if (time>7200) {
								time=7200;
							}
						}
						Logouttme=Calendar.getInstance().getTime();//重新获取退出时间（24小时制） 
						uLoginDao.updateLoginTime(time,Logouttme, user,logintime);  //按String类型的登录时间以及用户id查找，更新在线时间和退出时间
		        	}
		        }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
