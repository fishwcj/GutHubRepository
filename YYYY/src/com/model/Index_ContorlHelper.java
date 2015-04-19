package com.model;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import junit.framework.Test;

import com.activity.Count_Activity;
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
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

@SuppressWarnings({ "deprecation", "unused" })
public class Index_ContorlHelper {
	private DataBase dataBase;
	private String currentString;// ��ǰ����
	Context context;
	LinearLayout[][] linearLayoutChild;
	int i = 0;

	/**
	 * ���캯�����õ�LocalActivityManager��DataBase
	 * 
	 * @param manager
	 * @param dataBase
	 */
	@SuppressLint("SimpleDateFormat")
	public Index_ContorlHelper(Context context, DataBase dataBase) {
		this.context = context;
		this.dataBase = dataBase;
		// ���쵱ǰ���� xxxx-xx
		java.util.Date currentDate = new java.util.Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		this.currentString = format.format(currentDate);
	}

	/**
	 * �˺��������������ݵ�ǰ����һ�ν����жϽ���������Ϊ
	 * 
	 * @param current
	 * @param passed
	 */
	public void getDecide(int current, int passed) {
		if (current == 1 || passed == 1) { // ��ˮ
//			getFrameOfThisYear();
			 updateStreamUI(current);
		}
		if (current == 0) { // ����,������else if
			updatejzUI();
		}
		/*
		 * author LLL 
		 * content:ͳ�� 
		 * time:4.18
		 * ��pieView��TJ_DrawHelper��ViewBase������model����
		 */
		if (current == 2) {
			// ��ͼ����;
			draw_TJ();
		}
	}

	/**
	 * ������ˮ����
	 * 
	 * @param current
	 */
	private void updateStreamUI(int current) {
		LinearLayout linearLayout = (LinearLayout) Stream_Activity.streamActivity
				.findViewById(R.id.lin);
		// ��������ˮ���棬����
		if (current == 1) {
			LS_DataBaseHelper ls_DataBaseHelper = new LS_DataBaseHelper(
					context, dataBase);
			LSManager lsManager = new LSManager(ls_DataBaseHelper);
			
			String[] jString = currentString.split("-");
			int number = Integer.parseInt(jString[1]);// �õ���ǰ����
			System.out.println("��ǰ������" + number);

			lsManager.getFrame(number);
		}
		// ������ˮ����,�����ˮ����
		else {
			linearLayout.removeAllViews();
		}
	}

	/**
	 * ������˽���
	 */
	private void updatejzUI() {
		JZ_DataBaseHelper jz_DataBaseHelper = new JZ_DataBaseHelper();
		jz_DataBaseHelper.updateBudgetRemain(dataBase);
	}
	
	/*
	 * ��ͳ��ͼ
	 */
	private void draw_TJ(){
		Activity count_Activity = Count_Activity.countActivity;
		LinearLayout pieViewLayout = (LinearLayout)count_Activity.findViewById(R.id.pieView);
		TJ_DrawHelper drawHelper = new TJ_DrawHelper(count_Activity,
				dataBase);

		PieView pieView = drawHelper.Draw();
		if (pieView.percent[0] == 0) {
			Toast.makeText(count_Activity, "û�����Ѽ�¼", Toast.LENGTH_SHORT)
					.show();
		} else {
			if(pieViewLayout != null){
				pieViewLayout.removeAllViews();
			}
			LayoutParams show = new LayoutParams(100, 100);
			pieViewLayout.addView(pieView, show);
			//count_Activity.setContentView(pieView, show);
		}

	}
}
