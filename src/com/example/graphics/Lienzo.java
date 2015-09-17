package com.example.graphics;

import com.example.menu.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import android.view.View.OnTouchListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;

public class Lienzo extends View implements OnTouchListener, OnScrollListener {

	Bitmap bitmap = null;
	Context context = null;
	Rect src, dst;
	float x = 0, y = 0;

	final Paint paint = new Paint();
	
	public Lienzo(Context context) {
		super(context);

		this.context = context;

		if(!isInEditMode())
		{
		initialize();
		}

		// TODO Auto-generated constructor stub
	}

	public Lienzo(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		this.context = context;

		if(!isInEditMode())
		{
		initialize();
		}
	}

	public Lienzo(Context context, AttributeSet attrs) {
		super(context, attrs);

		this.context = context;
		if(!isInEditMode())
		{
		initialize();
		}
	

	}

	
	protected void initialize() {
		
		
		if (context != null) {
			BitmapDrawable bd = (BitmapDrawable) this.context.getResources()
					.getDrawable(R.drawable.disco01);
			bitmap = bd.getBitmap();

			src = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

			dst = new Rect();
		}

	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// if (bitmap != null) {
		// widthMeasureSpec = bitmap.getWidth();
		// heightMeasureSpec = bitmap.getHeight();
		// } else {
		// widthMeasureSpec = 40;
		// heightMeasureSpec = 40;
		//
		// }
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	public void onDraw(Canvas canvas) {

		String texto;
		
		if (bitmap != null) {

			texto = "x=" + x + " y=" + y;
			float l = paint.measureText(texto);
			
			dst.set((int) x, (int) y, (int) (x + l), (int) y + 40);
			paint.setColor(Color.RED);

			canvas.drawBitmap(bitmap, src, dst, paint);
			canvas.drawText(texto, x, y, paint);

		} else {
			paint.setColor(Color.RED);
			canvas.drawRect(x, y, x + 40, y + 40, paint);
		}

	}

	public boolean onTouch(View v, MotionEvent event) {
		x = event.getX();
		y = event.getY();
		invalidate();
		return false;
	}

	public void onScroll(AbsListView arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

	}

	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub

	}

}
