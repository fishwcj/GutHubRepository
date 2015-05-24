package com.activity;

import com.dao.Kind_Dao;
import com.model.set.KindGUI;
import com.yyyy.yyyy.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AddKind_Activity extends Activity {
	private TextView firstKindName;
	private TextView changeKind;
	private LinearLayout layout;
	private KindGUI kindGUI;
	private TextView newKind;
	private TextView sy;
	private TextView ok;
	public static Activity addKind_Activity;
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_addkind);
		addKind_Activity = this;
		kindGUI = new KindGUI();
		firstKindName = (TextView)this.findViewById(R.id.firstkind_name);
		changeKind = (TextView)this.findViewById(R.id.changekind);
		layout = (LinearLayout)this.findViewById(R.id.chlidkind);
		newKind = (TextView)this.findViewById(R.id.newkind);
		sy = (TextView)this.findViewById(R.id.sy_k);
		ok = (TextView)this.findViewById(R.id.ok_k);
		firstKindName.setText(Kind_Dao.getfirstKindName(JZ_Activity.consumekind));
		changeKind.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(AddKind_Activity.this,FirstKind_Activity.class);
				startActivity(intent);
			}
		});
		kindGUI.createChlid(JZ_Activity.consumekind, layout);
		newKind.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				kindGUI.addNew(JZ_Activity.consumekind, layout);
			}
		});
		sy.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(AddKind_Activity.this,Index_Activity.class);
				startActivity(intent);
			}
		});
		ok.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(AddKind_Activity.this,Index_Activity.class);
				startActivity(intent);
			}
		});
	}
}
