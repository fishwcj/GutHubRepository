package com.model.count;

import java.util.ArrayList;

import com.dao.TJ_DAO;
import com.inteface.Chart;

public class LineChart implements Chart{
	ArrayList<Float>  clothes_consume;
	ArrayList<Float>  eat_consume;
	ArrayList<Float>  house_consume;
	ArrayList<Float>  walk_consume;
	TJ_DAO tj_DAO;
	@Override
	public void draw() {
		// TODO Auto-generated method stub
		tj_DAO = new TJ_DAO();
		clothes_consume = tj_DAO.getconsume("´©½ð´÷Òø");
		eat_consume = tj_DAO.getconsume("¾Æ×ã·¹±¥");
		house_consume = tj_DAO.getconsume("Ë¹ÊÇÂªÊÒ");
		walk_consume = tj_DAO.getconsume("Ì¤ÆÆÌúÐ¬");
		
	}

}
