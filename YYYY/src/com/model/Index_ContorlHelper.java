package com.model;

import java.sql.Date;
import java.text.SimpleDateFormat;

import com.activity.Index_Activity;
import com.dao.DataBase;
import com.dao.LS_DataBaseHelper;
import com.yyyy.yyyy.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.LocalActivityManager;
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
	private LocalActivityManager manager;
	private DataBase dataBase;

	// 得到index_activity
	private Activity indexActivity;
	private String currentString;

	/**
	 * 构造函数，得到LocalActivityManager和DataBase
	 * 
	 * @param manager
	 * @param dataBase
	 */
	@SuppressLint("SimpleDateFormat")
	public Index_ContorlHelper(LocalActivityManager manager, DataBase dataBase) {
		this.manager = manager;
		this.dataBase = dataBase;
		indexActivity = manager.getActivity("Index_Activity");
		// 获得当前日期 xxxx-xx
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

		if (current == 3 || passed == 3) {
			LinearLayout linearLayout = (LinearLayout) manager.getActivity(
					"Stream_Activity").findViewById(R.id.lin);
			// 刚滑动到流水界面，更新
			if (current == 3) {
				updateStreamLayout(linearLayout);
			} else { // 刚滑出流水界面
				linearLayout.removeAllViews();
			}
		}
		if (current == 2) {
			updateBudgetRemain();
		}
	}

	/**
	 * 更新记账页面的预算余额显示
	 */
	public void updateBudgetRemain() {

		Activity jz_Activity = manager.getActivity("JZ_Activity");
		TextView remainTextView = (TextView) jz_Activity
				.findViewById(R.id.budgetRemain);
		SQLiteDatabase db = dataBase.getReadableDatabase();
		Cursor cursor = db.rawQuery(
				"select remain from tabletotalbudget where month = '"
						+ currentString + "'", null);
		if (cursor.moveToNext()) {
			String remainString = cursor.getString(cursor
					.getColumnIndex("remain"));
			remainTextView.setText(remainString);
		}
	}

	/**
	 * 查询所有流水信息更新流水界面
	 * 
	 * @param linearLayout
	 */
	@SuppressLint("NewApi")
	private void updateStreamLayout(LinearLayout linearLayout) {
		// 得到lsDAO对象
		LS_DataBaseHelper ls_DataBaseHelper = new LS_DataBaseHelper(
				indexActivity, dataBase);

		// 查询流水返回游标
		Cursor cursor = ls_DataBaseHelper.selectAllAccount();
		int number = cursor.getCount();
		// 动态生成xml布局
		LinearLayout.LayoutParams LP_FW = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT);
		LP_FW.weight = 1;

		// linearLayoutChild样式
		LinearLayout.LayoutParams LP_FW1 = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		LP_FW1.height = 100;
		LP_FW1.topMargin = 1;
		Activity streamActivity = manager.getActivity("Stream_Activity");
		LinearLayout[] linearLayoutChild = new LinearLayout[number];
		TextView[][] textView = new TextView[number][3];
		// 消费
		String consumeString;
		// 消费类别
		String kindString;
		// 流水时间
		String dateString;
		// 收入支出
		String inOrOutString;
		// 切分时间用
		String[] dates;
		// 文字html处理
		String style;
		// i做为TextView数组的索引
		int i = 0;
		// 得到linearLayout的样式
		Drawable drawble = streamActivity.getResources().getDrawable(
				R.drawable.stream_textview);
		while (cursor.moveToNext()) {
			for (int j = 0; j < 3; j++)
				textView[i][j] = new TextView(streamActivity);
			linearLayoutChild[i] = new LinearLayout(streamActivity);
			linearLayoutChild[i].setBackground(drawble);
			linearLayoutChild[i].setLayoutParams(LP_FW1);
			// 从游标获得结果
			inOrOutString = cursor.getString(cursor.getColumnIndex("inorout"));
			if (inOrOutString.equals("0"))
				consumeString = "-"
						+ cursor.getString(cursor.getColumnIndex("consume"));
			else {
				consumeString = "+"
						+ cursor.getString(cursor.getColumnIndex("consume"));
			}
			kindString = cursor.getString(cursor.getColumnIndex("kind"));
			dateString = cursor.getString(cursor.getColumnIndex("date"));

			dates = dateString.split(" ");
			dateString = Date.valueOf(dates[0]).toString();
			dates = dateString.split("-");

			// 设置第一个textView:显示日期
			style = "<span><font color=\"#9ACD32\"><big><big>" + dates[1]
					+ "月/</big></big></font>" + dates[2] + "日</span>";
			textView[i][0].setText(Html.fromHtml(style));
			textView[i][0].setLayoutParams(LP_FW);
			linearLayoutChild[i].addView(textView[i][0]);

			// 设置第二个textView:显示消费类别
			style = "<span><font color=\"#EE5C42\"><big>" + kindString
					+ "</big></font></span>";
			textView[i][1].setText(Html.fromHtml(style));
			textView[i][1].setLayoutParams(LP_FW);
			textView[i][1].setGravity(Gravity.CENTER);
			linearLayoutChild[i].addView(textView[i][1]);

			// 设置第三个textView:显示消费金额
			style = "<span><font><big>" + consumeString
					+ "</big>元</font></span>";
			textView[i][2].setText(Html.fromHtml(style));
			textView[i][2].setLayoutParams(LP_FW);
			textView[i][2].setGravity(Gravity.CENTER);
			if (inOrOutString.equals("0"))
				textView[i][2].setTextColor(Color.RED);
			else {
				textView[i][2].setTextColor(0xFF9ACD32);
			}
			linearLayoutChild[i].addView(textView[i][2]);

			// 将LinearLayoutChild加入到父容器
			linearLayout.addView(linearLayoutChild[i]);
			i++;
		}
	}
}
