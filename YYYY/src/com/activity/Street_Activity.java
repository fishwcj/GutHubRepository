package com.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.function.StreetConnecter;
import com.yyyy.yyyy.R;

public class Street_Activity extends Activity {
	public static Activity street_Activity;
	public static LinearLayout rootlinearLayout;
	private TextView bj;
	private TextView sjz;
//	public static ArrayList<StreetMessageBean> messageBeans;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_street);
		street_Activity = this;
		rootlinearLayout = (LinearLayout) this.findViewById(R.id.root);
		bj = (TextView)this.findViewById(R.id.bj);
		sjz = (TextView)this.findViewById(R.id.sjz);
		StreetConnecter connecter = new StreetConnecter();
		connecter.connect();
		bj.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Street_Activity.this, Publish_Activity.class);
				startActivity(intent);
			}
		});
		
		sjz.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Street_Activity.this, Index_Activity.class);
				startActivity(intent);
			}
		});
	}
}
