package com.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.logic.BackgroundColor;
import com.model.user.IsLogin;
import com.model.user.Regist;
import com.yyyy.yyyy.R;

public class User_Activity extends Activity {
	public static Activity user_Activity;
	private TextView login;
	private TextView get;
	private TextView name;
	private TextView id;
	private Button back;
	private Button budget_set;
	private Button week_set;
	private TextView changeOthers;
	@SuppressLint("DefaultLocale")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		user_Activity = this;
		String sql = "select * from user";
		Cursor cursor = (Cursor)Index_Activity.basicDAO.selectCursor(sql);
		cursor.moveToLast();
//		int tag = 0;
//		if (cursor.moveToLast()) {
//			tag = cursor.getInt(cursor.getColumnIndex("tag"));
//			System.out.println("目前用户状态是 " + cursor.getString(cursor.getColumnIndex("id")) + "   " + cursor.getString(cursor.getColumnIndex("id")) + "  " + tag);
//		}
//		
//		// 如果已经登录则显示个人中心界面
//		boolean isOnline = (tag == 1) ? true : false;
		if (IsLogin.isLogin()) {
			setContentView(R.layout.activity_logined);
			name = (TextView) this.findViewById(R.id.name);
			id = (TextView) this.findViewById(R.id.ID);
			changeOthers = (TextView)this.findViewById(R.id.logined);
			name.setText(cursor.getString(cursor.getColumnIndex("name")));
			String longid = String.format("%06d", Integer.parseInt(cursor
					.getString(cursor.getColumnIndex("id"))));
			id.setText("ID:" + longid);
			changeOthers.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(User_Activity.this,Login_Activity.class);
					startActivity(intent);
				}
			});

		} else {
			setContentView(R.layout.activity_user);
			login = (TextView) this.findViewById(R.id.login);
			get = (TextView) this.findViewById(R.id.get);

			login.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(User_Activity.this,Login_Activity.class);
					startActivity(intent);
				}
			});

			/**
			 * 获取新注册用户
			 */
			get.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					final ProgressDialog pd = ProgressDialog.show(User_Activity.this, "注册", "自动注册中，请稍后……");
					new Thread(new Runnable() {
						@Override
						public void run() {
							// TODO Auto-generated method stub
							Regist register = new Regist();
							register.getNewID();
							pd.dismiss();
						}
					}).start();
				}
			});
		}
		back = (Button) findViewById(R.id.back);
		budget_set = (Button) findViewById(R.id.budget_set);
		week_set = (Button) findViewById(R.id.week_set);
		budget_set.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(User_Activity.this, YS_Activity.class);
				startActivity(intent);
			}
		});
		back.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				User_Activity.this.finish();

				BackgroundColor backgroundColor = new BackgroundColor();
				backgroundColor.refreshback();
			}
		});
		week_set.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(User_Activity.this, SetWeek_Activity.class);
				startActivity(intent);
			}
		});
	}
}
