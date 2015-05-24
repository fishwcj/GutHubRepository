package com.model;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.text.TextPaint;

/**
 * @author LLL 画饼图类
 */
public class PieView extends DrawViewBase {
	int areaX = 20;// 饼图 出现位置的 X坐标
	int areaY = 40;// 饼图出现的Y坐标
	int areaWidth;
	int areaHight;
	int colors[];
	float percent[];// 浮点型
	Paint paint = new Paint();// 画饼图
	TextPaint textPaint = new TextPaint();// 写注释
	DecimalFormat decimalFormat = new DecimalFormat("0.00");// 显示两位小数
	String p;

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
		String type[] = { "衣", "食", "住", "行" };
		areaWidth = width;
		areaHight = height;
		paint.setStyle(Style.FILL);
		paint.setAntiAlias(true);
		textPaint.setTextSize(50);
		textPaint.setStyle(Style.FILL);
		BigDecimal b;//设置显示两位小数
		float p;
		RectF rectF,rectF1;
		// 锯齿效果
		int tempAngle = 0;
		for (int j = 0; j < percent.length; j++) {

			paint.setColor(colors[j]);
			// 画扇形；顺时针
			rectF = new RectF(areaX, areaY, areaX + areaWidth, areaHight);
			canvas.drawArc(rectF, tempAngle, percent[j], true, paint);
			tempAngle += percent[j];
		}
		for (int j = 0; j < percent.length; j++) {
			int i = 60;
			paint.setColor(colors[j]);
			textPaint.setColor(colors[j]);
			rectF1 = new RectF(areaX + areaWidth+10 , areaWidth/4 + i * j, areaX
					+ areaWidth*9/8+10, areaWidth*3/8 + i * j);// 四个参数分别是上下左右的四个点的坐标
			canvas.drawRect(rectF1, paint);
			b = new BigDecimal(percent[j] / 3.6);
			p = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
			canvas.drawText(type[j] + "  " + p + "%", areaX + areaWidth + 100,
					areaWidth*3/8 + i * j, textPaint);
		}
	}
}
