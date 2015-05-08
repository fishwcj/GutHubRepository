package com.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.activity.Index_Activity;
import com.dao.basic.SQLString;
import com.mnitools.GetNowDate;

import android.annotation.SuppressLint;
import android.database.Cursor;

public class SySearch_DAO {
	
	private Cursor cursor;
	private String sql;
//	private ISelect cursorSelecter;
//	private ISelect stringSelecter;
	
	public SySearch_DAO() {
		// TODO Auto-generated constructor stub
//		cursorSelecter = new SelectCursor(Index_Activity.db);
//		stringSelecter = new SelectString(Index_Activity.db);
	}
	/**
	 * 得到id
	 * @return
	 */
	public String searchId(){
//		sql = "select id from user";
//		String id = (String)stringSelecter.select(sql);
		sql = SQLString.getSearchId_Sy();
		String id = Index_Activity.basicDAO.selectString(sql);
		return id;
	}
	/**
	 * 查询得到新增流水
	 * @param time	上次同步的时间
	 * @return
	 */
	public Cursor searchStreamCount(){
		String time = getLastsytime();
//		sql = "select * from test1 where date > '" + time + "'";
//		cursor = (Cursor)cursorSelecter.select(sql);
		sql = SQLString.getSearchStreamCount_Sy(time);
		cursor = (Cursor)Index_Activity.basicDAO.selectCursor(sql);
		int i = cursor.getCount();
		System.out.println("新增流水:" + i );
		return cursor;
	}
	
	
	/**
	 * 得到本月预算和预算剩余
	 * @param datetime	哪一月的预算	 格式:xxxx-xx
	 * @return
	 */
	public Cursor searchBudget(String datetime){
//		sql = "select * from tabletotalbudget where month = '" + datetime + "'";
//		cursor = (Cursor)cursorSelecter.select(sql);
		sql = SQLString.getSearchBudget_Sy(datetime);
		cursor = (Cursor)Index_Activity.basicDAO.selectCursor(sql);
		return cursor;
	}
	
	
	/**
	 * 得到本月收入
	 * @param datetime	哪一月的收入	格式:xxxx-xx
	 * @return
	 */
	public Cursor searchincome(String datetime){
//		sql = "select * from consumein where month = '" + datetime + "'";
//		cursor = (Cursor)cursorSelecter.select(sql);
		sql = SQLString.getSearchincome_Sy(datetime);
		cursor = (Cursor)Index_Activity.basicDAO.selectCursor(sql);
		return cursor;
	}
	
	
	/**
	 * 得到本月分类预算
	 * @param datetime	哪一月的分类预算
	 * @return
	 */
	public Cursor searchBudgetByKind(String datetime){
//		sql = "select * from tablebudget where month = '" + datetime + "'";
//		cursor = (Cursor)cursorSelecter.select(sql);
		sql = SQLString.getSearchBudgetByKind_Sy(datetime);
		cursor = (Cursor)Index_Activity.basicDAO.selectCursor(sql);
		return cursor;
	}
	
	@SuppressLint("SimpleDateFormat")
	public boolean isNextMonth(String date) throws ParseException{
		String lastDate = getLastsytime();
//		sql = "select sytime from time";
//		lastDate = (String)stringSelecter.select(sql);
//		sql = SQLString.getSytime_Sy();
//		lastDate = Index_Activity.basicDAO.selectString(sql);
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date1 = format.parse(lastDate);
		format = new SimpleDateFormat("yyyy-MM");
		lastDate = format.format(date1);
		return !(lastDate.equals(date));
	}
	
	public String getLastsytime(){
		sql = SQLString.getSytime_Sy();
		String lastDate = Index_Activity.basicDAO.selectString(sql);
		System.out.println("上一次同步时间" + lastDate);
		return lastDate;
	}
	
	
	/**
	 * 每次同步之后更新时间表
	 */
	@SuppressLint("SimpleDateFormat")
	public void updateTime(){
		String currenString = (new GetNowDate()).getNowDate("yyyy-MM-dd hh:mm:ss");
		System.out.println("现在更新同步时间" + currenString);
		
//		sql = "update time set sytime = '" + currenString + "'";
//		(new UpdateSQL(Index_Activity.db)).updateSQLite(sql);
		sql = SQLString.getUpdateTime_Sy(currenString);
		Index_Activity.basicDAO.update(sql);
	}
}
