package cn.itcast.itcaststore.service;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

import cn.itcast.itcaststore.dao.ProductDao;
import cn.itcast.itcaststore.domain.PageBean;
import cn.itcast.itcaststore.domain.Product;
import cn.itcast.itcaststore.exception.AddProductException;
import cn.itcast.itcaststore.exception.FindProductByIdException;
import cn.itcast.itcaststore.exception.ListProductException;
import cn.itcast.itcaststore.utils.DataSourceUtils;
public class ProductService {
	private ProductDao dao = new ProductDao();
	// 添加商品
	public void addProduct(Product p) throws AddProductException {
		try {
			dao.addProduct(p);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AddProductException("添加商品失败");
		}
	}
	// 查找所有商品信息
	public List<Product> listAll() throws ListProductException {
		try {
			return dao.listAll();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ListProductException("查询商品失败");
		}
	}
	// 查看商品当月销量
	public List<Object[]> salesnumber(String year, String month,String id){
		try {
			return dao.salesnumber(year,month,id);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	// 查看一类商品当月销量
	public List<Object[]> salescategorynumber(String year, String month,String category){
		try {
			return dao.salescategorynumber(year,month,category);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}		
	}
	// 分页操作
	public PageBean findProductByPage(int currentPage, int currentCount,String category) {
		PageBean bean = new PageBean();
		// 封装每页显示数据条数
		bean.setCurrentCount(currentCount);
		// 封装当前页码
		bean.setCurrentPage(currentPage);
		// 封装当前查找类别
		bean.setCategory(category);
		try {
			// 获取总条数
			int totalCount = dao.findAllCount(category);
			bean.setTotalCount(totalCount);
			// 获取总页数
			int totalPage = (int) Math.ceil(totalCount * 1.0 / currentCount);
			bean.setTotalPage(totalPage);
			// 获取当前页数据
			List<Product> ps = dao.findByPage(currentPage, currentCount,category);
			bean.setPs(ps);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bean;
	}
	// 根据id查找商品
	public Product findProductById(String id) throws FindProductByIdException {
		try {
			return dao.findProductById(id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindProductByIdException("根据ID查找商品失败");
		}
	}
	// 根据id查找商品后增加商品评价
	public void increaseevaluate(String id) {
		try {
			dao.increaseevaluate(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	// 根据id查找商品后降低商品评价
	public void reduceevaluate(String id) {
		try {
			dao.reduceevaluate(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	// 下载销售榜单，查询已经支付的商品信息
	public List<Object[]> download(String year, String month) {
		List<Object[]> salesList = null;
		try {
			salesList = dao.salesList(year, month);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return salesList;
	}
	// 多条件查询
	public List<Product> findProductByManyCondition(String id, String name,
			String category, String minprice, String maxprice) {
		List<Product> ps = null;
		try {
			ps = dao.findProductByManyCondition(id, name, category, minprice,
					maxprice);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ps;
	}
	// 修改商品信息
	public void editProduct(Product p) {
		try {
			dao.editProduct(p);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//前台，获取本周热销商品
	public List<Object[]> getWeekHotProduct() {
		try {
			return dao.getWeekHotProduct();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("前台获取本周热销商品失败！");
		}
	}
	//前台，用于搜索框根据书名来模糊查询相应的图书
	public PageBean findBookByName(int currentPage, int currentCount,
			String searchfield) {
		PageBean bean = new PageBean();
		// 封装每页显示数据条数
		bean.setCurrentCount(currentCount);
		// 封装当前页码
		bean.setCurrentPage(currentPage);
		// 封装模糊查询的图书名
		bean.setSearchfield(searchfield);
		try {
			// 获取总条数
			int totalCount = dao.findBookByNameAllCount(searchfield);
			bean.setTotalCount(totalCount);

			// 获取总页数
			int totalPage = (int) Math.ceil(totalCount * 1.0 / currentCount);
			bean.setTotalPage(totalPage);

			//满足条件的图书
			List<Product> ps = dao.findBookByName(currentPage,currentCount,searchfield);
			bean.setPs(ps);
			return bean;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("前台搜索框根据书名查询图书失败！");
		}
	}
	//后台系统，根据id删除商品信息
	public void deleteProduct(String id) {
		try {
			dao.deleteProduct(id);
		} catch (SQLException e) {
			throw new RuntimeException("后台系统根据id删除商品信息失败！");
		}
	}
}
