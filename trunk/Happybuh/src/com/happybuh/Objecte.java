package com.happybuh;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

public class Objecte {
	int num_burbuja;
	float x, y;
	float vx,vy;
	float tx, ty;
	public Bitmap img;
	//public Bitmap plataf;
	public boolean platafd;
	//Sprites
		private boolean sprite; //es sprite o no
		private int ih;		//numero d'imatges horitzontals a l'sprite
		private int iv;		//numero d'imatges verticals a l'sprite
		public int sfcount;	//sprite frame count
		private int fpi;		//frames per imatge
		private Rect src;
		private Rect dest;
		public boolean acabat;
		public boolean tocat;
		public int preu_moneda;
		public int moneda;
		private Random rand;
		public int marxar;
		public int id;
	public Objecte(){}
	//JUGADOR
	public Objecte(Bitmap nuvolnou, float i, float j,float ntx, float nty, float nvx, float nvy) {
		tocat = false;
		x = i;
		y = j;
		vx = nvx;
		vy = nvy;
		tx = ntx;
		ty = nty;
		img = Bitmap.createScaledBitmap(nuvolnou,(int)ntx,(int)nty,false);
		GV.posiplataforma.posy = 0;
		platafd = false;
	}
	
	//Creacio de plataforma amb sprite
	public Objecte(Bitmap bm, float nx, float ny, float ntx, float nty, float nvx, float nvy, int nih, int niv, int nfpi, int cont) {
		tocat = false;
		x = nx;
		y = ny;
		tx = ntx;
		ty = nty;
		vx = nvx;
		vy = nvy;
		sprite = true;
		acabat = false;
		ih = nih;
		iv = niv;
		sfcount = -1;
		fpi = nfpi;
		src = new Rect();
		dest = new Rect();
		img = Bitmap.createScaledBitmap(bm,(int)tx*ih,(int)ty*iv,false);
		rand = new Random();
		if(rand.nextBoolean()) {
			moneda = 1;
			preu_moneda = 1 + rand.nextInt(5);
		}
		marxar = 0;
		id = cont;
	}
	
	//Creacio de plataforma amb sprite que cauen
		public Objecte(Bitmap bm, float nx, float ny, float ntx, float nty, float nvx, float nvy, int nih, int niv, int nfpi, int cau, int cont) {
			tocat = false;
			x = nx;
			y = ny;
			tx = ntx;
			ty = nty;
			vx = nvx;
			vy = nvy;
			sprite = true;
			acabat = false;
			ih = nih;
			iv = niv;
			sfcount = -1;
			fpi = nfpi;
			src = new Rect();
			dest = new Rect();
			img = Bitmap.createScaledBitmap(bm,(int)tx*ih,(int)ty*iv,false);
			rand = new Random();
			if(rand.nextBoolean()) {
				moneda = 1;
				preu_moneda = 1 + rand.nextInt(5);
			}
			marxar = 0;
			if(cau == 1)platafd = true;
			id = cont;
		}

	
	//Creacio de plataformes
	public Objecte(Bitmap plataforma, float i, float j,float ntx, float nty) {
		tocat = false;
		x = i;
		y = j;
		tx = ntx;
		ty = nty;
		img = Bitmap.createScaledBitmap(plataforma,(int)ntx,(int)nty,false);
		platafd = false;
		sprite = false;
		rand = new Random();
		//if(rand.nextBoolean()) {
			moneda = 1;
			preu_moneda = 1 + rand.nextInt(5);
		//}
	}
	
	//Creacio de plataformes dinamiques que cauen
		public Objecte(Bitmap plataforma, float i, float j,float ntx, float nty, float nvy) {
			tocat = false;
			x = i;
			y = j;
			tx = ntx;
			ty = nty;
			vy = 0;
			vx = 0;
			platafd = true;
			img = Bitmap.createScaledBitmap(plataforma,(int)ntx,(int)nty,false);
		}
	
	public Objecte(Bitmap burbuja, int num, float i, float j, float ntx,float nty, float nvx, float nvy) {
		x = i;
		y = j;
		vx = nvx;
		vy = nvy;
		tx = ntx;
		ty = nty;
		num_burbuja = num;
		img = Bitmap.createScaledBitmap(burbuja,(int)ntx,(int)nty,false);
	}
	public void actualitza() {
		if(marxar == 1)  {
			y -= vy;
		}
		else {
			x+=vx;
			y+=vy;
		}
		if(sprite && sfcount/fpi>=(ih*iv)) acabat = true;
	}
	
	public void draw(Canvas canvas) {
		if(sprite) {
			int s = (sfcount)/fpi;
			src.set((int)((s%ih)*tx), (int)((s/ih)*ty), (int)((s%ih)*tx+tx), (int)((s/ih)*ty+ty));
			dest.set((int)x,(int)y,(int)(x+tx),(int)(y+ty));
			canvas.drawBitmap(img, src, dest, null);
			//Log.d("SPRITE COUNT",sfcount+" "+s+" "+(int)((s%ih)*tx)+" "+(int)((s/iv)*ty)+" "+(int)((s%ih)*tx+tx)+" "+(int)((s/iv)*ty+ty));
			sfcount++;
		}
		else canvas.drawBitmap(img, x, y, null);
	}
}
