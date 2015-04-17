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

		// 获取AssetManager对象
//		AssetManager mAssetManager = context.getAssets();
//		InputStream xml = mAssetManager.open("time.xml");
//		readDate(xml);
//		System.out.println("成功解析xml");
		
		//打开数据库
		dataBase = new DataBase(context, "user.db");
		db = dataBase.getWritableDatabase();
		//获取上次所属月 字符串表示
		sql = "select lastdate from time";
		Cursor cursor = db.rawQuery(sql, null);
		if(cursor.moveToNext()){
			oldDateString = cursor.getString(cursor.getColumnIndex("lastdate"));
		}
		
		System.out.println("上次登录在:" + oldDateString);
		// 得到上一次所属月 xxxx-xx 类表示
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		System.out.println("上一月是"+oldDateString);
		this.oldDate = format.parse(this.oldDateString);

		// 得到本次所属月 xxxx-xx 类表示
		currentString = format.format(currentDate);
		System.out.println("这一月是"+currentString);
		currentDate = format.parse(currentString);

		// 比较 如果进入下一月则更新预算表等等
		if (oldDate.before(currentDate)) {
			System.out.println("进入新的一月，初始化预算...");
			initNew();
			sql = "update time set lastdate = " + currentString;
			db.execSQL(sql);
			//还可加功能“提醒用户设置本月预算或上月分析已经出炉”
			System.out.println("初始化预算成功！");
		}else{
			System.out.println("没有进入新的一月，不用初始化");
		}
	}

	public void readDate(InputStream xml) {
//		XmlPullParser pullParser = Xml.newPullParser();
//		try {
//			pullParser.setInput(xml, "UTF-8");
//			int event = pullParser.getEventType();
//
//			// 读取到日期信息
//			while (event != XmlPullParser.END_DOCUMENT) {
//				System.out.println("正在遍历");
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
//			System.out.println("time.xml解析失败！");
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
	 * 初始化每月总预算
	 */
	private void initNewTotalBudget() {
		sql = "insert into tabletotalbudget(totalbudget,remain, month) values (0,0,'"
				+ currentString + "')";
		db.execSQL(sql);
	}
	
	/**
	 * 初始化收入表
	 */
	private void initNewincome(){
		sql = "insert into consumein(mony, month) values (0, '" + currentString
				+ "')";
		db.execSQL(sql);
	}
	
	/**
	 * 初始化每月分类预算表
	 */
	private void initNewKindBudget() {
		// 添加衣
		sql = "insert into tablebudget(budget, kind, remain, month) values(0, 1, 0,'"
				+ currentString + "')";
		db.execSQL(sql);
		// 添加食
		sql = "insert into tablebudget(budget, kind, remain, month) values(0, 2, 0,'"
				+ currentString + "')";
		db.execSQL(sql);
		// 添加住
		sql = "insert into tablebudget(budget, kind, remain, month) values(0, 3, 0,'"
				+ currentString + "')";
		db.execSQL(sql);
		// 添加行
		sql = "insert into tablebudget(budget, kind, remain, month) values(0, 4, 0,'"
				+ currentString + "')";
		db.execSQL(sql);
	}
	
	/**
	 * 初始化每月消费统计
	 */
	private void initNewCount() {

	}
}
