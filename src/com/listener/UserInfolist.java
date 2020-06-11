package com.listener;
import java.util.*;;
//监听用户上下线

public class UserInfolist {
	private static UserInfolist user=new UserInfolist();
	private Vector vector=null;
	public UserInfolist(){
		this.vector=new Vector();
	}
	public static UserInfolist getInstance(){
		return user;
	}
	//添加用户到用户列表
	public boolean  addUser(String user) {
		if (user!=null) {
			this.vector.add(user);
			return true;
		}
		else{ return false;}
	}
	//获取用户列表
	public Vector getList() {
		return vector;
	}
	//移除用户
	public void removeUser(String user){
		if (user!=null) {
			this.vector.remove(user);
		}
	}
}
