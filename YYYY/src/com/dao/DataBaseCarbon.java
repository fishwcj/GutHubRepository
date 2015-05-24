package com.dao;

//import java.text.SimpleDateFormat;
//import java.util.Date;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class DataBaseCarbon extends SQLiteOpenHelper{

		/**
		 * 数据库版本
		 */
		private static final int VERSION = 1;

		public DataBaseCarbon(Context context, String name, CursorFactory factory,
				int version) {
			super(context, name, factory, version);
			// TODO Auto-generated constructor stub
		}

		// 该构造函数只有3个参数，在上面函数 的游标固定为null
		public DataBaseCarbon(Context context, String name, int verson) {
			this(context, name, null, verson);
		}

		// 该构造函数只有2个参数，在上面函数 的基础山将版本号固定了
		public DataBaseCarbon(Context context, String name) {
			this(context, name, VERSION);
		}

		@SuppressLint("SimpleDateFormat")
		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			System.out.println("create a sqlite database");

			// 获得当前日期 xxxx-xx
//			String currentString;
//			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
//			currentString = format.format(new Date());

			// 测试表2流水表：包括 消费、类型、时间
			db.execSQL("create table stream(consume float, kind varchar(20), id int, date datetime, inorout int)");
			// 测试表3预算表
			String sql = "create table tablebudget(budget float, kind int, remain float, month date)";
			db.execSQL(sql);

			// 测试表4总预算表:应该包含时间（年、月），总预算金额。。测试就略去时间
			sql = "create table tabletotalbudget(totalbudget float,remain float, month date)";
			db.execSQL(sql);
			
			// 创建表5收入表
			sql = "create table consumein(mony float, month date)";
			db.execSQL(sql);
			// 创建时间日期表
			sql = "create table time(lastdate date, sytime datetime)";
			db.execSQL(sql);

			// 测试表5消费类型
			sql = "create table kind(firstid int, secondid int, kindname varchar(20))";
			db.execSQL(sql);

			// 测试表6 用户表 tag为0代表未登录（未注册状态）
			sql = "create table user(id varchar(10), name varchar(20), tag int)";
			db.execSQL(sql);
			//测试表7  攒钱表
			sql = "create table target(name varchar(30),time int,lefttime int,content varchar(40),tips varchar(30), advise varchar(30))";
			db.execSQL(sql);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			System.out.println("update a sqlite database");
		}
}
