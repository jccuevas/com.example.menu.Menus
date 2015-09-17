package com.example.menu;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class RecordsSQLiteHelper extends SQLiteOpenHelper implements RecordTableColumns {

	

	// Database creation sql statement
	private static final String DATABASE_CREATE = "create table "
			+ TABLE_RECORDS + "(" + COLUMN_ID
			+ " integer primary key autoincrement, " + COLUMN_TAG
			+ " text not null, " + COLUMN_VALUE + " integer nor null);";

	public RecordsSQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);

	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(RecordsSQLiteHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECORDS);
		onCreate(db);
	}

}