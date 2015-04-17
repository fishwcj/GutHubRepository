package com.activity;

import android.app.Activity;
import android.os.Bundle;

import com.yyyy.yyyy.R;

public class Stream_Activity extends Activity {

	@Override
	protected void onResume() {
		super.onResume();
		System.out.println("进了resume");
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stream);
		System.out.println("Stream被创建");
	}
}
