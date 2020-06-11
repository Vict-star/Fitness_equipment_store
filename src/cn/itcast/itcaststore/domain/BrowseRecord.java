package cn.itcast.itcaststore.domain;

import java.util.Date;

public class BrowseRecord {
	private int user_id; // 用户编号
	private String product_id; // 商品编号
	private String product_category; // 商品类别
	private String stay_time; // 停留时长/秒
	private Date browse_time;// 浏览时间
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public String getProduct_category() {
		return product_category;
	}
	public void setProduct_category(String product_category) {
		this.product_category = product_category;
	}
	public String getStay_time() {
		return stay_time;
	}
	public void setStay_time(String stay_time) {
		this.stay_time = stay_time;
	}
	public Date getBrowse_time() {
		return browse_time;
	}
	public void setBrowse_time(Date browse_time) {
		this.browse_time = browse_time;
	}
	@Override
	public String toString() {
		return "BrowseRecord [user_id=" + user_id + ", product_id=" + product_id + ", product_category="
				+ product_category + ", stay_time=" + stay_time + ", browse_time=" + browse_time + "]";
	}
	
}