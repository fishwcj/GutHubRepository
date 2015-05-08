package com.model.user;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.activity.Index_Activity;
import com.dao.basic.SQLString;

import android.annotation.SuppressLint;

public class Init {

	private Date currentDate = new Date();
	private String currentString = "";
	private Date oldDate = new Date();
	private String oldDateString = "";
	private String sql = "";
//	private InsertSQL insertSQL = null;
	@SuppressLint("SimpleDateFormat")
	public Init() throws ParseException{
		
//		insertSQL = new InsertSQL(Index_Activity.db);
//		//获取上次所属月 字符串表示
//		sql = "select lastdate from time";
//		ISelect selecter = new SelectString(Index_Activity.db);
//		oldDateString = (String)selecter.select(sql);
		sql = SQLString.getLastdate_In();
		oldDateString = Index_Activity.basicDAO.selectString(sql);
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
//			sql = "update time set lastdate = " + currentString;
//			UpdateSQL updateSQL = new UpdateSQL(Index_Activity.db);
//			updateSQL.updateSQLite(sql);
			sql = SQLString.getUpdateLastdate_In(currentString);
			Index_Activity.basicDAO.update(sql);
			
			//还可加功能“提醒用户设置本月预算或上月分析已经出炉”
			System.out.println("初始化预算成功！");
		}else{
			System.out.println("没有进入新的一月，不用初始化");
		}
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
//		sql = "insert into tabletotalbudget(totalbudget,remain, month) values (0,0,'"
//				+ currentString + "')";
//		insertSQL.insertSQLite(sql);
		sql = SQLString.getInitNewTotalBudget_In(currentString);
		Index_Activity.basicDAO.insert(sql);
	}
	
	/**
	 * 初始化收入表
	 */
	private void initNewincome(){
//		sql = "insert into consumein(mony, month) values (0, '" + currentString
//				+ "')";
//		insertSQL.insertSQLite(sql);
		sql = SQLString.getInitNewincome_In(currentString);
		Index_Activity.basicDAO.insert(sql);
	}
	
	/**
	 * 初始化每月分类预算表
	 */
	private void initNewKindBudget() {
//		
//		// 添加衣
//		sql = "insert into tablebudget(budget, kind, remain, month) values(0, 1, 0,'"
//				+ currentString + "')";
//		insertSQL.insertSQLite(sql);
//		// 添加食
//		sql = "insert into tablebudget(budget, kind, remain, month) values(0, 2, 0,'"
//				+ currentString + "')";
//		insertSQL.insertSQLite(sql);
//		// 添加住
//		sql = "insert into tablebudget(budget, kind, remain, month) values(0, 3, 0,'"
//				+ currentString + "')";
//		insertSQL.insertSQLite(sql);
//		// 添加行
//		sql = "insert into tablebudget(budget, kind, remain, month) values(0, 4, 0,'"
//				+ currentString + "')";
//		insertSQL.insertSQLite(sql);
//		
		for(int kind = 1; kind < 5; kind++){
			sql = SQLString.getInitNewKindBudget_In(kind, currentString);
			Index_Activity.basicDAO.insert(sql);
		}
	}
	
	/**
	 * 初始化每月消费统计
	 */
	private void initNewCount() {

	}
}
