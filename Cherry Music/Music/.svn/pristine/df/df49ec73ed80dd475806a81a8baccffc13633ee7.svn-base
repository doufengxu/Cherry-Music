package com.nine.music.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nine.music.R;
import com.nine.music.adapter.FavouriteAdapter;
import com.nine.music.service.MediaPlayService;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.Audio.Media;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class FindFavouriteFragment extends Fragment {
	private View view;
	private ListView listView;
	private ContentResolver contentResolver;
	private Cursor cursor;
	private List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	private TextView textView_favourite;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.fragment_favourite_find, null);
		listView = (ListView) view.findViewById(R.id.listView_favouite);
		textView_favourite = (TextView) view
				.findViewById(R.id.textView_favourite);

		// 创建数据源
		contentResolver = getActivity().getContentResolver();

		cursor = contentResolver
				.query(Uri
						.parse("content://com.nine.music.dbhelper.MyContentProvider/favourite"),
						null, null, null, null);

		if (cursor != null && cursor.getCount() != 0) {
			list.clear();
			for (int i = 0; i < cursor.getCount(); i++) {
				cursor.moveToNext();
				String title = cursor.getString(cursor
						.getColumnIndex(MediaStore.Audio.Media.TITLE));
				String artist = cursor.getString(cursor
						.getColumnIndex(MediaStore.Audio.Media.ARTIST));
				String path = cursor.getString(cursor
						.getColumnIndex(MediaStore.Audio.Media.DATA));
				Map<String, String> map = new HashMap<String, String>();

				map.put("title", title);
				map.put("artist", artist);
				map.put("path", path);
				map.put("lrc", "");

				Log.d("abc", "favoirite--------->" + title);

				list.add(map);
			}

		}

		// 创建适配器
		FavouriteAdapter adapter = new FavouriteAdapter(list, getActivity());

		// 绑定适配器
		listView.setAdapter(adapter);

		// 设置无数据的时候显示
		listView.setEmptyView(textView_favourite);

		// 设置监听
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("title", list.get(position).get("title"));
				map.put("artist", list.get(position).get("artist"));
				map.put("path", list.get(position).get("path"));
				map.put("image", "");
				map.put("id", "");
				map.put("lrc", "");
				MediaPlayService.list_music.add(map);
				MediaPlayService.mService.preparePlay(list.get(position).get(
						"path"));
			}
		});

		return view;
	}

}
