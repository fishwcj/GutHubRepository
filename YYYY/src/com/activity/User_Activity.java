package com.activity;

import com.dao.DataBase;
import com.model.Regist;
import com.yyyy.yyyy.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class User_Activity extends Activity {
	public static Activity user_Activity;
	private TextView login;
	private TextView get;
	private TextView name;
	private TextView id;
	DataBase dataBase;

	@SuppressLint("DefaultLocale")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		user_Activity = this;
		this.dataBase = new DataBase(User_Activity.this, "user.db");
		SQLiteDatabase db = dataBase.getWritableDatabase();
		String sql = "select * from user";
		Cursor cursor = db.rawQuery(sql, null);
		int tag = 0;
		if (cursor.moveToNext()) {
			tag = cursor.getInt(cursor.getColumnIndex("tag"));
		}
		// 如果已经登录则显示个人中心界面
		boolean isOnline = (tag == 1) ? true : false;
		if (isOnline) {
			setContentView(R.layout.activity_logined);
			name = (TextView) this.findViewById(R.id.name);
			id = (TextView) this.findViewById(R.id.ID);
			name.setText(cursor.getString(cursor.getColumnIndex("name")));
			String longid = String.format("%06d", Integer.parseInt(cursor
					.getString(cursor.getColumnIndex("id"))));
			id.setText("ID:" + longid);

		} else {
			setContentView(R.layout.activity_user);
			login = (TextView) this.findViewById(R.id.login);
			get = (TextView) this.findViewById(R.id.get);

			login.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub

				}
			});

			/**
			 * 获取新注册用户
			 */
			get.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					final DataBase dataBase = new DataBase(User_Activity.this,
							"user.db");
					final ProgressDialog pd = ProgressDialog.show(User_Activity.this, "注册", "自动注册中，请稍后……");
					new Thread(new Runnable() {
						@Override
						public void run() {
							// TODO Auto-generated method stub
							Regist register = new Regist(dataBase);
							register.getNewID();
							pd.dismiss();
						}
					}).start();
				}
			});
		}

	}
}
