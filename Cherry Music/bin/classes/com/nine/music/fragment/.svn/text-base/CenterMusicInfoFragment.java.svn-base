package com.nine.music.fragment;

import java.util.ArrayList;
import java.util.Map;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.nine.music.R;
import com.nine.music.adapter.Local_Song_Adapter;
import com.nine.music.parser.CenterMusicInfoAnalusis;
import com.nine.music.service.MediaPlayService;

public class CenterMusicInfoFragment extends Fragment {
	private TextView textView_artist;
	private ImageView imageView_info;
	private ListView listView_song;
	private View view;
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_center_music_info, null);

		// ��ʼ�����
		init();

		return view;
	}

	private void setlistview(String name) {
		HttpUtils http = new HttpUtils();
		String url = "http://search.kuwo.cn/r.s?all="
				+ name
				+ "&ft=music&itemset=web_2013&client=kt&pn=0&rn=5&rformat=json&encoding=utf8";
		http.send(HttpMethod.GET, url, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				System.out.println("---onFailure---");
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				list = CenterMusicInfoAnalusis.Josn(arg0.result);
				if (list != null) {
					Local_Song_Adapter adapter = new Local_Song_Adapter(
							getActivity(), list, 1);
					listView_song.setAdapter(adapter);
				}

			}

		});

	}

	/**
	 * ��ʼ�����
	 */
	private void init() {
		// TODO Auto-generated method stub
		textView_artist = (TextView) view
				.findViewById(R.id.textView_Artist_Info_Fragment);
		if (MediaPlayService.list_music != null
				&& MediaPlayService.list_music.size() != 0) {

			String name = MediaPlayService.list_music
					.get(MediaPlayService.CurrentPosition).get("artist")
					.toString();
			textView_artist.setText(name);
			setlistview(name);

		}

		imageView_info = (ImageView) view
				.findViewById(R.id.imageView_ArtistInfo_Info_Fragment);
		listView_song = (ListView) view
				.findViewById(R.id.listView_Info_Fragment);

		// ��ʼ��ListView;
		iniListView();
	}

	/**
	 * ��ʼ��ListView
	 */
	private void iniListView() {
		// �������Դ

		// ����������

		// ��������

		// ���ü�����

	}

}
