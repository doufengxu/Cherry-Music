package com.nine.music.fragment;

import java.io.Writer;

import com.nine.music.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class FragmentTopDetail extends Fragment {
	private String url;
	private WebView webView;

	public FragmentTopDetail(String urls) {
		// TODO Auto-generated constructor stub
		this.url = urls;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = LayoutInflater.from(getActivity()).inflate(
				R.layout.activity_top_detail, null);
		webView = (WebView) view.findViewById(R.id.webView_Top_Detail);
		// 或者设置
		WebSettings settings = webView.getSettings();
		settings.setBuiltInZoomControls(true);
		settings.setLoadWithOverviewMode(true);
		settings.setUseWideViewPort(true);
		
//		webView.set
		
		//开启连接
		webView.setWebChromeClient(new WebChromeClient(){
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				// TODO Auto-generated method stub
				super.onProgressChanged(view, newProgress);
			}
		});
		
		webView.loadUrl(url);

		return view;
	}
}
