/*
 * Author LLL
 * Time:4.14
 * 
 */
package com.dao;

import java.util.ArrayList;

import android.database.Cursor;

import com.activity.Index_Activity;
import com.dao.basic.SQLString;

public class TJ_DAO {
	
	//String tablename = "test1";

	public TJ_DAO() {
		
	}

	/**
	 * 
	 * ��ȡĳһ��������ܶ ��float_list ����ʽ����
	 */
	public ArrayList<Float> getTypeConsume() {

		ArrayList<Float> perc_List;
		Cursor cursor;
		Cursor cursor1;
		float item;
		float total = 0;
		//float percent;
		//BigDecimal b, b1;
		perc_List = new ArrayList<Float>();
//		String sql = "select totalbudget-remain as total_consume from tabletotalbudget";
//		cursor1 = Index_Activity.db.rawQuery(sql, null);
//		while (cursor1.moveToNext()) {
//			total = cursor1.getFloat(cursor1.getColumnIndex("total_consume"));
//		}
//		cursor1.close();
		String sql = SQLString.getConsume_Tj();//����Ԥ����в�ѯ�����ѽ��
		cursor1 = (Cursor)Index_Activity.basicDAO.selectCursor(sql);
		while (cursor1.moveToNext()) {
		total = cursor1.getFloat(cursor1.getColumnIndex("total_consume"));
	}
	cursor1.close();
		if (total == 0) {
			perc_List.add(total);
			perc_List.add(total);
			perc_List.add(total);
			perc_List.add(total);

		} else {
//			sql = "select budget-remain as sum_consume from tablebudget ";
//			cursor = Index_Activity.db.rawQuery(sql, null);
			sql = SQLString.getConsume1_Tj();//�ӷ���Ԥ����в�ѯ�����ѽ��
			cursor = (Cursor)Index_Activity.basicDAO.selectCursor(sql);
			while (cursor.moveToNext()) {
				item = cursor.getFloat(cursor.getColumnIndex("sum_consume"));


				perc_List.add(item);
			}
			cursor.close();
		}
		return perc_List;
	}
	public ArrayList<Float> getconsume(String type) {
		ArrayList<Float> consumeList = new ArrayList<Float>();
		Cursor cursor;
		float item;
//		String sql = "select consume from test1 where kind = '"+type+"' order by date asc";
//		cursor = Index_Activity.db.rawQuery(sql, null);
		String sql = SQLString.getConsume_Tj(type);
		cursor = (Cursor)Index_Activity.basicDAO.selectCursor(sql);
		
		while(cursor.moveToNext()){
			item = cursor.getFloat(cursor.getColumnIndex("consume"));
			consumeList.add(item);
		}
		return consumeList;
	}
}
