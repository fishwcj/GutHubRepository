package com.model.user;

import com.activity.Index_Activity;
import com.dao.basic.SQLString;

public class IsLogin {
	public static boolean isLogin() {
		boolean tag = false;
		String sql = SQLString.getIsLogin_Is();
		int islogin = Index_Activity.basicDAO.selectInt(sql);
		tag = (islogin == 1) ? true : false;
		return tag;
	}
}
