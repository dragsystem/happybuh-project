package com.happybuh;

import java.util.Random;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;

public class Lacasito {
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
	
	public Lacasito(Bitmap bm,float nx, float ny, float ntx, float nty) {
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
	
	public int choques(int num) {
		float posx = GV.puntuacio_world.prot.x;
		float tamx = GV.puntuacio_world.prot.tx;
		float posy = GV.puntuacio_world.prot.y;
		float tamy = GV.puntuacio_world.prot.ty;
		float LacxM = x + tx/2;
		float LacyM = y + ty/2;
		if(posx <= LacxM && posx+tamx >= LacxM && posy <= LacyM && posy+tamy >= LacyM) {
			GV.puntuacio_world.num_monedas--;
			return num;
		}
		else {
			return -1;
		}
		
	}
	
	public void draw(Canvas canvas) {
		canvas.drawBitmap(img, x, y, null);
	}
}
