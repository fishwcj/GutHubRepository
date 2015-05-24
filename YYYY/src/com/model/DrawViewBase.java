package com.model;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;

import com.activity.Count_Activity;

public class DrawViewBase extends View {

	public int width;
	public int height;
	DisplayMetrics dm = new DisplayMetrics();

	public DrawViewBase(Context context) {
		super(context);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		Activity count_Activity = Count_Activity.countActivity;
		count_Activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		width = dm.widthPixels / 2;
		height = dm.widthPixels / 2;
		setMeasuredDimension(width, height);// 设置实际高度和宽度
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
}
