package com.nine.music.utils.listener;

import java.util.ArrayList;
import java.util.List;

import com.nine.music.R;
import com.nine.music.activity.PlayActivity;
import com.nine.music.adapter.SelectionAdapter;
import com.nine.music.fragment.CommenLocalFragment;
import com.nine.music.service.MediaPlayService;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

import android.widget.ImageView;
import android.widget.Toast;

public class MusicControlListener implements OnPageChangeListener {

	private ViewPager music_control;
	private int statenum = 0;
	public static List<ImageView> list_music_control = new ArrayList<ImageView>();
	private SharedPreferences sharedPreferences;
	private Context context;
	private int toast = 0;

	// public MediaPlayService mService;

	@Override
	public void onPageSelected(int arg0) {
		music_control.setCurrentItem(1);

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {

		if (arg1 < 0.7 && arg1 > 0.6) {
			list_music_control.get(1).setImageResource(
					R.drawable.theme_pink_controll_next);
			sharedPreferences.edit().putString("music_state", "playing")
					.commit();
			sharedPreferences.edit().putString("next", "nextDown").commit();

		}
		if (arg1 > 0.3 && arg1 < 0.4) {
			list_music_control.get(1).setImageResource(
					R.drawable.theme_pink_controll_prev);
			sharedPreferences.edit().putString("music_state", "playing")
					.commit();
			sharedPreferences.edit().putString("next", "nextUp").commit();

		}
		if (arg1 == 0) {
			System.out.println("------arg1 == 0---------");
			if (MediaPlayService.mService == null) {

			} else {
				String next = sharedPreferences.getString("next", "");
				int LocalListeningMusic = sharedPreferences.getInt(
						"LocalListeningMusic", 0);
				if (next.equals("nextDown")) {
					LocalListeningMusic++;
					if (LocalListeningMusic == MediaPlayService.list_music
							.size()) {
						LocalListeningMusic = 0;
					}
				}
				if (next.equals("nextUp")) {
					LocalListeningMusic--;
					if (LocalListeningMusic < 0) {
						LocalListeningMusic = MediaPlayService.list_music
								.size() - 1;
					}
				}
				sharedPreferences.edit()
						.putInt("LocalListeningMusic", LocalListeningMusic)
						.commit();
				Boolean isnext = MediaPlayService.mService
						.next(LocalListeningMusic);
				if (isnext) {
					list_music_control.get(1).setImageResource(
							R.drawable.theme_pink_controll_pause);
					sharedPreferences.edit()
							.putString("music_state", "playing").commit();

				} else {

					if (MediaPlayService.list_music.size() == 1
							&& MediaPlayService.mService != null
							&& MediaPlayService.mService.isPlay()) {
						list_music_control.get(1).setImageResource(
								R.drawable.theme_pink_controll_pause);
						sharedPreferences.edit()
								.putString("music_state", "playing").commit();

					} else {
						list_music_control.get(1).setImageResource(
								R.drawable.theme_pink_controll_play);
						sharedPreferences.edit()
								.putString("music_state", "pause").commit();

					}

				}

			}

		}

	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		statenum += arg0;

		if (arg0 == 1) {

			PlayActivity.viewPager.requestDisallowInterceptTouchEvent(true);
		}
		if (arg0 == 0) {
			if (statenum == 1) {
				judge();
			}
			statenum = 0;
			PlayActivity.viewPager.requestDisallowInterceptTouchEvent(false);
		}
	}

	protected void judge() {

		if (MediaPlayService.mediaPlayer == null) {
			Toast.makeText(context, "请选择音乐", 0).show();
		} else {

			if (MediaPlayService.mService.isPlay()) {
				//
				MediaPlayService.mService.pause();
				list_music_control.get(1).setImageResource(
						R.drawable.theme_pink_controll_play);
				if (SelectionAdapter.imageViews[0] != null) {
					SelectionAdapter.imageViews[0]
							.setImageResource(R.drawable.theme_pink_controll_play);
				}
				sharedPreferences.edit().putString("music_state", "pause")
						.commit();

			} else {
				if (MediaPlayService.list_music != null
						&& MediaPlayService.list_music.size() != 0) {

					MediaPlayService.mService.start();
					list_music_control.get(1).setImageResource(
							R.drawable.theme_pink_controll_pause);
					if (SelectionAdapter.imageViews[0] != null) {
						SelectionAdapter.imageViews[0]
								.setImageResource(R.drawable.theme_pink_controll_pause);
					}
					sharedPreferences.edit()
							.putString("music_state", "playing").commit();
				}
			}

		}

	}

	// }
	// }

	public MusicControlListener(ViewPager music_control, int statenum,
			Context context, List<ImageView> list_music_control,
			SharedPreferences sharedPreferences) {
		super();
		// this.mService = mService;
		this.music_control = music_control;
		this.statenum = statenum;
		this.list_music_control = list_music_control;
		this.sharedPreferences = sharedPreferences;
		this.context = context;
	}

}
