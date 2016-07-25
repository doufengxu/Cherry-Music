package com.nine.music.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nine.music.R;
import com.nine.music.fragment.GuideFragment;

public class GuideActivity extends FragmentActivity {
	private ViewPager viewPager_guide;
	private LinearLayout linearLayout_point;
	private ImageView imageView_point[] = new ImageView[3];
	private List<Fragment> list = new ArrayList<Fragment>();
	private Button button_login;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_guide);
		FirstActivity.sharedPreferences.edit().putBoolean("isFirst", false)
				.commit();
		FirstActivity.sharedPreferences = null;
		init();
		set_viewPager();
		button_login.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(GuideActivity.this, MainActivity.class));
				finish();
			}
		});

	}

	private void set_viewPager() {
		for (int i = 1; i <= 3; i++) {
			GuideFragment fragment_guide = new GuideFragment();
			Bundle bundle = new Bundle();
			bundle.putInt("guide", i);
			fragment_guide.setArguments(bundle);
			list.add(fragment_guide);
		}
		Guide_adapter guide_adapter = new Guide_adapter(
				getSupportFragmentManager());
		viewPager_guide.setAdapter(guide_adapter);
		viewPager_guide.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {

				if (arg0 == 2) {
					for (int i = 0; i < 3; i++) {
						imageView_point[i].setVisibility(View.GONE);
						button_login.setVisibility(View.VISIBLE);
					}
				} else {
					for (int i = 0; i < 3; i++) {
						imageView_point[i].setVisibility(View.VISIBLE);
						button_login.setVisibility(View.GONE);
						imageView_point[i]
								.setImageResource(R.drawable.bg_pet_circle);
					}
					imageView_point[arg0]
							.setImageResource(R.drawable.bg_pet_circle_p);
				}

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

	private class Guide_adapter extends FragmentPagerAdapter {

		public Guide_adapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int arg0) {
			// TODO Auto-generated method stub
			return list.get(arg0);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

	}

	private void init() {
		button_login = (Button) findViewById(R.id.button_login);
		viewPager_guide = (ViewPager) findViewById(R.id.viewPager_guide);
		linearLayout_point = (LinearLayout) findViewById(R.id.linearLayout_point);
		for (int i = 0; i < imageView_point.length; i++) {
			imageView_point[i] = (ImageView) linearLayout_point.getChildAt(i);

		}

	}

}
