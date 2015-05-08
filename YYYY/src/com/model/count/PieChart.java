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

	public PieChart() {

	}

	// 从数据库获取每种类别的百分比
	public void getPercent() {
		consume_type_list = new ArrayList<Float>();
		TJ_DAO tj_DataBaseHelper = new TJ_DAO();
		consume_type_list = tj_DataBaseHelper.getTypeConsume();
		System.out.println(consume_type_list.get(0) + "kkkkkkkkkkkkk");
		percent[0] = (float) consume_type_list.get(0);
		percent[1] = (float) consume_type_list.get(1);
		percent[2] = (float) consume_type_list.get(2);
		percent[3] = (float) consume_type_list.get(3);
	}

	@Override
	public void draw() {
		getPercent();
		CategorySeries series = new CategorySeries("消费分析");
		series.add("衣", percent[0]);
		series.add("食", percent[1]);
		series.add("住", percent[2]);
		series.add("行", percent[3]);
		// 创建DefaultRenderer组件
		DefaultRenderer renderer = new DefaultRenderer();

		renderer.setMargins(new int[] { 10, 20, 30, 0 });// 设置图例之间的间接单位为px
		renderer.setLabelsTextSize(30);// 设置标签的字体大小
		renderer.setLabelsColor(Color.WHITE);
		renderer.setZoomButtonsVisible(true);// 设置显示放大缩小按钮
		renderer.setDisplayValues(true);// 是否显示值
		renderer.setChartTitle("消费类别分析");
		renderer.setLegendTextSize(30);// 设置图例的字体大小
		// 添加每种消费类别及其百分比
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
		// 产生饼图
		GraphicalView pieView = ChartFactory.getPieChartView(context, series,
				renderer);
		LinearLayout pieViewLayout = (LinearLayout) context
				.findViewById(R.id.pieView1);
		if (percent[0] == 0 && percent[1] == 0 && percent[2] == 0
				&& percent[3] == 0) {
			Toast.makeText(context, "没有消费记录", Toast.LENGTH_SHORT).show();
		} else {
			if (pieViewLayout != null) {
				pieViewLayout.removeAllViews();// 先清除原来的图
			}
			LayoutParams show = new LayoutParams(LayoutParams.WRAP_CONTENT, 500);// 确定
			// 画布大小
			pieViewLayout.addView(pieView, show);// 将上图作为子布局加入到父布局中
		}
	}
}
