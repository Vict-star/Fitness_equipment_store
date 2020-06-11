package com.zxt.test;

import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

public class FoldLineService {
	public JFreeChart createFoldLineTools() {
	    //获取折线图数据源
	    DefaultCategoryDataset dataset=getDataSet();
	    //从折线图工具类中获取创建完成的折线图
	    JFreeChart chart=FoldLineTools.createFoldLine(dataset);
	    return chart;
	}
	public static DefaultCategoryDataset getDataSet(){
	    //创建数据集
	    DefaultCategoryDataset dataset=new DefaultCategoryDataset();
	    //添加数据
	    dataset.addValue(15636.36, "张三", "一月");
	    dataset.addValue(10001.36, "张三", "二月");
	    dataset.addValue(8856.20, "张三", "三月");
	    dataset.addValue(5964.26, "张三", "四月");
	    dataset.addValue(12365.23, "李四", "一月");
	    dataset.addValue(20056.12, "李四", "二月");
	    dataset.addValue(7896.36, "李四", "三月");
	    dataset.addValue(23005.45, "李四", "四月");
	    return dataset;
	}
}
