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
	private float budgetRemain;	//本月预算余额
	private float budget;		//本月预算
	private float income;	//收入
	private String date;	//同步日期
	private ArrayList<String> budgetByKindList = new ArrayList<String>();	//本月分类预算
	private ArrayList<String> streamCountList = new ArrayList<String>();	//新增流水信息
	
	
	/**
	 * 得到日期
	 * @return
	 */
	public String getDate() {
		return date;
	}

	/**
	 * 设置日期
	 * @param date
	 */
	public void setDate(String date) {
		this.date = date;
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
	 * 得到本月预算剩余
	 * @return
	 */
	public float getBugetRemain(){
		return budgetRemain;
	}
	
	/**
	 * 设置本月预算剩余
	 * @return
	 */
	public void setBudgetRemain(float budgetRemain){
		this.budgetRemain = budgetRemain;
	}
	
	/**
	 * 得到本月总预算
	 * @return
	 */
	public float getBuget(){
		return budget;
	}
	
	/**
	 * 设置本月总预算
	 * @return
	 */
	public void setBudget(float budget){
		this.budget = budget;
	}
	
	/**
	 * 得到本月收入总额
	 * @return
	 */
	public float getincome(){
		return income;
	}
	
	/**
	 * 设置本月收入总额
	 * @return
	 */
	public void setincome(float income){
		this.income = income;
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
		return streamCountList;
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
