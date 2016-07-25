package com.nine.music.dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.provider.MediaStore;

public class SQLiteHelpers extends SQLiteOpenHelper {

	private static String download = "CREATE TABLE " + "download" + " ("
			+ "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ MediaStore.Audio.Media.TITLE + " VARCHAR(50),"
			+ MediaStore.Audio.Media.DATA + " VARCHAR(50),"
			+ MediaStore.Audio.Media.ARTIST + " VARCHAR(50),"
			+ MediaStore.Audio.Media.DURATION + " VARCHAR(50)," + "music_id"
			+ " VARCHAR(50)," + "image VARCHAR(50)," + "lyric_path VARCHAR(50)"
			+ ");";

	private static String favourite = "CREATE TABLE " + "favourite" + " ("
			+ "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ MediaStore.Audio.Media.TITLE + " VARCHAR(50),"
			+ MediaStore.Audio.Media.DATA + " VARCHAR(50),"
			+ MediaStore.Audio.Media.ARTIST + " VARCHAR(50),"
			+ MediaStore.Audio.Media.DURATION + " VARCHAR(50)," + "music_id"
			+ " VARCHAR(50)," + "image VARCHAR(50)," + "lyric_path VARCHAR(50)"
			+ ");";

	public SQLiteHelpers(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		db.execSQL(download);
		db.execSQL(favourite);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
