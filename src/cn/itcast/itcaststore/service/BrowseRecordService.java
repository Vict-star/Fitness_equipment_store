package cn.itcast.itcaststore.service;

import java.sql.SQLException;
import java.util.List;
import cn.itcast.itcaststore.dao.BrowseRecordDao;
import cn.itcast.itcaststore.domain.BrowseRecord;

public class BrowseRecordService {
	BrowseRecordDao bRecordDao=new BrowseRecordDao();
	// 插入浏览记录
		public void addBrowseRecor(BrowseRecord bRecord) throws SQLException {
			try {
				bRecordDao.addBrowseRecor(bRecord);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		//查找用户购买销售人员其所负责销售商品的  浏览  记录
		public List<BrowseRecord> fingBrowseRecordByCategory(String category){
			try {
				List<BrowseRecord> bList= bRecordDao.fingBrowseRecordByCategory(category);
				return bList;
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
		//查找用户购买销售人员其所负责销售商品的  购买  记录
		public List<Object[]> fingBuyRecordByCategory(String category){
			try {
				List<Object[]> objects= bRecordDao.fingBuyRecordByCategory(category);
				return objects;
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
}
