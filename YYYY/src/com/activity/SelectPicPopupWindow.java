package com.activity;

/**
 * 该类为类型选择时弹出的类型显示界面
 */
import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.logic.ConsumKindManager;
import com.yyyy.yyyy.R;

public class SelectPicPopupWindow extends Activity implements OnClickListener {

	private TextView yi;
	private TextView shi;
	private TextView zhu;
	private TextView xing;
	private TextView kind;
	private LinearLayout layout;
	public static Activity selectPicPopupWindow;
	private ConsumKindManager consumKindManager;

	@Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.alert);  
        selectPicPopupWindow = this;
        yi = (TextView)this.findViewById(R.id.yi);
        shi = (TextView)this.findViewById(R.id.shi);
        zhu = (TextView)this.findViewById(R.id.zhu);
        xing = (TextView)this.findViewById(R.id.xing);
        layout = (LinearLayout)this.findViewById(R.id.two);
        kind = (TextView)JZ_Activity.jzActivity.findViewById(R.id.kind);
        consumKindManager = new ConsumKindManager();
        
        /**
         * 衣 按钮事件
         */
        yi.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kind.setText("衣  > ");
				JZ_Activity.consumekind = 1;
				consumKindManager.freshButton(1, layout);
			}
		});
        
        /**
         * 食 按钮事件
         */
        shi.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kind.setText("食  > ");
				JZ_Activity.consumekind = 2;
				consumKindManager.freshButton(2, layout);
			}
		});
        
        /**
         * 住 按钮事件
         */
        zhu.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kind.setText("住  > ");
				JZ_Activity.consumekind = 3;
				consumKindManager.freshButton(3, layout);
			}
		});
        
        /**
         * 行 按钮事件
         */
        xing.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kind.setText("行  > ");
				JZ_Activity.consumekind = 4;
				consumKindManager.freshButton(4, layout);
			}
		});
    }	// 实现onTouchEvent触屏函数但点击屏幕时销毁本Activity

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		finish();
		return true;
	}

	public void onClick(View v) {
		
	}

}
