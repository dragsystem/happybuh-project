package com.happybuh;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Tramps {
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
	private int sfcount;	//sprite frame count
	private int conttramp;
	private int fpi;		//frames per imatge
	private Rect src;
	private Rect dest;
	public boolean acabat;
	private int mov;
	
	public Tramps(Bitmap bm, float nx, float ny, float ntx, float nty, float nvx, float nvy, int nih, int niv, int nfpi) {
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
		sfcount = 1;
		conttramp = 0;
		fpi = nfpi;
		mov = 1;
		src = new Rect();
		dest = new Rect();
		//y = GV.heightpercent(100f) - ty;
		y = y - ty;
		img = Bitmap.createScaledBitmap(bm,(int)tx*ih,(int)ty*iv,false);
	}
	
	public void actualitza(float desplazamiento) {
		y += vy;
		x += desplazamiento;
		if(sprite && sfcount/fpi>=(ih*iv)) {
			//acabat = true;
			sfcount = 0;
		}
	}
	
	public void draw(Canvas canvas) {
		if (!sprite) canvas.drawBitmap(img, x, y, null);
		else {
			if(conttramp %1000 == 0) {
				int s = sfcount/fpi;
//				src.set((int)((s%ih)*tx), (int)((s/iv)*ty), (int)((s%ih)*tx+tx), (int)((s/iv)*ty+ty));
				src.set((int)((s%ih)*tx), (int)(0), (int)((s%ih)*tx+tx), (int)(ty));
				dest.set((int)x,(int)y,(int)(x+tx),(int)(y+ty));
				canvas.drawBitmap(img, src, dest, null);
				sfcount++;
				conttramp = -1;
			}
			++conttramp;
		}
		
	}
		
	public void choque() {
		float posx = GV.puntuacio_world.prot.x;
		float tamx =  GV.puntuacio_world.prot.x + GV.puntuacio_world.prot.tx;
		float posy = GV.puntuacio_world.prot.y;
		float tamy = GV.puntuacio_world.prot.y + GV.puntuacio_world.prot.ty;
		float LacxM = x + tx;
		float LacyM = y + ty;
		float vel = GV.puntuacio_world.prot.vy;
		
		if(((posx <= x +tx && posx >= x) || (tamx <= x+tx && tamx >= x)) && (tamy > y) && vel >= 0) {
			GV.puntuacio_world.fast_die = 1;
		}
		
	}
	
}
