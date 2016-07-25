package cn.sharesdk.demo.Application;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationManager;

public class MyApplication extends Application {
	private static MyApplication myApplication=new MyApplication();
	
	public NotificationManager notificationManager;
	public Notification notification;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		myApplication = this;
	}

	public static MyApplication getInstance() {
		return myApplication;
	}

}
