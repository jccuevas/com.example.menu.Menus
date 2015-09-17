package com.example.menu;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;



public class NewListAdapter extends BaseAdapter {

	private final Context context;
	private List<Car> values;
	private Car current = null;
	private View m_parent;
	
	public static final int ROW_BACKGORUND_ALPHA = 50;
	public static final int ROW_SELECTED_ALPHA = 80;

	public NewListAdapter(Context context,List<Car> values) {
		super();
		this.context = context;
		this.values = values;
		

	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		final View rowView = inflater.inflate(R.layout.lisview_row_cars,
				parent, false);

		rowView.setClickable(true);
		
		m_parent=parent;

		final TextView name = (TextView) rowView.findViewById(R.id.tag);
		final TextView brand = (TextView) rowView
				.findViewById(R.id.row_car_brand);
		final TextView hp = (TextView) rowView
				.findViewById(R.id.row_car_hp);
		
		final ImageButton delete = (ImageButton) rowView
			.findViewById(R.id.row_car_delete);

		current = values.get(position);

		if (current != null) {
			name.setText(current.getName());
			brand.setText(current.getBrand());
			hp.setText(String.valueOf(current.getHp()));

			delete.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					values.remove(position);
			
					notifyDataSetInvalidated();
					m_parent.postInvalidate();
					
				}});

		}
		return rowView;
	}




	@Override
	public int getCount() {
		if (this.values != null) {
			return values.size();
		} else {
			return 0;
		}
	}

	@Override
	public Object getItem(int position) {

		if (this.values != null)

			return values.get(position);
		else
			return null;
	}

	@Override
	public long getItemId(int arg0) {
		if (this.values != null)
			return values.get(arg0).getId();
		else
			return -1;
	}



//
//	@Override
//	public void onNewCreature() {
//		this.values=this.board.sortCreaturesByInitiative(false, 20);
//		this.notifyDataSetChanged();
//		
//		
//	}
//
//	@Override
//	public void onCreatureDeleted() {
//		this.values=this.board.sortCreaturesByInitiative(false, 20);
//		this.notifyDataSetChanged();
//		
//	}
//	
	

}
