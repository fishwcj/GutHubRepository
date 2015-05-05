package com.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.activity.Index_Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;

public class YS_DAO {

	Context context;
	// ���ݿ����
//	DataBase dataBase;
	// Ԥ����� budget
	String tableNameString = "budget";
	String currentString;

	@SuppressLint("SimpleDateFormat")
	public YS_DAO(Context context) {
//		this.dataBase = dataBase;
		// ��õ�ǰ����
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		currentString = format.format(new Date());
	}

	/**
	 * ��ѯ���ݿ�õ���������Ԥ����Ϣ
	 * 
	 * @return
	 */
	public Cursor read_budget() {
//		SQLiteDatabase db = dataBase.getReadableDatabase();
		Cursor cursor;
		cursor = Index_Activity.db.rawQuery("select budget from tablebudget where month = '"
				+ currentString + "'", null);
		System.out.println("��ѯʱ�䣺" + currentString);
		return cursor;
	}

	/**
	 * ��ȡ��Ԥ��
	 * 
	 * @return
	 */
	public String read_totalbudget() {
//		SQLiteDatabase db = dataBase.getReadableDatabase();
		String totalString = "Ŷ�����ݿ�崻���...";
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
	 * ���û�и���Ԥ������룬����������
	 * 
	 * @param consum_mony
	 *            Ԥ����
	 * @param kind
	 *            Ԥ�����
	 * @return
	 */
	public boolean add(float[] budget, int[] kind) {
//		SQLiteDatabase db = dataBase.getWritableDatabase();
		String sql;
		for (int i = 0; i < budget.length; i++) {
			// ���Ԥ�㣬�Դ�ǰ�����ݸ���
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
	 * ������Ԥ���
	 * 
	 * @param totalbudget
	 *            ��Ԥ��
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
	 * ������Ԥ���,��ÿ�μ�һ��ʱ����
	 */
	private boolean deltotal(float consume) {
//		db = dataBase.getWritableDatabase();
		String sql = "update tabletotalbudget set remain = remain -" + consume
				+ " where month = '" + currentString + "'";
		Index_Activity.db.execSQL(sql);
		return true;
	}

	/**
	 * ÿ��һ���˸��·���Ԥ��ʣ��
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
	 * ���������
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
