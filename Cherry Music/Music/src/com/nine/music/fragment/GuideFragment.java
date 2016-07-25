package com.nine.music.fragment;

import com.nine.music.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;


public class GuideFragment extends Fragment {
	private ImageView imageView_01;
	private ImageView imageView_02;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_guide, null);
		imageView_01 = (ImageView) view.findViewById(R.id.imageView1);
		imageView_02 = (ImageView) view.findViewById(R.id.imageView2);
		set_imageView();
		return view;
	}

	private void set_imageView() {
		int guide = getArguments().getInt("guide");
		switch (guide) {
		case 2:
			imageView_01.setImageResource(R.drawable.ic_intro_content_2);
			imageView_02.setImageResource(R.drawable.ic_intro_title_2);
			break;

		case 3:
			imageView_01.setImageResource(R.drawable.ic_intro_content_3);
			imageView_02.setImageResource(R.drawable.ic_intro_title_3);

			break;
		}
	}

}
