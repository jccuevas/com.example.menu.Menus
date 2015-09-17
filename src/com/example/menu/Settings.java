package com.example.menu;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

public class Settings extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);

	}

	public void onClickShow(View view) {
		FragmentManager fm = getFragmentManager();
		Fragment fragment = fm.findFragmentById(R.id.fragment_list);

		FragmentTransaction ft = fm.beginTransaction();

		if (fragment != null)
			ft.remove(fragment);

		Lista lista = new Lista();

		ft.replace(R.id.fragment_viewer, lista);
		ft.addToBackStack(null);
		ft.commit();

	}

}
