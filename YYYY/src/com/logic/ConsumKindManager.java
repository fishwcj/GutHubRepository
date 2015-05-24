package com.logic;

import android.annotation.SuppressLint;

import android.database.Cursor;

import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.activity.Index_Activity;
import com.activity.JZ_Activity;
import com.activity.SelectPicPopupWindow;
import com.dao.basic.SQLString;
import com.yyyy.yyyy.R;

public class ConsumKindManager {
//	private DataBase dataBase;
//	private SQLiteDatabase db;
	private TextView kind;// 类型
	Drawable drawable = SelectPicPopupWindow.selectPicPopupWindow
			.getResources().getDrawable(R.drawable.blackbutton);

	public ConsumKindManager() {
//		this.dataBase = Index_Activity.dataBase;
//		db = dataBase.getWritableDatabase();
		kind = (TextView) JZ_Activity.jzActivity
				.findViewById(R.id.kind);
	}

	/**
	 * 更新右边二级菜单
	 * 
	 * @param mainkind
	 *            左边以及菜单编号
	 * @param layout
	 *            右边布局
	 */
	@SuppressLint("NewApi")
	public void freshButton(int mainkind, LinearLayout layout) {
		layout.removeAllViews();
		String sql = SQLString.getFreshButton_Co(mainkind);
		Cursor cursor = (Cursor)Index_Activity.basicDAO.selectCursor(sql);
		int number = cursor.getCount();
		LinearLayout.LayoutParams LP_FW = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT);
		LP_FW.height = 80;
		TextView[] textView = new TextView[number];
		cursor.moveToNext();
		for (int i = 0; i < number; i++) {
			String kindname = cursor.getString(cursor
					.getColumnIndex("kindname"));
			textView[i] = new TextView(
					SelectPicPopupWindow.selectPicPopupWindow);
			textView[i].setGravity(Gravity.CENTER);
			textView[i].setText(kindname);
			textView[i].setBackground(drawable);
			textView[i].setLayoutParams(LP_FW);
			textView[i].setId(number + 1);
			textView[i].setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					TextView view = (TextView) v;
					String kindname = kind.getText().toString()
							+ view.getText().toString();
					kind.setText(kindname);
					SelectPicPopupWindow.selectPicPopupWindow.finish();
				}
			});
			cursor.moveToNext();
			layout.addView(textView[i]);
		}
	}
}
