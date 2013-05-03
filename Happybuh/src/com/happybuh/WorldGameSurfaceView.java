package com.happybuh;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class WorldGameSurfaceView extends SurfaceView {
	private SurfaceHolder holder;
	private WorldGameThread gtdt;
	Resources resources;
	BitmapFactory.Options options;
	
	//control thread
	private boolean surfacecreated;
	private boolean threadrunning;
	//fons
	private Paint fons;
	private Bitmap scaled;
	
	public float pos_actx;
	public float pos_acty;
	public float pos_actx2;
	public float pos_acty2;
	
	//joc
	final float SPEED = 10;
	private CrearMapa mapa;
	public Protagonista prota;

	public WorldGameSurfaceView(Context context) {
		super(context);
		init();
	}

	public WorldGameSurfaceView(Context context, AttributeSet attrs,int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public WorldGameSurfaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init() {
		surfacecreated = false;
		threadrunning = false;
		holder = getHolder();
		holder.addCallback( new Callback() {
			
			public void surfaceDestroyed(SurfaceHolder holder) {
				stopthread();
				surfacecreated = false;
			}
			
			public void surfaceCreated(SurfaceHolder holder) {
				holder.setFormat(PixelFormat.RGBA_8888);
				
				fons = new Paint(); 
			    Shader shader = new LinearGradient(0,0,GV.Screen.metrics.widthPixels,GV.Screen.metrics.heightPixels,new int[]{0xFFA4D8FF,0xFF0031D2},null,Shader.TileMode.CLAMP);
			    fons.setShader(shader);
			    

			        Bitmap background = BitmapFactory.decodeResource(getResources(), R.drawable.fondo1);
			        float scale = (float)background.getHeight()/(float)getHeight();
			        int newWidth = Math.round(background.getWidth()/scale);
			        int newHeight = Math.round(background.getHeight()/scale);
			        scaled = Bitmap.createScaledBitmap(background, newWidth, newHeight, true);
			    
			    GV.puntuacio_world.coins = 0;
			    GV.puntuacio_world.vides = 3;
			    GV.puntuacio_world.disparo = 150;
			    GV.puntuacio_world.gameover = 0;
			    GV.puntuacio_world.fast_die = 0;
			    GV.puntuacio_world.num_monedas = 0;
			    prota = new Protagonista();
			    mapa = new CrearMapa(0);
				surfacecreated = true;
			}
			
			public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
				startthread();
			}
		});  
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
//		canvas.drawRect(0,0,getWidth(),getHeight(), fons);
		if (GV.puntuacio_world.gameover == 0) {
			canvas.drawBitmap(scaled, 0, 0, null); // draw the background
			mapa.plataforma_limit();
			mapa.draw(canvas);
			if(GV.puntuacio_world.fast_die == 1) prota.actualitza_die();
			else prota.actualiza();
			prota.draw(canvas);
			if(GV.puntuacio_world.disparo == 0) {
				GV.puntuacio_world.disparo = 150;
			}
			--GV.puntuacio_world.disparo;
			//GV.Activities.worldgame.handler.sendEmptyMessage(0);
		}
		else  if(GV.puntuacio_world.gameover == 1){
//			GV.Instancies.game11layout.acaba();
			canvas.drawColor(Color.parseColor("#FFCC00"));
			GV.Activities.worldgame.handler.sendEmptyMessage(3);
		}
	}
	
	/*public void reset(){
	    GV.puntuacio_world.vides = 3;
	    GV.puntuacio_world.disparo = 150;
	    GV.puntuacio_world.gameover = 0;
	    GV.puntuacio_world.fast_die = 0;
	    prota = new Protagonista();
	    mapa = new CrearMapa(0);
	    GV.puntuacio_world.gameover = 0;
	    GV.Activities.worldgame.handler.sendEmptyMessage(0);
	}*/
	
/*	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(event.getActionMasked() == MotionEvent.ACTION_POINTER_DOWN) {
			return true;
		}
		if(event.getActionMasked() == MotionEvent.ACTION_UP) {
			for(int i = 0; i < GV.puntuacio_world.plataformes_mapa.size(); ++i) {
				GV.puntuacio_world.plataformes_mapa.get(i).x = 0;
			}
			return true;
		}
		
		return super.onTouchEvent(event);
	}*/
/*	@Override
	public boolean onTouchEvent(MotionEvent e) {
		int pointerID;
//		pos_actx = e.getX();
//		pos_acty = e.getY();
		float tam1 = (float)GV.Screen.metrics.widthPixels/7;
		float tam2 = (float)GV.Screen.metrics.widthPixels/7*2;
		float tam3 = (float)GV.Screen.metrics.widthPixels/7*6;
		float tam = (float)GV.Screen.metrics.widthPixels;
		float tamy = (float)GV.Screen.metrics.heightPixels/12*11;
		switch(e.getAction()) {
		case MotionEvent.ACTION_DOWN:
			pointerID = (e.getAction() & MotionEvent.ACTION_POINTER_ID_MASK) >> MotionEvent.ACTION_POINTER_ID_SHIFT;
			pos_actx =  e.getX(pointerID);
			pos_acty =  e.getY(pointerID);
			if(pos_acty >= tamy) {
				if(pos_actx >= 0 && pos_actx <= tam1) GV.puntuacio_world.control = 3;
				else if(pos_actx > tam1 && pos_actx <= tam2) GV.puntuacio_world.control = 1;
				else if (pos_actx > tam3 && pos_actx <= tam) {
					if(GV.puntuacio_world.csalto == 0) {
						GV.puntuacio_world.salto = 1;
						GV.puntuacio_world.csalto = 1;
					}
				}
			}
			break;
		case MotionEvent.ACTION_POINTER_2_DOWN:
			pointerID = (e.getAction() & MotionEvent.ACTION_POINTER_ID_MASK) >> MotionEvent.ACTION_POINTER_ID_SHIFT;
			pos_actx2 = e.getX(pointerID);
			pos_acty2 = e.getY(pointerID);
			if(pos_acty2 >= tamy) {
				if(pos_actx2 >= 0 && pos_actx2 <= tam1) GV.puntuacio_world.control = 3;
				else if(pos_actx2 > tam1 && pos_actx2 <= tam2) GV.puntuacio_world.control = 1;
				else if (pos_actx2 > tam3 && pos_actx2 <= tam) {
					if(GV.puntuacio_world.csalto == 0) {
						GV.puntuacio_world.salto = 1;
						GV.puntuacio_world.csalto = 1;
					}
				}
			}
			break;
		case MotionEvent.ACTION_UP:
			GV.puntuacio_world.control = 0;
			break;
		case MotionEvent.ACTION_POINTER_2_UP:
			break;
		}

		return true;
		
	}*/
	
	@Override
	public boolean onTouchEvent(MotionEvent e) {
		int pointerID;
//		pos_actx = e.getX();
//		pos_acty = e.getY();
		float tam1 = (float)GV.Screen.metrics.widthPixels/7;
		float tam2 = (float)GV.Screen.metrics.widthPixels/7*2;
		float tam3 = (float)GV.Screen.metrics.widthPixels/7*6;
		float tam = (float)GV.Screen.metrics.widthPixels;
		float tamy = (float)GV.Screen.metrics.heightPixels/12*11;
		switch(e.getAction()) {
		case MotionEvent.ACTION_DOWN:
			pointerID = (e.getAction() & MotionEvent.ACTION_POINTER_ID_MASK) >> MotionEvent.ACTION_POINTER_ID_SHIFT;
			pos_actx =  e.getX(pointerID);
			pos_acty =  e.getY(pointerID);
			
			if(boton_izq(pos_actx, pos_acty)) {
				GV.Activities.worldgame.izquierda.setImageResource(R.drawable.b_left_p);
				GV.puntuacio_world.control = 3;
			}
			if(boton_der(pos_actx, pos_acty)){
				GV.Activities.worldgame.derecha.setImageResource(R.drawable.b_right_p);
				GV.puntuacio_world.control = 1;
			}
			if(boton_salto(pos_actx, pos_acty)) {
				GV.Activities.worldgame.salto.setImageResource(R.drawable.b_jump_p);
				if(GV.puntuacio_world.csalto == 0) {
					GV.puntuacio_world.salto = 1;
					GV.puntuacio_world.csalto = 1;
				}
			}
			break;
		case MotionEvent.ACTION_POINTER_2_DOWN:
			pointerID = (e.getAction() & MotionEvent.ACTION_POINTER_ID_MASK) >> MotionEvent.ACTION_POINTER_ID_SHIFT;
			pos_actx2 = e.getX(pointerID);
			pos_acty2 = e.getY(pointerID);
			if(boton_izq(pos_actx2, pos_acty2)) {
				GV.puntuacio_world.control = 3;
			}
			if(boton_der(pos_actx2, pos_acty2)) {
				GV.puntuacio_world.control = 1;
			}
			if(boton_salto(pos_actx2, pos_acty2)) {
				GV.Activities.worldgame.salto.setImageResource(R.drawable.b_jump_p);
				if(GV.puntuacio_world.csalto == 0) {
					GV.puntuacio_world.salto = 1;
					GV.puntuacio_world.csalto = 1;
				}
			}
			break;
		case MotionEvent.ACTION_UP:
			GV.puntuacio_world.control = 0;
			GV.Activities.worldgame.salto.setImageResource(R.drawable.b_jump);
			GV.Activities.worldgame.izquierda.setImageResource(R.drawable.b_left);
			GV.Activities.worldgame.derecha.setImageResource(R.drawable.b_right);
			break;
		case MotionEvent.ACTION_POINTER_2_UP:
			break;
		}

		return true;
		
	}
	
	public boolean boton_izq(float posx, float posy) {
		if(posx >= GV.World_mov.ll_left && posx <= GV.World_mov.ll_left + GV.World_mov.izq_right 
				&& posy >= GV.World_mov.ll_top && posy <= GV.World_mov.ll_bot)return true;
		return false;
	}
	public boolean boton_der(float posx, float posy) {
		if(posx >= GV.World_mov.ll_left + GV.World_mov.der_left && posx <= GV.World_mov.ll_right 
				&& posy >= GV.World_mov.ll_top && posy <= GV.World_mov.ll_bot)return true;
		return false;
	}
	
	public boolean boton_salto(float posx, float posy) {
		if(posx >= GV.World_mov.s_left && posx <= GV.World_mov.s_right
				&& posy >= GV.World_mov.s_top && posy <= GV.World_mov.s_bot) return true;
		return false;
	}
	
	public void startthread() {
		if(!surfacecreated) return;
		if(threadrunning) return;
		gtdt = new WorldGameThread(this);
		gtdt.setRunning(true);
		gtdt.start();
		threadrunning = true;
	}
	
	public void stopthread() {
		if(!surfacecreated) return;
		if(!threadrunning) return;
		boolean retry = true;
		while (retry) {
			try {
				gtdt.setRunning(false);
				gtdt.join();
				threadrunning = false;
				retry = false;
				
			} catch (InterruptedException e) {}
		}
	}
}
