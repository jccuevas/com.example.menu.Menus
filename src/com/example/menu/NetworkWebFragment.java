package com.example.menu;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NetworkWebFragment extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
//		FragmentManager fm=getFragmentManager();
		
		//if(fm.findFragmentById(R.id.layout_fragment_network_navigation)!=null)
			//setHasOptionsMenu(true);//Para comunicar que el panel quiere recibir los eventos de la barra de acción
		
		return inflater.inflate(R.layout.layout_network_web, null);
		
	}
	
	public void setText(String text)
	{
		TextView t= (TextView) getActivity().findViewById(R.id.network_web_textView_text);
		if(t!=null)
		{
			t.setText(text);
		}
		
	}
	

}
