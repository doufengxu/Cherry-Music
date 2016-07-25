package com.nine.music.activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
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
import com.nine.music.parser.Radio_item;
import com.nine.music.service.MediaPlayService;

public class Radio_Detailed_Activity extends Activity {
	HttpUtils httpUtils;
	BitmapUtils bitmapUtils;
	String url;
	String music_pic_path;
	List<Map<String, String>> list;
	ImageView music_back;
	ImageView music_name_option;
	ImageView music_pic;
	ImageView music_start_stop;
	TextView music_name;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.radio_detailed);
		Intent in = this.getIntent();
		String radio_id = in.getStringExtra("radio_id");
		String img = in.getStringExtra("img");
		int size = in.getIntExtra("size", 0);
		Log.e("<<<<<<<<<<<<<<<<<<", radio_id);
		Log.e("<<<<<<<<<<<<<<<<<<", img);
		Log.e("size<<<<<<<<<<<<<<<<<<", "" + size);
		url = "http://api.chrrs.com:8122/radiomusic?platform=Android4.4.2&version=1.0.3&market=2&model=vivo+Xplay3S&token=null&uuid=20102086&test=0&radio_id="
				+ radio_id;
		music_pic_path = "http://image01.cdn.chrrs.com" + img;
		inti();
		download();
		click();
	}

	public void inti() {
		// TODO Auto-generated method stub
		bitmapUtils = new BitmapUtils(getApplicationContext());
		httpUtils = new HttpUtils();
		list = new ArrayList<Map<String, String>>();
		music_back = (ImageView) findViewById(R.id.music_back);
		music_name = (TextView) findViewById(R.id.music_name);
		music_name_option = (ImageView) findViewById(R.id.music_name_option);
		music_pic = (ImageView) findViewById(R.id.music_pic);
		music_start_stop = (ImageView) findViewById(R.id.music_start_stop);
	}

	// 下载
	public void download() {
		httpUtils.send(HttpMethod.GET, url, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				list = Radio_item.getJson(arg0.result);
				Log.e("LLLLLLLLL", list.toString());
				music_name.setText(list.get(0).get("music_name"));
				// MediaPlayService.mService
				// .preparePlay("http://stream02.chrrs.com/6/6/0/30446660.mp3");
				play();
			}
		});
		bitmapUtils.display(music_pic, music_pic_path,
				new BitmapLoadCallBack<View>() {

					@Override
					public void onLoadCompleted(View arg0, String arg1,
							Bitmap arg2, BitmapDisplayConfig arg3,
							BitmapLoadFrom arg4) {
						// TODO Auto-generated method stub
						music_pic.setImageBitmap(arg2);
					}

					@Override
					public void onLoadFailed(View arg0, String arg1,
							Drawable arg2) {
						// TODO Auto-generated method stub

					}
				});
	}

	// 点击事件
	public void click() {
		music_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Radio_Detailed_Activity.this.finish();
			}
		});
		music_name_option.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ArrayList<String> li = new ArrayList<String>();
				for (int i = 0; i < list.size(); i++) {
					li.add(list.get(i).get("music_name"));
				}
				Log.e("name", li.toString());
				Intent in = new Intent(Radio_Detailed_Activity.this,
						Music_name_option_Activity.class);
				in.putExtra("music_name", li);
				startActivity(in);
			}
		});
		music_start_stop.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (MediaPlayService.mediaPlayer.isPlaying()) {
					MediaPlayService.mediaPlayer.pause();
				} else {
					try {
						MediaPlayService.mediaPlayer.start();
					} catch (IllegalStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		});
	}

	// 播放
	public void play() {
		final String music_id = list.get(0).get("music_id").toString();
		final String artist_id = list.get(0).get("artist_id").toString();
		String str = Constants.download[0];
		final String music_url = str + ReturnURL.retrnUrl(music_id, artist_id);
		MediaPlayService.mService.preparePlay(music_url);
		MediaPlayService.mediaPlayer.setOnErrorListener(new OnErrorListener() {

			@Override
			public boolean onError(MediaPlayer mp, int what, int extra) {
				// TODO Auto-generated method stub
				switch (what) {
				case -1004:
					for (int i = 1; i < list.size(); i++) {
						final String music_id1 = list.get(i).get("music_id")
								.toString();
						final String artist_id1 = list.get(i).get("artist_id")
								.toString();
						for (int j = 1; j < 3; j++) {
							String str1 = Constants.download[j];
							final String music_url1 = str1
									+ ReturnURL.retrnUrl(music_id1, artist_id1);
							MediaPlayService.mService.preparePlay(music_url1);
						}
					}
					break;

				default:
					break;
				}
				return false;
			}
		});
	}
	//停止
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		MediaPlayService.mediaPlayer.pause();
	}

}
