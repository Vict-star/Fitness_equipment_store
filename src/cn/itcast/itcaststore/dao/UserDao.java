package cn.itcast.itcaststore.dao;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.itcaststore.domain.BrowseRecord;
import cn.itcast.itcaststore.domain.Product;
import cn.itcast.itcaststore.domain.User;
import cn.itcast.itcaststore.utils.DataSourceUtils;

public class UserDao {
	// 添加用户
	public void addUser(User user) throws SQLException {
		String sql = "insert into user(username,PASSWORD,gender,email,telephone,occupation,introduce,activeCode) values(?,?,?,?,?,?,?,?)";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		int row = runner.update(sql, user.getUsername(), user.getPassword(),
				user.getGender(), user.getEmail(), user.getTelephone(),user.getOccupation(),
				user.getIntroduce(), user.getActiveCode());
		if (row == 0) {
			throw new RuntimeException();
		}
	}
	// 更新评分
		public void uppdatescore(int score,int id) throws SQLException {
			String sql = "update user set score=? where id=?";
			QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
			int row = runner.update(sql, score,id);
			if (row == 0) {
				throw new RuntimeException();
			}
		}
	// 添加销售人员
	public void addSalesman(User user) throws SQLException {
		String sql = "insert into user(username,PASSWORD,gender,telephone,role,state) values(?,?,?,?,'销售人员',1)";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		int row = runner.update(sql, user.getUsername(), user.getPassword(),
				user.getGender(), user.getTelephone());
		if (row == 0) {
			throw new RuntimeException();
		}
	}
	// 查找所有销售人员
	public List<User> listAll() throws SQLException {
		String sql = "select * from user where role='销售人员'";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<User>(User.class));
	}		
	// 查找所有普通用户
		public List<User> listAllCustomer() throws SQLException {
			String sql = "select * from user where role='普通用户' order by score DESC";
			QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
			return runner.query(sql, new BeanListHandler<User>(User.class));
		}
	//查找某个用户购买总数
		public int FindTolNum(int id)throws SQLException {
			String sql = "SELECT SUM(orderitem.buynum) totalnum "
					+ "FROM orders,orderItem "
					+ "WHERE orders.paystate=1 and orders.user_id=? "
					+ "AND  orders.id=orderItem.order_id";
			QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
			Object object =runner.query(sql, new ScalarHandler(), id);
			String num=String.valueOf(object);
			int TolNum=0;
			if (num!="null"&&num!=null) {
				TolNum=Integer.parseInt(num);
			}
			return TolNum;
		}
	//查找某个用户购买总金额
		public double FindTolMoney(int id)throws SQLException {
			String sql = "select SUM(moneylist ) from "
					+ "(SELECT orders.money as moneylist  "
					+ "FROM orders,orderItem "
					+ "WHERE orders.paystate=1 and orders.user_id=? AND "
					+ "orders.id=orderItem.order_id group BY orders.id)a";
			QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
			Object object =runner.query(sql, new ScalarHandler(), id);
			String money=String.valueOf(object);
			double TolMoney=0;
			if (money!=null&&money!="null") {
				TolMoney=Double.parseDouble(money);
			}
			return TolMoney;
		}
		//查找某个用户某年月的购买金额
		public double FindYearMonthMoney(String year, String month,int id)throws SQLException {
			String sql = "SELECT sum(moneylist ) from "
					+ "(SELECT orders.money as moneylist  "
					+ "FROM orders,orderItem "
					+ "WHERE orders.paystate=1 and orders.user_id=? and "
					+ "year(ordertime)=? and month(ordertime)=? AND  "
					+ "orders.id=orderItem.order_id group BY orders.id)a";
			QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
			Object object =runner.query(sql, new ScalarHandler(), id, year, month);
			String money=String.valueOf(object);
			double TolMoney=0;
			if (money!=null&&money!="null") {
				TolMoney=Double.parseDouble(money);
			}
			return TolMoney;
		}
	//查找某个用户最常购买商品种类(嵌套查询)
		public String findMostBuy(int id)throws SQLException {
			String sql ="select pcategory from "
					+ "(select pcategory, max(sumnum) from "
					+ "(select products.category as pcategory, SUM(orderitem.buynum) as sumnum "
					+ "FROM orders,orderItem,products "
					+ "WHERE orders.paystate=1 and orders.user_id=? AND "
					+ "orders.id=orderItem.order_id AND products.id = orderitem.product_id "
					+ "GROUP BY products.category ORDER BY sumnum DESC)b)c";
			QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
			String pcategory=(String)runner.query(sql, new ScalarHandler(), id);
			return pcategory;
		}
	//查找某个用户最常浏览商品种类
		public String findMostBrowser(int id)throws SQLException {
			String sql ="select pcategory from "
					+ "(select pcategory, max(countnum) from "
					+ "(select products.category as pcategory, count(*)  as countnum  "
					+ "FROM products,browserecord WHERE  browserecord.user_id=? "
					+ "and browserecord.product_id=products.id "
					+ "GROUP BY products.category ORDER BY countnum  DESC)b)c";
			QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
			String pcategory=(String)runner.query(sql, new ScalarHandler(), id);
			return pcategory;
		}
	//查找某个用户最近一周最常浏览商品种类
		public String findWeekMostBrowser(int id)throws SQLException {
			String sql ="select pcategory from "
					+ "(select pcategory, max(countnum) from "
					+ "(select products.category as pcategory, count(*)  as countnum  "
					+ "FROM products,browserecord WHERE  browserecord.user_id=? "
					+ "AND browserecord.browse_time > DATE_SUB(NOW(), INTERVAL 7 DAY) "
					+ "and browserecord.product_id=products.id "
					+ "GROUP BY products.category ORDER BY countnum  DESC)b)c";
			QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
			String pcategory=(String)runner.query(sql, new ScalarHandler(), id);
			return pcategory;
		}
	//查找某个用户总的浏览次数
		public int findTolBrowser(int id)throws SQLException {
			String sql ="select count(*)  FROM browserecord WHERE  browserecord.user_id=?";
			QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
			//查询出满足条件的总数量，为long类型
			Long count = (Long)runner.query(sql, new ScalarHandler(), id);
			return count.intValue();
		}
	//近两周登录总时长
		public String find2WeekLogin(int id)throws SQLException {
			String sql ="select SUM(time) FROM userlogin "
					+ "WHERE  user_id=? AND logintime > DATE_SUB(NOW(), INTERVAL 14 DAY) ";
			QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
			Object object =runner.query(sql, new ScalarHandler(), id);
			String time=String.valueOf(object);
			return time;
		}
	//用户最常上线IP地址
		public String findMostIP(int id)throws SQLException {
			String sql ="select IP from "
					+ "(select IP, max(countIP) from "
					+ "(select IP, count(*) as countIP FROM userlogin "
					+ "WHERE user_id=? GROUP BY IP ORDER BY countIP DESC)b)c";
			QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
			Object object =runner.query(sql, new ScalarHandler(), id);
			String IP=String.valueOf(object);
			return IP;
		}
		//查找用户的  浏览  记录
		public List<BrowseRecord> fingBrowseRecordByUser(int id)throws SQLException {
			String sql = "select * from browserecord where user_id=?";
			QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
			return runner.query(sql, new BeanListHandler<BrowseRecord>(BrowseRecord.class) ,id);
		}
		//查找用户的  购买  记录
		public List<Object[]> fingBuyRecordByUser(int id)throws SQLException {
			String sql ="select orders.user_id, orderItem.product_id, products.price, orderItem.buynum, products.price*orderItem.buynum, orderItem.time "
					+ "from orders, orderItem, products "
					+ "where orders.user_id=? and orders.paystate=1 "
					+ "and products.id=orderItem.product_id and orderItem.order_id=orders.id ORDER BY orderItem.time";
			QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
			return runner.query(sql, new ArrayListHandler(), id);
		}
	// 更新用户
	public void updateuser(User user) throws SQLException {
		String sql = "update user set PASSWORD=?,gender=?,telephone=? where id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		int row = runner.update(sql, user.getPassword(),user.getGender(), user.getTelephone(),user.getId());
		if (row == 0) {
			throw new RuntimeException();
		}
	}
	// 更新销售人员信息
	public void updatesalesman(User user) throws SQLException {
		String sql = "update user set PASSWORD=?,gender=?,telephone=?,product_category=? where id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		int row = runner.update(sql,user.getPassword(), user.getGender(), user.getTelephone(),user.getProduct_category(), user.getId());
		if (row == 0) {
			throw new RuntimeException();
		}
	}
	// 根据激活码查找用户
	public User findUserByActiveCode(String activeCode) throws SQLException {
		String sql = "select * from user where activeCode=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanHandler<User>(User.class), activeCode);
	}
	// 根据id查找用户
	public User findUserById(int id) throws SQLException {
		String sql = "select * from user where id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanHandler<User>(User.class), id);
	}
	// 根据商品种类查找销售人员
		public User findUserByCategory(String category) throws SQLException {
			String sql = "select * from user where product_category=? and role='销售人员'";
			QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
			return runner.query(sql, new BeanHandler<User>(User.class), category);
		}
	// 激活用戶
	public void activeUser(String activeCode) throws SQLException {
		String sql = "update user set state=? where activeCode=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, 1, activeCode);
	}
	
	//根据用户名与密码查找用户
	public User findUserByUsernameAndPassword(String username, String password) throws SQLException {
		String sql="select * from user where username=? and PASSWORD=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanHandler<User>(User.class),username,password);
	}

}
