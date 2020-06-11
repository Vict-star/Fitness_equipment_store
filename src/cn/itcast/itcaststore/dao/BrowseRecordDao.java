package cn.itcast.itcaststore.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.itcaststore.domain.BrowseRecord;
import cn.itcast.itcaststore.domain.Product;
import cn.itcast.itcaststore.utils.DataSourceUtils;

public class BrowseRecordDao {
	// 插入浏览记录
	public void addBrowseRecor(BrowseRecord bRecord) throws SQLException {
		String sql = "insert into browserecord(user_id, product_id, product_category, stay_time) values(?,?,?,?)";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, bRecord.getUser_id(),bRecord.getProduct_id(),bRecord.getProduct_category(),bRecord.getStay_time());
		}
	//查找用户购买销售人员其所负责销售商品的  浏览  记录
	public List<BrowseRecord> fingBrowseRecordByCategory(String category)throws SQLException {
		String sql = "select * from browserecord where product_category=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<BrowseRecord>(BrowseRecord.class) ,category);
	}
	//查找用户购买销售人员其所负责销售商品的  购买  记录
	public List<Object[]> fingBuyRecordByCategory(String category)throws SQLException {
		String sql ="select orders.user_id, orderItem.product_id, products.price, orderItem.buynum, products.price*orderItem.buynum, orderItem.time "
				+ "from orders, orderItem, products "
				+ "where products.category=? and orders.paystate=1 "
				+ "and products.id=orderItem.product_id and orderItem.order_id=orders.id ORDER BY orderItem.time";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new ArrayListHandler(), category);
	}
}
