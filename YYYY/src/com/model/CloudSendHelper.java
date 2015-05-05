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
		// ��õ�ǰ���� xxxx-xx
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		currentString = format.format(new Date());
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM");
		currentStringShort = format1.format(new Date());
		cloudData.setDate(currentStringShort);
	}

	/**
	 * ����Ƿ��½����½��ͬ�����������ѵ�½
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
	 * ���ӷ�����������cloudData����
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

				// ��װ����
				setcloudData();
				URL url = null;
				HttpURLConnection con = null;
				try {
					url = new URL(
							"http://192.168.191.1:8080/Bill/servlet/ReceiveServlet");
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.println("����urlʧ��");
				}
				try {
					// ����

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
					System.out.println("��������");
					con.connect();
					// ��������
					ObjectOutputStream objectOutputStream = new ObjectOutputStream(
							con.getOutputStream());
					objectOutputStream.writeObject(cloudData);
					objectOutputStream.flush();
					System.out.println("���ͳɹ�");
					System.out.println("������:" + con.getResponseCode());
					InputStream ist = con.getInputStream();

					ObjectInputStream objectInputStream = new ObjectInputStream(
							ist);
					Object obj = objectInputStream.readObject();
					System.out.println(obj);
					objectInputStream.close();
					objectOutputStream.close();
					Looper.prepare();
					new Handler(Looper.getMainLooper());
					Toast.makeText(JZ_Activity.jzActivity, "ͬ���ɹ���",
							Toast.LENGTH_SHORT).show();
					Looper.loop();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("���ӷ�����ʧ��");
					Looper.prepare();
					new Handler(Looper.getMainLooper());
					Toast.makeText(JZ_Activity.jzActivity, "�ף����翪С����~",
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
	 * �����ݶ����װ
	 * 
	 * @param cloudData
	 */
	public void setcloudData() {

		// ��װ�û�id
		cloudData.setUserNameID(search.searchId());

		// ��װ��ˮ
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

		// ��װÿ�·���Ԥ��
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

		// ��װ��Ԥ��
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
			System.out.println("��ȡ��Ԥ��ʱδ�����κ�����");
		}

		// ��װ����
		float income;
		cursor = search.searchincome(currentStringShort);
		if (cursor.moveToNext()) {
			income = Float.parseFloat(cursor.getString(cursor
					.getColumnIndex("mony")));
			cloudData.setincome(income);
		} else {
			System.out.println("δ��ȡ������");
		}

		// ����ͬ��ʱ��
		search.updateTime();
		System.out.println("�������׼����ϣ�");
	}
}
