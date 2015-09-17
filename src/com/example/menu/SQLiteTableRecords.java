package com.example.menu;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.AsyncTask;
import android.util.Log;

public class SQLiteTableRecords {

	// Database fields
	private SQLiteDatabase database = null;
	private RecordsSQLiteHelper dbHelper;
	private String[] allColumns = { RecordsSQLiteHelper.COLUMN_ID,
			RecordsSQLiteHelper.COLUMN_TAG,
			RecordsSQLiteHelper.COLUMN_VALUE };

	public SQLiteTableRecords(Context context) {
		//Se crea la base de datos
		dbHelper = new RecordsSQLiteHelper(context);
	}

	public void open() throws SQLException {
		//Se obtiene acceso escritura/lectura a la base de datos
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	/**
	 * Adds one record to the opened database
	 * 
	 * @param record A Record object with the key/value pair
	 * @return A copy from the database of the added record
	 */
	public Record addRecord(Record record) {
		ContentValues values = new ContentValues();
		long insertId = -1;

		values.put(RecordsSQLiteHelper.COLUMN_TAG, record.getTag());
		values.put(RecordsSQLiteHelper.COLUMN_VALUE, record.getValue());

		//Se inserta el nuevo registro en la base de datos
		insertId = database.insert(RecordsSQLiteHelper.TABLE_RECORDS, null,
				values);

		//Se busca el recien ingresado registro
		Cursor cursor = database.query(RecordsSQLiteHelper.TABLE_RECORDS,
				allColumns, RecordsSQLiteHelper.COLUMN_ID + " = " + insertId,
				null, null, null, null);

		cursor.moveToFirst();
		Record newComment = cursorToRecord(cursor);
		cursor.close();
		return newComment;
	}

	
	public void deleteEntry(Record record) {
		long id = record.getId();
		System.out.println("Comment deleted with id: " + id);
		database.delete(RecordsSQLiteHelper.TABLE_RECORDS,
				RecordsSQLiteHelper.COLUMN_ID + " = " + id, null);
	}

	public List<Record> getAllRecords() {
		List<Record> records = new ArrayList<Record>();

		Cursor cursor = database.query(RecordsSQLiteHelper.TABLE_RECORDS,
				allColumns, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Record record = cursorToRecord(cursor);
			records.add(record);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return records;
	}

	private Record cursorToRecord(Cursor cursor) {
		Record record = new Record();

		record.setId(cursor.getLong(0));
		record.setTag(cursor.getString(1));
		record.setValue((int) cursor.getLong(2));
		return record;
	}

	public class OpenDBTask extends
			AsyncTask<RecordsSQLiteHelper, SQLiteDatabase, SQLiteDatabase> {

		@Override
		protected SQLiteDatabase doInBackground(RecordsSQLiteHelper... arg0) {
			try {
				SQLiteDatabase db = arg0[0].getWritableDatabase();
				return db;
			} catch (SQLiteException ex) {
				Log.d("OpenDBTask", ex.getMessage());
				return null;
			}

		}

		protected void onPostExecute(final SQLiteDatabase result) {
			database = result;

		}

	}

}