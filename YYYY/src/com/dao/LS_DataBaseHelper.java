package com.dao;

//import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class LS_DataBaseHelper {
	Context context;
	DataBase dataBase;
	String tableNameString = "test1";	// ��ˮ���� test1
	
	public LS_DataBaseHelper(Context context, DataBase dataBase){
		this.dataBase = dataBase;
//		this.manager = manager;
		this.context  = context;
		
	}
	
	
	/**
	 * ��ѯ������ˮ��
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
