package com.nine.music.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.nine.music.R;

import android.R.color;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Local_Song_Adapter extends BaseAdapter {
	Context context;
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();;
	int num = 0;

	public Local_Song_Adapter(Context context, List<Map<String, Object>> list) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.list = list;
	}

	public Local_Song_Adapter(Context context, List<Map<String, Object>> list,
			int a) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.list = list;
		this.num = a;

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
					R.layout.item_local_song_listview, null);
			mHolder.song_pic = (ImageView) convertView
					.findViewById(R.id.song_pic);
			mHolder.song_name = (TextView) convertView
					.findViewById(R.id.song_name);
			mHolder.singer_name = (TextView) convertView
					.findViewById(R.id.singer_name);
			convertView.setTag(mHolder);
		} else {
			mHolder = (ViewHolder) convertView.getTag();
		}
		mHolder.song_name.setText(list.get(position).get("title").toString());
		mHolder.singer_name
				.setText(list.get(position).get("artist").toString());
		if (num == 1) {
			mHolder.song_name.setTextColor(Color.WHITE);
			mHolder.singer_name.setTextColor(Color.WHITE);
		}
		mHolder.song_pic
				.setImageResource(R.drawable.theme_pink_ic_default_song);
		return convertView;
	}

	public class ViewHolder {
		ImageView song_pic;
		TextView song_name;
		TextView singer_name;
	}
}
