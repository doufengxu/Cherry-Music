package com.nine.music.dbhelper;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class MyContentProvider extends ContentProvider {
	private static final String NAME = "music.db";
	private static final int VERSION = 1;
	private static String authority = "com.nine.music.dbhelper.MyContentProvider";
	private static UriMatcher uriMatcher;
	private SQLiteHelpers openHelper;

	public static String download = "content://com.nine.music.dbhelper.MyContentProvider/download";
	public static String favourite = "content://com.nine.music.dbhelper.MyContentProvider/favourite";
	private SQLiteDatabase sqLiteDatabase;

	static {
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI(authority, "download", 1);
		uriMatcher.addURI(authority, "favourite", 2);

	}

	@Override
	public boolean onCreate() {
		openHelper = new SQLiteHelpers(getContext(), NAME, null, VERSION);
		sqLiteDatabase = openHelper.getReadableDatabase();
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {

		int code = uriMatcher.match(uri);
		Cursor cursor = null;
		switch (code) {
		case 1:

			cursor = sqLiteDatabase.query("download", projection, selection,
					selectionArgs, null, null, null);

			break;
		case 2:

			cursor = sqLiteDatabase.query("favourite", projection, selection,
					selectionArgs, null, null, null);

			break;

		}
		return cursor;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {

		int code = uriMatcher.match(uri);
		// Cursor cursor = null;
		switch (code) {
		case 1:
			// cursor = sqLiteDatabase.query("download", new String[] { "_id" },
			// selection, selectionArgs, null, null, null);
			break;
		case 2:
			sqLiteDatabase.insert("favourite", "null", values);
			break;

		}

		// TODO Auto-generated method stub

		return null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		int code = uriMatcher.match(uri);
		switch (code) {
		case 1:

			sqLiteDatabase.delete("download", selection, selectionArgs);
			break;
		case 2:

			sqLiteDatabase.delete("favourite", selection, selectionArgs);
			// System.out.println("-----------------sqLiteDatabase---------------");
			break;

		}
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

}
