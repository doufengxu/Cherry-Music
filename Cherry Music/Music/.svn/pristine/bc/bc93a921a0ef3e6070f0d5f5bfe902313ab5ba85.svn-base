package com.nine.music.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.nine.music.R;
import com.nine.music.adapter.SearchAdapter;
import com.nine.music.constants.Constants;
import com.nine.music.parser.SeacrchAnalysis;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class SearchActivity extends Activity {
	private ImageView imageView_back;
	private EditText editText_input;
	private ImageView imageView_search;
	private ListView listView;
	private TextView textView;
	private HttpUtils httpUtils;
	private List<Map<String, String>> list_message;
	private SearchAdapter adapter;
	private ActionBar actionBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);

		init();
	}

	/**
	 * 初始化数据
	 */
	private void init() {
		// TODO Auto-generated method stub
		imageView_back = (ImageView) findViewById(R.id.imageView_back_search);
		editText_input = (EditText) findViewById(R.id.editText_input_search);
		imageView_search = (ImageView) findViewById(R.id.imageView_sure_search);
		listView = (ListView) findViewById(R.id.listView_result_search);
		textView = (TextView) findViewById(R.id.textView_empty_search);
		actionBar = getActionBar();
		actionBar.hide();

		list_message = new ArrayList<Map<String, String>>();
		httpUtils = new HttpUtils();

		// 回退按钮
		back();

		// 搜索按钮
		search();

		// 初始化ListView
		iniListView();

	}

	/**
	 * 初始化ListView
	 */
	private void iniListView() {

		// 构建适配器
		adapter = new SearchAdapter(list_message, this);
		// 绑定适配器
		listView.setAdapter(adapter);
		listView.setEmptyView(textView);
		// 设置监听

	}

	private void search() {
		// TODO Auto-generated method stub
		imageView_search.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String message = editText_input.getText().toString();
				httpUtils.send(HttpMethod.GET, Constants.SEARCH1 + message
						+ Constants.SEARCH2, new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						// TODO Auto-generated method stub
						list_message = SeacrchAnalysis
								.getJsons(responseInfo.result);
						Log.d("abc", "" + list_message);
						adapter.addList(list_message);
						adapter.notifyDataSetChanged();
					}
				});

			}
		});
	}

	private void back() {
		// TODO Auto-generated method stub
		imageView_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				SearchActivity.this.finish();
			}
		});
	}

}
