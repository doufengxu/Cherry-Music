package com.nine.music.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nine.music.R;
import com.nine.music.adapter.Local_Singer_Adapter.ViewHolder;

public class Singer_song_Adapter extends BaseAdapter {
	Context context;
	List<Map<String, String>> list = new ArrayList<Map<String, String>>();

	public Singer_song_Adapter(Context context, List<Map<String, String>> list) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.list = list;
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
		ViewHolder mHolder=null;
		if(convertView==null){
			mHolder = new ViewHolder();
			convertView=LayoutInflater.from(context).inflate(R.layout.item_singer_song_listview, null);
			mHolder.song_pic=(ImageView) convertView.findViewById(R.id.song_picture);
			mHolder.song_name=(TextView) convertView.findViewById(R.id.song_title);
			mHolder.song_artist=(TextView) convertView.findViewById(R.id.song_artist);
			convertView.setTag(mHolder);
		}
		else {
			mHolder=(ViewHolder) convertView.getTag();
		}
		mHolder.song_pic.setImageResource(R.drawable.theme_pink_ic_default_song);
		mHolder.song_name.setText(list.get(position).get("name").toString());
		if(list.get(position).get("artist").toString().equals(""))
		{
			mHolder.song_artist.setVisibility(View.INVISIBLE);
		}
		else {
			mHolder.song_artist.setText(list.get(position).get("artist").toString());
		}
		return convertView;
	}

	class ViewHolder {
		ImageView song_pic;
		TextView song_name;
		TextView song_artist;
	}
}
