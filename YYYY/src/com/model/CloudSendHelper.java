package com.model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
//import android.content.IntentSender.SendIntentException;
import android.database.Cursor;
//import android.provider.CalendarContract.Reminders;

import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.widget.Toast;

import com.activity.JZ_Activity;
import com.bean.CloudData;
import com.dao.DataBase;
import com.dao.SySearch_DAO;

public class CloudSendHelper {

	private DataBase dataBase;
	private CloudData cloudData = new CloudData();
	private SySearch_DAO search;
	private Cursor cursor;
	@SuppressWarnings("unused")
	private String currentString;
	private String currentStringShort;
	private IsLogin IsLogin;

	@SuppressLint("SimpleDateFormat")
	public CloudSendHelper(DataBase dataBase) {
		this.dataBase = dataBase;
		this.search = new SySearch_DAO();
		this.IsLogin = new IsLogin();
		// 获得当前日期 xxxx-xx
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		currentString = format.format(new Date());
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM");
		currentStringShort = format1.format(new Date());
		cloudData.setDate(currentStringShort);
	}

	/**
	 * 检查是否登陆，登陆后同步，否则提醒登陆
	 * 
	 * @return
	 * @throws MalformedURLException
	 * @throws ClassNotFoundException
	 */
	public boolean checkAndSend() throws MalformedURLException,
			ClassNotFoundException {
		boolean ok = false;
		if (IsLogin.isLogin(dataBase)) {
			ok = send();
		}
		return ok;
	}

	/**
	 * 链接服务器并发送cloudData对象
	 * 
	 * @return
	 * @throws MalformedURLException
	 * @throws ClassNotFoundException
	 */
	@SuppressLint("ShowToast")
	public boolean send() throws MalformedURLException, ClassNotFoundException {
		boolean tag = false;
		new Thread() {
			public void run() {

				// 封装对象
				setcloudData();
				URL url = null;
				HttpURLConnection con = null;
				try {
					url = new URL(
							"http://192.168.191.1:8080/Bill/servlet/ReceiveServlet");
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.println("创建url失败");
				}
				try {
					// 链接

					StrictMode
							.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
									.detectDiskReads().detectDiskWrites()
									.detectNetwork().penaltyLog().build());
					con = (HttpURLConnection) url.openConnection();
					con.setDoInput(true);
					con.setDoOutput(true);
					con.setUseCaches(false);
					con.setRequestProperty("Content-Type",
							"application/x-java-serialized-object");
					con.setRequestMethod("POST");
					con.setConnectTimeout(3000);
					System.out.println("尝试连接");
					con.connect();
					// 发送数据
					ObjectOutputStream objectOutputStream = new ObjectOutputStream(
							con.getOutputStream());
					objectOutputStream.writeObject(cloudData);
					objectOutputStream.flush();
					System.out.println("发送成功");
					System.out.println("返回码:" + con.getResponseCode());
					InputStream ist = con.getInputStream();

					ObjectInputStream objectInputStream = new ObjectInputStream(
							ist);
					Object obj = objectInputStream.readObject();
					System.out.println(obj);
					objectInputStream.close();
					objectOutputStream.close();
					Looper.prepare();
					new Handler(Looper.getMainLooper());
					Toast.makeText(JZ_Activity.jzActivity, "同步成功！",
							Toast.LENGTH_SHORT).show();
					Looper.loop();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("链接服务器失败");
					Looper.prepare();
					new Handler(Looper.getMainLooper());
					Toast.makeText(JZ_Activity.jzActivity, "咦？网络开小差了~",
							Toast.LENGTH_SHORT).show();
					Looper.loop();
					con.disconnect();
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

		// 封装用户id
		cloudData.setUserNameID(search.searchId());

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

		// 更新同步时间
		search.updateTime();
		System.out.println("传输对象准备完毕！");
	}
}
