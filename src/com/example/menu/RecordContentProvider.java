package com.example.menu;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class RecordContentProvider extends ContentProvider implements RecordTableColumns{

	private static final String uri =
		    "content://uja.epsl.aaptm.android.contentproviders/registros";
		 
	public static final Uri CONTENT_URI = Uri.parse(uri);
	
	private RecordsSQLiteHelper rdbh;
	
	private static final int RECORD = 1;
	private static final int RECORD_ID = 2;
	private static final UriMatcher uriMatcher= new UriMatcher(UriMatcher.NO_MATCH);
	
	static {
	    
	    uriMatcher.addURI("uja.epsl.aaptm.android.contentproviders", "registros", RECORD);
	    uriMatcher.addURI("uja.epsl.aaptm.android.contentproviders", "registros/#", RECORD_ID);
	}
	
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		int cont;
		 
	    //Si es una consulta a un ID concreto construimos el WHERE
	    String where = selection;
	    if(uriMatcher.match(uri) == RECORD_ID){
	            where = "_id=" + uri.getLastPathSegment();
	        }
	 
	    SQLiteDatabase db = rdbh.getWritableDatabase();
	 
	    cont = db.delete(TABLE_RECORDS, where, selectionArgs);
	 
	    return cont;
		
	}

	@Override
	public String getType(Uri uri) {
		
		int match = uriMatcher.match(uri);
		 
	    switch (match)
	    {
	        case RECORD:
	            return "vnd.android.cursor.dir/vnd.aaptm.record";
	        case RECORD_ID:
	            return "vnd.android.cursor.item/vnd.aaptm.record";
	        default:
	            return null;
	    }
		
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		long regId = 1;
		 
	    SQLiteDatabase db = rdbh.getWritableDatabase();
	 
	    regId = db.insert(TABLE_RECORDS, null, values);
	 
	    Uri newUri = ContentUris.withAppendedId(CONTENT_URI, regId);
	 
	    return newUri;
		
	}

	@Override
	public boolean onCreate() {
		rdbh = new RecordsSQLiteHelper(getContext());
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {

		//Si es una consulta a un ID concreto construimos el WHERE
	    String where = selection;
	    if(uriMatcher.match(uri) == RECORD_ID){
	                where = "_id=" + uri.getLastPathSegment();
	        }
	 
	    SQLiteDatabase db = rdbh.getWritableDatabase();
	 
	    Cursor c = db.query(TABLE_RECORDS, projection, where,
	                    selectionArgs, null, null, sortOrder);
		return c;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		 int cont;
		 
		    //Si es una consulta a un ID concreto construimos el WHERE
		    String where = selection;
		    if(uriMatcher.match(uri) == RECORD_ID){
		            where = "_id=" + uri.getLastPathSegment();
		        }
		 
		    SQLiteDatabase db = rdbh.getWritableDatabase();
		 
		    cont = db.update(TABLE_RECORDS, values, where, selectionArgs);
		 
		    return cont;
	}
	
	
	


}
