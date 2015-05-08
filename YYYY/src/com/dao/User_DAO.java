package com.dao;

import com.activity.Index_Activity;
import com.dao.basic.SQLString;
import com.model.user.IsLogin;

import android.database.Cursor;

public class User_DAO {
	
	public String getUserId(){
		String sql = SQLString.getUserId_Us();
		String userId = Index_Activity.basicDAO.selectString(sql);
		return userId;
	}
	
	public String getUserName(){
		String sql = SQLString.getUserName_Us();
		String userId = Index_Activity.basicDAO.selectString(sql);
		return userId;
	}
	
	public boolean isLogin(){
		return IsLogin.isLogin();
	}
	
	public Cursor getAllMessages(){
		String sql = SQLString.getALLMessages_Us();
		Cursor cursor = (Cursor)Index_Activity.basicDAO.selectCursor(sql);
		return cursor;
	}
}
