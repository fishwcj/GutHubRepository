package com.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.activity.JZ_Activity;

import android.annotation.SuppressLint;
import android.content.Context;
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
	 * 查询得到新增流水
	 * @param time	上次同步的时间
	 * @return
	 */
	public Cursor searchStreamCount(){
		String time = getLastsytime();
		sql = "select * from test1 where date > '" + time + "'";
		cursor = db.rawQuery(sql, null);
		return cursor;
	}
	
	
	/**
	 * 得到本月预算和预算剩余
	 * @param datetime	哪一月的预算	 格式:xxxx-xx
	 * @return
	 */
	public Cursor searchBudget(String datetime){
		sql = "select * from tabletotalbudget where month = '" + datetime + "'";
		cursor = db.rawQuery(sql, null);
		return cursor;
	}
	
	
	/**
	 * 得到本月收入
	 * @param datetime	哪一月的收入	格式:xxxx-xx
	 * @return
	 */
	public Cursor searchincome(String datetime){
		sql = "select * from consumein where month = '" + datetime + "'";
		cursor = db.rawQuery(sql, null);
		return cursor;
	}
	
	
	/**
	 * 得到本月分类预算
	 * @param datetime	哪一月的分类预算
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
		return last;
	}
	
	
	/**
	 * 每次同步之后更新时间表
	 */
	public void updateTime(){
		
	}
}
