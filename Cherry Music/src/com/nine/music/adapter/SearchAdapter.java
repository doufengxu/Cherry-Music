package com.nine.music.adapter;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.nine.music.R;
import com.nine.music.constants.Constants;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Environment;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SearchAdapter extends BaseAdapter {
	private List<Map<String, String>> list;
	private Context context;
	private HttpUtils httpUtils;
	private NotificationManager manager;

	public void addList(List<Map<String, String>> lists) {
		list.addAll(lists);
		httpUtils = new HttpUtils();
	}

	public SearchAdapter(List<Map<String, String>> lists, Context contexts) {
		// TODO Auto-generated constructor stub
		this.list = lists;
		this.context = contexts;
		manager = (NotificationManager) context.getSystemService(contexts.NOTIFICATION_SERVICE);
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
	public View getView(final int position, View converView, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if (converView == null) {
			holder = new ViewHolder();
			converView = LayoutInflater.from(context).inflate(
					R.layout.item_search, null);
			holder.textView_title = (TextView) converView
					.findViewById(R.id.textView_title_search);
			holder.textView_artist = (TextView) converView
					.findViewById(R.id.textView_artist_search);
			holder.imageView_download = (ImageView) converView
					.findViewById(R.id.imageView_download_search);
			converView.setTag(holder);
		} else {
			holder = (ViewHolder) converView.getTag();
		}
		Log.d("abc",
				"=========已执行=========" + list.get(position).get("SONGNAME"));
		holder.textView_title.setText(list.get(position).get("SONGNAME"));
		holder.textView_artist.setText(list.get(position).get("ARTIST"));
		holder.imageView_download.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				httpUtils.send(HttpMethod.GET, Constants.SEARCH1_DOWNLOAD1
						+ list.get(position).get("MUSICRID")
						+ Constants.SEARCH1_DOWNLOAD2,
						new RequestCallBack<String>() {

							@Override
							public void onFailure(HttpException arg0,
									String arg1) {
								// TODO Auto-generated method stub

							}

							@Override
							public void onSuccess(
									ResponseInfo<String> responseInfo) {
								// TODO Auto-generated method stub
								if (Environment.getExternalStorageState()
										.equals(Environment.MEDIA_MOUNTED)) {
									File file = Environment
											.getExternalStorageDirectory();
									if (!file.exists()) {
										file.mkdirs();
									}
									String path = file.getAbsolutePath()
											+ File.separator
											+ "download"
											+ File.separator
											+ list.get(position)
													.get("SONGNAME") + ".mp3";
									httpUtils.download(responseInfo.result, path, new RequestCallBack<File>() {
										
										public void onLoading(long total, long current, boolean isUploading) {
											NotificationCompat.Builder builder = new NotificationCompat.Builder(
													context);
											builder.setSmallIcon(R.drawable.ic_logo_text);
											builder.setContentTitle(list.get(position)
													.get("SONGNAME"));
											int currents = (int) (current / (float) total * 100);

											builder.setProgress(100, currents, false);
											builder.setContentText(currents + "%");
											manager.notify(2, builder.build());

										};
										
										@Override
										public void onSuccess(ResponseInfo<File> arg0) {
											// TODO Auto-generated method stub
											
										}
										
										@Override
										public void onFailure(HttpException arg0, String arg1) {
											// TODO Auto-generated method stub
											
										}
									});
									
								}
							}
						});
			}
		});

		return converView;
	}

	class ViewHolder {
		TextView textView_title;
		TextView textView_artist;
		ImageView imageView_download;
	}

}
