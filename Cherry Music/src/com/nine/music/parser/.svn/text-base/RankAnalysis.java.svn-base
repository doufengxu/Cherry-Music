package com.nine.music.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RankAnalysis {

	/**
	 * 获得排行榜json数据
	 * @param data
	 * @return
	 */
	public List<Map<String, String>> getJson(String data) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		JSONObject object;
		try {
			object = new JSONObject(data);
			JSONObject object2 = object.getJSONObject("data");
			JSONArray array = object2.getJSONArray("list");
			for (int i = 0; i < array.length(); i++) {
				Map<String, String> map = new HashMap<String, String>();
				JSONObject object3 = array.getJSONObject(i);
				String name = object3.getString("name");
				String rank = object3.getString("rank");

				map.put("name", name);
				map.put("rank", rank);

				list.add(map);
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 获得排行详细数据
	 * @param data
	 * @return
	 */
	public List<Map<String, String>> getAnalysis(String data) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		JSONArray array;
		try {
			array = new JSONArray(data);
			for (int i = 0; i < array.length(); i++) {
				JSONObject object = array.getJSONObject(i);
			
				Map<String,String> map = new HashMap<String, String>();				
				String artist_id = object.getString("artist_id");
				String music_id = object.getString("music_id");
				String music_name = object.getString("music_name");
				String artist = object.getString("artist");
				String cover_path = object.getString("cover_path");
				
				map.put("artist_id", artist_id);
				map.put("music_id", music_id);
				map.put("music_name", music_name);
				map.put("artist", artist);
				map.put("cover_path", cover_path);
				
				list.add(map);
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
