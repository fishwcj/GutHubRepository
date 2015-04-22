package com.activity;

/**
 * 1.��Ӳ���+��ť�¼������ڲ���
 * 2.���ȷ����ť
 * @author wcj
 * @time 15-3-31��
 */

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.dao.DataBase;
import com.dao.YS_DataBaseHelper;
import com.model.BackgroundColor;
import com.yyyy.yyyy.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class JZ_Activity extends Activity {

	public static TextView budgetRemain;
	private TextView kind;
	public TextView consume;
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
	// ���԰�ť
	// private Button syButton;

	private TextView number_in;
	private TextView number_out;
	private Button button_ok;
	private Button number_float;
	private Button number_clear;
	private String consumString = "";
	public static TextView consumed;
	public static LinearLayout linearLayout;
	// 0����֧����1�������룬Ĭ��֧��
	private int inOrOut = 0;
	public static int consumekind = 2; // ������������Ĭ��Ϊʳ��
	private ArrayList<String> kindList = new ArrayList<String>();
	SQLiteDatabase db;
	public static Activity jzActivity;

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (db != null) {
			db.close();// SQLiteDatabase sqldb;
		}
	}

	@Override
	protected void onResume() {
		System.out.println("hehe������Resume");
	};

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jz1);
		jzActivity = this;
		System.out.println("JZ������");
		System.out.println("JZ�߳�:" + Thread.currentThread().getId());
		/**
		 * �������
		 */
		consume = (TextView) this.findViewById(R.id.consume);
		budgetRemain = (TextView) this.findViewById(R.id.budgetRemain);
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
		number_in = (TextView) this.findViewById(R.id.button_in);
		number_out = (TextView) this.findViewById(R.id.button_out);
		number_float = (Button) this.findViewById(R.id.number_float);
		number_clear = (Button) this.findViewById(R.id.number_clear);
		button_ok = (Button) this.findViewById(R.id.ok);
		consumed = (TextView) this.findViewById(R.id.comsumed);
		linearLayout = (LinearLayout) this.findViewById(R.id.background);
		// ���԰�ť
		// syButton = (Button) this.findViewById(R.id.sy);
		kind = (TextView) this.findViewById(R.id.kind);

		kindList.add("���㷹��");
		kindList.add("�������");
		kindList.add("���㷹��");
		kindList.add("˹��ª��");
		kindList.add("̤����Ь");

		// /**
		// * ����ͬ����ť
		// */
		// syButton.setOnClickListener(new View.OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// // TODO Auto-generated method stub
		// DataBase dataBase = new DataBase(JZ_Activity.this, "user.db");
		// CloudSendHelper cloudSendHelper = new CloudSendHelper(dataBase);
		// try {
		// try {
		// if (cloudSendHelper.send()) {
		// System.out.println("ͬ���ɹ���");
		// Toast.makeText(JZ_Activity.this, "ͬ���ɹ�!",
		// Toast.LENGTH_LONG).show();
		// }
		// } catch (ClassNotFoundException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// } catch (MalformedURLException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		// });
		/**
		 * ��������¼�
		 */
		kind.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(JZ_Activity.this,
						SelectPicPopupWindow.class));
			}
		});

		/**
		 * ������¼�
		 */
		number_in.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				number_in.setTextColor(Color.RED);
				number_out.setTextColor(Color.WHITE);
				inOrOut = 1;
				consume.setTextColor(Color.GREEN);
			}
		});

		/**
		 * ֧�����¼�
		 */
		number_out.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				number_in.setTextColor(Color.WHITE);
				number_out.setTextColor(Color.RED);
				inOrOut = 0;
				consume.setTextColor(Color.RED);
				System.out.println("�л���֧�����inOrOutӦ��=0��ʵ��Ϊ" + inOrOut);
			}
		});

		/**
		 * ��ť0���¼�
		 */
		number_0.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (check(consumString)) { // ��������Ƿ�Ϸ�
					consumString += "0";
					consume.setText(consumString.toCharArray(), 0,
							consumString.length());
				}
			}
		});

		/**
		 * ��ť1���¼�
		 */
		number_1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (check(consumString)) { // ��������Ƿ�Ϸ�
					consumString += "1";
					consume.setText(consumString.toCharArray(), 0,
							consumString.length());
				}
			}
		});
		/**
		 * ��ť2���¼�
		 */
		number_2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (check(consumString)) { // ��������Ƿ�Ϸ�
					consumString += "2";
					consume.setText(consumString.toCharArray(), 0,
							consumString.length());
				}
			}
		});
		/**
		 * ��ť3���¼�
		 */
		number_3.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (check(consumString)) { // ��������Ƿ�Ϸ�
					consumString += "3";
					consume.setText(consumString.toCharArray(), 0,
							consumString.length());
				}
			}
		});
		/**
		 * ��ť4���¼�
		 */
		number_4.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (check(consumString)) { // ��������Ƿ�Ϸ�
					consumString += "4";
					consume.setText(consumString.toCharArray(), 0,
							consumString.length());
				}
			}
		});
		/**
		 * ��ť5���¼�
		 */
		number_5.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (check(consumString)) { // ��������Ƿ�Ϸ�
					consumString += "5";
					consume.setText(consumString.toCharArray(), 0,
							consumString.length());
				}
			}
		});
		/**
		 * ��ť6���¼�
		 */
		number_6.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (check(consumString)) { // ��������Ƿ�Ϸ�
					consumString += "6";
					consume.setText(consumString.toCharArray(), 0,
							consumString.length());
				}
			}
		});
		/**
		 * ��ť7���¼�
		 */
		number_7.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (check(consumString)) { // ��������Ƿ�Ϸ�
					consumString += "7";
					consume.setText(consumString.toCharArray(), 0,
							consumString.length());
				}
			}
		});
		/**
		 * ��ť8���¼�
		 */
		number_8.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (check(consumString)) { // ��������Ƿ�Ϸ�
					consumString += "8";
					consume.setText(consumString.toCharArray(), 0,
							consumString.length());
				}
			}
		});
		/**
		 * ��ť9���¼�
		 */
		number_9.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (check(consumString)) { // ��������Ƿ�Ϸ�
					consumString += "9";
					consume.setText(consumString.toCharArray(), 0,
							consumString.length());
				}
			}
		});
		/**
		 * ��ť.���¼�
		 */
		number_float.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (check(consumString)) {
					if (consumString.length() > 0
							&& (consumString.indexOf(".") < 0)) {
						consumString = consume.getText().toString() + ".";
						consume.setText(consumString.toCharArray(), 0,
								consumString.length());
					}
				}
			}
		});
		/**
		 * ��ťC���¼�
		 */
		number_clear.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				consumString = "";
				consume.setText(consumString.toCharArray(), 0,
						consumString.length());
			}
		});

		/**
		 * ��ťok���¼�
		 */
		button_ok.setOnClickListener(new View.OnClickListener() {

			@SuppressLint("SimpleDateFormat")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (consumString.length() > 0) {
					ContentValues values = new ContentValues();
					// ���ʱ��
					SimpleDateFormat sDateFormat = new SimpleDateFormat(
							"yyyy-MM-dd    hh:mm:ss");
					String date = sDateFormat.format(new java.util.Date());
					// �ַ���ת��Ϊ������
					float consume1 = Float.parseFloat(consumString);
					// �������ݣ� ע��ֵ������Ҫƥ��
					values.put("consume", consume1);
					// ��ArrayList�����ҵ����͵���������
					if (inOrOut == 0)
						values.put("kind", kindList.get(consumekind));
					else {
						values.put("kind", "����");
					}
					values.put("date", date);
					values.put("inorout", inOrOut);
					// �����ݿⲢ����
					DataBase dataBase = new DataBase(JZ_Activity.this,
							"user.db");
					SQLiteDatabase db = dataBase.getWritableDatabase();
					db.insert("test1", null, values);
					// �������������
					consumString = "";
					consume.setText(consumString.toCharArray(), 0,
							consumString.length());
					YS_DataBaseHelper ys_DataBaseHelper = new YS_DataBaseHelper(
							JZ_Activity.this, dataBase);

					// �����֧�������Ԥ�����������������
					if (inOrOut == 0) {
						// ͬʱ�������ݿ�2��Ԥ���
						ys_DataBaseHelper.update(consume1, consumekind);
						// ������ʾ���
						Float remain = Float.parseFloat(budgetRemain.getText()
								.toString());
						remain = remain - consume1;
						budgetRemain.setText(remain.toString());
						System.out.println("����ɹ�");
						// db.close();
					} else {
						// ���������
						ys_DataBaseHelper.updatein(consume1, db);
						System.out.println("����ɹ�");
						// db.close();
					}
				}
				BackgroundColor backgroundColor = new BackgroundColor();
				backgroundColor.refreshback();
				// ��������
				String consumed = new DecimalFormat("0.0")
						.format(Index_Activity.budget - Index_Activity.remain);
				JZ_Activity.consumed.setText(consumed);
				Toast.makeText(JZ_Activity.this, "�ɹ�����һ��!", Toast.LENGTH_LONG)
						.show();
			}
		});
		/*
		 * ��ť����Ԥ��İ�ť
		 */
		budgetRemain.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(JZ_Activity.this, YS_Activity.class);
				startActivity(intent);
			}
		});

	}

	/**
	 * ��������Ƿ�Ϸ�
	 * 
	 * @param consumeString
	 *            �༭���ַ���
	 * @return
	 */
	public boolean check(String consumeString) {
		boolean TAG = false;
		// ������
		if (consumeString.contains(".")) {
			if (consumeString.indexOf(".") < 4
					|| (consumeString.length() - consumeString.indexOf(".")) < 3) {
				TAG = true;
			}
		} else {// ����
			if (consumeString.length() < 6) {
				TAG = true;
			}
		}
		return TAG;
	}
}
