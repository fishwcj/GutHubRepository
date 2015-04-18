package com.model;

import java.sql.Date;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.activity.Stream_Activity;
import com.dao.LS_DataBaseHelper;
import com.yyyy.yyyy.R;

public class LSManager {
	private LS_DataBaseHelper ls_DataBaseHelper;

	public LSManager(LS_DataBaseHelper ls_DataBaseHelper) {
		// TODO Auto-generated constructor stub
		this.ls_DataBaseHelper = ls_DataBaseHelper;
	}

	/**
	 * 查询所有流水信息更新流水界面
	 * 
	 * @param linearLayout
	 */
	@SuppressLint("NewApi")
	public void updateStreamLayout(LinearLayout linearLayout) {
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
		Activity streamActivity = Stream_Activity.streamActivity;
		LinearLayout[] linearLayoutChild = new LinearLayout[number];
		TextView[][] textView = new TextView[number][3];
		String consumeString; // 消费
		String kindString; // 消费类别
		String dateString; // 流水时间
		String inOrOutString; // 收入支出
		String[] dates; // 切分时间用
		String style; // 文字html处理
		int i = 0; // i做为TextView数组的索引
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
