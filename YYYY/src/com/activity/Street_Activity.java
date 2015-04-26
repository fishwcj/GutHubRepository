package com.activity;

import java.util.ArrayList;
import java.util.Iterator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.function.StreetConnecter;
import com.function.StreetGUI;
import com.function.StreetMessageBean;
import com.yyyy.yyyy.R;

public class Street_Activity extends Activity {
	public static Activity street_Activity;
	public static LinearLayout rootlinearLayout;
	private TextView bj;
//	public static ArrayList<StreetMessageBean> messageBeans;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_street);
		street_Activity = this;
		rootlinearLayout = (LinearLayout) this.findViewById(R.id.root);
		bj = (TextView)this.findViewById(R.id.bj);
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
	}
}
