package com.model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
//import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
//import android.content.IntentSender.SendIntentException;
import android.database.Cursor;
//import android.provider.CalendarContract.Reminders;

import android.os.StrictMode;

import com.dao.DataBase;
import com.dao.SearchCloudData;

public class CloudSendHelper {

	DataBase dataBase;
	CloudData cloudData = new CloudData();
	SearchCloudData search;
	Cursor cursor;
	String currentString;
	String currentStringShort;

	@SuppressLint("SimpleDateFormat")
	public CloudSendHelper(DataBase dataBase) {
		this.dataBase = dataBase;
		this.search = new SearchCloudData(dataBase);

		// 获得当前日期 xxxx-xx
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		currentString = format.format(new Date());
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM");
		currentStringShort = format1.format(new Date());
		cloudData.setDate(currentStringShort);
	}

	/**
	 * 链接服务器并发送cloudData对象
	 * 
	 * @return
	 * @throws MalformedURLException
	 * @throws ClassNotFoundException
	 */
	public boolean send() throws MalformedURLException, ClassNotFoundException {
		boolean tag = false;
		new Thread(){
			public void run(){
		
		// 封装对象
		setcloudData();
		URL url = null;
		try {
			url = new URL("http://192.168.191.1:8080/My/servlet/Receive");
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			// 链接

			StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
					.detectDiskReads().detectDiskWrites().detectNetwork()
					.penaltyLog().build());
			System.out.println("开始连接");
			URLConnection con = url.openConnection();
			System.out.println("打开连接");
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setConnectTimeout(50000);
			System.out.println("尝试连接");
			con.connect();
			System.out.println("链接成功!");
			// 发送数据
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(
					new BufferedOutputStream(con.getOutputStream()));
			objectOutputStream.writeObject(cloudData);
			objectOutputStream.flush();
			objectOutputStream.close();
			System.out.println("发送成功");

			ObjectInputStream objectInputStream = new ObjectInputStream(
					new BufferedInputStream(con.getInputStream()));
			Object obj = objectInputStream.readObject();
			System.out.println(obj);
			objectInputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			};
		}.start();
		tag = true;
		return tag;
	}

	/**
	 * 将数据对象封装
	 * 
	 * @param cloudData
	 */
	public void setcloudData() {

		// 封装流水
		cursor = search.searchStreamCount();
		String stream = "";
		String kind = "";
		String consume = "";
		String inorout = "";
		String date = "";
		while (cursor.moveToNext()) {
			consume = cursor.getString(cursor.getColumnIndex("consume"));
			date = cursor.getString(cursor.getColumnIndex("date"));
			inorout = cursor.getString(cursor.getColumnIndex("inorout"));
			kind = cursor.getString(cursor.getColumnIndex("kind"));
			stream = consume + "&" + kind + "&" + date + "&" + inorout;
			cloudData.addStreamCountList(stream);
		}

		// 封装每月分类预算
		cursor = search.searchBudgetByKind(currentStringShort);
		String budget = "";
		String remain = "";
		String all = "";
		while (cursor.moveToNext()) {
			budget = cursor.getString(cursor.getColumnIndex("budget"));
			kind = cursor.getString(cursor.getColumnIndex("kind"));
			remain = cursor.getString(cursor.getColumnIndex("remain"));
			all = kind + "&" + budget + "&" + remain;
			cloudData.addBudgetByKindList(all);
		}

		// 封装总预算
		float totalbudget;
		float budgetRemain;
		cursor = search.searchBudget(currentStringShort);
		if (cursor.moveToNext()) {
			totalbudget = Float.parseFloat(cursor.getString(cursor
					.getColumnIndex("totalbudget")));
			budgetRemain = Float.parseFloat(cursor.getString(cursor
					.getColumnIndex("remain")));
			cloudData.setBudget(totalbudget);
			cloudData.setBudgetRemain(budgetRemain);
		} else {
			System.out.println("读取总预算时未读到任何数据");
		}

		// 封装收入
		float income;
		cursor = search.searchincome(currentStringShort);
		if (cursor.moveToNext()) {
			income = Float.parseFloat(cursor.getString(cursor
					.getColumnIndex("mony")));
			cloudData.setincome(income);
		} else {
			System.out.println("未读取到收入");
		}
		System.out.println("传输对象准备完毕！");
	}
}
