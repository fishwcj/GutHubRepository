package com.bean;

import java.io.Serializable;

public class CommentBean implements Serializable  {

	private static final long serialVersionUID = 1L;
	private String imessageid;
	private String iuserid;
	private String iuserName;
	private String commentid;
	private String userid;
	private String userName;
	private String content;
	private String time;
	
	public String getImessageid() {
		return imessageid;
	}
	
	public void setImessageid(String imessageid) {
		this.imessageid = imessageid;
	}
	
	public String getIuserid() {
		return iuserid;
	}
	
	public void setIuserid(String iuserid) {
		this.iuserid = iuserid;
	}
	
	public String getIuserName() {
		return iuserName;
	}
	
	public void setIuserName(String iuserName) {
		this.iuserName = iuserName;
	}
	
	public String getCommentid() {
		return commentid;
	}
	
	public void setCommentid(String commentid) {
		this.commentid = commentid;
	}
	
	public String getUserid() {
		return userid;
	}
	
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getTime() {
		return time;
	}
	
	public void setTime(String time) {
		this.time = time;
	}

}
