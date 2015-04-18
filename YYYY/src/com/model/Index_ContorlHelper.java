package com.model;

import java.sql.Date;
import java.text.SimpleDateFormat;

import com.activity.Index_Activity;
import com.activity.JZ_Activity;
import com.activity.Stream_Activity;
import com.dao.DataBase;
import com.dao.JZ_DataBaseHelper;
import com.dao.LS_DataBaseHelper;
import com.yyyy.yyyy.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.LocalActivityManager;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.GetChars;
import android.text.Html;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

@SuppressWarnings({ "deprecation", "unused" })
public class Index_ContorlHelper {
	private DataBase dataBase;
	private String currentString;// 当前日期
	Context context;

	/**
	 * 构造函数，得到LocalActivityManager和DataBase
	 * 
	 * @param manager
	 * @param dataBase
	 */
	@SuppressLint("SimpleDateFormat")
	public Index_ContorlHelper(Context context, DataBase dataBase) {
		this.context = context;
		this.dataBase = dataBase;
		// 构造当前日期 xxxx-xx
		java.util.Date currentDate = new java.util.Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		this.currentString = format.format(currentDate);
	}

	/**
	 * 此函数做决定，根据当前和上一次界面判断接下来的行为
	 * 
	 * @param current
	 * @param passed
	 */
	public void getDecide(int current, int passed) {
		if (current == 3 || passed == 3) { // 流水
			updateStreamUI(current);
		}
		if (current == 2) { // 记账,不能用else if
			updatejzUI();
		}
	}

	/**
	 * 处理流水界面
	 * 
	 * @param current
	 */
	private void updateStreamUI(int current) {
		LinearLayout linearLayout = (LinearLayout) Stream_Activity.streamActivity
				.findViewById(R.id.lin);
		// 滑动到流水界面，更新
		if (current == 3) {
			LS_DataBaseHelper ls_DataBaseHelper = new LS_DataBaseHelper(
					context, dataBase);
			LSManager lsManager = new LSManager(ls_DataBaseHelper);
			lsManager.updateStreamLayout(linearLayout);
		}
		// 滑出流水界面,清除流水界面
		else {
			linearLayout.removeAllViews();
		}
	}

	/**
	 * 处理记账界面
	 */
	private void updatejzUI() {
		JZ_DataBaseHelper jz_DataBaseHelper = new JZ_DataBaseHelper();
		jz_DataBaseHelper.updateBudgetRemain(dataBase);
	}
}
