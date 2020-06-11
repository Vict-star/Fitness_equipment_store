package com.zxt.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class PieService {
	/**
	 * 从饼状图工具类里面获取创建的Columnar柱状图
	 */
	public JFreeChart createPieTools() {
	    // TODO Auto-generated method stub
	    // 获取饼状图的数据集
	    DefaultPieDataset dataset = getDataSet();
	    // 获取饼状图工具类创建的饼状图
	    JFreeChart chart = PieTools.createPie(dataset);
	    return chart;
	}

	/**
	 * 添加饼状图的数据
	 * 
	 * @return
	 */
	private static DefaultPieDataset getDataSet() {
	    // 数据可以从数据库中得到
	    DefaultPieDataset dataset = new DefaultPieDataset();
	    // 添加数据
	    Map<String, Double> map = new HashMap<String, Double>();
	    map.put("张三", (double) 33);
	    map.put("李四", (double) 14);
	    map.put("李四", (double) 27);
	    map.put("王六", (double) 11);
	    Double sum = 0.0;
	    int count = map.size();
	    Set<String> keys = map.keySet();
	    for (String key : keys) {
	        sum += sum + map.get(key);

	    }
	    for (String key : keys) {

	        dataset.setValue(key, map.get(key));
	    }
	    // dataset.setValue("张三",30);
	    // dataset.setValue("李四",12);
	    // dataset.setValue("李四",12);
	    // dataset.setValue("王六",10);

	    return dataset;
	}
}
	