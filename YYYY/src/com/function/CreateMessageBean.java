package com.function;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateMessageBean {
	
	public StreetMessageBean create(String content){
		StreetMessageBean messageBean = new StreetMessageBean();
		messageBean.setMessage(content);
		messageBean.setUserName(getUserName());
		messageBean.setAddress(getAddress());
		messageBean.setDatetime(getDate());
		messageBean.setTag(getTag());
		
		return messageBean;
	}
	
	private String getUserId(){
		String userid = "";
		
		return userid;
	}
	
	private String getUserName(){
		String userName = "�����";
		
		return userName;
	}
	
	private String getAddress(){
		String address = "�й�ʯ�ʹ�ѧ��������";
		
		return address;
	}
	
	private String getDate(){
		String currentString = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		currentString = format.format(new Date());
		return currentString;
	}
	
	private String getTag(){
		String tag = "��ʯ";
		return tag;
	}
}
