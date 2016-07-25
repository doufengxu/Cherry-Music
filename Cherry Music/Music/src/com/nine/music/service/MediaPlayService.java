package com.nine.music.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import android.app.Application;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.MergeCursor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import cn.sharesdk.demo.Application.MyApplication;

import com.nine.music.R;
import com.nine.music.activity.MainActivity;
import com.nine.music.activity.PlayActivity;
import com.nine.music.constants.Constants;
import com.nine.music.fragment.CenterPlayMusicFragment;
import com.nine.music.lrc.LrcHandle;
import com.nine.music.utils.listener.MusicControlListener;

public class MediaPlayService extends Service {
	// private Binder MediaPlayBinder = new MediaPlayBinder();
	public static MediaPlayer mediaPlayer;
	private SharedPreferences sharedPreferences;
	public static MediaPlayService mService = new MediaPlayService();
	public static ArrayList<Map<String, Object>> list_music = new ArrayList<Map<String, Object>>();
	private Random random;
	public static int CurrentPosition = 0;
	public static int lrc;
	private List<Long> mTimeList;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		System.out.println("----onCreate-------");
		mediaPlayer = new MediaPlayer();
		mediaPlayer.setOnPreparedListener(new OnPreparedListener() {

			@Override
			public void onPrepared(MediaPlayer arg0) {
				// TODO Auto-generated method stub
				Log.d("abc", "--------------------------------------->>"
						+ list_music.size());

				MyApplication.getInstance().notification.contentView
						.setTextViewText(R.id.textView_notification_title,
								list_music
										.get(MediaPlayService.CurrentPosition)
										.get("title").toString());
				MyApplication.getInstance().notification.contentView
						.setTextViewText(R.id.textView_notification_artist,
								list_music
										.get(MediaPlayService.CurrentPosition)
										.get("artist").toString());
				MyApplication.getInstance().notification.contentView
						.setImageViewResource(R.id.imageView_play_noti,
								R.drawable.ic_noti_pause);
				MyApplication.getInstance().notificationManager.notify(1,
						MyApplication.getInstance().notification);

			}
		});

		mediaPlayer.setOnCompletionListener(new OnCompletionListener() {

			@Override
			public void onCompletion(MediaPlayer mp) {
				sharedPreferences = getSharedPreferences("CenterMusicConfig", 0);
				int num = sharedPreferences.getInt("music_mode", 0);
				int LocalListeningMusic = sharedPreferences.getInt(
						"LocalListeningMusic", 0);
				System.out
						.println(LocalListeningMusic + "--------------------");

				switch (num) {
				case 0:
					LocalListeningMusic++;
					if (LocalListeningMusic == MediaPlayService.list_music
							.size()) {
						LocalListeningMusic = 0;
					}
					sharedPreferences.edit()
							.putInt("LocalListeningMusic", LocalListeningMusic)
							.commit();

					break;
				case 1:
					int num2 = list_music.size();
					if (random == null) {
						random = new Random();
					}
					LocalListeningMusic = random.nextInt(num);
					sharedPreferences.edit()
							.putInt("LocalListeningMusic", LocalListeningMusic)
							.commit();

					break;
				case 2:
					break;

				}
				if (!(MediaPlayService.list_music.size() == 1)) {

					next(LocalListeningMusic);
				}

			}
		});
	}

	public boolean next(int num) {
		// sharedPreferences = getSharedPreferences("CenterMusicConfig", 0);
		// if (list_music.size() == 1) {
		//
		// } else
		if (list_music != null && list_music.size() != 0) {

			if (mediaPlayer == null) {
				mediaPlayer = new MediaPlayer();
			}
			if (mediaPlayer.isPlaying()) {
				mediaPlayer.pause();
				mediaPlayer.seekTo(0);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// String path = null;
			// int LocalListeningMusic = sharedPreferences.getInt(
			// "LocalListeningMusic", 0);
			String path = list_music.get(num).get("path").toString();
			if (CenterPlayMusicFragment.textView_musicName != null
					&& list_music != null) {

				CenterPlayMusicFragment.textView_musicName.setText(list_music
						.get(num).get("title").toString());
				CenterPlayMusicFragment.textView_musicName.setText(list_music
						.get(num).get("artist").toString());
			}

			if (MediaPlayService.list_music.size() != 0) {
				String aa = MediaPlayService.list_music.get(CurrentPosition)
						.get("image").toString();
				if (aa != null && aa.equals("1")) {
					preparePlay(Constants.download[0]
							+ list_music.get(CurrentPosition).get("path")
									.toString());
				} else {

					preparePlay(path);
				}
			}

			return true;

		}
		return false;

	}

	/**
	 * ׼������
	 * 
	 * @param path
	 *            �����ֵ�ַ
	 */
	public void preparePlay(String path) {
		// ��λ
		mediaPlayer.reset();
		try {

			// ��ȡ���Դ����ַ��
			mediaPlayer.setDataSource(path);
			// ׼��
			mediaPlayer.prepare();
			// �����
			mediaPlayer.start();

			boolean open = MainActivity.sharedPreferences.getBoolean("open",
					false);
			System.out.println(open);
			if (open) {
				CenterPlayMusicFragment.handler.sendEmptyMessage(1);
			}

			lrc = 0;
			LrcHandle lrcHandler = new LrcHandle();
			// lrcHandler.readLRC();
			String path2 = MediaPlayService.list_music
					.get(MediaPlayService.CurrentPosition).get("lrc")
					.toString();
			System.out.println("lrccccccc" + path2);
			if (path2 == null || path2.equals("")) {
				// System.out.println("333");
			} else {

				lrcHandler.readLRC(path);
				mTimeList = lrcHandler.getTime();
				new Thread(new Runnable() {
					// int i = 0;

					@Override
					public void run() {
						while (mediaPlayer.isPlaying()) {

							try {
								Thread.sleep(mTimeList.get(lrc + 1)
										- mTimeList.get(lrc));
							} catch (InterruptedException e) {
							}
							lrc++;
							// if (lrc == mTimeList.size() - 1) {
							// mediaPlayer.stop();
							// break;
							// }
						}
					}
				}).start();

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * ֹͣ����
	 */
	public void stop() {
		mediaPlayer.stop();
	}

	/**
	 * ��ͣ����
	 */
	public void pause() {
		mediaPlayer.pause();
	}

	/**
	 * ��ת���
	 * 
	 * @param position
	 */
	public void setSeekBarPosition(int position) {
		mediaPlayer.seekTo(position);
	}

	/**
	 * ��ø��������
	 */
	public int getMaxSize() {
		return mediaPlayer.getDuration();
	}

	public int getCurrentPosition() {
		return mediaPlayer.getCurrentPosition();
	}

	/**
	 * �ж��Ƿ����ڲ���
	 * 
	 * @return
	 */
	public boolean isPlay() {
		// System.out.println("---------------" + mediaPlayer == null);
		return mediaPlayer.isPlaying();
		// return false;

	}

	@Override
	public void onRebind(Intent intent) {
		// TODO Auto-generated method stub
		super.onRebind(intent);
	}

	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		return super.onUnbind(intent);
	}

	/**
	 * �󶨷��ط������
	 */
	// @Override
	// public IBinder onBind(Intent arg0) {
	// // TODO Auto-generated method stub
	// return MediaPlayBinder;
	// }

	/**
	 * ����ģʽ�����ط������
	 * 
	 * @author Professor_DXL
	 * 
	 */
	public class MediaPlayBinder extends Binder {
		public MediaPlayService getService() {
			return MediaPlayService.this;

		}
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		System.out.println("--------88--------");
		// boolean isPlay = isPlay();
		// if (isPlay) {
		// mediaPlayer.pause();
		// mediaPlayer.seekTo(0);
		// try {
		// Thread.sleep(500);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		// }
		//
		// preparePlay(MediaPlayService.list_music
		// .get(intent.getIntExtra("position", 0)).get("path").toString());

		return super.onStartCommand(intent, flags, startId);

	}

	public void start() {
		mediaPlayer.start();
		CenterPlayMusicFragment.handler.sendEmptyMessage(3);

	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		System.out.println("----onDestroy2222-----");

	}

	public void OnlineLinstenner(String uri) {
		Uri uris = Uri.parse(uri);
		mediaPlayer.reset();
		try {
			mediaPlayer.setDataSource(this, uris);
			mediaPlayer.prepare();
			mediaPlayer.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
