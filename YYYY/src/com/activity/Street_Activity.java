package com.activity;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bean.StreetMessageBean;
import com.model.street.StreetConnecter;
import com.yyyy.yyyy.R;

public class Street_Activity extends Activity {
	public static Activity street_Activity;
	public static LinearLayout rootlinearLayout;
	public static ArrayList<StreetMessageBean> MessageBeanlist;
	private SwipeRefreshLayout swipeView;
	private ScrollView scrollView;
	private TextView bj;
	private TextView sjz;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_street);
		street_Activity = this;
		rootlinearLayout = (LinearLayout) this.findViewById(R.id.root);
		bj = (TextView) this.findViewById(R.id.bj);
		sjz = (TextView) this.findViewById(R.id.sjz);
		scrollView = (ScrollView) this.findViewById(R.id.touch);
		swipeView = (SwipeRefreshLayout) this.findViewById(R.id.swipe);
		swipeView.setColorSchemeResources(android.R.color.holo_blue_dark,
				android.R.color.holo_blue_light,
				android.R.color.holo_green_light,
				android.R.color.holo_green_light);
		StreetConnecter connecter = new StreetConnecter();
		connecter.connect();
		swipeView
				.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

					@Override
					public void onRefresh() {
						// TODO Auto-generated method stub
						swipeView.setRefreshing(true);
						(new Handler()).postDelayed(new Runnable() {
							@Override
							public void run() {
								StreetConnecter connecter = new StreetConnecter();
								connecter.connect();
								swipeView.setRefreshing(false);
							}
						}, 3000);
					}

				});

		scrollView.setOnTouchListener(new View.OnTouchListener() {

			@SuppressLint("ClickableViewAccessibility")
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (event.getAction() == MotionEvent.ACTION_MOVE) {
					// 可以监听到ScrollView的滚动事件
					if(scrollView.getScrollY() == 0){
						swipeView.setEnabled(true);
					}else{
						swipeView.setEnabled(false);
					}
				}
				return false;
			}
		});

		bj.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Street_Activity.this,
						Publish_Activity.class);
				startActivity(intent);
			}
		});

		sjz.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Street_Activity.this,
						Index_Activity.class);
				startActivity(intent);
			}
		});
	}
}
