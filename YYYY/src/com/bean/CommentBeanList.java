package com.bean;

import java.io.Serializable;
import java.util.ArrayList;

public class CommentBeanList implements Serializable  {

	private static final long serialVersionUID = 1L;
	private ArrayList<CommentBean> commentBeans;
	
	public ArrayList<CommentBean> getCommentBeans() {
		return commentBeans;
	}
	
	public void setCommentBeans(ArrayList<CommentBean> commentBeans) {
		this.commentBeans = commentBeans;
	}
}
