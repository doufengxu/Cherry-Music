package com.nine.music.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.JsonReader;

public class SelectionAnalysis {

	public List<Map<String, String>> getJson(String data) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		JSONObject object1;
		try {

			object1 = new JSONObject(data);
			JSONObject object_data = object1.getJSONObject("data");
			JSONArray array_list = object_data.getJSONArray("list");

			for (int i = 0; i < array_list.length(); i++) {
				Map<String, String> map = new HashMap<String, String>();
				JSONObject object_all = array_list.getJSONObject(i);

				String best_id = object_all.getString("best_id");
				String content = object_all.getString("content");
				String img = object_all.getString("img");
				String title = object_all.getString("title");
				String listen_count = object_all.getString("listen_count");

				map.put("best_id", best_id);
				map.put("content", content);
				map.put("img", img);
				map.put("title", title);
				map.put("listen_count", listen_count);

				list.add(map);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 获得头条大图数据
	 * 
	 * @param data
	 * @return
	 */
	public List<Map<String, String>> getTopJson(String data) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		JSONObject object1;
		try {
			object1 = new JSONObject(data);
			JSONObject object2 = object1.getJSONObject("data");
			JSONObject object3 = object2.getJSONObject("ad_ext");
			JSONArray array = object3.getJSONArray("top");
			for (int i = 0; i < array.length(); i++) {
				Map<String, String> map = new HashMap<String, String>();
				JSONObject object4 = array.getJSONObject(i);
				String img = object4.getString("img");
				String param = object4.getString("param");

				map.put("img", img);
				map.put("param", param);

				list.add(map);

			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 获得头条左右图片详情页数据
	 * 
	 * @param data
	 * @return
	 */
	public List<Map<String, String>> getLeftDetail(String data,
			String leftOrRight) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();

		JSONObject object1;
		try {
			object1 = new JSONObject(data);
			JSONObject object2 = object1.getJSONObject("data");
			JSONObject object3 = object2.getJSONObject("ad_ext");
			JSONObject object4 = object3.getJSONObject(leftOrRight);
			JSONArray array = object4.getJSONArray("list");
			for (int i = 0; i < array.length(); i++) {
				Map<String, String> map = new HashMap<String, String>();
				JSONObject object5 = array.getJSONObject(i);
				String column_id = object5.getString("column_id");
				String cover_img = object5.getString("cover_img");

				map.put("column_id", column_id);
				map.put("cover_img", cover_img);

				list.add(map);

			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 获得头条左右图片
	 * 
	 * @param data
	 * @return
	 */
	public Map<String, String> getLeftPic(String data, String leftOrRight) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map = new HashMap<String, String>();

		JSONObject object1;
		try {
			object1 = new JSONObject(data);
			JSONObject object2 = object1.getJSONObject("data");
			JSONObject object3 = object2.getJSONObject("ad_ext");
			JSONObject object4 = object3.getJSONObject(leftOrRight);
			String img = object4.getString("img");

			map.put("img", img);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 歌曲解析
	 */

	public List<Map<String, String>> getSong(String data) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		JSONObject object;
		try {
			object = new JSONObject(data);
			JSONObject object2 = object.getJSONObject("data");
			JSONArray array = object2.getJSONArray("music");
			for (int i = 0; i < array.length(); i++) {
				Map<String, String> map = new HashMap<String, String>();

				JSONObject object3 = array.getJSONObject(i);
				String artist = object3.getString("artist");
				String artist_id = object3.getString("artist_id");
				String cover_path = object3.getString("cover_path");
				String music_id = object3.getString("music_id");
				String music_name = object3.getString("music_name");

				map.put("artist", artist);
				map.put("artist_id", artist_id);
				map.put("cover_path", cover_path);
				map.put("music_id", music_id);
				map.put("music_name", music_name);

				list.add(map);

			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
