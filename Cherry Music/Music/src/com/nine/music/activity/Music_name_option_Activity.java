package com.nine.music.activity;

import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.nine.music.R;
import com.nine.music.service.MediaPlayService;

public class Music_name_option_Activity extends Activity {
	ListView lv;
	ArrayList<String> li;
	ArrayAdapter<String> adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.music_name_option);
		inti();
		Intent in = this.getIntent();
		li = (ArrayList<String>) in.getSerializableExtra("music_name");
		Log.e("name1", li.toString());
		adapter = new ArrayAdapter<String>(this, R.layout.item_seting,
				R.id.textView_seting, li);
		lv.setAdapter(adapter);
	}

	private void inti() {
		// TODO Auto-generated method stub
		lv = (ListView) findViewById(R.id.musi_name_option_listView1);
		li = new ArrayList<String>();
	}
	public void item_ckcik(){
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
}
