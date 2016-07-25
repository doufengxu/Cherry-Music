package com.nine.music.adapter;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MusicControlAdapter extends PagerAdapter {

	private List<ImageView> list_music_control = new ArrayList<ImageView>();

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list_music_control.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		container.addView(list_music_control.get(position));
		return list_music_control.get(position);

	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView(list_music_control.get(position));
		// super.destroyItem(container, position, object);
	}

	public MusicControlAdapter(List<ImageView> list_music_control) {
		super();
		this.list_music_control = list_music_control;
	}

}
