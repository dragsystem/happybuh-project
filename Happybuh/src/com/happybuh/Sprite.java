package com.happybuh;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Sprite {
	private Objecte objecte;
	private Bitmap explosio;
	float x, y;
	float vx,vy;
	float tx, ty;
	//Sprites
	private int ih;		//numero d'imatges horitzontals a l'sprite
	private int iv;		//numero d'imatges verticals a l'sprite
	private int sfcount;	//sprite frame count
	private int fpi;		//frames per imatge
	private Rect src;
	private Rect dest;
	public boolean acabat;	


	public Sprite(Bitmap bm, float nx, float ny, float ntx, float nty, float nvx, float nvy, int nih, int niv, int nfpi) {
		x = nx;
		y = ny;
		tx = ntx;
		ty = nty;
		vx = nvx;
		vy = nvy;
		acabat = false;
		ih = nih;
		iv = niv;
		sfcount = -1;
		fpi = nfpi;
		src = new Rect();
		dest = new Rect();
		explosio = Bitmap.createScaledBitmap(bm,(int)tx*ih,(int)ty*iv,false);
	}
	
	public void draw(Canvas canvas) {
		int s = sfcount/fpi;
		src.set((int)((s%ih)*tx), (int)((s/iv)*ty), (int)((s%ih)*tx+tx), (int)((s/iv)*ty+ty));
		dest.set((int)x,(int)y,(int)(x+tx),(int)(y+ty));
		canvas.drawBitmap(explosio, src, dest, null);
		sfcount++;
	}
	
	public void actualitza() {
		x += vx;
		y += vy;
		if(sfcount/fpi>(ih*iv)) acabat = true;
	}
}