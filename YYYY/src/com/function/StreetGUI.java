package com.function;

import com.activity.Street_Activity;
import com.bean.StreetMessageBean;
import com.yyyy.yyyy.R;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class StreetGUI {
	private LinearLayout linearLayout;// ���಼��

	public StreetGUI(LinearLayout linearLayout) {
		this.linearLayout = linearLayout;
		linearLayout.removeAllViews();
	}

	/**
	 * ������ϢչʾGUI
	 * 
	 * @param message
	 *            һ����Ϣ����
	 */
	@SuppressLint("InflateParams")
	public void createGUI(StreetMessageBean message) {
			LayoutInflater inflater = LayoutInflater
					.from(Street_Activity.street_Activity);
			View view = inflater.inflate(R.layout.message_templet, null);
			((TextView)view.findViewById(R.id.name)).setText(message.getUserName());//�����ǳ�
			((TextView)view.findViewById(R.id.message)).setText(message.getMessage());//������Ϣ��
			((TextView)view.findViewById(R.id.address)).setText(message.getAddress());//���õ�ַ
			int commentsNumber = message.getComments();
			String commentsString = "����(" + commentsNumber + ")";
			((TextView)view.findViewById(R.id.comments)).setText(commentsString);//��������
			linearLayout.addView(view);
	}
	
	/**
	 * ��������ʧ��
	 */
	public void createErrorGUI(){
		LayoutInflater inflater = LayoutInflater.from(Street_Activity.street_Activity);
		View view  = inflater.inflate(R.layout.connect_error, null);
		linearLayout.addView(view);
		((Button)view.findViewById(R.id.tryagin)).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				StreetConnecter connecter = new StreetConnecter();
				connecter.connect();
			}
		});
	}
}
