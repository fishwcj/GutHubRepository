package com.dao;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;

import com.activity.Index_Activity;
import com.activity.JZ_Activity;
import com.yyyy.yyyy.R;

public class JZ_DataBaseHelper {
	private String currentString;

	@SuppressLint("SimpleDateFormat")
	public JZ_DataBaseHelper() {
		// 获得当前日期，用于查询数据库限制条件
		java.util.Date currentDate = new java.util.Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		this.currentString = format.format(currentDate);
	}

	/**
	 * 更新记账页面的预算余额显示
	 */
	public void updateBudgetRemain(DataBase dataBase) {
		Activity jz_Activity = JZ_Activity.jzActivity;//获得JZ_Activity引用
		TextView remainTextView = (TextView) jz_Activity
				.findViewById(R.id.budgetRemain);
		SQLiteDatabase db = dataBase.getReadableDatabase();
		String sql = "select remain,totalbudget from tabletotalbudget where month = '"
				+ currentString + "'";
		Cursor cursor = db.rawQuery(sql, null);
		if (cursor.moveToNext()) {
			String remainString = cursor.getString(cursor
					.getColumnIndex("remain"));
			Index_Activity.budget = Float.parseFloat(cursor.getString(cursor
					.getColumnIndex("totalbudget")));
			remainString = new DecimalFormat("0.0").format(Float.parseFloat(remainString));//格式化浮点数
			System.out.println("记账里格式化的是" + remainString);
			remainTextView.setText(remainString);
			System.out.println("预算预算是："+Index_Activity.budget);
			System.out.println("余额余额是:" + Index_Activity.remain);
		}
	}
}
