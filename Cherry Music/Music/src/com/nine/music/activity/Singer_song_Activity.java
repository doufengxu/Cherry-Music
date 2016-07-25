package com.nine.music.activity;

import java.util.ArrayList;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import com.nine.music.R;
import com.nine.music.adapter.Singer_song_Adapter;

public class Singer_song_Activity extends Activity {
	ListView lv;
	MediaPlayer mediaPlayer = new MediaPlayer();
	ArrayList<Map<String, String>> list = new ArrayList<Map<String, String>>();
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.singer_song);
		lv = (ListView) findViewById(R.id.listView1);
		
		Intent in = this.getIntent();
		list = (ArrayList<Map<String, String>>) in.getSerializableExtra("List");
//		Log.e("<<<<<<<<<<<<<<<<<<<", list.toString());
		System.out.println(list.size());
		Singer_song_Adapter singer_song_Adapter = new Singer_song_Adapter(
				getApplication(), list);
		lv.setAdapter(singer_song_Adapter);
		play();
	}

	public void play() {
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				String music_path = list.get(arg2).get("song_path").toString();
			
				if (mediaPlayer.isPlaying()) {
					mediaPlayer.stop();
				
				} else {
					prepareAndPlay(music_path);
				
				}
			}

		});
	}

	public void prepareAndPlay(String path) {
		mediaPlayer.reset(); // 复位
		try {
			mediaPlayer.setDataSource(path); // 设置播放源
			mediaPlayer.prepare(); // 准备
			mediaPlayer.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
