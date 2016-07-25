package com.nine.music.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RadioAnalysis {

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

				String img = object_all.getString("img");
				String listen_count = object_all.getString("listen_count");
				String nick = object_all.getString("nick");
				String radio_id = object_all.getString("radio_id");
				String radio_name = object_all.getString("radio_name");

				map.put("img", img);
				map.put("listen_count", listen_count);
				map.put("nick", nick);
				map.put("radio_id", radio_id);
				map.put("radio_name", radio_name);

				list.add(map);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
