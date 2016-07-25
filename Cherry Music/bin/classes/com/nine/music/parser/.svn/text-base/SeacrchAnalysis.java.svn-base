package com.nine.music.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SeacrchAnalysis {

	
	public static List<Map<String, String>> getJsons(String data) {
		// TODO Auto-generated method stub
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		JSONObject object;
		try {
			object = new JSONObject(data);
			JSONArray array = object.getJSONArray("abslist");
			for (int i = 0; i < array.length(); i++) {
				Map<String, String> map = new HashMap<String, String>();
				JSONObject object2 = array.getJSONObject(i);
				String MUSICRID = object2.getString("MUSICRID");
				String SONGNAME = object2.getString("SONGNAME");
				String ARTIST = object2.getString("ARTIST");
				
				map.put("MUSICRID", MUSICRID);
				map.put("SONGNAME", SONGNAME);
				map.put("ARTIST", ARTIST);
				
				list.add(map);

			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
