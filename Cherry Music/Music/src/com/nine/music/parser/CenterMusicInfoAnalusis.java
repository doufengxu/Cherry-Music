package com.nine.music.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CenterMusicInfoAnalusis {

	public static ArrayList<Map<String, Object>> Josn(String json) {
		ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		try {
			JSONObject jsonObject = new JSONObject(json);
			JSONArray array = jsonObject.getJSONArray("abslist");
			for (int i = 0; i < array.length(); i++) {
				JSONObject jsonObject2 = array.getJSONObject(i);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("title", jsonObject2.getString("SONGNAME"));
				map.put("artist", jsonObject2.getString("ARTIST"));
				list.add(map);

			}
			return list;

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

}
