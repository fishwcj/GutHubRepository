package com.activity;

import com.model.user.IsLogin;
import com.yyyy.yyyy.R;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Target_Activity extends Activity {
	private TextView addTarget;
	private TextView sy;
	private TextView gl;
	private TextView targetTime;
	private TextView targetTime1;
	private TextView targetName;
	private TextView targetContent;
	private TextView targettips;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (IsLogin.hasTarget()) {
			setContentView(R.layout.hasadd);
			sy = (TextView) this.findViewById(R.id.sy_m);
			gl = (TextView) this.findViewById(R.id.gl_m);
			targetName = (TextView) this.findViewById(R.id.targetName);
			targetContent = (TextView) this.findViewById(R.id.targetContent);
			targetTime = (TextView) this.findViewById(R.id.targettime);
			targetTime1 = (TextView) this.findViewById(R.id.targettime1);
			targettips = (TextView) this.findViewById(R.id.targetnotice);
			Cursor cursor = IsLogin.getTarget();
			if (cursor.moveToLast()) {
				targetName.setText(cursor.getString(cursor
						.getColumnIndex("name")));
				targetTime.setText(cursor.getString(cursor
						.getColumnIndex("time")));
				targetTime1.setText(cursor.getString(cursor
						.getColumnIndex("lefttime")));
				targetContent.setText(cursor.getString(cursor
						.getColumnIndex("content")));
				targettips.setText(cursor.getString(cursor
						.getColumnIndex("tips")));
			}
			sy.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(Target_Activity.this,
							Index_Activity.class);
					Target_Activity.this.startActivity(intent);
				}
			});

			gl.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(Target_Activity.this,
							RestTarget_Activity.class);
					Target_Activity.this.startActivity(intent);
				}
			});

		} else {
			setContentView(R.layout.add_goal);
			addTarget = (TextView) this.findViewById(R.id.add);
			addTarget.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(Target_Activity.this,
							Add_Activity.class);
					Target_Activity.this.startActivity(intent);
				}
			});
		}
	}
}
