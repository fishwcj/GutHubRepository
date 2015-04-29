package com.activity;

import com.function.CreateMessageBean;
import com.function.PublishConnecter;
import com.function.StreetMessageBean;
import com.yyyy.yyyy.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Publish_Activity extends Activity {
	private TextView fs;
	private TextView pjz;
	private EditText content;
	public static Activity publish_Activity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_publish);
		fs = (TextView) this.findViewById(R.id.fs);
		pjz = (TextView) this.findViewById(R.id.pjz);
		content = (EditText) this.findViewById(R.id.content);
		publish_Activity = this;

		/**
		 * 发送事件
		 */
		fs.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String conntentString = content.getText().toString();
				if (conntentString.length() > 0) {
					CreateMessageBean creater = new CreateMessageBean();// 封装信息
					StreetMessageBean messageBean = creater
							.create(conntentString);
					PublishConnecter connecter = new PublishConnecter();
					connecter.send(messageBean);
					content.setText("");
				} else {
					Toast.makeText(Publish_Activity.this, "亲，写点内容再发吧~!",
							Toast.LENGTH_LONG).show();
				}
			}
		});

		pjz.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Publish_Activity.this,
						Index_Activity.class);
				startActivity(intent);
			}
		});
	}
}
