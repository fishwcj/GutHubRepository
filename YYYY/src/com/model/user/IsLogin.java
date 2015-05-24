package com.model.user;

import android.database.Cursor;

import com.activity.Index_Activity;
import com.dao.basic.SQLString;

public class IsLogin {
	public static boolean isLogin() {
		boolean tag = false;
		String sql = SQLString.getIsLogin_Is();
		int islogin = Index_Activity.basicDAO.selectInt(sql);
		tag = (islogin == 1) ? true : false;
		return tag;
	}
	
	public static String getId(){
		String sql = SQLString.getSearchId_Sy();
		String id = Index_Activity.basicDAO.selectString(sql);
		return id;
	}
	
	public static boolean hasTarget(){
		boolean tag = false;
		String sql = SQLString.getHasTarget_Is();
		Cursor cursor = (Cursor)Index_Activity.basicDAO.selectCursor(sql);
		tag = (cursor.getCount() != 0) ? true : false;
		return tag;
	}
	
	public static boolean initTarget(String name,int time,String content){
		boolean tag = false;
		String sql = SQLString.getInitTarget_Is(name, time, content);
		Index_Activity.basicDAO.insert(sql);
		tag = true;
		return tag;
	}
	
	public static Cursor getTarget(){
		String sql = SQLString.getTarget_Is();
		Cursor cursor = (Cursor)Index_Activity.basicDAO.selectCursor(sql);
		return cursor;
	}
	
	public static boolean updateTarget(String name,String content, int time){
		boolean tag = false;
		String sql = SQLString.getUpdateTarget_Is(name, content, time);
		Index_Activity.basicDAO.update(sql);
		tag = true;
		return tag;
	}
}
