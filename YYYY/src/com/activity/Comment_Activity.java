package com.activity;

import java.util.ArrayList;
import java.util.Iterator;

import com.bean.CommentBean;
import com.bean.CommentBeanList;
import com.bean.StreetMessageBean;
import com.model.street.CommentBehavior;
import com.model.street.CreateCommentBean;
import com.model.street.StreetGUI;
import com.yyyy.yyyy.R;

import android.annotation.SuppressLint;
import android.app.Activity;
//import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Comment_Activity extends Activity {
	public static Activity comment_Activity;
	private StreetMessageBean messageBean;
	private TextView username;
	private TextView content;
	private TextView time;
	private TextView sy;
	private TextView zyj;
	private EditText editText;
	private LinearLayout linearLayout;
	private Button send;
	private int location;
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			return true;
		}
		return super.onKeyDown(keyCode, event);

	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_comment);
		comment_Activity = this;
		this.linearLayout = (LinearLayout)this.findViewById(R.id.root_c);
		username = (TextView)this.findViewById(R.id.name_y);
		content = (TextView)this.findViewById(R.id.message_c);
		time = (TextView)this.findViewById(R.id.time);
		editText = (EditText)this.findViewById(R.id.mycomment);
		send = (Button)this.findViewById(R.id.send);
		sy = (TextView)this.findViewById(R.id.sy_c);
		zyj = (TextView)this.findViewById(R.id.zyj_c);
		
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		location = bundle.getInt("location");
		messageBean = Street_Activity.MessageBeanlist.get(location);
		username.setText(messageBean.getUserName());
		content.setText(messageBean.getMessage());
		time.setText(messageBean.getDatetime());
		new Thread(new GetCommentsThread()).start();//开始加载网络数据
		
		send.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String mycomments = editText.getText().toString();
				if(mycomments.length() != 0){
				CreateCommentBean creater = new CreateCommentBean();
				CommentBean commentBean = creater.createBean(mycomments, messageBean);
				SendCommentsThread sender = new SendCommentsThread();
				sender.setCommentBean(commentBean);
				new Thread(sender).start();
				editText.setText("");
				InputMethodManager imm = (InputMethodManager) getSystemService(Comment_Activity.INPUT_METHOD_SERVICE);  
				imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS); 
				}else{
					Toast.makeText(Comment_Activity.this, "写点内容再发吧~", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		sy.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Comment_Activity.this,
						Index_Activity.class);
				startActivity(intent);
			}
		});
		
		zyj.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Comment_Activity.this,
						Street_Activity.class);
				startActivity(intent);
			}
		});
	}
	
	@SuppressLint("HandlerLeak")
	Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			System.out.println("进入了handler,开始处理...");
			CommentBeanList commentBeanList = (CommentBeanList)msg.obj;
			ArrayList<CommentBean> list = commentBeanList.getCommentBeans();
			Iterator<CommentBean> iterator = list.iterator();
			StreetGUI streetGUI = new StreetGUI(linearLayout);
			while(iterator.hasNext()){
				streetGUI.createCommentGUI(iterator.next());
			}
		};
	};
	
	public class GetCommentsThread implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			CommentBehavior communicater = new CommentBehavior();
			CommentBeanList list = communicater.getComments(messageBean.getMessageId());
			Message msg = Message.obtain();
			msg.obj = list;
			handler.sendMessage(msg);
		}
		
	}
	
	public class SendCommentsThread implements Runnable{

		private CommentBean commentBean = null;
		public void setCommentBean(CommentBean commentBean) {
			this.commentBean = commentBean;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			CommentBehavior communicater = new CommentBehavior();
			if(communicater.sendComment(commentBean)){//发送评论成功
				CommentBeanList commentBeanList = communicater.getComments(messageBean.getMessageId());//获取评论
				Message message = Message.obtain();
				message.obj = commentBeanList;
				handler.sendMessage(message);
			}
		}
		
	}
}
