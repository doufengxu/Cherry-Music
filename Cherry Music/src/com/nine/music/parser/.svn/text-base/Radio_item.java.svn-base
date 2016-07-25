package com.nine.music.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Radio_item {
	public static List<Map<String, String>> getJson(String data) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		try {
			JSONObject j1 = new JSONObject(data);
			JSONObject j2 = j1.getJSONObject("data");
			JSONArray j3 = j2.getJSONArray("random");
			for (int i = 0; i < j3.length(); i++) {
				Map<String, String> map = new HashMap<String, String>();
				JSONObject j4 = j3.getJSONObject(i);
				String artist = j4.getString("artist");
				map.put("artist", artist);
				String artist_id = j4.getString("artist_id");
				map.put("artist_id", artist_id);
				String music_id = j4.getString("music_id");
				map.put("music_id", music_id);
				String music_name = j4.getString("music_name");
				map.put("music_name", music_name);
				String music_type = j4.getString("music_type");
				map.put("music_type", music_type);
				list.add(map);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (list.size() == 0) {
			try {
				JSONObject j1 = new JSONObject(data);
				JSONObject j2 = j1.getJSONObject("data");
				JSONArray j3 = j2.getJSONArray("top");
				for (int i = 0; i < j3.length(); i++) {
					Map<String, String> map = new HashMap<String, String>();
					JSONObject j4 = j3.getJSONObject(i);
					String artist = j4.getString("artist");
					map.put("artist", artist);
					String artist_id = j4.getString("artist_id");
					map.put("artist_id", artist_id);
					String music_id = j4.getString("music_id");
					map.put("music_id", music_id);
					String music_name = j4.getString("music_name");
					map.put("music_name", music_name);
					String music_type = j4.getString("music_type");
					map.put("music_type", music_type);
					list.add(map);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return list;
		} else {

			return list;
		}

	}

}
