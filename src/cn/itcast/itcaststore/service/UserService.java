package cn.itcast.itcaststore.service;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javax.security.auth.login.LoginException;
import cn.itcast.itcaststore.dao.UserDao;
import cn.itcast.itcaststore.domain.BrowseRecord;
import cn.itcast.itcaststore.domain.User;
import cn.itcast.itcaststore.exception.ActiveUserException;
import cn.itcast.itcaststore.exception.RegisterException;
import cn.itcast.itcaststore.utils.MailUtils;

public class UserService {
	private UserDao dao = new UserDao();
	// 注册操作
	public void register(User user) throws RegisterException {
		// 调用dao完成注册操作
		try {
			dao.addUser(user);   //往数据库插入用户数据（state=0，未激活）
			// 发送激活邮件
			String emailMsg = "感谢您注册网上书城，点击"
					+ "<a href='http://localhost:8080/itcaststore/activeUser?activeCode="
					+ user.getActiveCode() + "'>&nbsp;激活&nbsp;</a>后使用。"
							+ "<br />为保障您的账户安全，请在24小时内完成激活操作";
			MailUtils.sendMail(user.getEmail(), emailMsg);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RegisterException("注冊失败");  //自定义注册异常类
		}
	}
	// 添加销售人员
	public void addSalesman(User user) throws SQLException {
		try {
			dao.addSalesman(user);  //添加销售人员
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void uppdatescore(int score,int id){
		try {
			dao.uppdatescore(score,id);  //更新评分
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// 查找所有普通用户
	public List<User> listAllCustomer() throws SQLException {
		List<User> uList=null;
		try {
			uList=dao.listAllCustomer();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return uList;
	}
	//查找某个用户购买总数
	public int FindTolNum(int id){
		int TolNum=0;
		try {
			TolNum=dao.FindTolNum(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return TolNum;
	}
	//查找某个用户购买总金额
	public double FindTolMoney(int id){
		double TolMoney=0;
		try {
			TolMoney=dao.FindTolMoney(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return TolMoney;
	}
	//查找某个用户某年月的购买金额
		public double FindYearMonthMoney(String year, String month,int id){
			double YearMonthMoney=0;
			try {
				YearMonthMoney=dao.FindYearMonthMoney(year,month,id);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return YearMonthMoney;
		}
	//查找某个用户最常购买商品种类(嵌套查询)
	public String findMostBuy(int id){
		String pcategory=null;
		try {
			pcategory=dao.findMostBuy(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pcategory;
	}
	//查找某个用户最常浏览商品种类
	public String findMostBrowser(int id){
		String pcategory=null;
		try {
			pcategory=dao.findMostBrowser(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pcategory;	
	}
	//查找某个用户最近一周最常浏览商品种类
	public String findWeekMostBrowser(int id){
		String pcategory=null;
		try {
			pcategory=dao.findWeekMostBrowser(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pcategory;
	}
	//查找某个用户总的浏览次数
	public int findTolBrowser(int id){	
		int count=0;
		try {
			count=dao.findTolBrowser(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	//近两周登录总时长
		public String find2WeekLogin(int id){
			String time="0";
			try {
				time=dao.find2WeekLogin(id);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return time;
		}
	//用户最常上线IP地址
		public String findMostIP(int id){
			String IP="0:0:0:0:0:0:0:1";
			try {
				IP=dao.findMostIP(id);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return IP;	
		}
	//查找用户的  浏览  记录
		public List<BrowseRecord> fingBrowseRecordByUser(int id){
			try {
				List<BrowseRecord> bList= dao.fingBrowseRecordByUser(id);
				return bList;
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
	//查找用户的  购买  记录
		public List<Object[]> fingBuyRecordByUser(int id){
			try {
				List<Object[]> objects= dao.fingBuyRecordByUser(id);
				return objects;
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
	// 查找所有销售人员
	public List<User> listAll() throws SQLException {
		List<User> uList=null;
		try {
			uList=dao.listAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return uList;
	}
	// 更新用户信息
	public void updateuser(User user) throws RegisterException {
		// 调用dao完成注册操作
		try {
			dao.updateuser(user);   //更新用户信息
		} catch (Exception e) {
			e.printStackTrace();
			throw new RegisterException("更新失败");  //自定义注册异常类
		}
	}
	// 更新销售人员信息
	public void updatesalesman(User user) throws Exception {
		// 调用dao完成注册操作
		try {
			dao.updatesalesman(user);   //更新用户信息
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// 激活用户
	public void activeUser(String activeCode) throws ActiveUserException {
		try {
			// 根据激活码查找用户
			User user = dao.findUserByActiveCode(activeCode);
			if (user == null) {
				throw new ActiveUserException("激活用户失败，验证码错误");
			}else {
				System.out.println("激活码正确");
			}
			// 判断激活码是否过期 24小时内激活有效.
			// 1.得到注册时间
			Date registTime = user.getRegistTime();
			// 2.判断是否超时
			long time = System.currentTimeMillis() - registTime.getTime();
			if (time / 1000 / 60 / 60 > 24) {
				throw new ActiveUserException("激活码过期");
			}else {
				System.out.println("激活码未过期");
			}
			// 激活用户，就是修改用户的state状态
			dao.activeUser(activeCode);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ActiveUserException("激活用户失败");
		}
	}
	// 根据激活码查找用户
		public User findUserByActiveCode(String activeCode) throws ActiveUserException {
			try {
				// 根据激活码查找用户
				User user = dao.findUserByActiveCode(activeCode);
				return user;
			} catch (Exception e) {
				e.printStackTrace();
				throw new ActiveUserException("根据激活码查找用户失败");
			}
		}
	//根据id查找用户
		public User findUserById(int id) {
			User user =null;
			try {
				// 根据激活码查找用户
				user = dao.findUserById(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return user;
		}
	// 根据商品种类查找销售人员
		public User findUserByCategory(String category){
			User user =null;
			try {
				// 根据激活码查找用户
				user = dao.findUserByCategory(category);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return user;
		}
	// 登录操作
	public User login(String username, String password) throws LoginException {
		try {
			//根据登录时表单输入的用户名和密码，查找用户
			User user = dao.findUserByUsernameAndPassword(username, password);
			//如果找到，还需要确定用户是否为激活用户
			if (user != null) {
				// 只有是激活才能登录成功，否则提示“用户未激活”
				if (user.getState() == 1) {
					return user;
				}
				throw new LoginException("用户未激活");
			}
			throw new LoginException("用户名或密码错误");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new LoginException("登录失败");
		}
	}
}
