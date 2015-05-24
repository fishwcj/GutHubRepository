package com.model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.activity.Index_Activity;
import com.activity.User_Activity;
import com.dao.DataBase;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.StrictMode;
import android.widget.Toast;

public class Regist {
	private String idAndPassword;
	private SQLiteDatabase db;

	public Regist(DataBase dataBase) {
		this.db = dataBase.getWritableDatabase();
	}

	public void getNewID() {
		int tag = -1;
		String sql = "select tag from user";
		Cursor cursor = db.rawQuery(sql, null);
		if (cursor.moveToNext()) {
			tag = cursor.getInt(cursor.getColumnIndex("tag"));
			System.out.println("tag = " + tag);
		}
		if (tag == 0) {
			connect();
			updateState(idAndPassword);
			Intent intent = new Intent(User_Activity.user_Activity, Index_Activity.class);
			User_Activity.user_Activity.startActivity(intent);
		} else {
			Toast.makeText(User_Activity.user_Activity, "�Ѿ���ȡ!��Ҫ̰������-_-||",
					Toast.LENGTH_LONG).show();
		}
	}

	/**
	 * ���ӷ����������id
	 */
	public void connect() {
		URL url = null;
		try {
			url = new URL("http://192.168.191.1:8080/Bill/servlet/RegistServlet");
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			// ����

			StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
					.detectDiskReads().detectDiskWrites().detectNetwork()
					.penaltyLog().build());
			System.out.println("��ʼ����");
			URLConnection con = url.openConnection();
			System.out.println("������");
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setConnectTimeout(5000);
			System.out.println("��������");
			con.connect();
			// ��������
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(
					new BufferedOutputStream(con.getOutputStream()));
			objectOutputStream.writeObject("");
			objectOutputStream.flush();
			objectOutputStream.close();
			System.out.println("���ͳɹ�");

			ObjectInputStream objectInputStream = new ObjectInputStream(
					new BufferedInputStream(con.getInputStream()));
			idAndPassword = (String) objectInputStream.readObject();
			System.out.println(idAndPassword);
			objectInputStream.close();
			System.out.println("��ȡ����id��Ϊ:"+ idAndPassword);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * �������ݿ��
	 * 
	 * @param idAndPassword
	 */
	public void updateState(String idAndPassword) {
		String sql = "update user set id = " + idAndPassword;
		db.execSQL(sql);
		sql = "update user set tag = 1";
		db.execSQL(sql);
		System.out.println("id��Ϊ"+ idAndPassword);
	}
}
