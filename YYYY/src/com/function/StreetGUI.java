package com.function;

import com.activity.Street_Activity;
import com.yyyy.yyyy.R;
import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class StreetGUI {
	private LinearLayout linearLayout;// 大类布局

	public StreetGUI(LinearLayout linearLayout) {
		this.linearLayout = linearLayout;
//		linearLayout.removeAllViews();
	}

	/**
	 * 创建信息展示GUI
	 * 
	 * @param message
	 *            一条信息对象
	 */
	@SuppressLint("InflateParams")
	public void createGUI(StreetMessageBean message) {
			LayoutInflater inflater = LayoutInflater
					.from(Street_Activity.street_Activity);
			View view = inflater.inflate(R.layout.message_templet, null);
			((TextView)view.findViewById(R.id.name)).setText(message.getUserName());//设置昵称
			((TextView)view.findViewById(R.id.message)).setText(message.getMessage());//设置消息体
			((TextView)view.findViewById(R.id.address)).setText(message.getAddress());//设置地址
			int commentsNumber = message.getComments();
			String commentsString = "评论(" + commentsNumber + ")";
			((TextView)view.findViewById(R.id.comments)).setText(commentsString);//设置评论
			linearLayout.addView(view);
	}
}
