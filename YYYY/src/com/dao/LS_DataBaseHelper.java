package com.dao;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class LS_DataBaseHelper {
	Activity manager;
	// 数据库对象
	DataBase dataBase;
	// 流水表名 test1
	String tableNameString = "test1";
	
	public LS_DataBaseHelper(Activity manager, DataBase dataBase){
		this.dataBase = dataBase;
		this.manager = manager;
	}
	
	
	/**
	 * 查询所有流水账
	 * 
	 * @param manager
	 * @param dataBase
	 * @return
	 */
	public Cursor selectAllAccount() {
		SQLiteDatabase db = dataBase.getReadableDatabase();
		Cursor cursor = db.rawQuery(
				"select consume, kind, date, inorout from test1 order by date desc",
				null);
		return cursor;
	}
}
