package com.nine.music.adapter;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.BaseAdapter;
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
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.nine.music.R;
import com.nine.music.config.ReturnURL;
import com.nine.music.constants.Constants;
import com.nine.music.parser.SelectionAnalysis;
import com.nine.music.service.MediaPlayService;

public class SelectionAdapter extends BaseAdapter {
	private List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	private Context context;
	private BitmapUtils bitmapUtils;
	private HttpUtils httpUtils;
	private List<Map<String, String>> list_SongDetail;
	private SelectionAnalysis analysis;

	public static ImageView[] imageViews = new ImageView[1];

	int aa;
	boolean bb = true;
	int flag = 0;
	ImageView imageView;

	public SelectionAdapter(Context contexts, List<Map<String, String>> lists) {
		this.list = lists;
		this.context = contexts;
		bitmapUtils = new BitmapUtils(context);
		httpUtils = new HttpUtils();
		list_SongDetail = new ArrayList<Map<String, String>>();
		analysis = new SelectionAnalysis();
	}

	public void addList(List<Map<String, String>> lists) {
		list.addAll(lists);
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
	public View getView(final int position, View converView, ViewGroup viewGroup) {
		final ViewHolder viewHolder;

		// if (converView == null) {
		viewHolder = new ViewHolder();

		converView = LayoutInflater.from(context).inflate(
				R.layout.item_selection, null);
		viewHolder.imageView_detail = (ImageView) converView
				.findViewById(R.id.imageView_Main_Selection);
		viewHolder.imageView_play = (ImageView) converView
				.findViewById(R.id.imageView_MainPlay_Selection);
		viewHolder.textView_listen_count = (TextView) converView
				.findViewById(R.id.textView_listenCount_Selection);

		viewHolder.imageView_play.setTag(position);
		viewHolder.imageView_play.setId(position);
		converView.setTag(viewHolder);
		// } else {
		// viewHolder = (ViewHolder) converView.getTag();
		// }

		// 图片下载
		bitmapUtils.display(viewHolder.imageView_detail,
				Constants.IMAGEVIEW_PREFIX + list.get(position).get("img"),
				new BitmapLoadCallBack<ImageView>() {

					@Override
					public void onLoadCompleted(ImageView container,
							String uri, Bitmap bitmap,
							BitmapDisplayConfig config, BitmapLoadFrom from) {
						Log.d("main", "---------->>" + uri);

						// 动画效果（淡入淡出）
						Animation animation = new AlphaAnimation(0, 1);
						animation.setDuration(1000);

						viewHolder.imageView_detail.setImageBitmap(bitmap);
						viewHolder.imageView_detail.setAnimation(animation);
					}

					@Override
					public void onLoadFailed(ImageView container, String uri,
							Drawable drawable) {
						// TODO Auto-generated method stub
						viewHolder.imageView_detail
								.setImageResource(R.drawable.ic_launcher);
					}
				});

		// 播放按钮
		imageView = (ImageView) converView.findViewWithTag(aa);

		viewHolder.imageView_play.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				if (bb) {
					bb = false;
					aa = (Integer) viewHolder.imageView_play.getTag();
					imageViews[0] = viewHolder.imageView_play;

					isonclick(position, viewHolder);

				} else {
					int cc = (Integer) viewHolder.imageView_play.getTag();

					if (cc == aa) {
						System.out.println(flag);
					} else {
						System.out.println("-----");
						imageViews[0]
								.setImageResource(R.drawable.theme_pink_controll_play);
						flag = 0;

					}

					// imageView.setTag(viewHolder.imageView_play);
					imageViews[0] = viewHolder.imageView_play;
					aa = (Integer) viewHolder.imageView_play.getTag();
					isonclick(position, viewHolder);

				}

			}

			public void isonclick(final int position,
					final ViewHolder viewHolder) {
				if (flag == 0) {

					for (int i = 0; i < Constants.download.length; i++) {

						httpUtils.send(HttpMethod.GET,
								Constants.SECLECTION_DETAIL
										+ list.get(position).get("best_id"),
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
										list_SongDetail = analysis
												.getSong(responseInfo.result);
										ArrayList<String> list = new ArrayList<String>();

										String music_id = list_SongDetail.get(
												(int) getItemId(position)).get(
												"music_id");
										String artist_id = list_SongDetail.get(
												(int) getItemId(position)).get(
												"artist_id");
										final String url = ReturnURL.retrnUrl(
												music_id, artist_id);
										String artist = list_SongDetail.get(
												(int) getItemId(position)).get(
												"artist");
										String music_name = list_SongDetail
												.get((int) getItemId(position))
												.get("music_name");
										String cover_path = Constants.IMAGEVIEW_PREFIX2
												+ list_SongDetail
														.get((int) getItemId(position))
														.get("cover_path");

										final Map<String, Object> map = new HashMap<String, Object>();
										map.put("artist", artist);
										map.put("title", music_name);
										map.put("image_path", cover_path);
										map.put("path", url);
										map.put("image", "1");
										System.out.println("=====url======="
												+ url);
										map.put("lrc", "");
										map.put("id", music_id);

										MediaPlayService.list_music.clear();
										MediaPlayService.list_music.add(map);
										MediaPlayService.CurrentPosition = 0;
										MediaPlayService.mService
												.preparePlay(Constants.download[0]
														+ url);
										MediaPlayService.mediaPlayer
												.setOnErrorListener(new OnErrorListener() {

													@Override
													public boolean onError(
															MediaPlayer arg0,
															int what, int extra) {

														switch (what) {
														case -1004:
															MediaPlayService.mService
																	.preparePlay(Constants.download[1]
																			+ url);

															break;

														default:
															break;
														}

														return false;
													}
												});
										if (MediaPlayService.mService.mediaPlayer
												.isPlaying()) {
											viewHolder.imageView_play
													.setImageResource(R.drawable.theme_pink_controll_pause);
										}

										Toast.makeText(context, "" + url,
												Toast.LENGTH_SHORT).show();
									}
								});
					}
					flag++;
				} else if (flag == 1) {
					if (MediaPlayService.mediaPlayer.isPlaying()) {
						MediaPlayService.mediaPlayer.pause();

						viewHolder.imageView_play
								.setImageResource(R.drawable.theme_pink_controll_play);
					} else {
						MediaPlayService.mediaPlayer.start();

						viewHolder.imageView_play
								.setImageResource(R.drawable.theme_pink_controll_pause);

					}

				}
			}
		});

		// 在线听人数
		viewHolder.textView_listen_count.setText(list.get(position).get(
				"listen_count"));

		return converView;
	}

	public void intUrl(String url, Map<String, Object> map) {

		try {
			URL text_url = new URL(Constants.download[0] + url);

			HttpURLConnection connection = (HttpURLConnection) text_url
					.openConnection();
			connection.setRequestMethod("GET");

			if (connection.getResponseCode() == 200) {
				MediaPlayService.list_music.clear();
				MediaPlayService.list_music.add(map);

				MediaPlayService.mService.preparePlay(Constants.download[0]
						+ url);
			} else {
				URL text_url2 = new URL(Constants.download[1] + url);
				HttpURLConnection connection2 = (HttpURLConnection) text_url2
						.openConnection();
				connection.setRequestMethod("GET");
				if (connection2.getResponseCode() == 200) {
					MediaPlayService.list_music.clear();
					MediaPlayService.list_music.add(map);

					MediaPlayService.mService.preparePlay(Constants.download[1]
							+ url);
				} else {
					URL text_url3 = new URL(Constants.download[2] + url);
					HttpURLConnection connection3 = (HttpURLConnection) text_url3
							.openConnection();
					connection.setRequestMethod("GET");
					if (connection3.getResponseCode() == 200) {
						MediaPlayService.list_music.clear();
						MediaPlayService.list_music.add(map);

						MediaPlayService.mService
								.preparePlay(Constants.download[2] + url);
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	class ViewHolder {
		private ImageView imageView_detail;
		private ImageView imageView_play;
		private TextView textView_listen_count;
	}
}
