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
 * @author LLL ����ͼ��
 */
public class PieView extends DrawViewBase {
	int areaX = 20;// ��ͼ ����λ�õ� X����
	int areaY = 40;// ��ͼ���ֵ�Y����
	int areaWidth;
	int areaHight;
	int colors[];
	float percent[];// ������
	Paint paint = new Paint();// ����ͼ
	TextPaint textPaint = new TextPaint();// дע��
	DecimalFormat decimalFormat = new DecimalFormat("0.00");// ��ʾ��λС��
	String p;

	/**
	 * @param context
	 *            ������
	 * @param colors
	 *            ��������ɫ����
	 * @param shade_colors
	 *            ��Ӱ��ɫ����
	 * @param percent
	 *            �ٷֱ� (�ͱ�����360)
	 */
	public PieView(Context context, int[] colors, float[] percent) {
		super(context);
		this.colors = colors;
		this.percent = percent;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		String type[] = { "��", "ʳ", "ס", "��" };
		areaWidth = width;
		areaHight = height;
		paint.setStyle(Style.FILL);
		paint.setAntiAlias(true);
		textPaint.setTextSize(50);
		textPaint.setStyle(Style.FILL);
		BigDecimal b;//������ʾ��λС��
		float p;
		RectF rectF,rectF1;
		// ���Ч��
		int tempAngle = 0;
		for (int j = 0; j < percent.length; j++) {

			paint.setColor(colors[j]);
			// �����Σ�˳ʱ��
			rectF = new RectF(areaX, areaY, areaX + areaWidth, areaHight);
			canvas.drawArc(rectF, tempAngle, percent[j], true, paint);
			tempAngle += percent[j];
		}
		for (int j = 0; j < percent.length; j++) {
			int i = 60;
			paint.setColor(colors[j]);
			textPaint.setColor(colors[j]);
			rectF1 = new RectF(areaX + areaWidth+10 , areaWidth/4 + i * j, areaX
					+ areaWidth*9/8+10, areaWidth*3/8 + i * j);// �ĸ������ֱ����������ҵ��ĸ��������
			canvas.drawRect(rectF1, paint);
			b = new BigDecimal(percent[j] / 3.6);
			p = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
			canvas.drawText(type[j] + "  " + p + "%", areaX + areaWidth + 100,
					areaWidth*3/8 + i * j, textPaint);
		}
	}
}
