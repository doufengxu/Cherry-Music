package com.nine.music.adapter;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class FindAdapter extends FragmentPagerAdapter {
	private List<Fragment> list;

	public FindAdapter(FragmentManager manager,List<Fragment> lists) {
		super(manager);
		// TODO Auto-generated constructor stub
		this.list = lists;
	}

	@Override
	public Fragment getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

}
