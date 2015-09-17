package com.example.menu;

import com.example.graphics.Lienzo;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ScaleDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Graficos extends Activity {
	private ImageView logoandroid = null;
	private ImageView logoandroidscale = null;
	private TextView  textscale=null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_graficos);

		logoandroid = (ImageView) findViewById(R.id.imageView_logoandroid);

		ClipDrawable drawable = (ClipDrawable) logoandroid.getDrawable();
		drawable.setLevel((drawable.getLevel() + 1000) % 10000);

		logoandroid.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ClipDrawable drawable = (ClipDrawable) logoandroid
						.getDrawable();
				drawable.setLevel((drawable.getLevel() + 1000) % 10000);

			}
		});

		logoandroidscale = (ImageView) findViewById(R.id.ImageView_logoandroidscale);
		textscale = (TextView)findViewById(R.id.graficos_scale);
		
		logoandroidscale.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ScaleDrawable scale = (ScaleDrawable) logoandroidscale.getDrawable();
				scale.setLevel((scale.getLevel() + 1000) % 10000);
				
				textscale.setText("Nivel="+scale.getLevel());


			}
		});

		Lienzo lienzo = (Lienzo) findViewById(R.id.custom_drawable_lienzo);
		lienzo.setOnTouchListener(lienzo);

	}

	/**
	 * Inicia una transición entre dos drawables: TransitionDrawable La el
	 * drawable de transición se ha puesto como fondo del ImageView
	 * 
	 * @param param
	 */
	public void onTransition(View param) {
		ImageView image = (ImageView) findViewById(R.id.imageViewTransition);
		TransitionDrawable drawable = (TransitionDrawable) image.getDrawable();
		drawable.startTransition(2000);// 500 ms
		// drawable.reverseTransition(2000);

	}

	/**
	 * Inicia una animación de deformación de un drawable almacenada en un XML,
	 * en este caso en anim/tween.xml
	 * 
	 * @param param
	 */
	public void onTween(View param) {
		ImageView image = (ImageView) findViewById(R.id.imageViewTween);

		Animation h = AnimationUtils.loadAnimation(this, R.anim.tween);
		image.startAnimation(h);

	}

	/**
	 * Inicia una animación por pasos definida en un xml
	 * 
	 * @param param
	 */
	public void onFrame(View param) {
		ImageView image = (ImageView) findViewById(R.id.imageViewFrame);

		AnimationDrawable animation = (AnimationDrawable) image.getDrawable();

		if (animation.isRunning())
			animation.stop();
		else
			animation.start();

	}
}
