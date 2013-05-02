package com.happybuh;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class WorldObjecte {

	public float x;
	public float y;
	public float vx;
	public float vy;
	public float tx;
	public float ty;
	public int id;
	public int marca;
	public int vides;
	public int mundo;
	private Bitmap img;
	//Sprites
	public boolean sprite; //es sprite o no
	private int ih;		//numero d'imatges horitzontals a l'sprite
	private int iv;		//numero d'imatges verticals a l'sprite
	private int sfcount;	//sprite frame count
	private int fpi;		//frames per imatge
	private Rect src;
	private Rect dest;
	private int casa;
	public boolean acabat;
	
	public WorldObjecte(float nx, float ny, float nvx, float nvy, float ntx, float nty, int nmarca, Bitmap imgn) {
		x = nx;
		y = ny;
		vx = nvx;
		vy = nvy;
		tx = ntx;
		ty = nty;
		img = imgn;
		marca = nmarca;
		sprite = false;
		img = Bitmap.createScaledBitmap(imgn,(int)tx,(int)ty,false);
	}
	
	//Creacio casa final
	public WorldObjecte(float nx, float ny, float nvx, float nvy, float ntx, float nty, int nmarca, Bitmap imgn, int ncasa) {
		x = nx;
		y = ny;
		vx = nvx;
		vy = nvy;
		tx = ntx;
		ty = nty;
		img = imgn;
		marca = nmarca;
		sprite = false;
		casa = ncasa;
		img = Bitmap.createScaledBitmap(imgn,(int)tx,(int)ty,false);
	}
	
	//Creacio protagonista
	public WorldObjecte(float nx, float ny, float nvx, float nvy, float ntx, float nty, int nmarca, int nvides,  Bitmap imgn) {
		x = nx;
		y = ny;
		vx = nvx;
		vy = nvy;
		tx = ntx;
		ty = nty;
		img = imgn;
		marca = nmarca;
		vides = nvides;
		sprite = false;
		img = Bitmap.createScaledBitmap(imgn,(int)tx,(int)ty,false);
	}
	
	//Creacio fondos
		public WorldObjecte(int nmundo, int nid, float nx, float ny, float nvx, float nvy, float ntx, float nty, Bitmap imgn) {
			id = nid;
			x = nx;
			y = ny;
			vx = nvx;
			vy = nvy;
			tx = ntx;
			ty = nty;
			img = imgn;
			sprite = false;
			mundo = nmundo;
			img = Bitmap.createScaledBitmap(imgn,(int)tx,(int)ty,false);
		}
	
	public WorldObjecte(Bitmap bm, float nx, float ny, float ntx, float nty, float nvx, float nvy, int nih, int niv, int nfpi) {
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
		fpi = nfpi;
		src = new Rect();
		dest = new Rect();
		GV.Plat.limder = x + ((float)(GV.Screen.metrics.widthPixels*0.075)*3);
		GV.Plat.limizq = x - + ((float)(GV.Screen.metrics.widthPixels*0.075)*3);
		//y = GV.heightpercent(100f) - ty;
		y = y - ty;
		img = Bitmap.createScaledBitmap(bm,(int)tx*ih,(int)ty*iv,false);
	}
	
	public void actualitza(float desplazamiento) {
		if(casa == 1) choque();
		if(marca == 1) {
//			vx = desplazamiento;
			marca = 2;
		}
		else if (marca == 2) {
			vx = 0;
			marca = 0;
			desplazamiento = 0;
		}
		x += desplazamiento;
		y += vy;
//		x += vx;
		if(sprite && sfcount/fpi>(ih*iv)) acabat = true;
	}
	
	
	public void actualitza() {
		x+=vx;
		y+=vy;
		if(sprite && sfcount/fpi>=(ih*iv)) acabat = true;
	}
	
	public void draw(Canvas canvas) {
		if (!sprite) canvas.drawBitmap(img, x, y, null);
		else {
			int s = sfcount/fpi;
			src.set((int)((s%ih)*tx), (int)((s/iv)*ty), (int)((s%ih)*tx+tx), (int)((s/iv)*ty+ty));
			dest.set((int)x,(int)y,(int)(x+tx),(int)(y+ty));
			canvas.drawBitmap(img, src, dest, null);
			//sfcount++;
		}
	}
	
	public void choque() {
		float posx = GV.puntuacio_world.prot.x;
		float tamx = GV.puntuacio_world.prot.tx;
		float posy = GV.puntuacio_world.prot.y;
		float tamy = GV.puntuacio_world.prot.ty;
		float LacxM = x;
		float LacyM = y;
		if(posx+tamx >= LacxM && posy+tamy >= LacyM) {
			//GV.Game.pasa_pantalla = 1;
		}
	}
	
	
	
	public void actder() {
		if(id== 1 && x < 0) {
			x = 0;
			vx = 0;
			GV.Control_mapas.parar = 1;
		}
		else if(GV.Control_mapas.parar == 1) {
			if(id==0) x = -tx;
			else if(id == 2) x = tx;
			else if (id == 1) x = 0;
			vx = 0;
		}
		x += vx;
	}
	
	public void actizq() {
		x += vx;
		if(id== 1 && (x+tx) > GV.Screen.metrics.widthPixels) {
			x = 0;
			vx = 0;
			GV.Control_mapas.parar = 1;
		}
		else if(GV.Control_mapas.parar == 1) {
			if(id==0) x = -tx;
			else if(id == 2) x = tx;
			else if (id == 1) x = 0;
			vx = 0;
		}
	}

}
