package com.nine.music.adapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.NotificationManager;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadCallBack;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.view.annotation.event.OnGroupClick;
import com.nine.music.R;
import com.nine.music.config.RankDownLoad;
import com.nine.music.config.ReturnURL;
import com.nine.music.constants.Constants;
import com.nine.music.dbhelper.MyContentProvider;
import com.nine.music.utils.ImageUtils.RoundBitmap;

public class RankAdapter extends BaseExpandableListAdapter {
	// 一级数据源
	private String[] group_name = { "欧美排行榜", "日语排行榜", "韩语排行榜", "华语排行榜" };
	// 二级数据源
	private List<List<Map<String, String>>> list_detail = new ArrayList<List<Map<String, String>>>();
	private Context context;
	private BitmapUtils bitmapUtils;
	private HttpUtils httpUtils;
	private ContentResolver contentResolver;
	private NotificationManager manager;
	private NotificationCompat.Builder builder;
	public int i=0;
	public boolean isConnection;

	public RankAdapter(List<List<Map<String, String>>> list_details,
			Context contexts) {
		// TODO Auto-generated constructor stub
		this.list_detail = list_details;
		this.context = contexts;

		bitmapUtils = new BitmapUtils(context);
		httpUtils = new HttpUtils();
		contentResolver = contexts.getContentResolver();
		manager = (NotificationManager) context.getSystemService(contexts.NOTIFICATION_SERVICE);
	}

	/**
	 * 集合更新
	 * 
	 * @param lists
	 */
	public void addList(List<List<Map<String, String>>> list_details) {
		this.list_detail.addAll(list_details);
		isConnection = true;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return list_detail.get(groupPosition).get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childPosition;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return list_detail.get(groupPosition).size();
	}

	/**
	 * 获取一级标签内容
	 */
	@Override
	public Object getGroup(int position) {
		// TODO Auto-generated method stub
		return group_name[position];
	}

	/**
	 * 获取一级标签总数
	 */
	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return group_name.length;
	}

	/**
	 * 获取一级标签的ID
	 */
	@Override
	public long getGroupId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	/**
	 * 一级标签设置
	 */
	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		// 为视图指定布局
		convertView = LayoutInflater.from(context).inflate(
				R.layout.item_rank_title, null);
		TextView textView = (TextView) convertView
				.findViewById(R.id.textView_titleItem_Rank);
		textView.setText(group_name[groupPosition]);
		return convertView;
	}

	/**
	 * 二级标签设置
	 */
	@Override
	public View getChildView(final int groupPosition, final int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		convertView = LayoutInflater.from(context).inflate(
				R.layout.item_similar_play, null);
		final ImageView imageView = (ImageView) convertView
				.findViewById(R.id.imageView_pic_similar);
		TextView textView_song = (TextView) convertView
				.findViewById(R.id.textView_song_similar);
		TextView textView_artist = (TextView) convertView
				.findViewById(R.id.textView_artist_similar);
		final ImageView imageView_download = (ImageView) convertView
				.findViewById(R.id.imageView_download_similar);

		Log.d("JJ",
				"----------------?"
						+ Constants.IMAGEVIEW_PREFIX
						+ list_detail.get(groupPosition).get(childPosition)
								.get("cover_path"));

		// 网络异步加载图片
		bitmapUtils.display(imageView,
				Constants.IMAGEVIEW_PREFIX2
						+ list_detail.get(groupPosition).get(childPosition)
								.get("cover_path"),
				new BitmapLoadCallBack<ImageView>() {

					@Override
					public void onLoadCompleted(ImageView imageView,
							String uri, Bitmap bitmap,
							BitmapDisplayConfig config, BitmapLoadFrom from) {

						// 动画效果（淡入淡出）
						Animation animation = new AlphaAnimation(0, 1);
						animation.setDuration(1000);
						Bitmap bitmap2 = RoundBitmap.toRoundBitmap(bitmap);
						imageView.setImageBitmap(bitmap2);
						imageView.setAnimation(animation);
					}

					@Override
					public void onLoadFailed(ImageView arg0, String arg1,
							Drawable arg2) {
						// TODO Auto-generated method stub
						imageView.setImageResource(R.drawable.ic_launcher);
					}
				});

		imageView_download.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				// 获取歌曲信息
				String artist_id = list_detail.get(groupPosition)
						.get(childPosition).get("artist_id");
				String music_id = list_detail.get(groupPosition)
						.get(childPosition).get("music_id");
				String music_name = list_detail.get(groupPosition)
						.get(childPosition).get("music_name");
				String artist = list_detail.get(groupPosition)
						.get(childPosition).get("artist");
				String cover_path = list_detail.get(groupPosition)
						.get(childPosition).get("cover_path");

				if (Environment.getExternalStorageState().equals(
						Environment.MEDIA_MOUNTED)) {
					File file = Environment.getExternalStorageDirectory();
					if (!file.exists()) {
						file.mkdirs();
					}
					final String url = ReturnURL.retrnUrl(music_id, artist_id);
					final String path = file.getAbsolutePath() + File.separator
							+ "download" + File.separator + music_name + ".mp3";

					ContentValues values = new ContentValues();
					values.put(MediaStore.Audio.Media.TITLE, music_name);
					values.put(MediaStore.Audio.Media.DATA, path);
					values.put(MediaStore.Audio.Media.ARTIST, artist);
					values.put("music_id", music_id);
					values.put("image", cover_path);
					contentResolver.insert(
							Uri.parse(MyContentProvider.download), values);

					// 下载歌曲
					RankDownLoad.download(url, path,imageView_download, context, manager, music_name);
				}

				Toast.makeText(context, "" + music_id+"=="+music_name, Toast.LENGTH_SHORT)
						.show();

			}
		});

		textView_song.setText(list_detail.get(groupPosition).get(childPosition)
				.get("music_name"));
		textView_artist.setText(list_detail.get(groupPosition)
				.get(childPosition).get("artist"));

		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isChildSelectable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	


}
