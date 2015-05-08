package com.activity;

import android.database.sqlite.SQLiteDatabase;

/**
 * 可能更换数据库，故设此类，以后更换数据库改此类即可
 * @author wcj
 *
 */
public class UpdateSQL {
	private SQLiteDatabase db;
	public UpdateSQL(SQLiteDatabase db) {
		// TODO Auto-generated constructor stub
		this.db = db;
	}
	
	/**
	 * SQLite数据库更新
	 * @param sql
	 * @return
	 */
	public boolean updateSQLite(String sql){
		boolean tag = false;
		db.execSQL(sql);
		tag = true;
		return tag;
	}
}
