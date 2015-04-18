package com.activity;

/**
 * 1.��Ӳ���+��ť�¼������ڲ���
 * 2.���ȷ����ť
 * @author wcj
 * @time 15-3-31��
 */
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.dao.DataBase;
import com.dao.YS_DataBaseHelper;
import com.model.CloudSendHelper;
import com.yyyy.yyyy.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class JZ_Activity extends Activity {

	private TextView budgetRemain;
	private TextView yiTextView;
	private TextView shiTextView;
	private TextView zhuTextView;
	private TextView xingTextView;
	private TextView kind;
	private TextView consume;
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
	private Button syButton;

	private TextView number_in;
	private TextView number_out;
	private Button button_ok;
	private Button number_float;
	private Button number_clear;
	private String consumString = "";
	// 0����֧����1�������룬Ĭ��֧��
	private int inOrOut = 0;
	private int consumekind = 2; // ������������Ĭ��Ϊʳ��
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
		setContentView(R.layout.activity_jz);
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
		// ���԰�ť
		syButton = (Button) this.findViewById(R.id.sy);

		yiTextView = (TextView) this.findViewById(R.id.yi);
		shiTextView = (TextView) this.findViewById(R.id.shi);
		zhuTextView = (TextView) this.findViewById(R.id.zhu);
		xingTextView = (TextView) this.findViewById(R.id.xing);
		kind = (TextView) this.findViewById(R.id.kind);

		Drawable drawable = getResources().getDrawable(R.drawable.button);
		Drawable drawable2 = getResources().getDrawable(R.drawable.radius);
		number_0.setBackground(drawable);
		number_1.setBackground(drawable);
		number_2.setBackground(drawable);
		number_3.setBackground(drawable);
		number_4.setBackground(drawable);
		number_5.setBackground(drawable);
		number_6.setBackground(drawable);
		number_7.setBackground(drawable);
		number_8.setBackground(drawable);
		number_9.setBackground(drawable);
		number_float.setBackground(drawable);
		number_clear.setBackground(drawable);
		button_ok.setBackground(drawable);
		// ���԰�ť
		syButton.setBackground(drawable);

		consume.setBackground(drawable2);

		kindList.add("���㷹��");
		kindList.add("�������");
		kindList.add("���㷹��");
		kindList.add("˹��ª��");
		kindList.add("̤����Ь");

		/**
		 * ����ͬ����ť
		 */
		syButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				DataBase dataBase = new DataBase(JZ_Activity.this, "user.db");
				CloudSendHelper cloudSendHelper = new CloudSendHelper(dataBase);
				try {
					try {
						if (cloudSendHelper.send()) {
							System.out.println("ͬ���ɹ���");
							Toast.makeText(JZ_Activity.this, "ͬ���ɹ�!",
									Toast.LENGTH_LONG).show();
						}
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
				System.out.println("�л����������inOrOutӦ��=1��ʵ��Ϊ" + inOrOut);
				kind.setText("һ���������ָ������1000��Ǯ��");
				yiTextView.setClickable(false);
				shiTextView.setClickable(false);
				zhuTextView.setClickable(false);
				xingTextView.setClickable(false);
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
				yiTextView.setClickable(true);
				shiTextView.setClickable(true);
				zhuTextView.setClickable(true);
				xingTextView.setClickable(true);
			}
		});

		/**
		 * �µ��¼�
		 */
		yiTextView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kind.setText("����ȹ��");
				consumekind = 1;
			}
		});

		/**
		 * ʳ���¼�
		 */
		shiTextView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kind.setText("���˼��׷����Ź�");
				consumekind = 2;
			}
		});

		/**
		 * ס���¼�
		 */
		zhuTextView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kind.setText("�������С�ù�50��Ǯһ��Ŷ");
				consumekind = 3;
			}
		});

		/**
		 * �е��¼�
		 */
		xingTextView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kind.setText("�����ޱ�Ҫ���ѣ��´���·���мǣ�");
				consumekind = 4;
			}
		});

		/**
		 * ��ť0���¼�
		 */
		number_0.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				consumString = consume.getText().toString() + "0";
				consume.setText(consumString.toCharArray(), 0,
						consumString.length());
			}
		});

		/**
		 * ��ť1���¼�
		 */
		number_1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				consumString = consume.getText().toString() + "1";
				consume.setText(consumString.toCharArray(), 0,
						consumString.length());
			}
		});
		/**
		 * ��ť2���¼�
		 */
		number_2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				consumString = consume.getText().toString() + "2";
				consume.setText(consumString.toCharArray(), 0,
						consumString.length());
			}
		});
		/**
		 * ��ť3���¼�
		 */
		number_3.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				consumString = consume.getText().toString() + "3";
				consume.setText(consumString.toCharArray(), 0,
						consumString.length());
			}
		});
		/**
		 * ��ť4���¼�
		 */
		number_4.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				consumString = consume.getText().toString() + "4";
				consume.setText(consumString.toCharArray(), 0,
						consumString.length());
			}
		});
		/**
		 * ��ť5���¼�
		 */
		number_5.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				consumString = consume.getText().toString() + "5";
				consume.setText(consumString.toCharArray(), 0,
						consumString.length());
			}
		});
		/**
		 * ��ť6���¼�
		 */
		number_6.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				consumString = consume.getText().toString() + "6";
				consume.setText(consumString.toCharArray(), 0,
						consumString.length());
			}
		});
		/**
		 * ��ť7���¼�
		 */
		number_7.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				consumString = consume.getText().toString() + "7";
				consume.setText(consumString.toCharArray(), 0,
						consumString.length());
			}
		});
		/**
		 * ��ť8���¼�
		 */
		number_8.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				consumString = consume.getText().toString() + "8";
				consume.setText(consumString.toCharArray(), 0,
						consumString.length());
			}
		});
		/**
		 * ��ť9���¼�
		 */
		number_9.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				consumString = consume.getText().toString() + "9";
				consume.setText(consumString.toCharArray(), 0,
						consumString.length());
			}
		});
		/**
		 * ��ť.���¼�
		 */
		number_float.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (consumString.length() > 0 && (consumString.indexOf(".")  < 0)) {
					consumString = consume.getText().toString() + ".";
					consume.setText(consumString.toCharArray(), 0,
							consumString.length());
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
				Toast.makeText(JZ_Activity.this, "�ɹ�����һ��!",
						Toast.LENGTH_LONG).show();
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

}
