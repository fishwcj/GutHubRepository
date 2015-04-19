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
	
	 height = View.MeasureSpec.getSize(heightMeasureSpec);//计算高度（由上一层传入）
	 width = View.MeasureSpec.getSize(widthMeasureSpec);//计算宽度（由上一层传入）
	 //exactly
	 setMeasuredDimension(width, height);//设置实际高度和宽度	
	 super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	 }
}
