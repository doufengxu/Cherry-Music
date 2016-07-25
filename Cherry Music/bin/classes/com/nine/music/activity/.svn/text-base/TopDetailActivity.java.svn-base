package com.nine.music.activity;

import com.nine.music.R;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

public class TopDetailActivity extends Activity {
	private ImageView imageView_back;
	private TextView textView;
	private WebView webView;
	private String title;
	private String url;
	private ActionBar actionBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_top_detail);
		
		Intent intent = getIntent();
		title = intent.getStringExtra("title");
		url = intent.getStringExtra("url");

		init();
		
		
	}

	/**
	 * 初始化数据
	 */
	private void init() {
		imageView_back = (ImageView) findViewById(R.id.imageView_TopDetail_activity);
		textView = (TextView) findViewById(R.id.textView_TopDetail_activity);
		textView.setText(title);
		webView = (WebView) findViewById(R.id.webView_Top_Detail);
		actionBar = getActionBar();
		actionBar.hide();

		// 或者设置
		WebSettings settings = webView.getSettings();
		settings.setBuiltInZoomControls(true);
		settings.setLoadWithOverviewMode(true);
		settings.setUseWideViewPort(true);

		// 开启连接
		webView.setWebChromeClient(new WebChromeClient() {
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				// TODO Auto-generated method stub
				super.onProgressChanged(view, newProgress);
			}
		});

		webView.loadUrl(url);

	}

}
