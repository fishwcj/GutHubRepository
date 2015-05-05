package com.model;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import com.dao.TJ_DAO;

public class TJ_DrawPieHelper {
	ArrayList<Float> consume_type_listArrayList;
	TJ_DAO tj_DataBaseHelper;
	Activity manager;

	public TJ_DrawPieHelper(Activity manager) {
		this.manager = manager;
	}

	// 从数据库获取每种类别的百分比
	public void getPercent() {
		consume_type_listArrayList = new ArrayList<Float>();
		TJ_DAO tj_DataBaseHelper = new TJ_DAO(manager);
		consume_type_listArrayList = tj_DataBaseHelper.getTypeConsume();

	}

	// 根据 百分比画饼图
	public PieView Draw() {

		int[] colors = new int[] { Color.YELLOW, Color.RED, Color.BLUE,
				Color.GREEN };
		float[] percent = { 0, 0, 0, 0 };
		PieView pieView;
		getPercent();// 获取百分比
		if (consume_type_listArrayList != null) {
		
			for (int i = 0; i < consume_type_listArrayList.size(); i++) {
				percent[i] = consume_type_listArrayList.get(i);
				System.out.println("各自百分比"+percent[i]);
			}
		}
		
		pieView = new PieView(manager, colors, percent);
		return pieView;
	}
}
