package com.nine.music.fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.nine.music.R;
import com.nine.music.activity.Radio_Detailed_Activity;
import com.nine.music.adapter.RadioAdapter;
import com.nine.music.constants.Constants;
import com.nine.music.parser.RadioAnalysis;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class FindRadioFragment extends Fragment {
	private View view;
	private ListView listView_radio;
	private HttpUtils httpUtils;
	private List<Map<String, String>> list;
	private RadioAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		list = new ArrayList<Map<String, String>>();
		httpUtils = new HttpUtils();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.fragment_radio_find, null);

		// 初始化ListView
		iniListview();

		return view;
	}

	/**
	 * 初始化ListView
	 */
	private void iniListview() {
		// TODO Auto-generated method stub
		listView_radio = (ListView) view.findViewById(R.id.listView_radio_find);
		Log.d("main", "----Radio1------>>" + Constants.RADIO);

		// 创建数据源
		httpUtils.send(HttpMethod.GET, Constants.RADIO,
				new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						// TODO Auto-generated method stub

						RadioAnalysis analysis = new RadioAnalysis();
						list = analysis.getJson(responseInfo.result);
						adapter.addList(list);
						adapter.notifyDataSetChanged();

					}

					@Override
					public void onFailure(HttpException error, String msg) {
						// TODO Auto-generated method stub

					}
				});
		// 构建适配器
		adapter = new RadioAdapter(list, getActivity());

		// 绑定适配器
		listView_radio.setAdapter(adapter);

		// 设置监听器
		listView_radio.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent in = new Intent(getActivity(),
						Radio_Detailed_Activity.class);
				String radio_id=list.get(arg2).get("radio_id").toString();
				String img=list.get(arg2).get("img").toString();
				int size=list.size();
				Log.e(">>>>>>>>>>>>>>>>>", radio_id);
				Log.e(">>>>>>>>>>>>>>>>>", img);
				in.putExtra("radio_id", radio_id);
				in.putExtra("img", img);
				in.putExtra("size", size);
				startActivity(in);
			}
		});
	}
}
