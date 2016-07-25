package com.nine.music.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.nine.music.R;

public class LocalFragment extends Fragment {
	static View view;
	private ImageView tag_local;
	private ImageView tag_fav;
	private ImageView tag_phone;
	private ImageView tag_setting;
	private android.support.v4.app.FragmentTransaction fragmentTransaction;
	private Button song;
	private Button singer;
	ImageView search;
	EditText search_text;
	TextView line1;
	TextView line2;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_local, null);
		init();
		set_Imageview();
		return view;
	}

	private void set_Imageview() {
		tag_local.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				new_fragment(1);
				show();
				tag_local
						.setImageResource(R.drawable.theme_pink_ic_tag_local_p);
				tag_fav.setImageResource(R.drawable.ic_tag_fav);
				tag_phone.setImageResource(R.drawable.ic_tag_phone);
				tag_setting.setImageResource(R.drawable.ic_tag_setting);
			}

		});
		tag_fav.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				new_fragment(2);
				show();
				tag_local.setImageResource(R.drawable.ic_tag_local);
				tag_fav.setImageResource(R.drawable.theme_pink_ic_tag_fav_p);
				tag_phone.setImageResource(R.drawable.ic_tag_phone);
				tag_setting.setImageResource(R.drawable.ic_tag_setting);

			}
		});
		tag_phone.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				new_fragment(3);
				show();
				tag_local.setImageResource(R.drawable.ic_tag_local);
				tag_fav.setImageResource(R.drawable.ic_tag_fav);
				tag_phone
						.setImageResource(R.drawable.theme_pink_ic_tag_phone_p);
				tag_setting.setImageResource(R.drawable.ic_tag_setting);
			}
		});
		tag_setting.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				new_fragment(4);
				notshow();
				tag_local.setImageResource(R.drawable.ic_tag_local);
				tag_fav.setImageResource(R.drawable.ic_tag_fav);
				tag_phone.setImageResource(R.drawable.ic_tag_phone);
				tag_setting
						.setImageResource(R.drawable.theme_pink_ic_tag_setting_p);
			}
		});

	}

	protected void new_fragment(int page) {

		CommenLocalFragment fragment_music = new CommenLocalFragment();
		Bundle bundle = new Bundle();
		bundle.putInt("page", page);
		fragment_music.setArguments(bundle);

		fragmentTransaction = getActivity().getSupportFragmentManager()
				.beginTransaction();
		fragmentTransaction.replace(R.id.linearLayout_music_container,
				fragment_music);
		fragmentTransaction.commit();

		// Fragment fragment = null;
		//
		// switch (page) {
		// case 1:
		// fragment = getActivity().getSupportFragmentManager()
		// .findFragmentByTag("1");
		// break;
		// case 2:
		// fragment = getActivity().getSupportFragmentManager()
		// .findFragmentByTag("2");
		// break;
		// case 3:
		// fragment = getActivity().getSupportFragmentManager()
		// .findFragmentByTag("3");
		// break;
		// case 4:
		// fragment = getActivity().getSupportFragmentManager()
		// .findFragmentByTag("4");
		// break;
		//
		// }
		// if (fragment == null) {
		// getActivity().getSupportFragmentManager().beginTransaction()
		// .show(fragment);
		//
		// } else {
		//
		// CommenLocalFragment fragment_music = new CommenLocalFragment();
		// Bundle bundle = new Bundle();
		// bundle.putInt("page", page);
		// fragment_music.setArguments(bundle);
		// getActivity()
		// .getSupportFragmentManager()
		// .beginTransaction()
		// .add(R.id.linearLayout_music_container, fragment_music,
		// String.valueOf(page));
		//
		// }

	}

	private void init() {
		tag_local = (ImageView) view.findViewById(R.id.tag_local);
		tag_fav = (ImageView) view.findViewById(R.id.tag_fav);
		tag_phone = (ImageView) view.findViewById(R.id.tag_phone);
		tag_setting = (ImageView) view.findViewById(R.id.tag_setting);
		new_fragment(1);

	}

	// 再显示
	public void show() {
		song = (Button) view.findViewById(R.id.song);
		song.setVisibility(View.VISIBLE);
		singer = (Button) view.findViewById(R.id.singer);
		singer.setVisibility(View.VISIBLE);
		search = (ImageView) view.findViewById(R.id.search);
		search.setVisibility(View.VISIBLE);
		search_text = (EditText) view.findViewById(R.id.search_text);
		search_text.setVisibility(View.VISIBLE);
		line1 = (TextView) view.findViewById(R.id.line1);
		line1.setVisibility(View.VISIBLE);
		line2 = (TextView) view.findViewById(R.id.line2);
		line2.setVisibility(View.VISIBLE);
	}

	// 隐藏
	public void notshow() {
		song = (Button) view.findViewById(R.id.song);
		song.setVisibility(View.INVISIBLE);
		singer = (Button) view.findViewById(R.id.singer);
		singer.setVisibility(View.INVISIBLE);
		search = (ImageView) view.findViewById(R.id.search);
		search.setVisibility(View.INVISIBLE);
		search_text = (EditText) view.findViewById(R.id.search_text);
		search_text.setVisibility(View.INVISIBLE);
		line1 = (TextView) view.findViewById(R.id.line1);
		line1.setVisibility(View.INVISIBLE);
		line2 = (TextView) view.findViewById(R.id.line2);
		line2.setVisibility(View.INVISIBLE);
	}

}
