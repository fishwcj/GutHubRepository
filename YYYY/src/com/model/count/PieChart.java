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

	// �����ݿ��ȡÿ�����İٷֱ�
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
		CategorySeries series = new CategorySeries("���ѷ���");
		series.add("��", percent[0]);
		series.add("ʳ", percent[1]);
		series.add("ס", percent[2]);
		series.add("��", percent[3]);
		// ����DefaultRenderer���
		DefaultRenderer renderer = new DefaultRenderer();

		renderer.setMargins(new int[] { 10, 20, 30, 0 });// ����ͼ��֮��ļ�ӵ�λΪpx
		renderer.setLabelsTextSize(30);// ���ñ�ǩ�������С
		renderer.setLabelsColor(Color.WHITE);
		renderer.setZoomButtonsVisible(true);// ������ʾ�Ŵ���С��ť
		renderer.setDisplayValues(true);// �Ƿ���ʾֵ
		renderer.setChartTitle("����������");
		renderer.setLegendTextSize(30);// ����ͼ���������С
		// ���ÿ�����������ٷֱ�
		SimpleSeriesRenderer r1 = new SimpleSeriesRenderer();
		r1.setColor(Color.BLUE);
		renderer.addSeriesRenderer(r1);// ���ñ�ͼ��ɫ����
		SimpleSeriesRenderer r2 = new SimpleSeriesRenderer();
		r2.setColor(Color.GREEN);
		renderer.addSeriesRenderer(r2);// ���ñ�ͼ��ɫ��ʳ
		SimpleSeriesRenderer r3 = new SimpleSeriesRenderer();
		r3.setColor(Color.RED);
		renderer.addSeriesRenderer(r3); // ���ñ�ͼ��ɫ��ס
		SimpleSeriesRenderer r4 = new SimpleSeriesRenderer();
		r4.setColor(Color.YELLOW);
		renderer.addSeriesRenderer(r4); // ���ñ�ͼ��ɫ����
		// ������ͼ
		GraphicalView pieView = ChartFactory.getPieChartView(context, series,
				renderer);
		LinearLayout pieViewLayout = (LinearLayout) context
				.findViewById(R.id.pieView1);
		if (percent[0] == 0 && percent[1] == 0 && percent[2] == 0
				&& percent[3] == 0) {
			Toast.makeText(context, "û�����Ѽ�¼", Toast.LENGTH_SHORT).show();
		} else {
			if (pieViewLayout != null) {
				pieViewLayout.removeAllViews();// �����ԭ����ͼ
			}
			LayoutParams show = new LayoutParams(LayoutParams.WRAP_CONTENT, 500);// ȷ��
			// ������С
			pieViewLayout.addView(pieView, show);// ����ͼ��Ϊ�Ӳ��ּ��뵽��������
		}
	}
}
