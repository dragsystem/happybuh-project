package com.happybuh;

import java.util.Random;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;

public class Plataforma {
	public float x;
	public float y;
	public float vx;
	public float tx;
	public float ty;
	public float vel;
	private Random rand;
	public Bitmap img;
	BitmapFactory.Options options;
	public float suelo;
	Resources resources;
	
	public Plataforma(Bitmap bm,float nx, float ny, float ntx, float nty) {
		options = new BitmapFactory.Options();
		options.inPreferredConfig = Bitmap.Config.ARGB_8888;
		resources = GV.Activities.worldgame.getResources();
		x = nx;
		y = ny;
		tx = ntx;
		ty = nty;
		img = Bitmap.createScaledBitmap(bm,(int)tx,(int)ty,false);
		
	}
	
	public void actualitza(float desplazamiento) {
		 x += desplazamiento;
	}
	
	public void choque() {
		float posx = GV.puntuacio_world.prot.x;
		float tamx = GV.puntuacio_world.prot.tx;
		float posy = GV.puntuacio_world.prot.y;
		float tamy = GV.puntuacio_world.prot.ty;
		float vely = GV.puntuacio_world.prot.vy;
		
		if(vely > 0) {
			if(posx+tamx >= x && posx <= x+tx && posy+tamy >= y && posy+tamy <= y+ty ) {
//				Log.v("Toco plataforma", "PLATAFORA VELOCIDAD "+GV.puntuacio_world.prot.vy);
				GV.puntuacio_world.prot.vy = 0;
				GV.puntuacio_world.prot.y = y - GV.puntuacio_world.prot.ty;
				GV.puntuacio_world.cplat = 1;
				GV.puntuacio_world.csalto = 0;
			}
			else GV.puntuacio_world.cplat = 0;
		}
//		else if(GV.puntuacio_world.cplat == 1 && vely == 0) {
//			if(posx+tamx <= x || posx >= x+tx) {
//				Log.v("bajo plataforma ", "bajo a VELOCIDAD "+GV.puntuacio_world.prot.vy);
//				GV.puntuacio_world.prot.vy = GV.Screen.metrics.heightPixels*0.005f;
//				GV.puntuacio_world.csalto = 0;
//				GV.puntuacio_world.salto = 0;
//				GV.puntuacio_world.cplat = 0;
//			}
//		}
//		
	}
	
//	public void movimiento_prota() {
//		if (GV.puntuacio_world.csalto == 0) {
//			GV.puntuacio_world.prot.vy = GV.Screen.metrics.heightPixels*0.005f;
//			GV.puntuacio_world.csalto = 1;
//			GV.puntuacio_world.salto = 0;
//		}
//	}
	
	
	public void draw(Canvas canvas) {
		canvas.drawBitmap(img, x, y, null);
	}
}
