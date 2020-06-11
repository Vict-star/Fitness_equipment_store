package cn.itcast.itcaststore.dao;

import java.sql.SQLException;
import java.util.Date;

import org.apache.commons.dbutils.QueryRunner;

import cn.itcast.itcaststore.domain.User;
import cn.itcast.itcaststore.utils.DataSourceUtils;

public class UserLoginDao {
	//插入登录时间
	public void addLoginTime(User user,Date logintime,String IP,String logtime) throws SQLException {
		String sql = "insert into userlogin(user_id,logintime,IP,logtime) values(?,?,?,?)";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		int row = runner.update(sql, user.getId(), logintime, IP, logtime);
		if (row == 0) {
			throw new RuntimeException();
		}
	}
	//更新在线时长
	public void updateLoginTime(int time,Date logoutime, User user,String logtime) throws SQLException {
		String sql = "update userlogin set time=?,logoutime=?  where user_id=? and logtime=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		int row = runner.update(sql, time,logoutime, user.getId(),logtime);
		if (row == 0) {
			throw new RuntimeException();
		}
	}
}
