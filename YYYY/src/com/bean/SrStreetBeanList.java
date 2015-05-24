package com.bean;

import java.io.Serializable;
import java.util.ArrayList;

public class SrStreetBeanList implements Serializable  {
	/**
	 * –Ú¡–ªØ¿‡
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<StreetMessageBean> list;
	
	public ArrayList<StreetMessageBean> getList() {
		return list;
	}
	public void setList(ArrayList<StreetMessageBean> list) {
		this.list = list;
	}
}
