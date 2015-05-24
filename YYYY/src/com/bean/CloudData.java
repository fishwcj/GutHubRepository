package com.bean;

import java.io.Serializable;
import java.util.ArrayList;

public class CloudData implements Serializable {
	/**
	 * 序列化类
	 */
	private static final long serialVersionUID = 1L;
	private String userName;	//用户名
	private String userID;		//用户id
//	private float budgetRemain;	//本月预算余额
//	private float budget;		//本月预算
//	private float income;	//收入
//	private String date;	//同步日期
	private ArrayList<String> budgetByKindList = new ArrayList<String>();	//本月分类预算
	private ArrayList<String> streamCountList = new ArrayList<String>();	//新增流水信息
	private ArrayList<String> kind = new ArrayList<String>();				//消费类别
	private ArrayList<String> totalbudget = new ArrayList<String>();		//本月总预算
	private String time;//时间表
	private ArrayList<String> consumein = new ArrayList<String>();//收入表
	private ArrayList<String> target = new ArrayList<String>();//攒钱表
	
	
	public ArrayList<String> getKind() {
		return kind;
	}


	public void addKind(String kind) {
		this.kind.add(kind);
	}


	public ArrayList<String> getTotalbudget() {
		return totalbudget;
	}


	public void addTotalbudget(String totalbudget) {
		this.totalbudget.add(totalbudget);
	}


	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}


	public ArrayList<String> getConsumein() {
		return consumein;
	}


	public void addConsumein(String consumein) {
		this.consumein.add(consumein);
	}


	public ArrayList<String> getTarget() {
		return target;
	}


	public void addTarget(String target) {
		this.target.add(target);
	}


	/**
	 * 得到用户名
	 * @return
	 */
	public String getUserName(){
		return userName;
	}
	
	
	/**
	 * 设置用户名
	 * @return
	 */
	public void setUserName(String username){
		this.userName = username;
	}
	
	
	/**
	 * 得到用户id
	 * @return
	 */
	public String getUserID(){
		return userID;
	}
	
	
	/**
	 * 设置用户id
	 * @return
	 */
	public void setUserNameID(String userid){
		this.userID = userid;
	}
	
	/**
	 * 添加一条类型预算信息 
	 * @param item 组合格式:  	类别&预算&预算余额
	 */
	public void addBudgetByKindList(String item){
		budgetByKindList.add(item);
	}
	
	/**
	 * 得到本月所有分类预算信息
	 */
	public ArrayList<String> getBudgetByKindList(){
		return budgetByKindList;
	}
	
	/**
	 * 添加一条流水信息
	 * @param item 组合格式： 	消费金额&消费类别&消费时间&收入或支出
	 */
	public void addStreamCountList(String item) {
		streamCountList.add(item);
	}
	
	/**
	 * 得到流水list
	 * @return
	 */
	public ArrayList<String> getStreamCountList(){
		return streamCountList;
	}
}
