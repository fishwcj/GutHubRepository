package com.model.street;

import com.activity.Comment_Activity;
import com.activity.Street_Activity;
import com.bean.CommentBean;
import com.bean.StreetMessageBean;
import com.yyyy.yyyy.R;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

@SuppressLint("InflateParams")
public class StreetGUI {
	private LinearLayout linearLayout;// 大类布局

	public StreetGUI(LinearLayout linearLayout) {
		this.linearLayout = linearLayout;
		linearLayout.removeAllViews();
	}

	/**
	 * 创建信息展示GUI
	 * 
	 * @param message
	 *            一条信息对象
	 */
	@SuppressLint("InflateParams")
	public void createGUI(StreetMessageBean message,int location) {
			LayoutInflater inflater = LayoutInflater
					.from(Street_Activity.street_Activity);
			View view = inflater.inflate(R.layout.message_templet, null);
			((TextView)view.findViewById(R.id.name)).setText(message.getUserName());//设置昵称
			((TextView)view.findViewById(R.id.message)).setText(message.getMessage());//设置消息体
			((TextView)view.findViewById(R.id.address)).setText(message.getAddress());//设置地址
			int commentsNumber = message.getComments();
			String commentsString = "评论(" + commentsNumber + ")";
			TextView commentsTextView = (TextView)view.findViewById(R.id.comments);
			commentsTextView.setText(commentsString);//设置评论
			commentsTextView.setId(location);
			commentsTextView.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(Street_Activity.street_Activity,Comment_Activity.class);
					intent.putExtra("location", v.getId());
					Street_Activity.street_Activity.startActivity(intent);
				}
			});
			linearLayout.addView(view);
	}
	
	/**
	 * 链接网络失败
	 */
	public void createErrorGUI(){
		LayoutInflater inflater = LayoutInflater.from(Street_Activity.street_Activity);
		View view  = inflater.inflate(R.layout.connect_error, null);
		linearLayout.addView(view);
		((Button)view.findViewById(R.id.tryagin)).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Street_Activity.street_Activity,Street_Activity.class);
				Street_Activity.street_Activity.startActivity(intent);
			}
		});
	}
	
	/**
	 * 评论区
	 * @param commentBean
	 */
	public void createCommentGUI(CommentBean commentBean){
		LayoutInflater inflater = LayoutInflater
				.from(Comment_Activity.comment_Activity);
		View view = inflater.inflate(R.layout.comment_templet, null);
		((TextView)view.findViewById(R.id.name_c)).setText(commentBean.getUserName());
		((TextView)view.findViewById(R.id.content_c)).setText(commentBean.getContent());
		((TextView)view.findViewById(R.id.time_c)).setText(commentBean.getTime());
		((TextView)view.findViewById(R.id.repet)).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		linearLayout.addView(view);
	}
}
