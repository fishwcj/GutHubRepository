package com.inteface;

import android.widget.Button;

public interface IInputCheck {
	abstract void setLisener_number(Button button,String number);//键入0~9是检测
	abstract void setLisener_float(Button button,String point);//键入.时检测
	abstract void setLisener_clear(Button button);//键入清空时检测
	abstract boolean check(String consumeString);//检测是否合法的输入
	abstract void setViewString(String string);//设置字符串
}
