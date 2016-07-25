package com.nine.music.activity;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentFilter.MalformedMimeTypeException;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat.Builder;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RemoteViews;

import cn.sharesdk.demo.Application.MyApplication;

import com.nine.music.R;
import com.nine.music.config.NotiConfig;
import com.nine.music.fragment.FindFragment;
import com.nine.music.fragment.LocalFragment;
import com.nine.music.receive.MyBroadCastReceive;
import com.nine.music.service.MediaPlayService;
import com.nine.music.view.Main_buttom;

public class MainActivity extends FragmentActivity {
	private Main_buttom main_buttom;
	private LocalFragment fragment_left;

	private FragmentTransaction fragmentTransaction;

	public MediaPlayService mService;
	public ImageView imageView_play;
	public static SharedPreferences sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		set_imageView_transparent();
		init();

		set_main_buttom();
		set_imageView_play();

		// 创建通知栏
		iniNotification();

		// 注册广播
		Handler handlers = new Handler();
		MyBroadCastReceive receive = new MyBroadCastReceive(handlers);
		IntentFilter filter = new IntentFilter("2");
		registerReceiver(receive, filter);

	}

	private void set_imageView_play() {
		imageView_play.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				startActivity(new Intent(MainActivity.this, PlayActivity.class));
			}
		});

	}

	private void set_main_buttom() {
		main_buttom.linearLayout_left
				.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						Fragment fragment = getSupportFragmentManager()
								.findFragmentByTag("left");
						main_buttom.local_textview.setTextColor(getResources()
								.getColor(R.color.my_color3));
						main_buttom.local_pic
								.setImageResource(R.drawable.ic_tab_local_p);
						main_buttom.find_textview.setTextColor(getResources()
								.getColor(R.color.my_color4));
						main_buttom.find_pic
								.setImageResource(R.drawable.ic_tab_find);
						System.out.println(fragment == null);
						if (fragment == null) {
							LocalFragment fragment_left = new LocalFragment();
							getSupportFragmentManager()
									.beginTransaction()
									.replace(R.id.fragment_container,
											fragment_left, "left").commit();
						} else {
							System.out.println("-------------");
							getSupportFragmentManager().beginTransaction()
									.show(fragment).commit();
							Fragment fragment1 = getSupportFragmentManager()
									.findFragmentByTag("right");
							if (fragment1 != null) {
								getSupportFragmentManager().beginTransaction()
										.show(fragment1).commit();
							}
						}

					}
				});
		main_buttom.linearLayout_right
				.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						Fragment fragment = getSupportFragmentManager()
								.findFragmentByTag("right");
						// System.out.println(fragment == null);
						main_buttom.local_textview.setTextColor(getResources()
								.getColor(R.color.my_color4));
						main_buttom.local_pic
								.setImageResource(R.drawable.ic_tag_local);
						main_buttom.find_textview.setTextColor(getResources()
								.getColor(R.color.my_color3));
						main_buttom.find_pic
								.setImageResource(R.drawable.ic_tab_find_p);
						if (fragment == null) {

							FindFragment fragment_right = new FindFragment();

							getSupportFragmentManager()
									.beginTransaction()
									.replace(R.id.fragment_container,
											fragment_right, "right").commit();
						} else {

							getSupportFragmentManager().beginTransaction()
									.show(fragment).commit();
							Fragment fragment1 = getSupportFragmentManager()
									.findFragmentByTag("left");
							if (fragment1 != null) {
								getSupportFragmentManager().beginTransaction()
										.show(fragment1).commit();
							}

						}

					}
				});

	}

	private void set_imageView_transparent() {
		ImageView imageView_transparent = (ImageView) findViewById(R.id.imageView_transparent);
		float scale = this.getResources().getDisplayMetrics().density;
		int num = (int) (100 * scale + 0.5f);
		int height = getWindowManager().getDefaultDisplay().getHeight();
		LayoutParams layoutParams = imageView_transparent.getLayoutParams();
		layoutParams.height = height - num;
		imageView_transparent.setLayoutParams(layoutParams);

	}

	private void init() {

		startService(new Intent(this, MediaPlayService.class));
		sharedPreferences = getSharedPreferences("CenterMusicConfig", 0);
		imageView_play = (ImageView) findViewById(R.id.imageView_play);
		main_buttom = (Main_buttom) findViewById(R.id.main_buttom);
		// fragment_left = new LocalFragment();
		// fragment_right = new FindFragment();

		fragmentTransaction = getSupportFragmentManager().beginTransaction();
		// fragmentTransaction.replace(R.id.fragment_container, fragment_left);
		fragmentTransaction.add(R.id.fragment_container, new FindFragment(),
				"right").commit();

		// fragmentTransaction.commit();

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();

		stopService(new Intent(this, MediaPlayService.class));
		if (MediaPlayService.mediaPlayer != null) {
			System.out.println("---stopService-");

			if (MediaPlayService.mediaPlayer.isPlaying()) {

				MediaPlayService.mediaPlayer.stop();
				// mediaPlayer.pause();
				// mediaPlayer.seekTo(0);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			MediaPlayService.mediaPlayer.release();
			MediaPlayService.mediaPlayer = null;
			// System.out.println("-----mediaPlayer = null;------------");
		}
		sharedPreferences.edit().putString("isPlayingMusic", "").commit();

	}

	/**
	 * 注册广播
	 */
	private void iniNotification() {
		// TODO Auto-generated method stub
		NotificationManager manager = showCustomView();
		MyApplication.getInstance().notificationManager = manager;

	}

	/**
	 * 自定义通知栏
	 * 
	 * @return
	 */
	private NotificationManager showCustomView() {
		// TODO Auto-generated method stub
		RemoteViews remoteViews = new RemoteViews(getPackageName(),
				R.layout.notification_item);
		remoteViews.setTextViewText(R.id.textView_notification_title, "樱桃音乐");
		remoteViews
				.setTextViewText(R.id.textView_notification_artist, "未知艺术家 ");

		// 下一曲按钮
		Intent intent1 = new Intent("2");
		intent1.putExtra("action", NotiConfig.NEXT);
		PendingIntent intent_next = PendingIntent.getBroadcast(this, 1,
				intent1, 0);
		remoteViews.setOnClickPendingIntent(R.id.imageView_next_noti,
				intent_next);

		// 播放按钮
		Intent intent2 = new Intent("2");
		intent2.putExtra("action", NotiConfig.PLAY);
		PendingIntent intent_play = PendingIntent.getBroadcast(this, 2,
				intent2, 0);
		remoteViews.setOnClickPendingIntent(R.id.imageView_play_noti,
				intent_play);

		// 关闭按钮
		Intent intent3 = new Intent("2");
		intent3.putExtra("action", NotiConfig.CLOSE);
		PendingIntent intent_close = PendingIntent.getBroadcast(this, 3,
				intent3, 0);
		remoteViews.setOnClickPendingIntent(R.id.imageView_close_noti,
				intent_close);

		Builder builder = new Builder(MainActivity.this);
		builder.setContent(remoteViews).setSmallIcon(R.drawable.ic_launcher)
				.setOngoing(true).setTicker("樱桃音乐");

		Notification notifications = builder.build();

		MyApplication.getInstance().notification = notifications;
		NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

		manager.notify(1, notifications);
		return manager;
	}
}
