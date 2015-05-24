package com.activity;

import com.yyyy.yyyy.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FirstKind_Activity extends Activity {
	private TextView spjs;
	private TextView yfsp;
	private TextView cxjt;
	private TextView jjxf;
	private TextView xxjx;
	private TextView xxyl;
	private TextView rjjw;
	private TextView ylbj;
	private TextView qtzx;
	private TextView sy;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.firstkind_activity);
		spjs = (TextView) this.findViewById(R.id._spjs);
		yfsp = (TextView) this.findViewById(R.id._yfsp);
		cxjt = (TextView) this.findViewById(R.id._cxjt);
		jjxf = (TextView) this.findViewById(R.id._jjxf);
		xxjx = (TextView) this.findViewById(R.id._xxjx);
		xxyl = (TextView) this.findViewById(R.id._xxyl);
		rjjw = (TextView) this.findViewById(R.id._rjjw);
		ylbj = (TextView) this.findViewById(R.id._ylbj);
		qtzx = (TextView) this.findViewById(R.id._qtzx);
		sy = (TextView)this.findViewById(R.id.sy_f);
		
		sy.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(FirstKind_Activity.this,Index_Activity.class);
				startActivity(intent);
			}
		});
		
		spjs.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				jump(1);
			}
		});

		yfsp.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				jump(2);
			}
		});

		cxjt.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				jump(3);
			}
		});

		jjxf.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				jump(4);
			}
		});

		xxjx.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				jump(5);
			}
		});

		xxyl.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				jump(6);
			}
		});

		rjjw.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				jump(7);
			}
		});

		ylbj.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				jump(8);
			}
		});

		qtzx.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				jump(9);
			}
		});
	}

	private void jump(int consumekind) {
		JZ_Activity.consumekind = consumekind;
		Intent intent = new Intent(FirstKind_Activity.this,
				AddKind_Activity.class);
		startActivity(intent);
	}
}
