package com.activity;

import com.function.CreateMessageBean;
import com.function.PublishConnecter;
import com.function.StreetMessageBean;
import com.yyyy.yyyy.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Publish_Activity extends Activity {
	private TextView fs;
	private EditText content;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_publish);
		fs = (TextView)this.findViewById(R.id.fs);
		content = (EditText)this.findViewById(R.id.content);
		
		/**
		 * 发送事件
		 */
		fs.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String conntentString = content.getText().toString();
				CreateMessageBean creater = new CreateMessageBean();//封装信息
				StreetMessageBean messageBean = creater.create(conntentString);
				PublishConnecter connecter = new PublishConnecter();
				connecter.send(messageBean);
			}
		});
	}
}
