package com.nine.music.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

public class FindTopAdapter extends PagerAdapter {

	private List<ImageView> list = new ArrayList<ImageView>();
	private Context context;

	public FindTopAdapter(List<ImageView> lists, String datas, Context contexts) {
		// TODO Auto-generated constructor stub
		// this.list = lists;
		this.context = contexts;
	}

	public void addList(ImageView list_itm) {
		this.list.add(list_itm);
		Log.d("JJ", "----------->>" + list.size());
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return (arg0 == arg1);
	}

	@Override
	public Object instantiateItem(ViewGroup container, final int position) {
		container.addView(list.get(position));
		list.get(position).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
//				 TODO Auto-generated method stub
//				 Intent intent = new Intent(context,TopDetailActivity.class);
//				 List<Map<String, String>> list = analysis.getTopJson(data);
//				 if (position == 2) {
//				 intent.putExtra("url", list.get(2).get("img"));
//				 intent.putExtra("title", "白桦");
//				 } else if ((position == 3)) {
//				 intent.putExtra("url", list.get(3).get("img"));
//				 intent.putExtra("title", "亲密爱人");
//				 }else {
//				 intent.putExtra("url", "http://www.baidu.com");
//				 intent.putExtra("title", "百度");
//				 }
//				
//				 context.startActivity(intent);
				Toast.makeText(context, position, Toast.LENGTH_SHORT).show();
			}
		});
		return list.get(position);
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		container.removeView(list.get(position));
	}

}
