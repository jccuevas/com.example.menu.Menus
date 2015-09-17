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

public class FragmentMenuNetworking extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
			
		
		View fragment = inflater.inflate(R.layout.layout_fragment_menu_networking, null);
		
		Button b = (Button)fragment.findViewById(R.id.framentmenu_launchbutton);
		
		b.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent newactivity_networking = new Intent(getActivity(), Network.class);
				startActivity(newactivity_networking);
				
			}});
		return fragment;
		
	}
	
	public void publica(CharSequence text)
	{

		TextView t= (TextView) getActivity().findViewById(R.id.fragmentinfo_helptext);
		if(t!=null)
		{
			t.setText(text);
		}
	}

	
	
	
	
	
}
