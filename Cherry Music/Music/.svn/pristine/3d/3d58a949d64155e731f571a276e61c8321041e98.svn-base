package com.nine.music.fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Intent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;

import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.nine.music.R;

import com.nine.music.activity.TopSmallActivity;
import com.nine.music.adapter.FindTopAdapter;
import com.nine.music.adapter.SelectionAdapter;
import com.nine.music.constants.Constants;
import com.nine.music.parser.SelectionAnalysis;

public class FindSelectionFragment extends Fragment {
	private View view;
	private ListView listView_selection;
	private HttpUtils httpUtils;
	private List<Map<String, String>> list;
	private List<Map<String, String>> list_viewPager;
	private SelectionAdapter adapter;
	private List<ImageView> list_img;
	private BitmapUtils bitmapUtils;
	private FindTopAdapter TopAdapter;
	private SelectionAnalysis analysis;
	private String data;
	private boolean isBottom;
	private int currentPage = 21;

	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		list = new ArrayList<Map<String, String>>();
		list_img = new ArrayList<ImageView>();
		list_viewPager = new ArrayList<Map<String, String>>();
		bitmapUtils = new BitmapUtils(getActivity());
		analysis = new SelectionAnalysis();

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_seclection_find, null);

		// 初始化数据
		init();

		return view;
	}

	/**
	 * 初始化数据
	 */
	private void init() {
		listView_selection = (ListView) view
				.findViewById(R.id.listView_selection_find);
		httpUtils = new HttpUtils();
		iniListview();

	}

	/**
	 * 操作ListView
	 */
	private void iniListview() {

		// 数据源
		httpUtils.send(HttpMethod.GET, Constants.SECLECTION,
				new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {

						data = responseInfo.result;
						list = analysis.getJson(responseInfo.result);
						adapter.addList(list);
						adapter.notifyDataSetChanged();

					}

					@Override
					public void onFailure(HttpException error, String msg) {
						Toast.makeText(getActivity(), "网络堵车啦^_^#",
								Toast.LENGTH_SHORT).show();

					}
				});

		// 增加头条
		View view = LayoutInflater.from(getActivity()).inflate(
				R.layout.item_headview_fragment, null);
		iniView(view);
		listView_selection.addHeaderView(view);

		// 构建适配器
		adapter = new SelectionAdapter(getActivity(), list);

		// 绑定适配器
		listView_selection.setAdapter(adapter);

		// 设置监听
		listView_selection.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// Toast.makeText(getActivity(), position,
				// Toast.LENGTH_SHORT).show();

			}
		});

//		listView_selection.setOnScrollListener(new OnScrollListener() {
//
//			@Override
//			public void onScrollStateChanged(AbsListView view, int scrollState) {
//				// TODO Auto-generated method stub
//				if (scrollState == SCROLL_STATE_IDLE && isBottom) {
//					// 重新加载数据
//					Toast.makeText(getActivity(),
//							"" + Constants.SECLECTION_ADD + currentPage,
//							Toast.LENGTH_SHORT).show();
//
//					currentPage = currentPage + 21;
//					httpUtils.send(HttpMethod.GET, Constants.SECLECTION_ADD
//							+ currentPage, new RequestCallBack<String>() {
//
//						@Override
//						public void onFailure(HttpException arg0, String error) {
//							// TODO Auto-generated method stub
//
//						}
//
//						@Override
//						public void onSuccess(ResponseInfo<String> responseInfo) {
//							// TODO Auto-generated method stub
//							List<Map<String, String>> list_add = analysis
//									.getJson(responseInfo.result);
//
//							adapter.addList(list_add);
//							adapter.notifyDataSetChanged();
//
//						}
//					});
//				}
//			}
//
//			@Override
//			public void onScroll(AbsListView arg0, int firstVisibleItem,
//					int visibleItemCount, int totalItemCount) {
//				// TODO Auto-generated method stub
//				isBottom = ((firstVisibleItem + visibleItemCount) == totalItemCount);
//			}
//		});

	}

	private void iniView(View view) {
		final ViewPager viewPager = (ViewPager) view
				.findViewById(R.id.viewPager_top_find);
		final ImageView imageView_guide = (ImageView) view
				.findViewById(R.id.imageView_guideTop_find);
		final ImageView imageView_left = (ImageView) view
				.findViewById(R.id.imageView_left_find);
		final ImageView imageView_right = (ImageView) view
				.findViewById(R.id.imageView_right_find);

		httpUtils.send(HttpMethod.GET, Constants.SECLECTION,
				new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub
						System.out.println("-------onFailure-----");
					}

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						System.out.println("-------onSuccess-----");
						bitmapUtils.display(
								imageView_left,
								Constants.IMAGEVIEW_PREFIX
										+ analysis.getLeftPic(
												responseInfo.result, "left")
												.get("img"));
						bitmapUtils.display(
								imageView_right,
								Constants.IMAGEVIEW_PREFIX
										+ analysis.getLeftPic(
												responseInfo.result, "right")
												.get("img"));
					}
				});

		// 左右小头条监听

		imageView_left.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				Intent intent = new Intent(getActivity(),
						TopSmallActivity.class);
				intent.putExtra("data", data);
				intent.putExtra("state", "left");
				startActivity(intent);

			}
		});

		imageView_right.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(),
						TopSmallActivity.class);
				intent.putExtra("data", data);
				intent.putExtra("state", "right");
				startActivity(intent);
			}
		});

		// 创建数据源
		addImageView();

		// 构建适配器
		TopAdapter = new FindTopAdapter(list_img, data, getActivity());
		// 绑定适配器
		viewPager.setAdapter(TopAdapter);

		viewPager.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				int i = viewPager.getCurrentItem();
				Toast.makeText(getActivity(), "asdasd" + i, Toast.LENGTH_SHORT)
						.show();

			}
		});
		// 设置监听
		viewPager.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (MotionEvent.ACTION_MOVE == event.getAction()) {
					ViewParent parent = viewPager.getParent();
					parent.requestDisallowInterceptTouchEvent(true);
				}
				return false;
			}
		});
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub
				imageView_guide.setImageLevel(position);

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});

	}

	/**
	 * 创建ImageView数组
	 */
	private void addImageView() {

		// 下载头条ViewPager数据
		httpUtils.send(HttpMethod.GET, Constants.SECLECTION,
				new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						// TODO Auto-generated method stub

						list_viewPager = analysis
								.getTopJson(responseInfo.result);

						for (int i = 0; i < 3; i++) {
							// Log.d("JJ", i + "");
							ImageView imageView = new ImageView(getActivity());
							bitmapUtils.display(imageView,
									Constants.IMAGEVIEW_PREFIX
											+ list_viewPager.get(i).get("img"));
							imageView.setScaleType(ScaleType.FIT_XY);
							imageView.setLayoutParams(new LayoutParams(
									LayoutParams.MATCH_PARENT,
									LayoutParams.MATCH_PARENT));
							imageView.setOnClickListener(new OnClickListener() {

								@Override
								public void onClick(View arg0) {
									// TODO Auto-generated method stub
									Toast.makeText(getActivity(), "2",
											Toast.LENGTH_SHORT).show();
								}
							});

							list_img.add(imageView);
							TopAdapter.addList(imageView);
						}
						TopAdapter.notifyDataSetChanged();

					}
				});

	}

}
