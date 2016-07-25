package com.nine.music.adapter;

import java.util.List;
import java.util.Map;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadCallBack;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;
import com.nine.music.R;
import com.nine.music.constants.Constants;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class RadioAdapter extends BaseAdapter {
	private List<Map<String, String>> list;
	private Context context;
	private BitmapUtils bitmapUtils;

	public RadioAdapter(List<Map<String, String>> lists, Context contexts) {
		this.list = lists;
		this.context = contexts;
		bitmapUtils = new BitmapUtils(context);

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
	public View getView(int position, View coverView, ViewGroup viewGroup) {
		// TODO Auto-generated method stub
		final ViewHolder holder;

		if (coverView == null) {
			holder = new ViewHolder();
			coverView = LayoutInflater.from(context).inflate(
					R.layout.item_radio_find, null);
			holder.imageView = (ImageView) coverView
					.findViewById(R.id.imageView_detail_radio);

			coverView.setTag(holder);
		} else {
			holder = (ViewHolder) coverView.getTag();
		}

		// 下载图片
		bitmapUtils.display(holder.imageView, Constants.IMAGEVIEW_PREFIX
				+ list.get(position).get("img"),
				new BitmapLoadCallBack<ImageView>() {

					@Override
					public void onLoadCompleted(ImageView container,
							String uri, Bitmap bitmap,
							BitmapDisplayConfig config, BitmapLoadFrom from) {

						// 动画效果（淡入淡出）
						Animation animation = new AlphaAnimation(0, 1);
						animation.setDuration(1000);

						holder.imageView.setImageBitmap(bitmap);
						holder.imageView.setAnimation(animation);

						Log.d("main", "----RadioAdapter------>>" + uri);

					}

					@Override
					public void onLoadFailed(ImageView container, String uri,
							Drawable drawable) {
						// TODO Auto-generated method stub
						holder.imageView
								.setImageResource(R.drawable.ic_launcher);

					}
				});

		return coverView;
	}

	class ViewHolder {
		public ImageView imageView;
	}
}
