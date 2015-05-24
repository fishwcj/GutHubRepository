package com.function;

import java.util.ArrayList;
import java.util.Iterator;

import com.activity.Street_Activity;
import com.bean.StreetMessageBean;

public class GUIManager {
	
	/**
	 * ���ѽ�ˢ������
	 * @param list	���ѽ�״̬��Ϣ�б�
	 */
	public void createStreetGUI(ArrayList<StreetMessageBean> list) {
		StreetGUI guiCreater = new StreetGUI(Street_Activity.rootlinearLayout);
		Iterator<StreetMessageBean> iterator = list.iterator();
		while (iterator.hasNext()) {
			guiCreater.createGUI(iterator.next());
		}
	}
}
