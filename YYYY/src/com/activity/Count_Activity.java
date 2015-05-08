package com.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.yyyy.yyyy.R;

public class Count_Activity extends Activity {
	public static Activity countActivity;
	private Button plus;
	private Button sub;
	private TextView show_time;
	String[] time;
	int i = 8;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_count);
		countActivity = this;
		plus = (Button) findViewById(R.id.plus);
		sub = (Button) findViewById(R.id.sub);
		show_time = (TextView) findViewById(R.id.show_time);

		time = getResources().getStringArray(R.array.select_time);
		show_time.setText(time[0]);
		plus.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (i <= 18) {
					show_time.setText(time[++i]);
				}
				else{
					Toast.makeText(Count_Activity.this, "已经是这学期最后一周了哦",
							Toast.LENGTH_SHORT).show();
				}
			}
		});
		sub.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (i >= 1) {
					show_time.setText(time[--i]);
				} else {
					Toast.makeText(Count_Activity.this, "已经是这学期的第一周了哦",
							Toast.LENGTH_SHORT).show();
				}
			}
		});
//		show_time.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				
//			}
//		});

	}
}
