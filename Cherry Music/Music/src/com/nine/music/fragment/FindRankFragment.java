package com.nine.music.fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.nine.music.R;
import com.nine.music.adapter.RankAdapter;
import com.nine.music.constants.Constants;
import com.nine.music.parser.RankAnalysis;

public class FindRankFragment extends Fragment {
	private View view;
	private List<Map<String, String>> list;
	private List<List<Map<String, String>>> list_detail = new ArrayList<List<Map<String, String>>>();
	private HttpUtils httpUtils = new HttpUtils();
	private RankAdapter adapter;
	private boolean isEmpty = true;

	public FindRankFragment() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.fragment_rank_find, null);

		// 初始化数据
		init();

		return view;
	}

	/**
	 * 初始化数据
	 */
	private void init() {
		// TODO Auto-generated method stub
		ExpandableListView expandableListView = (ExpandableListView) view
				.findViewById(R.id.expaanListView_Rank_Find);

		// 数据源
		list = new ArrayList<Map<String, String>>();

		// 网络下载数据
		httpUtils.send(HttpMethod.GET, Constants.RANK,
				new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						// TODO Auto-generated method stub
						RankAnalysis analysis = new RankAnalysis();
						list = analysis.getJson(responseInfo.result);

						// 二级数据源
						for (int i = 0; i < list.size(); i++) {
							List<Map<String, String>> list_turn = new ArrayList<Map<String, String>>();
							list_turn = analysis.getAnalysis(list.get(i).get(
									"rank"));
							list_detail.add(list_turn);
						}

						adapter.addList(list_detail);
						adapter.notifyDataSetChanged();
						isEmpty = false;
					}
				});

		// 设置默认图标不显示
		expandableListView.setGroupIndicator(null);

		// 构建适配器
		adapter = new RankAdapter(list_detail, getActivity());

		// 绑定适配器
		expandableListView.setAdapter(adapter);
		
		expandableListView.setOnGroupClickListener(new OnGroupClickListener() {
			
			@Override
			public boolean onGroupClick(ExpandableListView arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				return isEmpty;
			}
		});
	}

}
