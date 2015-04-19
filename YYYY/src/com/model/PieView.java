package com.model;

import android.R.integer;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;

/**
 * @author LLL ����ͼ��
 */
public class PieView extends ViewBase {
	int areaX = 1;// ��ͼ ����λ�õ� X����
	int areaY = 22;// ��ͼ���ֵ�Y����
	int areaWidth;
	int areaHight;
	int colors[];
	float percent[];// ������
	RectF rectF = new RectF(areaX, areaY, areaX + areaWidth, areaHight);
	int color = Color.GREEN;
	Paint paint = new Paint();
	
	

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
		areaWidth = width+100;
		areaHight = height;
		paint.setColor(color);
		paint.setStyle(Style.FILL);
		paint.setAntiAlias(true);//���Ч��
		int tempAngle = 0;
		for (int j = 0; j < percent.length; j++) {
			
			paint.setColor(colors[j]);
			// �����Σ�˳ʱ��
			RectF rectF = new RectF(areaX, areaY, areaX + areaWidth-100, areaHight);
			canvas.drawArc(rectF, tempAngle, percent[j], true, paint);
			
			tempAngle += percent[j];
		}
		for(int j = 0;j < percent.length;j++){
			int i = 10;
			paint.setColor(colors[j]);
			RectF rectF1 = new RectF(50+i*j, 1, 60+i*j, 11);//�ĸ������ֱ����������ҵ��ĸ��������
			canvas.drawRect(rectF1, paint);
		}
	}
}
