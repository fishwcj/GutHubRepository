package com.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mnitools.GetNowDate;
import com.model.count.Draw_chart;
import com.model.count.LineChart;
import com.model.count.PieChart;
import com.yyyy.yyyy.R;

public class Count_Activity extends Activity {
	public static Activity countActivity;
	private Button plus;
	private Button sub;
	private TextView show_time;
	LinearLayout ViewLayout;
	String[] time;
	String nowDate;// 年
	String nowDateY;
	String nowDateM;// 月
	int InowDateM;// int型月
	int InowDateY;// int型年

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_count);
		ViewLayout = (LinearLayout) this.findViewById(R.id.view);
		countActivity = this;
		plus = (Button) findViewById(R.id.plus);
		sub = (Button) findViewById(R.id.sub);
		show_time = (TextView) findViewById(R.id.show_time);

		time = getResources().getStringArray(R.array.select_time);
		GetNowDate nowDateGet = new GetNowDate();
		nowDate = nowDateGet.getNowDate("yyyy-MM");
		nowDateY = nowDateGet.getNowDate("yyyy");
		nowDateM = nowDateGet.getNowDate("MM");
		InowDateM = Integer.parseInt(nowDateM);
		InowDateY = Integer.parseInt(nowDateY);
		show_time.setText(nowDate);

		plus.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				show_time.setText(nowDate);
				InowDateM = Integer.parseInt(nowDateM);
				PieChart pie = new PieChart(nowDate);
				Draw_chart.draw_Chart(pie);
				LineChart line = new LineChart(nowDate);
				Draw_chart.draw_Chart(line);

			}
		});
		sub.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// ViewLayout.removeAllViews();

				String nowDate;

				if (InowDateM >= 2) {
					InowDateM--;
					nowDate = InowDateY + "-" + InowDateM;
					UpdateView(nowDate);
					// show_time.setText(nowDate);
					// PieChart pie = new PieChart(nowDate);
					// Draw_chart.draw_Chart(pie);
					// LineChart line= new LineChart(nowDate);
					// Draw_chart.draw_Chart(line);
				} else {
					InowDateY--;
					InowDateM = 12;
					nowDate = InowDateY + "-" + InowDateM;
					UpdateView(nowDate);
					// show_time.setText(nowDate);
					// PieChart pie = new PieChart(nowDate);
					// Draw_chart.draw_Chart(pie);
					// LineChart line= new LineChart(nowDate);
					// Draw_chart.draw_Chart(line);

				}
			}
		});

	}
/**
 * @param nowdate
 * 画统计图
 */
	private void UpdateView(String nowdate) {
		show_time.setText(nowdate);
		PieChart pie = new PieChart(nowdate);
		Draw_chart.draw_Chart(pie);
		LineChart line = new LineChart(nowdate);
		Draw_chart.draw_Chart(line);
	}
}
