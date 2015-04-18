package com.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper {

	/**
	 * ���ݿ�汾
	 */
	private static final int VERSION = 1;

	public DataBase(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	// �ù��캯��ֻ��3�������������溯�� ���α�̶�Ϊnull
	public DataBase(Context context, String name, int verson) {
		this(context, name, null, verson);
	}

	// �ù��캯��ֻ��2�������������溯�� �Ļ���ɽ���汾�Ź̶���
	public DataBase(Context context, String name) {
		this(context, name, VERSION);
	}

	@SuppressLint("SimpleDateFormat")
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		System.out.println("create a sqlite database");

		// ��õ�ǰ���� xxxx-xx
		String currentString;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		currentString = format.format(new Date());

		// ���Ա�2��ˮ�������� ���ѡ����͡�ʱ��
		db.execSQL("create table test1(consume float, kind varchar(20), date datetime, inorout int)");
		// ���Ա�3Ԥ���
		String sql = "create table tablebudget(budget float, kind int, remain float, month date)";
		db.execSQL(sql);
		/**
		 * ������Ԥ����ж���Ĭ�ϵı���--��ʳס��
		 * 
		 * ���������������ͬʱ����ִ�г�ʼ��sql���
		 * 
		 * �Ժ�ÿ�����Ѽȸ������ݼ���
		 */
		// ������
		sql = "insert into tablebudget(budget, kind, remain, month) values(0, 1, 0,'"
				+ currentString + "')";
		db.execSQL(sql);
		// ����ʳ
		sql = "insert into tablebudget(budget, kind, remain, month) values(0, 2, 0,'"
				+ currentString + "')";
		db.execSQL(sql);
		// ����ס
		sql = "insert into tablebudget(budget, kind, remain, month) values(0, 3, 0,'"
				+ currentString + "')";
		db.execSQL(sql);
		// ������
		sql = "insert into tablebudget(budget, kind, remain, month) values(0, 4, 0,'"
				+ currentString + "')";
		db.execSQL(sql);

		// ���Ա�4��Ԥ���:Ӧ�ð���ʱ�䣨�ꡢ�£�����Ԥ��������Ծ���ȥʱ��
		sql = "create table tabletotalbudget(totalbudget float,remain float, month date)";
		db.execSQL(sql);
		// ��ʼ����Ԥ��Ϊ0
		sql = "insert into tabletotalbudget(totalbudget,remain, month) values (0,0,'"
				+ currentString + "')";
		db.execSQL(sql);
		// ������5�����
		sql = "create table consumein(mony float, month date)";
		db.execSQL(sql);
		sql = "insert into consumein(mony, month) values (0, '" + currentString
				+ "')";
		db.execSQL(sql);
		// ����ʱ�����ڱ�
		sql = "create table time(lastdate date, sytime datetime)";
		db.execSQL(sql);
		sql = "insert into time(lastdate,sytime) values ('" + currentString
				+ "','2012-01-01 00:00:00')";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		System.out.println("update a sqlite database");
	}

}