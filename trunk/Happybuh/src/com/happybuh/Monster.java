package com.happybuh;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Monster {
	public float x;
	public float y;
	public float vx;
	public float vy;
	public float tx;
	public float ty;
	public int marca;
	public int vides;
	private Bitmap img;
	//Sprites
	public boolean sprite; //es sprite o no
	private int ih;		//numero d'imatges horitzontals a l'sprite
	private int iv;		//numero d'imatges verticals a l'sprite
	private int sfcountd;	//sprite frame count dreta
	private int sfcounte;	//sprite frame count esquerra
	private int fpi;		//frames per imatge
	private Rect src;
	private Rect dest;
	public boolean acabat;
	private int mov;
	
	public Monster(Bitmap bm, float nx, float ny, float ntx, float nty, float nvx, float nvy, int nih, int niv, int nfpi) {
		x = nx;
		y = ny;
		tx = ntx;
		ty = nty;
		vx = 0;
		vy = 0;
		sprite = true;
		acabat = false;
		ih = nih;
		iv = niv;
		sfcountd = 1;
		sfcounte = 1;
		fpi = nfpi;
		mov = 1;
		src = new Rect();
		dest = new Rect();
		img = Bitmap.createScaledBitmap(bm,(int)tx*ih,(int)ty*iv,false);
	}
	
	public void actualitza(float desplazamiento) {
		y += vy;
		x += desplazamiento;
		if(sprite && sfcounte/fpi>=2) sfcounte = 0;
		if(sprite && sfcountd/fpi>=(ih*iv)) sfcountd = 2;
	}
	
	public void draw(Canvas canvas) {
		if(GV.puntuacio_world.disparo == 0) draw_esquerrad(canvas);
		else draw_esquerra(canvas);
	}
	
	public void draw_esquerra(Canvas canvas) {
		src.set(0,0, (int)(tx), (int)(ty));
		dest.set((int)x,(int)y,(int)(x+tx),(int)(y+ty));
		canvas.drawBitmap(img, src, dest, null);
	}
	
	public void draw_esquerrad(Canvas canvas) {
		src.set((int)(tx),0, (int)(2*tx), (int)(ty));
		dest.set((int)x,(int)y,(int)(x+tx),(int)(y+ty));
		canvas.drawBitmap(img, src, dest, null);
		float ntx = (float)(GV.Screen.metrics.widthPixels*0.075);
		float nty = (float)((GV.Screen.metrics.heightPixels/1.5)*0.1);
		GV.puntuacio_world.galleta_d.add(new Disparo(x, y+ty*0.5f, ntx, nty));
	}
	
}
