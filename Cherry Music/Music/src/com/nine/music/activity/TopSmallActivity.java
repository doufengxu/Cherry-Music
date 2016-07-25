package com.nine.music.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.nine.music.R;
import com.nine.music.adapter.TopSmallAdapter;
import com.nine.music.constants.Constants;
import com.nine.music.parser.SelectionAnalysis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TopSmallActivity extends Activity {
	private ImageView imageView_back;
	private TextView textView_title;
	private TextView textView_detail;
	private ListView listView;
	private String data;
	private String state;
	private HttpUtils httpUtils;
	private List<Map<String, String>> list_data;
	private SelectionAnalysis analysis;
	private Map<String, String> map_pic;
	private BitmapUtils bitmapUtils;
	private ImageView imageView_TopSmall_Detail;
	private TopSmallAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_topsmall_detail);

		Intent intent = getIntent();
		// data = intent.getStringExtra("data");
		state = intent.getStringExtra("state");
		Log.e("JJ", "---------download----------" + data);

		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		imageView_back = (ImageView) findViewById(R.id.imageView_LeftDetail_back);
		// textView_title = (TextView) findViewById(R.id.textView_title_left);
		// textView_detail = (TextView)
		// findViewById(R.id.textView_aritst_right);
		listView = (ListView) findViewById(R.id.listView_TopDetail_LeftAndRight);
		imageView_TopSmall_Detail = (ImageView) findViewById(R.id.imageView_TopSmall_Detail);

		httpUtils = new HttpUtils();
		list_data = new ArrayList<Map<String, String>>();
		analysis = new SelectionAnalysis();
		map_pic = new HashMap<String, String>();
		bitmapUtils = new BitmapUtils(this);

		// 回退按钮
		imgBack();

		// 设置textView
		// setTextView();

		// 初始化ListView
		iniListview();

	}

	private void setTextView() {
		// TODO Auto-generated method stub
		if (state.equals("left")) {
			textView_title.setText("音乐人专栏");
			textView_detail.setText("Column Musician");
		} else {
			textView_title.setText("乐以群分");
			textView_detail.setText("Music Genre");
		}
	}

	private void iniListview() {

		// 数据源
		httpUtils.send(HttpMethod.GET, Constants.SECLECTION,
				new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						// TODO Auto-generated method stub
						map_pic = analysis.getLeftPic(responseInfo.result, state);
						bitmapUtils.display(imageView_TopSmall_Detail, Constants.IMAGEVIEW_PREFIX+map_pic.get("img"));
						
						list_data = analysis.getLeftDetail(responseInfo.result, state);
						adapter.addList(list_data);
						adapter.notifyDataSetChanged();
					}
				});
		// 适配器
		adapter = new TopSmallAdapter(list_data, this);
		// 绑定
		listView.setAdapter(adapter);
		// 监听

	}

	/**
	 * 回退按钮监听
	 */
	private void imgBack() {
		// TODO Auto-generated method stub
		imageView_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				TopSmallActivity.this.finish();

			}
		});
	}

}
