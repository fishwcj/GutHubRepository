package com.activity;

import android.app.Activity;
import android.os.Bundle;

import com.yyyy.yyyy.R;

public class Count_Activity extends Activity {
	public static Activity countActivity;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_count);
		countActivity = this;
		System.out.println("Count±»´´½¨");
	}
}
