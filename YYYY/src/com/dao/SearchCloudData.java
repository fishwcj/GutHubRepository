package com.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//import com.activity.JZ_Activity;


import android.annotation.SuppressLint;
//import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SearchCloudData {
	
	private Cursor cursor;
	private String sql;
	SQLiteDatabase db;
	DataBase dataBase;
	
	public SearchCloudData(DataBase dataBase){
		db = dataBase.getWritableDatabase();
	}
	
	
	/**
	 * ��ѯ�õ�������ˮ
	 * @param time	�ϴ�ͬ����ʱ��
	 * @return
	 */
	public Cursor searchStreamCount(){
		String time = getLastsytime();
		sql = "select * from test1 where date > '" + time + "'";
		cursor = db.rawQuery(sql, null);
		int i = cursor.getCount();
		System.out.println("������ˮ:" + i );
		return cursor;
	}
	
	
	/**
	 * �õ�����Ԥ���Ԥ��ʣ��
	 * @param datetime	��һ�µ�Ԥ��	 ��ʽ:xxxx-xx
	 * @return
	 */
	public Cursor searchBudget(String datetime){
		sql = "select * from tabletotalbudget where month = '" + datetime + "'";
		cursor = db.rawQuery(sql, null);
		return cursor;
	}
	
	
	/**
	 * �õ���������
	 * @param datetime	��һ�µ�����	��ʽ:xxxx-xx
	 * @return
	 */
	public Cursor searchincome(String datetime){
		sql = "select * from consumein where month = '" + datetime + "'";
		cursor = db.rawQuery(sql, null);
		return cursor;
	}
	
	
	/**
	 * �õ����·���Ԥ��
	 * @param datetime	��һ�µķ���Ԥ��
	 * @return
	 */
	public Cursor searchBudgetByKind(String datetime){
		sql = "select * from tablebudget where month = '" + datetime + "'";
		cursor = db.rawQuery(sql, null);
		return cursor;
	}
	
	@SuppressLint("SimpleDateFormat")
	public boolean isNextMonth(String date) throws ParseException{
		String lastDate = "";
		sql = "select sytime from time";
		cursor = db.rawQuery(sql, null);
		if(cursor.moveToNext()){
			lastDate = cursor.getString(cursor.getColumnIndex("sytime"));
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date1 = format.parse(lastDate);
		format = new SimpleDateFormat("yyyy-MM");
		lastDate = format.format(date1);
		return !(lastDate.equals(date));
	}
	
	public String getLastsytime(){
		String last = "";
		sql = "select sytime from time";
		cursor = db.rawQuery(sql, null);
		if(cursor.moveToNext()){
			last = cursor.getString(cursor.getColumnIndex("sytime"));
		}
		System.out.println("��һ��ͬ��ʱ��" + last);
		return last;
	}
	
	
	/**
	 * ÿ��ͬ��֮�����ʱ���
	 */
	@SuppressLint("SimpleDateFormat")
	public void updateTime(){
		java.util.Date currentDate = new java.util.Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String currenString = format.format(currentDate);
		System.out.println("���ڸ���ͬ��ʱ��" + currenString);
		sql = "update time set sytime = '" + currenString + "'";
		db.execSQL(sql);
	}
}
