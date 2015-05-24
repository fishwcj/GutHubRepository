package com.function;

import android.annotation.SuppressLint;
import android.database.Cursor;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.activity.Index_Activity;
import com.bean.StreetMessageBean;

public class CreateMessageBean {

	public StreetMessageBean create(String content,String tag, float price) {
		StreetMessageBean messageBean = new StreetMessageBean();
		messageBean.setMessage(content);
		messageBean.setUserName(getUserName());
		messageBean.setAddress(getAddress());
		messageBean.setDatetime(getDate());
		messageBean.setUserID(getUserId());
		messageBean.setTag(tag);
		messageBean.setPrice(price);
		
		return messageBean;
	}

	private String getUserId() {
		String userid = "";
		String sql = "select id from user";
		Cursor cursor = Index_Activity.db.rawQuery(sql, null);
		if (cursor.moveToNext()) {
			userid = cursor.getString(1);
		}
		return userid;
	}

	private String getUserName() {
		String sql = "select name from user";
		Cursor cursor = Index_Activity.db.rawQuery(sql, null);
		String name = "默认昵称";
		if (cursor.moveToNext()) {
			name = cursor.getString(1);
		}
		return name;
	}

	private String getAddress() {
		String address = "中国石油大学（华东）";

		return address;
	}

	@SuppressLint("SimpleDateFormat")
	private String getDate() {
		String currentString = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		currentString = format.format(new Date());
		return currentString;
	}
}
