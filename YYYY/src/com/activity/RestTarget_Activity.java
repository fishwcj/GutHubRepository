package com.activity;

import com.model.user.IsLogin;
import com.yyyy.yyyy.R;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class RestTarget_Activity extends Activity{
	private TextView clk_name;
	private EditText _targetName;
	private TextView clk_content;
	private EditText _targetContent;
	private EditText _targetTime;
	private TextView clk_ok;
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.resettarget);
		clk_name = (TextView)this.findViewById(R.id.clk_name);
		clk_content = (TextView)this.findViewById(R.id.clk_content);
		clk_ok = (TextView)this.findViewById(R.id._target_ok);
		_targetName = (EditText)this.findViewById(R.id._target_name);
		_targetContent = (EditText)this.findViewById(R.id._target_content);
		_targetTime = (EditText)this.findViewById(R.id._target_time);
		Cursor cursor = IsLogin.getTarget();
		if(cursor.moveToLast()){
			_targetName.setText(cursor.getString(cursor.getColumnIndex("name")));
			_targetTime.setText(cursor.getString(cursor.getColumnIndex("time")));
			_targetContent.setText(cursor.getString(cursor.getColumnIndex("content")));
		}
		
		clk_name.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				_targetName.setFocusable(true);
			}
		});
		
		clk_content.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				_targetContent.setFocusable(true);
			}
		});
		
		clk_ok.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String name = _targetName.getText().toString();
				String content = _targetContent.getText().toString();
				int time = Integer.parseInt(_targetTime.getText().toString());
				if(IsLogin.updateTarget(name, content, time)){
					Intent intent = new Intent(RestTarget_Activity.this,Target_Activity.class);
					RestTarget_Activity.this.startActivity(intent);
				}
			}
		});
	}
}
