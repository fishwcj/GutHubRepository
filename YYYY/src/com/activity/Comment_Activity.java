package com.activity;

import com.bean.StreetMessageBean;
import com.yyyy.yyyy.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Comment_Activity extends Activity {
	public static Activity comment_Activity;
	private StreetMessageBean messageBean;
	private TextView username;
	private TextView content;
	private TextView time;
	private EditText editText;
	private Button send;
	private int location;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_comment);
		comment_Activity = this;
		username = (TextView)this.findViewById(R.id.name_y);
		content = (TextView)this.findViewById(R.id.message_c);
		time = (TextView)this.findViewById(R.id.time);
		editText = (EditText)this.findViewById(R.id.mycomment);
		send = (Button)this.findViewById(R.id.send);
		
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		location = bundle.getInt("lcation");
		messageBean = Street_Activity.MessageBeanlist.get(location);
		username.setText(messageBean.getUserName());
		content.setText(messageBean.getMessage());
		time.setText(messageBean.getDatetime());
		send.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String mycomments = editText.getText().toString();
				
			}
		});
	}
}
