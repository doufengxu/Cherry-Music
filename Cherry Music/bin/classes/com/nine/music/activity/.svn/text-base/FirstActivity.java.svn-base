package com.nine.music.activity;

import com.nine.music.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

public class FirstActivity extends Activity {

	private ImageView imageView_01;
	private ImageView imageView_02;
	private ImageView imageView_03;
	private ImageView imageView_04;
	private ImageView[] imageViews = new ImageView[4];
	private Animation alphaAnimation;
	public static SharedPreferences sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_first);

		init();

		show();
	}

	private void show() {

		for (int i = 0; i < imageViews.length; i++) {
			alphaAnimation = new AlphaAnimation(0, 1);
			alphaAnimation.setDuration(3000);

			alphaAnimation.setStartOffset(i * 500);
			imageViews[i].startAnimation(alphaAnimation);

		}
		alphaAnimation.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				sharedPreferences = getSharedPreferences("Login", 0);
				Boolean isFirst = sharedPreferences.getBoolean("isFirst", true);
				if (isFirst) {
					startActivity(new Intent(FirstActivity.this,
							GuideActivity.class));

				} else {

					startActivity(new Intent(FirstActivity.this,
							MainActivity.class));
				}
				finish();

			}
		});

	}

	private void init() {
		imageView_01 = (ImageView) findViewById(R.id.imageView_ic_start_01);
		imageView_02 = (ImageView) findViewById(R.id.imageView_ic_start_02);
		imageView_03 = (ImageView) findViewById(R.id.imageView_ic_start_03);
		imageView_04 = (ImageView) findViewById(R.id.imageView_ic_start_04);
		imageViews[0] = imageView_01;
		imageViews[1] = imageView_02;
		imageViews[2] = imageView_03;
		imageViews[3] = imageView_04;
	}
	
	

}
