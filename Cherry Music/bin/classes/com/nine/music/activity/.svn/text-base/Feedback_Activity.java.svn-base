package com.nine.music.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.nine.music.R;

public class Feedback_Activity extends Activity {
	ImageView back;
	Button send;
	EditText suggestion;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_feedback);
		init();
		click();
	}

	public void init() {
		back = (ImageView) findViewById(R.id.back);
		send = (Button) findViewById(R.id.send);
		suggestion = (EditText) findViewById(R.id.suggestion);
	}

	public void click() {
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Feedback_Activity.this.finish();
			}
		});

		send.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!(suggestion.getText().toString().equals(""))) {
					// TODO Auto-generated method stub
					Feedback_Activity.this.finish();
					Toast.makeText(getApplicationContext(),
							"你的意见已提交，感谢你对樱桃音乐的支持", 0).show();
				}
			}
		});
	}
}
