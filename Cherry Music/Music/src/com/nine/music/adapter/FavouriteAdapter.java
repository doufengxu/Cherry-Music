package com.nine.music.adapter;

import java.util.List;
import java.util.Map;

import org.w3c.dom.ls.LSSerializer;

import com.nine.music.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class FavouriteAdapter extends BaseAdapter {

	private List<Map<String, String>> list;
	private Context context;

	public FavouriteAdapter(List<Map<String, String>> lists, Context contexts) {
		// TODO Auto-generated constructor stub
		this.list = lists;
		this.context = contexts;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View converView, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder;
		if (converView == null) {
			viewHolder = new ViewHolder();
			converView = LayoutInflater.from(context).inflate(
					R.layout.item_favourite, null);
			viewHolder.textView_title = (TextView) converView
					.findViewById(R.id.textView_title_favourite);
			viewHolder.textView_artist = (TextView) converView
					.findViewById(R.id.textView_artist_favourite);
			converView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) converView.getTag();
		}
		if(list.size()!=0){
			viewHolder.textView_title.setText(list.get(position).get("title"));
			viewHolder.textView_artist.setText(list.get(position).get("artist"));
			
		}
		return converView;
	}

	class ViewHolder {
		TextView textView_title;
		TextView textView_artist;
	}

}
