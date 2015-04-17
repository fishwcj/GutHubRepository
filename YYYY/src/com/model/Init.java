package com.model;

import java.util.Date;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;

//import org.xmlpull.v1.XmlPullParser;
//import org.xmlpull.v1.XmlPullParserException;
//import org.xmlpull.v1.XmlSerializer;

import com.dao.DataBase;

import android.annotation.SuppressLint;
import android.content.Context;
//import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
//import android.util.Xml;

public class Init {

	private Date currentDate = new Date();
	private String currentString;
	private Date oldDate = new Date();
	private String oldDateString;
	private DataBase dataBase;
	private String sql;
	SQLiteDatabase db;
	@SuppressLint("SimpleDateFormat")
	public Init(Context context) throws ParseException{

		// ��ȡAssetManager����
//		AssetManager mAssetManager = context.getAssets();
//		InputStream xml = mAssetManager.open("time.xml");
//		readDate(xml);
//		System.out.println("�ɹ�����xml");
		
		//�����ݿ�
		dataBase = new DataBase(context, "user.db");
		db = dataBase.getWritableDatabase();
		//��ȡ�ϴ������� �ַ�����ʾ
		sql = "select lastdate from time";
		Cursor cursor = db.rawQuery(sql, null);
		if(cursor.moveToNext()){
			oldDateString = cursor.getString(cursor.getColumnIndex("lastdate"));
		}
		
		System.out.println("�ϴε�¼��:" + oldDateString);
		// �õ���һ�������� xxxx-xx ���ʾ
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		System.out.println("��һ����"+oldDateString);
		this.oldDate = format.parse(this.oldDateString);

		// �õ����������� xxxx-xx ���ʾ
		currentString = format.format(currentDate);
		System.out.println("��һ����"+currentString);
		currentDate = format.parse(currentString);

		// �Ƚ� ���������һ�������Ԥ���ȵ�
		if (oldDate.before(currentDate)) {
			System.out.println("�����µ�һ�£���ʼ��Ԥ��...");
			initNew();
			sql = "update time set lastdate = " + currentString;
			db.execSQL(sql);
			//���ɼӹ��ܡ������û����ñ���Ԥ������·����Ѿ���¯��
			System.out.println("��ʼ��Ԥ��ɹ���");
		}else{
			System.out.println("û�н����µ�һ�£����ó�ʼ��");
		}
	}

	public void readDate(InputStream xml) {
//		XmlPullParser pullParser = Xml.newPullParser();
//		try {
//			pullParser.setInput(xml, "UTF-8");
//			int event = pullParser.getEventType();
//
//			// ��ȡ��������Ϣ
//			while (event != XmlPullParser.END_DOCUMENT) {
//				System.out.println("���ڱ���");
//				if (event == XmlPullParser.START_DOCUMENT) {
//					if ("lastdate".equals(pullParser.getName())) {
//						this.oldDateString = pullParser.getAttributeName(0);
//						break;
//					}
//				}
//			}
//		} catch (XmlPullParserException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			System.out.println("time.xml����ʧ�ܣ�");
//		}

	}
	
	public void writeDate(OutputStream out) throws IllegalArgumentException, IllegalStateException, IOException{
//		XmlSerializer serializer = Xml.newSerializer();
//        serializer.setOutput(out, "UTF-8");
//        serializer.startDocument("UTF-8", true);
//        serializer.startTag(null, "time");
//        serializer.startTag(null, "sytime");            
//        serializer.text(currentString);
//        serializer.endTag(null, "sytime");
//        serializer.endTag(null, "time");
//        serializer.endDocument();
//        out.flush();
//        out.close();
	}
	public void initNew() {
		initNewTotalBudget();
		initNewKindBudget();
		initNewincome();
		initNewCount();
	}
	
	/**
	 * ��ʼ��ÿ����Ԥ��
	 */
	private void initNewTotalBudget() {
		sql = "insert into tabletotalbudget(totalbudget,remain, month) values (0,0,'"
				+ currentString + "')";
		db.execSQL(sql);
	}
	
	/**
	 * ��ʼ�������
	 */
	private void initNewincome(){
		sql = "insert into consumein(mony, month) values (0, '" + currentString
				+ "')";
		db.execSQL(sql);
	}
	
	/**
	 * ��ʼ��ÿ�·���Ԥ���
	 */
	private void initNewKindBudget() {
		// �����
		sql = "insert into tablebudget(budget, kind, remain, month) values(0, 1, 0,'"
				+ currentString + "')";
		db.execSQL(sql);
		// ���ʳ
		sql = "insert into tablebudget(budget, kind, remain, month) values(0, 2, 0,'"
				+ currentString + "')";
		db.execSQL(sql);
		// ���ס
		sql = "insert into tablebudget(budget, kind, remain, month) values(0, 3, 0,'"
				+ currentString + "')";
		db.execSQL(sql);
		// �����
		sql = "insert into tablebudget(budget, kind, remain, month) values(0, 4, 0,'"
				+ currentString + "')";
		db.execSQL(sql);
	}
	
	/**
	 * ��ʼ��ÿ������ͳ��
	 */
	private void initNewCount() {

	}
}
