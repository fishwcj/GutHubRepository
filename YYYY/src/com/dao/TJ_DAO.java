/*
 * Author LLL
 * Time:4.14
 * 
 */
package com.dao;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.activity.Index_Activity;

import android.app.Activity;
import android.database.Cursor;

public class TJ_DAO {
	Activity manager;
	// DataBase dataBase;
	String tablename = "test1";

	public TJ_DAO(Activity manager) {
		this.manager = manager;
	}

	/**
	 * 
	 * ��ȡĳһ��������ܶ ��float_list ����ʽ����
	 */
	public ArrayList<Float> getTypeConsume() {

		ArrayList<Float> perc_List;
		Cursor cursor;
		Cursor cursor1;
		float item;
		float total = 0;
		float percent;
		BigDecimal b, b1;
		perc_List = new ArrayList<Float>();
		String sql = "select totalbudget-remain as total_consume from tabletotalbudget";
		cursor1 = Index_Activity.db.rawQuery(sql, null);
		while (cursor1.moveToNext()) {
			total = cursor1.getFloat(cursor1.getColumnIndex("total_consume"));
		}
		cursor1.close();
		if (total == 0) {
			perc_List.add(total);
			perc_List.add(total);
			perc_List.add(total);
			perc_List.add(total);

		} else {
			sql = "select budget-remain as sum_consume from tablebudget ";
			cursor = Index_Activity.db.rawQuery(sql, null);
			while (cursor.moveToNext()) {
				item = cursor.getFloat(cursor.getColumnIndex("sum_consume"));
				b = new BigDecimal(item / total);
				percent = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
				b1 = new BigDecimal(percent * 360);
				percent = b1.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
				System.out.println("�ٷֱ�" + percent);
				// ������λ��Ч����

				perc_List.add(percent);
			}
			cursor.close();
		}
		return perc_List;
	}
}
