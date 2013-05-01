package com.happybuh;

import java.util.ArrayList;
import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

public class Plataformes {
	Random rand;
	ArrayList<Objecte> objectes;
	ArrayList<Bitmap> bitmaps;
	float ptx, pty, newy;			//tamany de x de la plataforma
	Bitmap plataf, platafd, moneda;
	BitmapFactory.Options options;
	float tmx, tmy;
	private int id_colisio;
	private int cont;
	public Plataformes() {
		rand = new Random();
		objectes = new ArrayList<Objecte>();
		carregaplataformes();
		id_colisio = -1;
	}
	
	private void carregaplataformes() {
		 options = new BitmapFactory.Options();
		options.inPreferredConfig = Bitmap.Config.ARGB_8888;
		plataf = BitmapFactory.decodeResource(GV.Instancies.jumpview.getResources(), R.drawable.nuvesprite, options);
		moneda = BitmapFactory.decodeResource(GV.Instancies.jumpview.getResources(), R.drawable.moneda, options);
		tmx = 0.035f*GV.Screen.metrics.heightPixels;
		tmy = 0.035f*GV.Screen.metrics.heightPixels;
		moneda = Bitmap.createScaledBitmap(moneda,(int)tmx,(int)tmy,false);
		cont = 0;
		base_plataformes();
	//	plataf = new Objecte(plataf,100,GV.Screen.metrics.heightPixels/2,GV.heightpc(0.15f),GV.heightpc(0.15f),speed,0,3,1,3);
	}
	
	public void base_plataformes() {
		float x, y;
		ptx = 0.15f*GV.Screen.metrics.widthPixels;
		pty = 0.05f*GV.Screen.metrics.heightPixels;
		x = GV.widthpc(0.5f)-(int)ptx/2;
		y = (float)GV.heightpc(1f)-(int)pty;
		objectes.add(new Objecte (plataf,x,y,ptx,pty,0,0,3,1,3, ++cont));
		x = 0;
		objectes.add(new Objecte (plataf,x,y,ptx,pty,0,0,3,1,3, ++cont));
		x = GV.widthpc(1f) - ptx;
		objectes.add(new Objecte (plataf,x,y,ptx,pty,0,0,3,1,3, ++cont));
	}
	
	/*public  void novaplataforma() {
		float x,y,tx,ty;
		ptx = 0.15f*GV.Screen.metrics.widthPixels;
		pty = 0.05f*GV.Screen.metrics.heightPixels;
		if(objectes.size() == 0) {
			x = (float)rand.nextInt(GV.Screen.metrics.widthPixels-(int)ptx);
			y = (float)GV.heightpc(1f)-(int)pty-GV.heightpc(0.1f);
		}
		else {
			x = (float)rand.nextInt(GV.Screen.metrics.widthPixels-(int)ptx);
			y = (objectes.get(objectes.size()-1).y)-(int)pty-GV.heightpc(0.1f);
		}
		
		if (rand.nextInt(10) == 10 || rand.nextInt(10) == 5) objectes.add(new Objecte (plataf,x,y,ptx,pty,0,0,3,1,3, 1));
		else objectes.add(new Objecte (plataf,x,y,ptx,pty,0,0,3,1,3));
	}*/
	
	public  void novaplataforma(float nvy) {
		float x,y,tx,ty;
		ptx = 0.15f*GV.Screen.metrics.widthPixels;
		pty = 0.05f*GV.Screen.metrics.heightPixels;
		if(objectes.size() == 0) {
			x = (float)rand.nextInt(GV.Screen.metrics.widthPixels-(int)ptx);
			y = (float)GV.heightpc(1f)-GV.heightpc(0.1f);
		}
		else {
			x = (float)rand.nextInt(GV.Screen.metrics.widthPixels-(int)ptx);
			y = (objectes.get(objectes.size()-1).y)-(int)pty-GV.heightpc(0.15f);
		}
		
		if (rand.nextInt(10) == 10 || rand.nextInt(10) == 5) objectes.add(new Objecte (plataf,x,y,ptx,pty,0,nvy,3,1,1,1, ++cont));
		else objectes.add(new Objecte (plataf,x,y,ptx,pty,0,nvy,3,1,1, ++cont));
	}
	
	public void actualitza(Jugador j) {
		float velocidad = 0;
		for(int i = 0; i < objectes.size(); ++i) {
			Objecte o = objectes.get(i);
			if(!o.platafd || (o.platafd && !o.tocat)) {
				o.vy -= GV.Screen.metrics.heightPixels*0.0015;
				if(o.vy <= 0) o.vy = 0;
				velocidad = o.vy;
			}
			o.actualitza();
			if(o.y >= GV.Screen.metrics.heightPixels) objectes.remove(o);
		}
		if (objectes.size() < 20) novaplataforma(velocidad);
	}
	
	
	/*public void actualitza2(Jugador j) {
		if (j.jugador.y <= GV.heightpc(0.30f) && j.jugador.vy < 0) j.jugador.vy = 0f + (float)(GV.Screen.metrics.heightPixels*0.005);
		if(j.jugador.y <= GV.heightpc(0.35f)) {
			for(int i = 0; i < objectes.size(); ++i) {
				Objecte o = objectes.get(i);
				if(o.platafd && o.vy <= GV.heightpc(0.02f)) o.vy = GV.heightpc(0.02f);
				else if (!o.platafd) o.vy = GV.heightpc(0.02f);
				o.actualitza();
				if(o.y >= GV.Screen.metrics.heightPixels) objectes.remove(o);
			}
		}
		else {
			for(int i = 0; i < objectes.size(); ++i) {
				Objecte o = objectes.get(i);
				if(o.platafd && o.vy <= GV.heightpc(0.02f)) {
					if(o.vy > 0) o.vy -= GV.heightpc(0.005f);
					else o.vy = 0f;
				}
				else if (!o.platafd) {
					if(o.vy > 0) o.vy -= GV.heightpc(0.005f);
					else o.vy = 0f;
				}
				o.actualitza();
				if(o.y >= GV.Screen.metrics.heightPixels) objectes.remove(o);
			}
		}
		if (objectes.size() < 20) novaplataforma();
	}*/
	

	
	public boolean colisio(float px, float py, float tx, float ty, float vy) {
		//PROGRAMAR COLISIONS
		float pecax = px + tx;
		float pecay = py + ty;
		boolean colx, coly;
		for(int i = 0; i < objectes.size(); ++i) {
			Objecte o = objectes.get(i);
			if(vy >= 0) {
				colx = colisiox(px, pecax, o.x, o.x+o.tx);
				coly = colisioy(py, pecay, o.y, o.y+o.ty);
				if(colx && coly) {
					if(o.y <= GV.heightpc(0.7f) && id_colisio != o.id && o.vy == 0)for(Objecte o_aux: objectes) o_aux.vy = GV.Screen.metrics.heightPixels*0.02f;
					id_colisio = o.id;
					if (o.platafd) o.vy = GV.heightpc(0.05f);
					if(o.moneda == 1) {
						o.moneda = 0;
						GV.puntuacio_jump.coins += o.preu_moneda;
						GV.Activities.jumpgame.handler.sendEmptyMessage(2);
						GV.Activities.jumpgame.handler.sendEmptyMessage(4);
					}
					GV.puntuacio_jump.get_exp += 0.02;
					GV.posiplataforma.posy = o.y;
					o.tocat = true;
					return true;
				}
			}
		}
		//GV.posiplataforma.modplataforma = false;
		return false;
	}
	private boolean colisiox(float x, float fx, float xp, float fxp) {
		if(fx > xp && x < fxp) return true;
		return false;
	}
	private boolean colisioy(float y, float fy, float yp, float fyp) {
		if(fy >= yp && fy <= fyp) return true;
		return false;
	}
	
	public void actualitza_gameover() {
		for(int i = 0; i < objectes.size(); ++i) {
			Objecte o = objectes.get(i);
			if((o.y+o.ty) <= 0) objectes.remove(i);
			else o.actualitza();
		}
		if(objectes.size() == 0) GV.puntuacio_jump.termina = 1;
	}
	
	public void fugir() {
		for(int i = 0; i < objectes.size(); ++i) {
			Objecte o = objectes.get(i);
			o.vy = GV.Screen.metrics.heightPixels*0.06f;
			o.marxar = 1;
			o.actualitza();
		}
	}
	
	public void muerte(Objecte j) {
		for(int i = 0; i < objectes.size(); ++i) {
			Objecte o = objectes.get(i);
			o.vy = -GV.Screen.metrics.heightPixels*0.06f;
			o.actualitza();
		}
		GV.Activities.jumpgame.handler.sendEmptyMessage(3);
	}
	
	public void draw(Canvas canvas, float dx, float w) {
		for(int i=0; i < objectes.size(); i++) {
			Objecte o = objectes.get(i);
			if(GV.puntuacio_jump.gameover == 0 && GV.puntuacio_jump.pause == 0){ 
				o.actualitza();
				if(o.acabat) o.sfcount=0; 
			}
			if(o.moneda == 1 && (GV.puntuacio_jump.gameover == 0 && GV.puntuacio_jump.pause == 0)){
				canvas.drawBitmap(moneda,o.x+(o.tx/2)-(tmx/2), o.y-(o.ty/2), null);
				//canvas.drawBitmap(moneda,100, 100, null);
			}
			o.draw(canvas);
		}
	}
}
