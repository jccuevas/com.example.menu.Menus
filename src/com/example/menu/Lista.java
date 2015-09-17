package com.example.menu;



import android.app.ListFragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Lista extends ListFragment {

	 boolean mDualPane=true;
	    int mCurCheckPosition = 0;
	
	
	
	 @Override
	    public void onActivityCreated(Bundle savedInstanceState) {
	        super.onActivityCreated(savedInstanceState);

	        // Populate list with our static array of titles.
	        String[] values = new String[] { "uno", "dos"};
	        

	        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
	                android.R.layout.simple_list_item_1, values);
	            setListAdapter(adapter);
	   
	    }

	
	 @Override
	    public void onSaveInstanceState(Bundle outState) {
	        super.onSaveInstanceState(outState);
	        outState.putInt("curChoice", mCurCheckPosition);
	    }

	    @Override
	    public void onListItemClick(ListView l, View v, int position, long id) {
	    	Panel panel = (Panel) getFragmentManager().findFragmentById(R.id.fragment_viewer);
            if (panel != null) {
                // Make new fragment to show this selection.
                panel.publica("Pulsado "+position);
            }
	    }

	 
	
}
