/*
 * Author LLL
 * Time:4.14
 * 
 */
package com.dao;

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
	 * 获取某一类的消费总额
	 */
	public ArrayList<Float> getTypeConsume() {

		ArrayList<Float> perc_List;
		Cursor cursor;
		Cursor cursor1;
		float item;
		float total = 0;
		float percent ;
		
		
		perc_List = new ArrayList<Float>();
		SQLiteDatabase db = dataBase.getReadableDatabase();
		cursor1 = db.rawQuery("select totalbudget-remain as total_consume from tabletotalbudget", null);
		int index = cursor1.getCount();
		
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
			
			cursor = db
					.rawQuery(
							"select budget-remain as sum_consume from tablebudget ",
							null);
			index = cursor.getCount();
			
			while (cursor.moveToNext()) {

				item = cursor.getFloat(cursor.getColumnIndex("sum_consume"));
				System.out.println(item+"itemitem");
				percent = item/total*360;
				perc_List.add(percent);

			}
			cursor.close();
		}

		
		
		return perc_List;
	}
}
