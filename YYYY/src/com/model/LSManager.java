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
	 * ��ѯ������ˮ��Ϣ������ˮ����
	 * 
	 * @param linearLayout
	 */
	@SuppressLint("NewApi")
	public void updateStreamLayout(LinearLayout linearLayout) {
		// ��ѯ��ˮ�����α�
		Cursor cursor = ls_DataBaseHelper.selectAllAccount();
		int number = cursor.getCount();
		// ��̬����xml����
		LinearLayout.LayoutParams LP_FW = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT);
		LP_FW.weight = 1;

		// linearLayoutChild��ʽ
		LinearLayout.LayoutParams LP_FW1 = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		LP_FW1.height = 100;
		LP_FW1.topMargin = 1;
		Activity streamActivity = Stream_Activity.streamActivity;
		LinearLayout[] linearLayoutChild = new LinearLayout[number];
		TextView[][] textView = new TextView[number][3];
		String consumeString; // ����
		String kindString; // �������
		String dateString; // ��ˮʱ��
		String inOrOutString; // ����֧��
		String[] dates; // �з�ʱ����
		String style; // ����html����
		int i = 0; // i��ΪTextView���������
		// �õ�linearLayout����ʽ
		Drawable drawble = streamActivity.getResources().getDrawable(
				R.drawable.stream_textview);
		while (cursor.moveToNext()) {
			for (int j = 0; j < 3; j++)
				textView[i][j] = new TextView(streamActivity);
			linearLayoutChild[i] = new LinearLayout(streamActivity);
			linearLayoutChild[i].setBackground(drawble);
			linearLayoutChild[i].setLayoutParams(LP_FW1);
			// ���α��ý��
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

			// ���õ�һ��textView:��ʾ����
			style = "<span><font color=\"#9ACD32\"><big><big>" + dates[1]
					+ "��/</big></big></font>" + dates[2] + "��</span>";
			textView[i][0].setText(Html.fromHtml(style));
			textView[i][0].setLayoutParams(LP_FW);
			linearLayoutChild[i].addView(textView[i][0]);

			// ���õڶ���textView:��ʾ�������
			style = "<span><font color=\"#EE5C42\"><big>" + kindString
					+ "</big></font></span>";
			textView[i][1].setText(Html.fromHtml(style));
			textView[i][1].setLayoutParams(LP_FW);
			textView[i][1].setGravity(Gravity.CENTER);
			linearLayoutChild[i].addView(textView[i][1]);

			// ���õ�����textView:��ʾ���ѽ��
			style = "<span><font><big>" + consumeString
					+ "</big>Ԫ</font></span>";
			textView[i][2].setText(Html.fromHtml(style));
			textView[i][2].setLayoutParams(LP_FW);
			textView[i][2].setGravity(Gravity.CENTER);
			if (inOrOutString.equals("0"))
				textView[i][2].setTextColor(Color.RED);
			else {
				textView[i][2].setTextColor(0xFF9ACD32);
			}
			linearLayoutChild[i].addView(textView[i][2]);
			// ��LinearLayoutChild���뵽������
			linearLayout.addView(linearLayoutChild[i]);
			i++;
		}
	}
}