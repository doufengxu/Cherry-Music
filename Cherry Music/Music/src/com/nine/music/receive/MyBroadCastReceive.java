package com.nine.music.receive;

import cn.sharesdk.demo.Application.MyApplication;

import com.nine.music.R;
import com.nine.music.config.NotiConfig;
import com.nine.music.service.MediaPlayService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

public class MyBroadCastReceive extends BroadcastReceiver {
	private Handler handler;
	private MyApplication application = MyApplication.getInstance();

	public MyBroadCastReceive(Handler handlers) {
		this.handler = handlers;
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		if (intent.getAction().equals("url")) {
			String url = intent.getStringExtra("url");
			Message message = Message.obtain();
			message.what = 001;
			message.obj = url;
			handler.sendMessage(message);
		} else if (intent.getAction().equals("2")) {
			String action = intent.getStringExtra("action");
			if (action.equals(NotiConfig.NEXT)) {
				MediaPlayService.mService
						.next(++MediaPlayService.CurrentPosition);
				application.notification.contentView.setTextViewText(
						R.id.textView_notification_title,
						MediaPlayService.list_music
								.get(MediaPlayService.CurrentPosition)
								.get("title").toString());
				application.notification.contentView.setTextViewText(
						R.id.textView_notification_artist,
						MediaPlayService.list_music
								.get(MediaPlayService.CurrentPosition)
								.get("artist").toString());
				application.notificationManager.notify(1,
						application.notification);
		
			} else if (action.equals(NotiConfig.PLAY)) {
				if (MediaPlayService.mediaPlayer.isPlaying()) {
					MediaPlayService.mediaPlayer.pause();
					application.notification.contentView.setImageViewResource(
							R.id.imageView_play_noti, R.drawable.ic_noti_play);
					application.notificationManager.notify(1,
							application.notification);
				} else {
					MediaPlayService.mediaPlayer.start();
					application.notification.contentView.setImageViewResource(
							R.id.imageView_play_noti, R.drawable.ic_noti_pause);
					application.notificationManager.notify(1,
							application.notification);
				}

			} else if (action.equals(NotiConfig.CLOSE)) {
				application.notificationManager.cancelAll();
				MediaPlayService.mediaPlayer.stop();
				MediaPlayService.mService.stopSelf();
				System.exit(0);
			}
		}

	}

}
