package com.function;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.net.URLConnection;

import com.activity.Publish_Activity;
import com.activity.Street_Activity;

import android.content.Intent;
import android.os.StrictMode;

public class PublishConnecter {
	
	
	public void send(StreetMessageBean messageBean) {
		// TODO Auto-generated method stub
		
		URL url;
		try {
			url = new URL("http://192.168.191.1:8080/Bill/servlet/PublishServlet");

			// 链接

			StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
					.detectDiskReads().detectDiskWrites().detectNetwork()
					.penaltyLog().build());
			System.out.println("开始连接");
			URLConnection con = url.openConnection();
			System.out.println("打开连接");
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setConnectTimeout(50000);
			System.out.println("尝试连接");
			con.connect();
			System.out.println("链接成功!");
			// 发送数据
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(
					new BufferedOutputStream(con.getOutputStream()));
			objectOutputStream.writeObject(messageBean);// 发送消息数据
			objectOutputStream.flush();
			objectOutputStream.close();
			System.out.println("发送成功");
			
			//读取返回结果信息
			ObjectInputStream objectInputStream = new ObjectInputStream(
					new BufferedInputStream(con.getInputStream()));
			Object obj = objectInputStream.readObject();
			String ok = (String)obj;
			objectInputStream.close();
			
			if(ok.equals("1")){
				//跳转回去刷新
				Intent intent = new Intent(Publish_Activity.publish_Activity, Street_Activity.class);
				Publish_Activity.publish_Activity.startActivity(intent);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
