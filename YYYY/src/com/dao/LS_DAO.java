package com.dao;

//import android.app.Activity;
import com.activity.Index_Activity;
import com.dao.basic.SQLString;

import android.content.Context;
import android.database.Cursor;

public class LS_DAO {
	Context context;
	String tableNameString = "test1"; // 流水表名 test1

	public LS_DAO(Context context) {
		this.context = context;

	}

	/**
	 * 查询所有流水账
	 * 
	 * @param manager
	 * @param dataBase
	 * @return
	 */
	public Cursor selectAllAccount(String dateString) {
		
//		String sql = "select consume, kind, date, inorout from stream where date >= '"
//				+ dateString
//				+ "' and date < date('"
//				+ dateString
//				+ "', '+1 month') order by date desc";
//		System.out.println("sql 语句是" + sql);
//		ISelect selecter = new SelectCursor(Index_Activity.db);
//		Cursor cursor = (Cursor)selecter.select(sql);
		String sql = SQLString.getSelectAllAccount_LS(dateString);
		Cursor cursor = (Cursor)Index_Activity.basicDAO.selectCursor(sql);
		return cursor;
	}
}
