package cn.itcast.itcaststore.domain;

import java.util.Date;

public class UserLogin {
	private int user_id; // 用户编号
	private Date logintime;// 登录时间
	private String logtime;// 登录时间2,用于更新是查找
	private int time; // 在线时长
	private String IP; //登录IP地址
	
	public String getLogtime() {
		return logtime;
	}
	public void setLogtime(String logtime) {
		this.logtime = logtime;
	}
	public String getIP() {
		return IP;
	}
	public void setIP(String iP) {
		IP = iP;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public Date getLogintime() {
		return logintime;
	}
	public void setLogintime(Date logintime) {
		this.logintime = logintime;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	
}
