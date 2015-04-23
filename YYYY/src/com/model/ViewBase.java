package com.model;

import android.content.Context;
import android.view.View;

public class ViewBase extends View {

	public int width;
	public int height;

	public ViewBase(Context context) {
		super(context);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		height = 500;
		width = 500;
		setMeasuredDimension(width, height);// 设置实际高度和宽度
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
}
