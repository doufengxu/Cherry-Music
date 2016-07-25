package com.nine.music.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nine.music.R;
import com.nine.music.activity.SearchActivity;
import com.nine.music.adapter.FindAdapter;

public class FindFragment extends Fragment {
	private EditText editText;
	private List<Fragment> list;
	private TextView[] textViews_tab;
	private ViewPager viewPager;
	private View view;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_find, null);

		// 初始化数据
		init();

		// 初始化标签栏
		initTab();

		// 初始化ViewPager
		iniViewPager();

		// 初始化editText;
		iniEditText();
		return view;
	}

	private void iniEditText() {
		// TODO Auto-generated method stub
		editText.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View arg0, boolean arg1) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(),SearchActivity.class);
				startActivity(intent);
			}
		});
	}

	/**
	 * 初始化数据
	 */
	private void init() {
		// TODO Auto-generated method stub
		list = new ArrayList<Fragment>();
		textViews_tab = new TextView[4];
		viewPager = (ViewPager) view.findViewById(R.id.viewPager_find);
		editText = (EditText) view.findViewById(R.id.editText_find_main);
		

	}

	private void iniViewPager() {
		// 创建数据源
		list.add(new FindSelectionFragment());
		list.add(new FindRadioFragment());
		list.add(new FindRankFragment());
		list.add(new FindFavouriteFragment());

		// 构建适配器
		FragmentManager manager = getFragmentManager();
		FindAdapter adapter = new FindAdapter(manager, list);
		// 绑定适配器
		viewPager.setAdapter(adapter);
		// 设置监听器
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				for (int i = 0; i < 4; i++) {
					textViews_tab[i].setEnabled(true);
					textViews_tab[i].setTextColor(Color.BLACK);
				}
				textViews_tab[position].setEnabled(false);
				textViews_tab[position].setTextColor(Color.RED);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

	/**
	 * 初始化标签栏
	 */
	private void initTab() {
		LinearLayout layout = (LinearLayout) view
				.findViewById(R.id.LinearLayout_Tab_Find);
		for (int i = 0; i < textViews_tab.length; i++) {
			TextView textView = (TextView) layout.getChildAt(i);

			// 设置各个标签
			textViews_tab[i] = textView;
			textViews_tab[i].setEnabled(true);
			textViews_tab[i].setTag(i);

			textViews_tab[i].setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View view) {
					// TODO Auto-generated method stub
					viewPager.setCurrentItem((Integer) view.getTag());
				}
			});
			textViews_tab[0].setEnabled(false);
			textViews_tab[0].setTextColor(Color.RED);

		}
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

}
