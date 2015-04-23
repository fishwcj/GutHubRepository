/*
 * Author LLL
 * Time:4.14
 * 
 */
package com.dao;

import java.math.BigDecimal;
import java.util.ArrayList;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class TJ_DataBaseHelper {
	Activity manager;
	DataBase dataBase;
	String tablename = "test1";

	public TJ_DataBaseHelper(Activity manager, DataBase dataBase) {
		this.manager = manager;
		this.dataBase = dataBase;
	}

	/*
	 * 
	 * 获取某一类的消费总额， 以float_list 的形式返回
	 */
	public ArrayList<Float> getTypeConsume() {

		ArrayList<Float> perc_List;
		Cursor cursor;
		Cursor cursor1;
		float item;
		float total = 0;
		float percent;
		BigDecimal b,b1;
		perc_List = new ArrayList<Float>();
		SQLiteDatabase db = dataBase.getReadableDatabase();
		cursor1 = db
				.rawQuery(
						"select totalbudget-remain as total_consume from tabletotalbudget",
						null);
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
			cursor = db.rawQuery(
					"select budget-remain as sum_consume from tablebudget ",
					null);
			while (cursor.moveToNext()) {
				item = cursor.getFloat(cursor.getColumnIndex("sum_consume"));
				b = new BigDecimal(item/total);
				percent = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
				b1 = new BigDecimal(percent * 360);
				percent = b1.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
				System.out.println("百分比"+percent);
				//保留两位有效数字
				
				perc_List.add(percent);
			}
			cursor.close();
		}
		return perc_List;
	}
}
