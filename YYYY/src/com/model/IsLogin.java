package com.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dao.DataBase;

public class IsLogin {
	public boolean isLogin(DataBase dataBase) {
		boolean tag = false;
		SQLiteDatabase db = dataBase.getWritableDatabase();
		String sql = "select tag from user";
		Cursor cursor = db.rawQuery(sql, null);
		if (cursor.moveToNext()) {
			int islogin = cursor.getInt(0);
			tag = (islogin == 1) ? true : false;
		}
		return tag;
	}
}
