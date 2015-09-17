package com.example.menu;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class FragmentosDinamicos extends Activity {
	public static final String TAG_PANEL = "panel";
	public static final String TAG_BARRA = "barra";

	private FragmentManager mManager = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragmentdynamic_layout);

		mManager = getFragmentManager();

		Barra barra = new Barra();

		FragmentTransaction ft = mManager.beginTransaction();

		ft.add(R.id.fragment_container, barra, TAG_BARRA);

		ft.commit();

	}

	public void show() {

		Fragment fragment = mManager.findFragmentByTag(TAG_PANEL);

		FragmentTransaction ft = mManager.beginTransaction();

		if (fragment == null) {

			Panel panel = new Panel();
			// Barra barra = new Barra();
			ft.add(R.id.fragment_container, panel, TAG_PANEL);

			ft.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
			ft.addToBackStack(null);
			ft.commit();

		} else
		{
			
			// Barra barra = new Barra();
			ft.show(fragment);

			ft.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
			ft.addToBackStack(null);
			ft.commit();
		}

	}

	public void replace() {

		Fragment fragment = mManager.findFragmentByTag(TAG_BARRA);

		FragmentTransaction ft = mManager.beginTransaction();

		if (fragment != null) {

			Panel panel = new Panel();

			ft.replace(R.id.fragment_container, panel);
			ft.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			ft.addToBackStack(null);
			ft.commit();

		}

	}

	public void hide() {

		Fragment fragment = mManager.findFragmentByTag(TAG_PANEL);

		FragmentTransaction ft = mManager.beginTransaction();

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
		}
		return false;
	}

}
