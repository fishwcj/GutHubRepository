package com.model;

import android.R.integer;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;

/**
 * @author LLL 画饼图类
 */
public class PieView extends ViewBase {
	int areaX = 1;// 饼图 出现位置的 X坐标
	int areaY = 22;// 饼图出现的Y坐标
	int areaWidth;
	int areaHight;
	int colors[];
	float percent[];// 浮点型
	RectF rectF = new RectF(areaX, areaY, areaX + areaWidth, areaHight);
	int color = Color.GREEN;
	Paint paint = new Paint();
	
	

	/**
	 * @param context
	 *            上下文
	 * @param colors
	 *            最上面颜色数组
	 * @param shade_colors
	 *            阴影颜色数组
	 * @param percent
	 *            百分比 (和必须是360)
	 */
	public PieView(Context context, int[] colors, float[] percent) {
		super(context);
		this.colors = colors;
		this.percent = percent;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		areaWidth = width+100;
		areaHight = height;
		paint.setColor(color);
		paint.setStyle(Style.FILL);
		paint.setAntiAlias(true);//锯齿效果
		int tempAngle = 0;
		for (int j = 0; j < percent.length; j++) {
			
			paint.setColor(colors[j]);
			// 画扇形；顺时针
			RectF rectF = new RectF(areaX, areaY, areaX + areaWidth-100, areaHight);
			canvas.drawArc(rectF, tempAngle, percent[j], true, paint);
			
			tempAngle += percent[j];
		}
		for(int j = 0;j < percent.length;j++){
			int i = 10;
			paint.setColor(colors[j]);
			RectF rectF1 = new RectF(50+i*j, 1, 60+i*j, 11);//四个参数分别是上下左右的四个点的坐标
			canvas.drawRect(rectF1, paint);
		}
	}
}
