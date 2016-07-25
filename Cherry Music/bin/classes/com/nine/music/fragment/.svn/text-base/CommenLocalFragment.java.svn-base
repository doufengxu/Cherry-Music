package com.nine.music.fragment;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Toast;

import com.nine.music.R;
import com.nine.music.activity.Feedback_Activity;
import com.nine.music.activity.MainActivity;
import com.nine.music.activity.Singer_song_Activity;
import com.nine.music.adapter.CommenLocalFragmentAdapter;
import com.nine.music.adapter.Local_Singer_Adapter;
import com.nine.music.adapter.Local_Song_Adapter;
import com.nine.music.dbhelper.MyContentProvider;
import com.nine.music.service.MediaPlayService;

public class CommenLocalFragment extends Fragment implements
		LoaderCallbacks<Cursor> {
	private View view;

	private ViewPager viewPager_center_local;

	Local_Song_Adapter local_Song_Adapter;
	Local_Singer_Adapter local_Singer_Adapter;

	private Uri uri;
	private int page;
	private List<ListView> list = new ArrayList<ListView>();

	private ListView listView_music;
	private ListView listView_singer;
	private LoaderManager loaderManager;

	private MainActivity mainActivity;
	private SharedPreferences sharedPreferences;
	private Button song;
	private Button singer;
	// MediaPlayService mService;
	ImageView search;
	EditText search_text;
	ArrayList<String> list_seting = new ArrayList<String>();
	private ListView listView_seting;

	// private TextView textView_Empty;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// 设置按钮事件
		if (getArguments().getInt("page", 0) == 4) {

			view = inflater.inflate(R.layout.fragment_seting, container, false);
			list_seting.add("仅Wi-Fi联网");
			list_seting.add("清理缓存");
			list_seting.add("关于樱桃");
			list_seting.add("意见反馈");
			listView_seting = (ListView) view
					.findViewById(R.id.listView_seting);
			ArrayAdapter<String> adapter_seting = new ArrayAdapter<String>(
					getActivity(), R.layout.item_seting, R.id.textView_seting,
					list_seting);
			listView_seting.setAdapter(adapter_seting);
			seting_item_click();
		} else {
			view = inflater.inflate(R.layout.fragment_center_local, null);
			seturi();
			init();
			setButton();
			viewpage_slide();
			Search();
		}

		// Butclick();
		// add_listView();
		// set_viewPager();
		//
		// set();
		// play();
		return view;
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		if (loaderManager != null) {

			loaderManager.destroyLoader(0);
		}
	}

	// 设置的点击事件
	public void seting_item_click() {
		listView_seting.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				switch (arg2) {
				case 0:
					break;
				case 1:
					Toast.makeText(getActivity(), "缓存以清除", 0).show();
					break;
				case 2:

					break;
				case 3:
					Intent in = new Intent(getActivity(),
							Feedback_Activity.class);
					startActivity(in);
					break;

				default:
					break;
				}
			}
		});
	}

	// 搜索功能
	public void Search() {
		search.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String str = search_text.getText().toString();
				ArrayList<Map<String, String>> list = new ArrayList<Map<String, String>>();
				if (!str.equals("")) {
					for (int i = 0; i < MediaPlayService.list_music.size(); i++) {
						Map<String, String> map = new HashMap<String, String>();
						if (str.equals(MediaPlayService.list_music.get(i)
								.get("artist").toString())) {
							map.put("name", MediaPlayService.list_music.get(i)
									.get("title").toString());
							map.put("artist", MediaPlayService.list_music
									.get(i).get("artist").toString());
							map.put("song_path", MediaPlayService.list_music
									.get(i).get("path").toString());
							list.add(map);
						}
						if (str.equals(MediaPlayService.list_music.get(i).get(
								"title"))) {
							map.put("name", MediaPlayService.list_music.get(i)
									.get("title").toString());
							map.put("artist", MediaPlayService.list_music
									.get(i).get("artist").toString());
							map.put("song_path", MediaPlayService.list_music
									.get(i).get("path").toString());
							list.add(map);
						} else {
							Toast.makeText(getActivity(),
									search_text.getText().toString() + "不存在", 0)
									.show();
						}
					}
					Intent in = new Intent(getActivity(),
							Singer_song_Activity.class);
					in.putExtra("List", list);
					startActivity(in);
				}
			}
		});
	}

	private void setButton() {
		song = (Button) LocalFragment.view.findViewById(R.id.song);
		singer = (Button) LocalFragment.view.findViewById(R.id.singer);
		song.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				viewPager_center_local.setCurrentItem(0);
				song.setTextColor(getResources().getColor(R.color.my_color1));
				singer.setTextColor(getResources().getColor(R.color.my_color2));
				RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				lp.addRule(RelativeLayout.CENTER_HORIZONTAL,
						RelativeLayout.TRUE);
				song.setLayoutParams(lp);
				RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				lp1.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,
						RelativeLayout.TRUE);
				singer.setLayoutParams(lp1);
			}
		});
		singer.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				viewPager_center_local.setCurrentItem(1);
				singer.setTextColor(getResources().getColor(R.color.my_color1));
				song.setTextColor(getResources().getColor(R.color.my_color2));
				RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT,
						RelativeLayout.TRUE);
				song.setLayoutParams(lp);
				RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				lp1.addRule(RelativeLayout.CENTER_HORIZONTAL,
						RelativeLayout.TRUE);
				singer.setLayoutParams(lp1);
			}
		});
	}

	private void seturi() {
		page = getArguments().getInt("page");
		switch (page) {
		case 1:
			uri = Uri.parse(MyContentProvider.download);
			break;
		case 2:
			uri = Uri.parse(MyContentProvider.favourite);
			break;
		case 3:
			uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
			break;
		}
	}

	private void init() {
		sharedPreferences = getActivity().getSharedPreferences(
				"CenterMusicConfig", 0);
		// mainActivity = (MainActivity) getActivity();
		// mService = mainActivity.mService;
		viewPager_center_local = (ViewPager) view
				.findViewById(R.id.viewPager_center_local);
		setViewPager();

		loaderManager = getActivity().getSupportLoaderManager();
		loaderManager.initLoader(0, null, this);
		search = (ImageView) LocalFragment.view.findViewById(R.id.search);
		search_text = (EditText) LocalFragment.view
				.findViewById(R.id.search_text);
		// song = (Button) LocalFragment.view.findViewById(R.id.song);
		// singer = (Button) LocalFragment.view.findViewById(R.id.singer);
		// manager.initLoader(0, null, this);

	}

	private void setViewPager() {
		listView_music = new ListView(getActivity());
		listView_singer = new ListView(getActivity());
		list.add(listView_music);
		list.add(listView_singer);
		CommenLocalFragmentAdapter adapter = new CommenLocalFragmentAdapter(
				list);

		viewPager_center_local.setAdapter(adapter);

	}

	private void addfav(Cursor cursor) {
		ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < cursor.getCount(); i++) {
			cursor.moveToNext();
			String title = cursor.getString((cursor
					.getColumnIndex(MediaStore.Audio.Media.TITLE)));
			String path = cursor.getString(cursor
					.getColumnIndex(MediaStore.Audio.Media.DATA));
			String artist = cursor.getString(cursor
					.getColumnIndex(MediaStore.Audio.Media.ARTIST));
			String id = cursor.getString(cursor.getColumnIndex("music_id"));
			String image = cursor.getString(cursor.getColumnIndex("image"));

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("title", title);
			map.put("artist", artist);
			map.put("path", path);
			map.put("image", image);
			map.put("id", id);
			map.put("lrc", "");
			list.add(map);
		}
		MediaPlayService.list_music.clear();
		MediaPlayService.list_music.addAll(list);

		if (list.size() != 0) {
			Local_Song_Adapter local_Song_Adapter = new Local_Song_Adapter(
					getActivity(), list);
			listView_music.setAdapter(local_Song_Adapter);
			Local_Singer_Adapter local_Singer_Adapter = new Local_Singer_Adapter(
					getActivity(), list);
			listView_singer.setAdapter(local_Singer_Adapter);

			onclick();

		}
	}

	private void addMusicFromSDcard(Cursor cursor) {
		MediaPlayService.list_music.clear();
		for (int i = 0; i < cursor.getCount(); i++) {
			cursor.moveToNext();
			int isMusic = cursor.getInt(cursor
					.getColumnIndex(MediaStore.Audio.Media.IS_MUSIC));
			if (isMusic != 0) {
				String title = cursor.getString((cursor
						.getColumnIndex(MediaStore.Audio.Media.TITLE)));

				String path = cursor.getString(cursor
						.getColumnIndex(MediaStore.Audio.Media.DATA));
				String artist = cursor.getString(cursor
						.getColumnIndex(MediaStore.Audio.Media.ARTIST));
				long duration = cursor.getLong(cursor
						.getColumnIndex(MediaStore.Audio.Media.DURATION));
				long id = cursor.getLong(cursor
						.getColumnIndex(MediaStore.Audio.Media._ID));

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("title", title);
				map.put("path", path);
				map.put("artist", artist);
				map.put("duration", String.valueOf(duration));
				map.put("image", "null");
				map.put("id", String.valueOf(id));
				// File file = new File(
				// Environment
				// .getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC),
				// "123.lrc");
				// map.put("lrc", file.getAbsolutePath());
				map.put("lrc", "");
				MediaPlayService.list_music.add(map);

				// File file = new
				// File(Environment.getExternalStorageDirectory(),
				// "123.lrc");

				// MediaPlayService.list_music.add(map);
			}
		}
		System.out.println(MediaPlayService.list_music.size());
		if (MediaPlayService.list_music.size() != 0) {

			listSetAdapter();
		}

	}

	private void listSetAdapter() {
		local_Song_Adapter = new Local_Song_Adapter(getActivity(),
				MediaPlayService.list_music);
		local_Singer_Adapter = new Local_Singer_Adapter(getActivity(),
				MediaPlayService.list_music);
		listView_music.setAdapter(local_Song_Adapter);
		listView_singer.setAdapter(local_Singer_Adapter);
		onclick();

	}

	public void onclick() {
		listView_music.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				MediaPlayService.CurrentPosition = position;
				MediaPlayService.mService.next(position);
				sharedPreferences.edit()
						.putString("isPlayingMusic", "LocalMusic").commit();

				sharedPreferences.edit()
						.putInt("LocalListeningMusic", position).commit();
				sharedPreferences.edit().putString("music_state", "playing")
						.commit();

				// System.out.println(MediaPlayService.mediaPlayer == null);
				// System.out.println(MediaPlayService.mService == null);

				// }

			}
		});

		listView_singer.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				System.out.println("---------");
				Intent in = new Intent(getActivity(),
						Singer_song_Activity.class);
				ArrayList<Map<String, String>> li = new ArrayList<Map<String, String>>();
				String str = MediaPlayService.list_music.get(arg2)
						.get("artist").toString();
				for (int i = 0; i < MediaPlayService.list_music.size(); i++) {
					// System.out.println(str);
					Map<String, String> map = new HashMap<String, String>();
					if (MediaPlayService.list_music.get(i).get("artist")
							.toString().equals(str)) {

						map.put("name",
								MediaPlayService.list_music.get(i).get("title")
										.toString());
						map.put("song_path", MediaPlayService.list_music.get(i)
								.get("path").toString());
						map.put("artist", "");
						li.add(map);
					}
				}
				// // Log.e(">>>>>>>>>>>>>", li.toString());
				in.putExtra("List", li);
				startActivity(in);
			}
		});
	}

	@Override
	public void onLoaderReset(Loader<Cursor> arg0) {

	}

	// viewpage滑动事件
	public void viewpage_slide() {
		viewPager_center_local
				.setOnPageChangeListener(new OnPageChangeListener() {

					@Override
					public void onPageSelected(int arg0) {
						// TODO Auto-generated method stub
						switch (arg0) {
						case 0:
							song.setTextColor(getResources().getColor(
									R.color.my_color1));
							singer.setTextColor(getResources().getColor(
									R.color.my_color2));
							RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
									LayoutParams.WRAP_CONTENT,
									LayoutParams.WRAP_CONTENT);
							lp.addRule(RelativeLayout.CENTER_HORIZONTAL,
									RelativeLayout.TRUE);
							song.setLayoutParams(lp);
							RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(
									LayoutParams.WRAP_CONTENT,
									LayoutParams.WRAP_CONTENT);
							lp1.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,
									RelativeLayout.TRUE);
							singer.setLayoutParams(lp1);
							break;
						case 1:
							singer.setTextColor(getResources().getColor(
									R.color.my_color1));
							song.setTextColor(getResources().getColor(
									R.color.my_color2));
							RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(
									LayoutParams.WRAP_CONTENT,
									LayoutParams.WRAP_CONTENT);
							lp2.addRule(RelativeLayout.ALIGN_PARENT_LEFT,
									RelativeLayout.TRUE);
							song.setLayoutParams(lp2);
							RelativeLayout.LayoutParams lp3 = new RelativeLayout.LayoutParams(
									LayoutParams.WRAP_CONTENT,
									LayoutParams.WRAP_CONTENT);
							lp3.addRule(RelativeLayout.CENTER_HORIZONTAL,
									RelativeLayout.TRUE);
							singer.setLayoutParams(lp3);
							break;

						default:
							break;
						}
					}

					@Override
					public void onPageScrolled(int arg0, float arg1, int arg2) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onPageScrollStateChanged(int arg0) {
						// TODO Auto-generated method stub

					}
				});
	}

	@Override
	public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
		System.out.println("---------222------------------");
		CursorLoader loader = new CursorLoader(getActivity());
		loader.setUri(uri);
		return loader;
	}

	@Override
	public void onLoadFinished(Loader<Cursor> arg0, Cursor arg1) {
		// 有待优化
		System.out.println(arg1.getCount());

		if (arg1 != null && arg1.getCount() != 0) {
			System.out.println(page);
			if (page == 3) {
				addMusicFromSDcard(arg1);

			} else if (page == 2) {
				addfav(arg1);
			} else {

			}

		}

	}

	// 歌曲、歌手点击事件
	public void Butclick() {
		song.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				viewPager_center_local.setCurrentItem(0);
				song.setTextColor(getResources().getColor(R.color.my_color1));
				singer.setTextColor(getResources().getColor(R.color.my_color2));
				RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				lp.addRule(RelativeLayout.CENTER_HORIZONTAL,
						RelativeLayout.TRUE);
				song.setLayoutParams(lp);
				RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				lp1.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,
						RelativeLayout.TRUE);
				singer.setLayoutParams(lp1);
			}
		});
		singer.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				viewPager_center_local.setCurrentItem(1);
				singer.setTextColor(getResources().getColor(R.color.my_color1));
				song.setTextColor(getResources().getColor(R.color.my_color2));
				RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT,
						RelativeLayout.TRUE);
				song.setLayoutParams(lp);
				RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				lp1.addRule(RelativeLayout.CENTER_HORIZONTAL,
						RelativeLayout.TRUE);
				singer.setLayoutParams(lp1);
			}
		});
	}

}
