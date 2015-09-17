package com.example.menu;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class Fragmentos extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_fragments);

	}

	public void show() {
		FragmentManager fm = getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();

		Fragment fragment = fm.findFragmentById(R.id.fragment_viewer);

		if (fragment != null) {

			if (!fragment.isVisible()) {
				ft.show(fragment);
				ft.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				ft.addToBackStack(null);
				ft.commit();
			}

		}

	}

	public void replace() {
		FragmentManager fm = getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();

		Fragment fragment = fm.findFragmentById(R.id.fragment_container);

		if (fragment == null) {

			Barra barra = new Barra();
			ft.add(R.id.fragment_viewer, barra);
			ft.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			ft.addToBackStack(null);
			ft.commit();

		} else {
			Barra barra = new Barra();
			
			ft.replace(R.id.fragment_viewer, barra);
			ft.remove(fragment);
			// ft.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			ft.addToBackStack(null);
			ft.commit();

		}

	}

	public void hide() {
		FragmentManager fm = getFragmentManager();
		Fragment fragment = fm.findFragmentById(R.id.fragment_viewer);

		FragmentTransaction ft = fm.beginTransaction();

		if (fragment != null) {

			ft.hide(fragment);
			ft.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			ft.addToBackStack(null);
			ft.commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_fragmentos, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_hidePanel:
			hide();
			return true;
		case R.id.menu_showPanel:
			show();
			return true;
		case R.id.menu_replacePanel:
			replace();
			return true;
		}
		return false;
	}

}
