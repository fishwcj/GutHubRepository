package com.function;

import com.activity.Street_Activity;
import com.yyyy.yyyy.R;
import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class StreetGUI {
	private LinearLayout linearLayout;// ���಼��

	public StreetGUI(LinearLayout linearLayout) {
		this.linearLayout = linearLayout;
//		linearLayout.removeAllViews();
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
}
