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

			// ����

			StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
					.detectDiskReads().detectDiskWrites().detectNetwork()
					.penaltyLog().build());
			System.out.println("��ʼ����");
			URLConnection con = url.openConnection();
			System.out.println("������");
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setConnectTimeout(50000);
			System.out.println("��������");
			con.connect();
			System.out.println("���ӳɹ�!");
			// ��������
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(
					new BufferedOutputStream(con.getOutputStream()));
			objectOutputStream.writeObject(messageBean);// ������Ϣ����
			objectOutputStream.flush();
			objectOutputStream.close();
			System.out.println("���ͳɹ�");
			
			//��ȡ���ؽ����Ϣ
			ObjectInputStream objectInputStream = new ObjectInputStream(
					new BufferedInputStream(con.getInputStream()));
			Object obj = objectInputStream.readObject();
			String ok = (String)obj;
			objectInputStream.close();
			
			if(ok.equals("1")){
				//��ת��ȥˢ��
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
