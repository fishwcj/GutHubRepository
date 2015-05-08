package com.model.cloud;

import java.net.MalformedURLException;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.activity.JZ_Activity;
import com.bean.CloudData;
import com.dao.SySearch_DAO;
import com.inteface.INetWork;
import com.mnitools.GetNowDate;
import com.mnitools.NetWorkCommunicate;
import com.model.user.IsLogin;

/**
 * 云端同步，发送同步数据
 */
public class CloudSendHelper {

	private CloudData cloudData = new CloudData();
	private SySearch_DAO search;
	private Cursor cursor;
	private String currentStringShort;
	private IsLogin IsLogin;
	private INetWork communicater;

	@SuppressLint("SimpleDateFormat")
	public CloudSendHelper() {
		this.search = new SySearch_DAO();
		this.IsLogin = new IsLogin();
		this.communicater = new NetWorkCommunicate();
		currentStringShort = (new GetNowDate()).getNowDate("yyyy-mm");
	}

	/**
	 * 设置网络链接方式（默认httpConnection链接）
	 * @param netWorkWay 实现了INetWork的链接方式
	 */
	public <T extends INetWork> void setNetWorkWay(T netWorkWay){
		this.communicater = netWorkWay;
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
		if (IsLogin.isLogin()) {
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
				setcloudData();//封装对象
				String url = "http://192.168.191.1:8080/Bill/servlet/ReceiveServlet";
				if (communicater.connect(url))//建立链接成功
					if (communicater.sendObject(cloudData)) {//发送数据成功
						Object responseObject = communicater.receiveObject();//读取返回对象
						System.out.println("返回对象" + responseObject.toString());
						
						Looper.prepare();
						new Handler(Looper.getMainLooper());
						Toast.makeText(JZ_Activity.jzActivity, "同步成功",
								Toast.LENGTH_SHORT).show();
						Looper.loop();
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
