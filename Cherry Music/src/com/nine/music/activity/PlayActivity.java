package com.nine.music.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.ImageView;

import com.nine.music.R;
import com.nine.music.adapter.CentrePlayingAdapter;
import com.nine.music.fragment.CenterMusicInfoFragment;
import com.nine.music.fragment.CenterMusicListFragment;
import com.nine.music.fragment.CenterPlayMusicFragment;
import com.nine.music.fragment.CommenLocalFragment;
import com.nine.music.service.MediaPlayService;

public class PlayActivity extends FragmentActivity {
	public static ViewPager viewPager;
	private List<Fragment> list = new ArrayList<Fragment>();
	private ImageView imageView_point;

	public SharedPreferences sharedPreferences;
	public List<Map<String, Object>> music_list;
	public int localListeningMusic;
	public MediaPlayService mService;

	// private

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_play);

		getInformation();

		init();
		add_Fragment();
		set_viewPager();

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		sharedPreferences.edit().putBoolean("open", false).commit();
	}

	private void getInformation() {
		sharedPreferences = getSharedPreferences("CenterMusicConfig", 0);
		sharedPreferences.edit().putBoolean("open", true).commit();
		String isPlayingMusic = sharedPreferences.getString("isPlayingMusic",
				"");
		if (isPlayingMusic.equals("LocalMusic")) {
			music_list = MediaPlayService.list_music;
			localListeningMusic = sharedPreferences.getInt(
					"LocalListeningMusic", 0);

		}

	}

	private void set_viewPager() {
		CentrePlayingAdapter adapter = new CentrePlayingAdapter(
				getSupportFragmentManager(), list);
		viewPager.setAdapter(adapter);
		viewPager.setCurrentItem(1);
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				imageView_point.setImageLevel(arg0);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});

	}

	private void add_Fragment() {
		CenterMusicInfoFragment centerMusicInfoFragment = new CenterMusicInfoFragment();
		CenterMusicListFragment centerMusicListFragment = new CenterMusicListFragment();
		CenterPlayMusicFragment centerPlayMusicFragment = new CenterPlayMusicFragment();

		list.add(centerMusicInfoFragment);
		list.add(centerPlayMusicFragment);
		list.add(centerMusicListFragment);

	}

	private void init() {
		viewPager = (ViewPager) findViewById(R.id.viewPager_play);
		imageView_point = (ImageView) findViewById(R.id.imageView_point);
		imageView_point.setImageLevel(1);
	}

}
