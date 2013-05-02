package com.happybuh;

import java.util.Random;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Disparo {
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
	
	public Disparo(float nx, float ny, float ntx, float nty) {
		options = new BitmapFactory.Options();
		options.inPreferredConfig = Bitmap.Config.ARGB_8888;
		resources = GV.Activities.worldgame.getResources();
		x = nx;
		y = ny;
		tx = ntx;
		ty = nty;
		rand = new Random();
//		vel = (GV.widtthpercent(0.001f) + (float)rand.nextInt((int)GV.widtthpercent(0.002f)))*direccio;
		vel = GV.Screen.metrics.heightPixels*0.005f;
		img = BitmapFactory.decodeResource(resources, R.drawable.galleta_disparo2, options);
		img = Bitmap.createScaledBitmap(img,(int)tx,(int)ty,false);
	}
	
	public void actualitza(float desplazamiento) {
		 y += vel;
		 x += desplazamiento;
		//if() choque();
	}
	
	public int choque(int num) {
		float posx = GV.puntuacio_world.prot.x;
		float tamx = GV.puntuacio_world.prot.tx;
		float posy = GV.puntuacio_world.prot.y;
		float tamy = GV.puntuacio_world.prot.ty;
		float LacxM = x + tx/2;
		float LacyM = y + ty/2;
		if(posx <= LacxM && posx+tamx >= LacxM && posy <= LacyM && posy+tamy >= LacyM) {
			GV.puntuacio_world.vides--;
			return num;
		}
		else return -1;
	}
	
	public void draw(Canvas canvas) {
		canvas.drawBitmap(img, x, y, null);
	}
}
