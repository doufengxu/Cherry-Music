package com.nine.music.adapter;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class CommenLocalFragmentAdapter extends PagerAdapter {
	private List<ListView> list = new ArrayList<ListView>();

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}

	public Object instantiateItem(ViewGroup container, int position) {
		container.addView(list.get(position));
		return list.get(position);
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView(list.get(position));
		super.destroyItem(container, position, object);
	}

	public CommenLocalFragmentAdapter(List<ListView> list) {
		super();
		this.list = list;
	}

}
