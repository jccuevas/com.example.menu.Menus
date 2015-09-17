package com.example.menu;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class ProviderUse extends Activity implements OnKeyListener {

	private int posTouched = -1;
	private SimpleCursorAdapter dataAdapter;
	private ListView listView = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_menu);

		String[] projection = new String[] { RecordTableColumns.COLUMN_ID,
				RecordTableColumns.COLUMN_TAG, RecordTableColumns.COLUMN_VALUE };

		Uri recordsUri = RecordContentProvider.CONTENT_URI;

		ContentResolver cr = getContentResolver();

		// Hacemos la consulta
		Cursor cur = cr.query(recordsUri, projection, // Columnas a devolver
				null, // Condición de la query
				null, // Argumentos variables de la query
				null); // Orden de los resultados

		// Se preparan las columnas que queremos mostrar
		String[] cursorColumn = new String[] { RecordTableColumns.COLUMN_TAG,
				RecordTableColumns.COLUMN_VALUE };

		// Se eligen los identificadores de TextView
		// donde se mostrarán las columnas
		int[] to = new int[] { R.id.tag, R.id.value };

		// Se crea el adaptador
		dataAdapter = new SimpleCursorAdapter(this,
				R.layout.lisview_row_cursor, cur, cursorColumn, to, 0);

		listView = (ListView) findViewById(R.id.lista);
		listView.setAdapter(dataAdapter);

		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> listView, View view,
					int position, long id) {
				
				Cursor cursor = (Cursor) listView.getItemAtPosition(position);

				// Get the state's capital from this row in the database.
				String tag = cursor.getString(cursor
						.getColumnIndexOrThrow(RecordTableColumns.COLUMN_TAG));
				String value = cursor.getString(cursor
						.getColumnIndexOrThrow(RecordTableColumns.COLUMN_VALUE));
				
				Toast.makeText(getApplicationContext(), tag+"="+value,
						Toast.LENGTH_SHORT).show();

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		case R.id.menu_help:
			// Creamos una nueva Activity
			Intent newactivity_help = new Intent(this, Help.class);
			startActivity(newactivity_help);
			return true;
		case R.id.menu_settings:
			// Creamos una nueva Activity
			Intent newactivity_settings = new Intent(this, Settings.class);
			startActivity(newactivity_settings);
			return true;
		}

		return false;
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.contextmenu_ver:
			Toast.makeText(this, "Elemento tocado: " + posTouched,
					Toast.LENGTH_SHORT).show();
			break;

		}
		return super.onContextItemSelected(item);
	}

	@Override
	public void onContextMenuClosed(Menu menu) {
		// TODO Auto-generated method stub
		super.onContextMenuClosed(menu);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.context_lista, menu);
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	public boolean onKey(View arg0, int arg1, KeyEvent arg2) {
		// TODO Auto-generated method stub
		return false;
	}

}
