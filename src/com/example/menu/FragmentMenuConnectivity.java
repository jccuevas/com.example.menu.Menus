package com.example.menu;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class FragmentMenuConnectivity extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View fragment = inflater.inflate(
				R.layout.layout_fragment_menu_connectivity, null);

		Button newExample = (Button) fragment
				.findViewById(R.id.framentmenu_launchbutton);
		newExample.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent newactivity_connectivity = new Intent(getActivity(),
						Connectivity.class);
				startActivity(newactivity_connectivity);

			}
		});
		return fragment;

	}

	public void publica(CharSequence text) {

		TextView t = (TextView) getActivity().findViewById(
				R.id.fragmentinfo_helptext);
		if (t != null) {
			t.setText(text);
		}
	}

}
