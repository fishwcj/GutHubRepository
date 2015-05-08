package com.model.street;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.bean.StreetMessageBean;
import com.dao.User_DAO;

public class CreateMessageBean {

	private User_DAO user_DAO;
	
	public CreateMessageBean() {
		this.user_DAO = new User_DAO();
	}
	
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
		String userid = user_DAO.getUserId();
		return userid;
	}

	private String getUserName() {
		String name = user_DAO.getUserName();
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
