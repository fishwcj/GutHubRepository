package com.model;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;

import com.dao.DataBase;
import com.dao.TJ_DataBaseHelper;

public class TJ_DrawHelper {
	ArrayList<Float> consume_type_listArrayList;
	TJ_DataBaseHelper tj_DataBaseHelper;
	Activity manager;
	DataBase dataBase;

	public TJ_DrawHelper(Activity manager, DataBase dataBase) {
		this.manager = manager;
		this.dataBase = dataBase;
	}

	// �����ݿ��ȡÿ�����İٷֱ�
	public void getPercent() {
		consume_type_listArrayList = new ArrayList<Float>();
		TJ_DataBaseHelper tj_DataBaseHelper = new TJ_DataBaseHelper(manager,
				dataBase);
		consume_type_listArrayList = tj_DataBaseHelper.getTypeConsume();

	}

	// ���� �ٷֱȻ���ͼ
	public PieView Draw() {

		int[] colors = new int[] { Color.YELLOW, Color.RED, Color.BLUE,
				Color.GREEN };
		float[] percent = { 0, 0, 0, 0 };
		getPercent();// ��ȡ�ٷֱ�
		if (consume_type_listArrayList == null) {
		} else {
			for (int i = 0; i < consume_type_listArrayList.size() - 1; i++) {
				percent[i] = consume_type_listArrayList.get(i);
			}
			percent[3] = 360 - percent[0] - percent[1] - percent[2];
		}
		PieView pieView = new PieView(manager, colors, percent);
		return pieView;
	}
}
