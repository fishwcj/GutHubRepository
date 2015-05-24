package com.model.count;

import java.util.ArrayList;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import android.app.Activity;
import android.graphics.Color;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Toast;

import com.activity.Count_Activity;
import com.dao.TJ_DAO;
import com.inteface.Chart;
import com.yyyy.yyyy.R;

public class PieChart implements Chart {
	ArrayList<Float> consume_type_list;
	TJ_DAO tj_DataBaseHelper;
	Activity context = Count_Activity.countActivity;
	float percent[] = { 0, 0, 0, 0 };
	//按时间进行画图
	String date;
	public PieChart(String date) {	
		this.date = date;
	}
	/**
	 * 从数据库获取每种类别的花的总钱数按时间进行查询
	 */
	public CategorySeries getSeries(String date) {
		System.out.println("lilingling"+date);
		CategorySeries series = new CategorySeries("消费分析");
		consume_type_list = new ArrayList<Float>();
		TJ_DAO tj_DataBaseHelper = new TJ_DAO();
		consume_type_list = tj_DataBaseHelper.getTypeConsume(date);
		for(int i = 0;i < 4;i++){
			percent[i] = (float) consume_type_list.get(i);
		}
		series.add("衣", percent[0]);
		series.add("食", percent[1]);
		series.add("住", percent[2]);
		series.add("行", percent[3]);
		return series;
	}
	/**
	 * 对将要画的饼图进行属性设置
	 */
	public DefaultRenderer getRenderer(){
		DefaultRenderer renderer = new DefaultRenderer();
		renderer.setMargins(new int[] { 10, 20, 30, 0 });// 设置图例之间的间接单位为px
		renderer.setLabelsTextSize(30);// 设置标签的字体大小
		renderer.setLabelsColor(Color.WHITE);
		renderer.setDisplayValues(true);// 是否显示值
		renderer.setChartTitle("消费类别分析");
		renderer.setLegendTextSize(30);// 设置图例的字体大小
		renderer.setZoomEnabled(false);
		// 添加每种消费类别及其百分比并设置每一个类别的属性
		SimpleSeriesRenderer r1 = new SimpleSeriesRenderer();
		r1.setColor(Color.BLUE);
		renderer.addSeriesRenderer(r1);// 设置饼图颜色，衣
		SimpleSeriesRenderer r2 = new SimpleSeriesRenderer();
		r2.setColor(Color.GREEN);
		renderer.addSeriesRenderer(r2);// 设置饼图颜色，食
		SimpleSeriesRenderer r3 = new SimpleSeriesRenderer();
		r3.setColor(Color.RED);
		renderer.addSeriesRenderer(r3); // 设置饼图颜色，住
		SimpleSeriesRenderer r4 = new SimpleSeriesRenderer();
		r4.setColor(Color.YELLOW);
		renderer.addSeriesRenderer(r4); // 设置饼图颜色，行
		return renderer;
	}
	@Override
	public void draw() {
		CategorySeries series = getSeries(date);
		DefaultRenderer renderer = getRenderer();
		GraphicalView pieView = ChartFactory.getPieChartView(context, series,
				renderer);
		LinearLayout pieViewLayout = (LinearLayout) context
				.findViewById(R.id.pieView1);
		if (pieViewLayout != null) {
			pieViewLayout.removeAllViews();// 先清除原来的图
		}
		if (percent[0] == 0 && percent[1] == 0 && percent[2] == 0
				&& percent[3] == 0) {
			Toast.makeText(context, "没有消费记录", Toast.LENGTH_SHORT).show();
		} else {
			
			// 确定画布大小
			LayoutParams show = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);		
			pieViewLayout.addView(pieView, show);// 将上图作为子布局加入到父布局中
		}
	}
}
