package com.nine.music.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nine.music.R;

public class Local_Singer_Adapter extends BaseAdapter {
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	Context context;
	int i, j;

	public Local_Singer_Adapter(Context context, List<Map<String, Object>> list) {
		this.context = context;
		this.list = list;
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder mHolder = null;
		if (convertView == null) {
			mHolder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.item_local_singer_listview, null);
			mHolder.singer_pic = (ImageView) convertView
					.findViewById(R.id.singer_pic);
			mHolder.singer_name = (TextView) convertView
					.findViewById(R.id.singer_name);
			convertView.setTag(mHolder);
		} else {
			mHolder = (ViewHolder) convertView.getTag();
		}
		mHolder.singer_pic
				.setImageResource(R.drawable.theme_pink_ic_default_singer);
		mHolder.singer_name
				.setText(list.get(position).get("artist").toString());

		return convertView;
	}

	class ViewHolder {
		ImageView singer_pic;
		TextView singer_name;
	}
}
