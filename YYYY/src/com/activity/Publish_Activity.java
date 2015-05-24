package com.activity;

import com.model.street.CreateMessageBean;
import com.model.street.PublishConnecter;
import com.yyyy.yyyy.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.bean.StreetMessageBean;
public class Publish_Activity extends Activity {
	private TextView fs;
	private TextView pjz;
	private EditText content;
	private EditText tag;
	private EditText price;
	private TextView getAddress;
	private String adress = "";
    private LocationClient locationClient = null;  
   private static final int UPDATE_TIME = 50000;  
	public static Activity publish_Activity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_publish);
		fs = (TextView) this.findViewById(R.id.fs);
		pjz = (TextView) this.findViewById(R.id.pjz);
		content = (EditText) this.findViewById(R.id.content);
		tag = (EditText) this.findViewById(R.id.tag);
		price = (EditText) this.findViewById(R.id.price);
		getAddress = (TextView) this.findViewById(R.id.myaddress);
		publish_Activity = this;

		/**
		 * 定位
		 */
		getAddress.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				locationClient = new LocationClient(Publish_Activity.this);  
		        //设置定位条件  
		        LocationClientOption option = new LocationClientOption();  
		        option.setOpenGps(true);                                //是否打开GPS  
		        option.setCoorType("bd09ll");                           //设置返回值的坐标类型。 
		        option.setIsNeedAddress(true);
		        option.setProdName("LocationDemo");                     //设置产品线名称。强烈建议您使用自定义的产品线名称，方便我们以后为您提供更高效准确的定位服务。  
		        option.setScanSpan(UPDATE_TIME);                        //设置定时定位的时间间隔。单位毫秒  
		        locationClient.setLocOption(option);  
		          
		        //注册位置监听器  
		        locationClient.registerLocationListener(new BDLocationListener() {  
		              
		            @Override  
		            public void onReceiveLocation(BDLocation location) {  
		                // TODO Auto-generated method stub  
		                if (location == null) {  
		                    return;  
		                }
		                System.out.println("地址为:"+location.getAddrStr() +"  街道：" +location.getStreet());
		                adress = location.getAddrStr();
		                getAddress.setText(adress);
		            }  
		              
		        });  
		        locationClient.start();
		        locationClient.requestLocation(); 
		        locationClient.stop();
			}
		});

		/**
		 * 发送事件
		 */
		fs.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String conntentString = content.getText().toString();
				String tagString = tag.getText().toString();
				float priceNumber = Float
						.parseFloat(price.getText().toString());
				if (conntentString.length() > 0) {
					CreateMessageBean creater = new CreateMessageBean();// 封装信息
					StreetMessageBean messageBean = creater.create(
							conntentString, tagString, priceNumber,adress);
					PublishConnecter connecter = new PublishConnecter();
					connecter.send(messageBean);
					content.setText("");
				} else {
					Toast.makeText(Publish_Activity.this, "亲，写点内容再发吧~!",
							Toast.LENGTH_LONG).show();
				}
			}
		});

		pjz.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Publish_Activity.this,
						Index_Activity.class);
				startActivity(intent);
			}
		});
	}
}
