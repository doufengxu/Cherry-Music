package com.nine.music.dbhelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper {
	private SQLiteHelpers helper;
	private Context context;

//	public DBHelper(Context context) {
//		helper = new SQLiteHelper(context, "local_song.db", 1);
//	}

	// 添加数据
	public boolean insert(String table, String nullColumnHack,
			ContentValues values) {
		boolean flag = false;
		SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
		long id = sqLiteDatabase.insert(table, nullColumnHack, values);
		if (id > 0) {
			flag = true;
		}
		return flag;
	}

	// 查看数据
	public List<Map<String, Object>> query(String table, String[] string,
			String selection, String[] selectionArgs) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
		Cursor cursor = sqLiteDatabase.query(table, string, selection,
				selectionArgs, null, null, null, null);
		int column = cursor.getColumnCount();
		while (cursor.moveToNext()) {
			Map<String, Object> map = new HashMap<String, Object>();
			for (int i = 0; i < column; i++) {
				map.put(cursor.getColumnName(i), cursor.getString(i));
			}
			list.add(map);
		}
		return list;
	}

	// 删除数据
	public boolean delete(String table, String whereClause, String[] whereArgs) {
		boolean flag = false;
		SQLiteDatabase sqLiteDatabase = null;
		try {
			sqLiteDatabase = helper.getWritableDatabase();
			int num = sqLiteDatabase.delete(table, whereClause, whereArgs);
			if (num != 0) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqLiteDatabase != null) {
				sqLiteDatabase.close();
			}
		}
		return flag;
	}
}
