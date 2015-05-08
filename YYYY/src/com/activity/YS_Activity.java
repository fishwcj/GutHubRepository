package com.activity;

/**
 * 15-4-7
 * 	更改设置完成按钮事件
 * @author wcj
 */
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import com.dao.YS_DAO;
import com.logic.BackgroundColor;
import com.yyyy.yyyy.R;

@SuppressLint("NewApi")
public class YS_Activity extends Activity {
	// 设置button样式
	private Drawable drawable1;
	// 显示总预算数值
	private TextView number_ys;
	// 编辑框
	private EditText input;
	// 类别选择项
	private RadioGroup select;
	private RadioButton clothes;
	private TextView show_clothes;
	private RadioButton eat;
	private TextView show_eat;
	private RadioButton house;
	private TextView show_house;
	@SuppressWarnings("unused")
	private RadioButton walk;
	private TextView show_walk;
	private ArrayList<TextView> show_list;// 单项预算的显示
//	private TextView item_view;// 自预算的显示view
	private RadioButton rb;
	// 键盘
	private Button number_1;
	private Button number_2;
	private Button number_3;
	private Button number_4;
	private Button number_5;
	private Button number_6;
	private Button number_7;
	private Button number_8;
	private Button number_9;
	private Button number_0;
	private Button number_float;
	private Button number_clear;
	// 确定
	private Button sure;
	private Button back;
	private String inputString = "";// 录入的每一项的字符串类型
	private String initString = "";// 初始值的字符串类型
	private String yss_total;// 总预算的字符串类型
	
	//不用初始化为0，，，直接从数据库读出来初始化。
	private Float ys_total;
	private float init_ys;// 每一项的初始值的float型
	private float ys_item;// 录入的每一项的值的float型

	// 最好先通过list再来以list的长度来生成数组
	private int kind[] = { 1, 2, 3, 4 };
	private float budget[] = { 0 , 0 ,0 , 0};

	private ArrayList<Integer> kind_list = new ArrayList<Integer>();
	private YS_DAO yS_handler;
	private Cursor cursor;
	// private int i = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ys);
		// 预算总额
		number_ys = (TextView) this.findViewById(R.id.number_ys);
		// 输入框
		input = (EditText) this.findViewById(R.id.input);
		// 类别选择
		select = (RadioGroup) this.findViewById(R.id.select);
		clothes = (RadioButton) this.findViewById(R.id.clothes);
		show_clothes = (TextView) this.findViewById(R.id.show_clothes);
		eat = (RadioButton) this.findViewById(R.id.eat);
		show_eat = (TextView) this.findViewById(R.id.show_eat);
		house = (RadioButton) this.findViewById(R.id.house);
		show_house = (TextView) this.findViewById(R.id.show_house);
		walk = (RadioButton) this.findViewById(R.id.walk);
		show_walk = (TextView) this.findViewById(R.id.show_walk);

		// 键盘
		number_0 = (Button) this.findViewById(R.id.number_0);
		number_1 = (Button) this.findViewById(R.id.number_1);
		number_2 = (Button) this.findViewById(R.id.number_2);
		number_3 = (Button) this.findViewById(R.id.number_3);
		number_4 = (Button) this.findViewById(R.id.number_4);
		number_5 = (Button) this.findViewById(R.id.number_5);
		number_6 = (Button) this.findViewById(R.id.number_6);
		number_7 = (Button) this.findViewById(R.id.number_7);
		number_8 = (Button) this.findViewById(R.id.number_8);
		number_9 = (Button) this.findViewById(R.id.number_9);
		number_float = (Button) this.findViewById(R.id.number_float);
		number_clear = (Button) this.findViewById(R.id.number_clear);
		// 确定
		sure = (Button) this.findViewById(R.id.sure);
		back = (Button) this.findViewById(R.id.back);
		/*
		 * 选择预算类别
		 */
		/*
		 * 设置button样式
		 */
		drawable1 = getResources().getDrawable(R.drawable.button);
		number_0.setBackground(drawable1);
		number_1.setBackground(drawable1);
		number_2.setBackground(drawable1);
		number_3.setBackground(drawable1);
		number_4.setBackground(drawable1);
		number_5.setBackground(drawable1);
		number_6.setBackground(drawable1);
		number_7.setBackground(drawable1);
		number_8.setBackground(drawable1);
		number_9.setBackground(drawable1);
		number_float.setBackground(drawable1);
		number_clear.setBackground(drawable1);
		sure.setBackground(drawable1);
		back.setBackground(drawable1);
		// 将show_view放到一个list中
		show_list = new ArrayList<TextView>();

		show_list.add(show_clothes);
		show_list.add(show_eat);
		show_list.add(show_house);
		show_list.add(show_walk);

		// 对应填充类型的list 注意：一定要一一对应
		kind_list.add(1);
		kind_list.add(2);
		kind_list.add(3);
		kind_list.add(4);
		yS_handler = new YS_DAO();
		// 从数据库中获取每种类型的预算值
		cursor = yS_handler.read_budget();
		// 读取游标的索引
		int i = 0;
		System.out.println("有结果" + cursor.getCount());
		while (cursor.moveToNext()) {
			show_list.get(i).setText(
					Float.toString(cursor.getFloat(cursor
							.getColumnIndex("budget"))));
			i++;
		}
		cursor.close();
		// 从数据库读取并更新总预算显示
		String totalbudget = yS_handler.read_totalbudget();
		number_ys.setText(totalbudget);
		ys_total = Float.parseFloat(totalbudget);
		select.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				int radioButtonId = group.getCheckedRadioButtonId();

				rb = (RadioButton) YS_Activity.this.findViewById(radioButtonId);
				rb.setTextColor(Color.WHITE);
				sure.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						inputString = input.getText().toString();
						rb.setTextColor(Color.BLACK);
						if (rb == clothes) {
							// 将输入的结果显示在“吃”位置
							initString = show_clothes.getText().toString();

							init_ys = Float.parseFloat(initString);

							budget[0] = init_ys;
							kind[0] = 1;
							if (inputString.length() < 1) {
								show_clothes.setText("0");
							} else {
								show_clothes.setText(input.getText());
							}
							// 将该预算加入到总预算中

						} else if (rb == eat) {
							// 将输入的结果显示在“玩”位置
							initString = show_eat.getText().toString();
							init_ys = Float.parseFloat(initString);

							budget[1] = init_ys;
							kind[1] = 2;

							if (inputString.length() < 1) {
								show_eat.setText("0");
							} else {
								show_eat.setText(input.getText());
							}
							// 将该预算加入到总预算中

						} else if (rb == house) {
							// 将输入的结果显示在“玩”位置
							initString = show_house.getText().toString();

							init_ys = Float.parseFloat(initString);

							budget[2] = init_ys;
							kind[2] = 3;

							if (inputString.length() < 1) {
								show_house.setText("0");
							} else {
								show_house.setText(input.getText());
							}
							// 将该预算加入到总预算中

						} else {
							initString = show_walk.getText().toString();
							init_ys = Float.parseFloat(initString);

							budget[3] = init_ys;
							kind[3] = 4;

							if (inputString.length() < 1) {
								show_walk.setText("0");
							} else {
								show_walk.setText(input.getText());
							}
						}
						if (inputString.length() < 1) {
							ys_item = 0.0f;
						} else {
							ys_item = Float.parseFloat(inputString);
						}

						ys_total = ys_total - init_ys + ys_item;

						yss_total = String.valueOf(ys_total);// 将预算转化为字符串

						number_ys.setText(yss_total);

						// 将budget[]和kind[]传入到数据库中
						//yS_handler.add(budget, kind);

						inputString = "";
						input.setText(inputString.toCharArray(), 0,
								inputString.length());

					}
				});

			}
		});
		/**
		 * 按钮0的事件
		 */
		number_0.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				inputString = input.getText().toString() + "0";
				input.setText(inputString.toCharArray(), 0,
						inputString.length());
			}
		});

		/**
		 * 按钮1的事件
		 */
		number_1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				inputString = input.getText().toString() + "1";
				input.setText(inputString.toCharArray(), 0,
						inputString.length());
			}
		});
		/**
		 * 按钮2的事件
		 */
		number_2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				inputString = input.getText().toString() + "2";
				input.setText(inputString.toCharArray(), 0,
						inputString.length());
			}
		});
		/**
		 * 按钮3的事件
		 */
		number_3.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				inputString = input.getText().toString() + "3";
				input.setText(inputString.toCharArray(), 0,
						inputString.length());
			}
		});
		/**
		 * 按钮4的事件
		 */
		number_4.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				inputString = input.getText().toString() + "4";
				input.setText(inputString.toCharArray(), 0,
						inputString.length());
			}
		});
		/**
		 * 按钮5的事件
		 */
		number_5.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				inputString = input.getText().toString() + "5";
				input.setText(inputString.toCharArray(), 0,
						inputString.length());
			}
		});
		/**
		 * 按钮6的事件
		 */
		number_6.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				inputString = input.getText().toString() + "6";
				input.setText(inputString.toCharArray(), 0,
						inputString.length());
			}
		});
		/**
		 * 按钮7的事件
		 */
		number_7.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				inputString = input.getText().toString() + "7";
				input.setText(inputString.toCharArray(), 0,
						inputString.length());
			}
		});
		/**
		 * 按钮8的事件
		 */
		number_8.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				inputString = input.getText().toString() + "8";
				input.setText(inputString.toCharArray(), 0,
						inputString.length());
			}
		});
		/**
		 * 按钮9的事件
		 */
		number_9.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				inputString = input.getText().toString() + "9";
				input.setText(inputString.toCharArray(), 0,
						inputString.length());
			}
		});
		/**
		 * 按钮.的事件
		 */
		number_float.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (inputString.length() > 0) {
					inputString = input.getText().toString() + ".";
					input.setText(inputString.toCharArray(), 0,
							inputString.length());
				}
			}
		});
		/**
		 * 按钮C的事件
		 */
		number_clear.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				inputString = "";
				input.setText(inputString.toCharArray(), 0,
						inputString.length());
			}
		});
		back.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// 首先插入到数据库，再结束此activity
				YS_DAO ysHelper = new YS_DAO();
				// 得到有多少个类别
				int numberOfKind = show_list.size();
				float[] budget = new float[numberOfKind];
				int[] kind = new int[numberOfKind];
				for (int i = 0; i < numberOfKind; i++) {
					// 读取TextView中的预算显示加入数组
					budget[i] = Float.parseFloat(show_list.get(i).getText()
							.toString());
					kind[i] = kind_list.get(i);
				}
				Index_Activity.remain += ys_total - Index_Activity.budget;
				Index_Activity.budget = ys_total;
				// 如果保存到数据库成功则结束此activity(保存总预算和分预算)
				if (ysHelper.add(budget, kind) && ysHelper.addtotal(ys_total)) {
					YS_Activity.this.finish();
				}
				BackgroundColor backgroundColor = new BackgroundColor();
				backgroundColor.refreshback();
			}
		});
	}
}
