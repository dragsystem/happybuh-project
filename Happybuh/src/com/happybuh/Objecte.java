package com.happybuh;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Objecte {
	
	float x, y;
	float vx,vy;
	float tx, ty;
	public Bitmap nuvol;
	//public Bitmap plataf;
	public Objecte(){}
	public Objecte(Bitmap nuvolnou, float i, float j,float ntx, float nty, float nvx, float nvy) {
		x = i;
		y = j;
		vx = nvx;
		vy = nvy;
		tx = ntx;
		ty = nty;
		nuvol = Bitmap.createScaledBitmap(nuvolnou,(int)ntx,(int)nty,false);
	}
	
	//Creacio de plataformes
	public Objecte(Bitmap plataforma, float i, float j,float ntx, float nty) {
		x = i;
		y = j;
		tx = ntx;
		ty = nty;
		nuvol = Bitmap.createScaledBitmap(plataforma,(int)ntx,(int)nty,false);
	}
	
	public void actualitza() {
		x+=vx;
		y+=vy;
	}
	
	public void draw(Canvas canvas) {
		canvas.drawBitmap(nuvol, x, y, null);
	}
}
