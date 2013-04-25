package com.happybuh;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

public class BubbleGameSurfaceView extends SurfaceView {
	private boolean surfacecreated;
	private boolean threadrunning;
	private BubbleGameThread thread;
	private SurfaceHolder holder;
	private Paint fons;
	private Base base;
	private Peces peces;
	private float pos_act, pos_mou;
	private boolean mou;

	public BubbleGameSurfaceView(Context context) {
		super(context);
		init();
	}
	
	public BubbleGameSurfaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	public BubbleGameSurfaceView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}
	
	private void init() {
		surfacecreated = false;
		threadrunning = false;
		holder = getHolder();
		GV.Instancies.bubbleview = this;
		holder.addCallback(new Callback() {
			
			public void surfaceDestroyed(SurfaceHolder holder) {
				stopthread();
				surfacecreated = false;
			}
			
			public void surfaceCreated(SurfaceHolder holder) {
				holder.setFormat(PixelFormat.RGBA_8888);
				fons = new Paint();
				base = new Base();
				peces = new Peces();
				Shader shader = new LinearGradient(GV.Screen.metrics.widthPixels,0,GV.Screen.metrics.widthPixels,GV.Screen.metrics.heightPixels/1.2f,new int[]{0xFF000033,0xFF000099},null,Shader.TileMode.CLAMP);
				//fons.setColor(0xFF00CCFF);
				fons.setShader(shader);
				surfacecreated = true;
			}
			
			public void surfaceChanged(SurfaceHolder holder, int format, int width,
					int height) {
					startthread();
			}
		});
			
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawRect(0, 0, getWidth(),getHeight(), fons);
		peces.actualitza();
		peces.draw(canvas);
		base.draw(canvas);
		colisions();
	}
	
	private void colisions() {
		peces.colisions(base.px, base.py, base.tx, base.ty);
	}
	
	public void startthread() {
		if(!surfacecreated) return;
		if(threadrunning) return;
		thread = new BubbleGameThread(this);
		thread.setRunning(true);
		thread.start();
		threadrunning = true;
	}
	public void stopthread() {
		if(!surfacecreated) return;
		if(!threadrunning) return;
		boolean retry = true;
		while (retry) {
			try {
				thread.setRunning(false);
				thread.join();
				threadrunning = false;
				retry = false;
				
			} catch (InterruptedException e) {}
		}
	}

	public boolean onTouchEvent(MotionEvent e) {
		// detectamos movimiento
		if(e.getAction() == e.ACTION_DOWN) {
			pos_act = e.getX();
		}
		if(e.getAction() == e.ACTION_MOVE) {
			pos_mou = e.getX()-pos_act;
			base.movex(pos_mou*1.5f);
			pos_act = e.getX();
		}
		return true;
	}
}
