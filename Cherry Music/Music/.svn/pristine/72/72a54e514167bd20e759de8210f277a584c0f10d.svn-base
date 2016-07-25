package com.nine.music.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.nine.music.R;
import com.nine.music.adapter.Local_Song_Adapter;
import com.nine.music.service.MediaPlayService;
import com.nine.music.utils.listener.MusicControlListener;

public class CenterMusicListFragment extends Fragment {
	private TextView textView_musicCount;
	public ListView listView_musicList;
	private View view;
	LinearLayout layout;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_center_music_list, null);
		// getActivity().findViewById(R.id.t)
		init();
		return view;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}

	private void init() {
		// TODO Auto-generated method stub
		textView_musicCount = (TextView) view
				.findViewById(R.id.textView_musicCount_List_Fragment);
		listView_musicList = (ListView) view
				.findViewById(R.id.ListView_CenterMusicListFragment);
		// layout = (LinearLayout)
		// view.findViewById(R.id.listView_Info_Fragment);

		iniListView();

	}

	private void iniListView() {

		if (MediaPlayService.list_music != null
				&& MediaPlayService.list_music.size() != 0) {

			Local_Song_Adapter adapter = new Local_Song_Adapter(getActivity(),
					MediaPlayService.list_music, 1);
			listView_musicList.setAdapter(adapter);
			// layout.addView(listView);
			listView_musicList
					.setOnItemClickListener(new OnItemClickListener() {

						@Override
						public void onItemClick(AdapterView<?> parent,
								View view, int position, long id) {

							SharedPreferences sharedPreferences = getActivity()
									.getSharedPreferences("CenterMusicConfig",
											0);
							MediaPlayService.mService.next(position);
							sharedPreferences.edit()
									.putString("isPlayingMusic", "LocalMusic")
									.commit();

							MediaPlayService.CurrentPosition = position;
							sharedPreferences.edit()
									.putInt("LocalListeningMusic", position)
									.commit();
							sharedPreferences.edit()
									.putString("music_state", "playing")
									.commit();
							MusicControlListener.list_music_control
									.get(1)
									.setImageResource(
											R.drawable.theme_pink_controll_pause);
							SharedPreferences preferences = getActivity()
									.getSharedPreferences("CenterMusicConfig",
											0);
							preferences.edit()
									.putString("music_state", "playing")
									.commit();

						}
					});

			// listView_musicList.setAdapter(adapter);

		}

	}
}
