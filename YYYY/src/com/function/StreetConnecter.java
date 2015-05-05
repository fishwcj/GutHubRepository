package com.function;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
//import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import com.activity.Street_Activity;
import com.bean.SrStreetBeanList;
import com.bean.StreetMessageBean;

import android.os.StrictMode;

//import android.os.StrictMode;

public class StreetConnecter{
	private String adrees = "�й�ʯ�ʹ�ѧ";
	
	public void connect() {
		// TODO Auto-generated method stub
		URL url;
		try {
			url = new URL("http://192.168.191.1:8080/Bill/servlet/StreetServlet");

			// ����

			StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
					.detectDiskReads().detectDiskWrites().detectNetwork()
					.penaltyLog().build());
			System.out.println("��ʼ����");
			URLConnection con = url.openConnection();
			System.out.println("������");
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setConnectTimeout(5000);
			System.out.println("��������");
			con.connect();
			System.out.println("���ӳɹ�!");
			// ��������
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(
					new BufferedOutputStream(con.getOutputStream()));
			objectOutputStream.writeObject(adrees);// ���͵�ַ
			objectOutputStream.flush();
			objectOutputStream.close();
			System.out.println("���ͳɹ�");
			
			//��ȡ���ؽ����Ϣ
			ObjectInputStream objectInputStream = new ObjectInputStream(
					new BufferedInputStream(con.getInputStream()));
			Object obj = objectInputStream.readObject();
			
			SrStreetBeanList streetBean = (SrStreetBeanList)obj;//ת��Ϊ���л�����
			ArrayList<StreetMessageBean> messageBeans = streetBean.getList();//��ȡ�б�
			System.out.println("�յ�������Ϣ");
			objectInputStream.close();
			
			//��ʾ������
			GUIManager guiManager = new GUIManager();
			guiManager.createStreetGUI(messageBeans);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			StreetGUI streetGUI = new StreetGUI(Street_Activity.rootlinearLayout);
			streetGUI.createErrorGUI();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
