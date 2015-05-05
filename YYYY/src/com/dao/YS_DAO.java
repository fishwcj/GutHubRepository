package com.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.activity.Index_Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;

public class YS_DAO {

	Context context;
	// 数据库对象
//	DataBase dataBase;
	// 预算表名 budget
	String tableNameString = "budget";
	String currentString;

	@SuppressLint("SimpleDateFormat")
	public YS_DAO(Context context) {
//		this.dataBase = dataBase;
		// 获得当前日期
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		currentString = format.format(new Date());
	}

	/**
	 * 查询数据库得到所有类别的预算信息
	 * 
	 * @return
	 */
	public Cursor read_budget() {
//		SQLiteDatabase db = dataBase.getReadableDatabase();
		Cursor cursor;
		cursor = Index_Activity.db.rawQuery("select budget from tablebudget where month = '"
				+ currentString + "'", null);
		System.out.println("查询时间：" + currentString);
		return cursor;
	}

	/**
	 * 读取总预算
	 * 
	 * @return
	 */
	public String read_totalbudget() {
//		SQLiteDatabase db = dataBase.getReadableDatabase();
		String totalString = "哦！数据库宕机了...";
		Cursor cursor = Index_Activity.db.rawQuery(
				"select totalbudget from tabletotalbudget where month = '"
						+ currentString + "'", null);
		if (cursor.moveToNext()) {
			totalString = cursor
					.getString(cursor.getColumnIndex("totalbudget"));
		}
		return totalString;
	}

	/**
	 * 如果没有该类预算则插入，如果有则更新
	 * 
	 * @param consum_mony
	 *            预算金额
	 * @param kind
	 *            预算类别
	 * @return
	 */
	public boolean add(float[] budget, int[] kind) {
//		SQLiteDatabase db = dataBase.getWritableDatabase();
		String sql;
		for (int i = 0; i < budget.length; i++) {
			// 添加预算，对此前的数据更新
			sql = "update tablebudget set budget = " + budget[i]
					+ ", remain = remain - budget + " + budget[i]
					+ " where kind = " + kind[i] + " and month = '"
					+ currentString + "'";
			Index_Activity.db.execSQL(sql);
		}
		// db.close();
		return true;
	}

	/**
	 * 更新总预算表
	 * 
	 * @param totalbudget
	 *            总预算
	 * @return
	 */
	public boolean addtotal(float totalbudget) {
//		SQLiteDatabase db = dataBase.getWritableDatabase();
		String sql = "update tabletotalbudget set remain = remain - totalbudget + "
				+ totalbudget + " where month = '" + currentString + "'";
		Index_Activity.db.execSQL(sql);
		sql = "update tabletotalbudget set totalbudget = " + totalbudget
				+ " where month = '" + currentString + "'";
		Index_Activity.db.execSQL(sql);
		// db.close();
		return true;
	}

	/**
	 * 更新总预算表,被每次记一笔时调用
	 */
	private boolean deltotal(float consume) {
//		db = dataBase.getWritableDatabase();
		String sql = "update tabletotalbudget set remain = remain -" + consume
				+ " where month = '" + currentString + "'";
		Index_Activity.db.execSQL(sql);
		return true;
	}

	/**
	 * 每记一次账更新分类预算剩余
	 * 
	 * @return
	 */
	public boolean update(float consume, int kind) {
//		SQLiteDatabase db = dataBase.getWritableDatabase();
		Index_Activity.db.execSQL("update tablebudget set remain = remain -" + consume
				+ " where kind = " + kind + " and month = '" + currentString
				+ "'");
		deltotal(consume);
		return true;
	}

	/**
	 * 更新收入表
	 * 
	 * @param in
	 * @param db
	 * @return
	 */
	public boolean updatein(float in) {
//		db = dataBase.getWritableDatabase();
		String sql = "update consumein set mony = mony + " + in
				+ " where month = '" + currentString + "'";
		Index_Activity.db.execSQL(sql);
		return true;
	}
}
