package com.example.graphics;

import com.example.menu.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

public class BitmapIds {

	public static final int DISCO_1 = 0;
	public static final int DISCO_2 = 1;

	protected Context context;

	public static final Integer[] mBitmaps = { android.R.drawable.bottom_bar };

	public BitmapIds(Context context) {
		this.context = context;
	}

	public Bitmap getBitmap(int id) {
		Bitmap bitmap = null;

		switch (id) {
		case DISCO_1:

			final BitmapDrawable drawable1 = (BitmapDrawable) context
					.getResources().getDrawable(R.drawable.disco01);
			bitmap = drawable1.getBitmap();
			break;
		case DISCO_2:

			final BitmapDrawable drawable2 = (BitmapDrawable) context
					.getResources().getDrawable(R.drawable.disco02);
			bitmap = drawable2.getBitmap();
			break;

		}
		return bitmap;
	}

}
