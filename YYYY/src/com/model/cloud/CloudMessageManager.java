package com.model.cloud;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;

import com.activity.Index_Activity;
import com.mnitools.NetWorkCommunicate;
import com.model.user.IsLogin;
import com.yyyy.yyyy.R;

public class CloudMessageManager {
	private NotificationManager manager;

	public void getMessage(NotificationManager manager) {
		this.manager = manager;
		if (IsLogin.isLogin()) {
			new Thread() {
				@SuppressWarnings("unchecked")
				public void run() {
					NetWorkCommunicate communicater = new NetWorkCommunicate();
					String urlString = "http://192.168.191.1:8080/Bill/servlet/ClientMessageServlet";
					if (communicater.connect(urlString)){
						String id = IsLogin.getId();
						int _id = Integer.parseInt(id);
						System.out.println("id是" + id);
						if (communicater.sendObject(_id)) {
							ArrayList<HashMap<String, String>> message = (ArrayList<HashMap<String, String>>) communicater.receiveObject();
							if (message != null)
								for (int i = 0; i < message.size(); i++) {
									show(message.get(i).get("messagetype"),message.get(i).get("messagecontent"));
								}
						}
					}
				}
			}.start();
		}
	}

	@SuppressWarnings("deprecation")
	public void show(CharSequence contentTitle, CharSequence contentText) {
		Notification notification = new Notification(R.drawable.ic_launcher,
				"消息内容", System.currentTimeMillis());
		Intent notificationIntent = new Intent();
		PendingIntent contentIntent = PendingIntent.getActivity(
				Index_Activity.indexActivity, 0, notificationIntent, 0);
		notification.setLatestEventInfo(Index_Activity.indexActivity,
				contentTitle, contentText, contentIntent);
		this.manager.notify(1, notification);
		System.out.println("执行了消息通知栏");
	}
}
