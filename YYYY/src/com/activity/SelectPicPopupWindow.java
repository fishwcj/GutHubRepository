package com.activity;

/**
 * 该类为类型选择时弹出的类型显示界面
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.logic.ConsumKindManager;
import com.yyyy.yyyy.R;

public class SelectPicPopupWindow extends Activity implements OnClickListener {

	private TextView spjs;
	private TextView yfsp;
	private TextView cxjt;
	private TextView jjxf;
	private TextView xxjx;
	private TextView xxyl;
	private TextView rjjw;
	private TextView ylbj;
	private TextView qtzx;
	private TextView zdy;
	
	private TextView kind;
	private LinearLayout layout;
	public static Activity selectPicPopupWindow;
	private ConsumKindManager consumKindManager;

	@Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.alert);  
        selectPicPopupWindow = this;
        spjs = (TextView)this.findViewById(R.id.spjs);
        yfsp = (TextView)this.findViewById(R.id.yfsp);
        cxjt = (TextView)this.findViewById(R.id.cxjt);
        jjxf = (TextView)this.findViewById(R.id.jjxf);
        xxjx = (TextView)this.findViewById(R.id.xxjx);
        xxyl = (TextView)this.findViewById(R.id.xxyl);
        rjjw = (TextView)this.findViewById(R.id.rjjw);
        ylbj = (TextView)this.findViewById(R.id.ylbj);
        qtzx = (TextView)this.findViewById(R.id.qtzx);
        zdy = (TextView)this.findViewById(R.id.zdy);
        
        layout = (LinearLayout)this.findViewById(R.id.two);
        kind = (TextView)JZ_Activity.jzActivity.findViewById(R.id.kind);
        consumKindManager = new ConsumKindManager();
        
        /**
         * 食品酒水 按钮事件
         */
        spjs.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kind.setText("食品酒水  > ");
				JZ_Activity.consumekind = 1;
				consumKindManager.freshButton(1, layout);
			}
		});
        
        /**
         * 衣服饰品 按钮事件
         */
        yfsp.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kind.setText("衣服饰品  > ");
				JZ_Activity.consumekind = 2;
				consumKindManager.freshButton(2, layout);
			}
		});
        
        /**
         * 出行交通 按钮事件
         */
        cxjt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kind.setText("出行交通  > ");
				JZ_Activity.consumekind = 3;
				consumKindManager.freshButton(3, layout);
			}
		});
        
        /**
         * 家居消费 按钮事件
         */
        jjxf.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kind.setText("家居消费  > ");
				JZ_Activity.consumekind = 4;
				consumKindManager.freshButton(4, layout);
			}
		});
        
        /**
         * 学习进修 按钮事件
         */
        xxjx.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kind.setText("学习进修  > ");
				JZ_Activity.consumekind = 5;
				consumKindManager.freshButton(5, layout);
			}
		});
        
        /**
         * 休闲娱乐 按钮事件
         */
        xxyl.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kind.setText("休闲娱乐  > ");
				JZ_Activity.consumekind = 6;
				consumKindManager.freshButton(6, layout);
			}
		});
        
        /**
         * 人际交往 按钮事件
         */
        rjjw.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kind.setText("人际交往  > ");
				JZ_Activity.consumekind = 7;
				consumKindManager.freshButton(7, layout);
			}
		});
        
        /**
         * 医疗保健 按钮事件
         */
        ylbj.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kind.setText("医疗保健  > ");
				JZ_Activity.consumekind = 8;
				consumKindManager.freshButton(8, layout);
			}
		});
        
        /**
         * 其它杂项 按钮事件
         */
        qtzx.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kind.setText("其它杂项  > ");
				JZ_Activity.consumekind = 9;
				consumKindManager.freshButton(9, layout);
			}
		});
        
        /**
         * 自定义消费
         */
        zdy.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SelectPicPopupWindow.this,AddKind_Activity.class);
				startActivity(intent);
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
