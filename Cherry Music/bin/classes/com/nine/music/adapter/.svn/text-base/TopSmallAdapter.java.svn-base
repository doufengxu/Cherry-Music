package com.nine.music.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.nine.music.R;
import com.nine.music.constants.Constants;
import com.nine.music.parser.SelectionAnalysis;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class TopSmallAdapter extends BaseAdapter {
	private List<Map<String, String>> list;
	private Context context;
	private BitmapUtils bitmapUtils;
	private String state;
	private HttpUtils httpUtils;
	private SelectionAnalysis analysis;

	public TopSmallAdapter(List<Map<String, String>> lists, Context contexts) {
		// TODO Auto-generated constructor stub
		this.list = lists;
		this.context = contexts;
		bitmapUtils = new BitmapUtils(context);
		analysis = new SelectionAnalysis();
	}

	public void addList(List<Map<String, String>> lists) {
		this.list.clear();
		this.list.addAll(lists);

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
		ViewHolder holder;

		if (converView == null) {
			holder = new ViewHolder();
			converView = LayoutInflater.from(context).inflate(
					R.layout.item_top_pic_detail, null);
			holder.imageView = (ImageView) converView
					.findViewById(R.id.imageView_TopSmall_detail);
			converView.setTag(holder);
		} else {
			holder = (ViewHolder) converView.getTag();
		}
		bitmapUtils.display(holder.imageView, Constants.IMAGEVIEW_PREFIX
				+ list.get(position).get("cover_img"));

		return converView;
	}

	class ViewHolder {
		ImageView imageView;
	}

}
