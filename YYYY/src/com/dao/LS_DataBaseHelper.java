package com.dao;

//import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class LS_DataBaseHelper {
	Context context;
	DataBase dataBase;
	String tableNameString = "test1"; // ��ˮ���� test1

	public LS_DataBaseHelper(Context context, DataBase dataBase) {
		this.dataBase = dataBase;
		// this.manager = manager;
		this.context = context;

	}

	/**
	 * ��ѯ������ˮ��
	 * 
	 * @param manager
	 * @param dataBase
	 * @return
	 */
	public Cursor selectAllAccount(String dateString) {
		SQLiteDatabase db = dataBase.getReadableDatabase();
		String sql = "select consume, kind, date, inorout from test1 where date >= '"
				+ dateString
				+ "' and date < date('"
				+ dateString
				+ "', '+1 month') order by date desc";
		System.out.println("sql �����" + sql);
		Cursor cursor = db.rawQuery(sql, null);
		return cursor;
	}
}
