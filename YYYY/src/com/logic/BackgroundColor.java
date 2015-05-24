package com.logic;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import com.activity.Index_Activity;
import com.activity.JZ_Activity;
import com.yyyy.yyyy.R;

public class BackgroundColor {
	
	@SuppressLint("NewApi")
	public void refreshback(){
		Index_Activity.remain = Float.parseFloat(JZ_Activity.budgetRemain.getText().toString());
		float persent = Index_Activity.remain / Index_Activity.budget;
		Drawable drawble;
		if(Index_Activity.remain == 0 && Index_Activity.budget == 0){
			drawble = JZ_Activity.jzActivity.getResources()
					.getDrawable(R.color.q);
		}else if(persent >= 0.8){
			drawble = JZ_Activity.jzActivity.getResources()
					.getDrawable(R.color.b8);
		}else if(persent >= 0.7){
			drawble = JZ_Activity.jzActivity.getResources()
					.getDrawable(R.color.b7);
		}else if(persent >= 0.6){
			drawble = JZ_Activity.jzActivity.getResources()
					.getDrawable(R.color.b6);
		}else if(persent >= 0.5){
			drawble = JZ_Activity.jzActivity.getResources()
					.getDrawable(R.color.b5);
		}else if(persent >= 0.4){
			drawble = JZ_Activity.jzActivity.getResources()
					.getDrawable(R.color.b4);
		}else if(persent >= 0.3){
			drawble = JZ_Activity.jzActivity.getResources()
					.getDrawable(R.color.b3);
		}else if(persent >= 0.2){
			drawble = JZ_Activity.jzActivity.getResources()
					.getDrawable(R.color.b2);
		}else if(persent >= 0.1){
			drawble = JZ_Activity.jzActivity.getResources()
					.getDrawable(R.color.b1);
		}else if(persent >= 0){
			drawble = JZ_Activity.jzActivity.getResources()
					.getDrawable(R.color.b0);
		}else{
			drawble = JZ_Activity.jzActivity.getResources()
					.getDrawable(R.color.be);
		}
		System.out.println("°Ù·Ö±È£º" + persent);
		JZ_Activity.linearLayout.setBackground(drawble);
//		JZ_Activity.linearLayout.setBackgroundColor(color);
	}
}
