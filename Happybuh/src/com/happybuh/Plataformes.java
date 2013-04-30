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
	float ptx;			//tamany de x de la plataforma
	float pty;
	Bitmap plataf, platafd, moneda;
	BitmapFactory.Options options;
	float tmx, tmy;
	
	public Plataformes() {
		rand = new Random();
		objectes = new ArrayList<Objecte>();
		carregaplataformes();
	}
	
	private void carregaplataformes() {
		 options = new BitmapFactory.Options();
		options.inPreferredConfig = Bitmap.Config.ARGB_8888;
		plataf = BitmapFactory.decodeResource(GV.Instancies.jumpview.getResources(), R.drawable.nuvesprite, options);
		moneda = BitmapFactory.decodeResource(GV.Instancies.jumpview.getResources(), R.drawable.moneda, options);
		tmx = 0.03f*GV.Screen.metrics.heightPixels;
		tmy = 0.035f*GV.Screen.metrics.heightPixels;
		moneda = Bitmap.createScaledBitmap(moneda,(int)tmx,(int)tmy,false);
	//	plataf = new Objecte(plataf,100,GV.Screen.metrics.heightPixels/2,GV.heightpc(0.15f),GV.heightpc(0.15f),speed,0,3,1,3);
	}
	
	public void novaplataforma() {
		float x,y,tx,ty;
		ptx = (0.1f + (float)rand.nextInt(8)/100)*GV.Screen.metrics.widthPixels;
		pty = 0.03f*GV.Screen.metrics.heightPixels;
		if(objectes.size() == 0) {
			x = (float)rand.nextInt(GV.Screen.metrics.widthPixels-(int)ptx);
			y = (float)GV.Screen.metrics.heightPixels-(int)pty*4.5f;
		}
		else {
			float anty = objectes.get(objectes.size()-1).y;
			float newx, newy;
			newx = (float)rand.nextInt(GV.Screen.metrics.widthPixels)-ptx/2;
			//newy = anty - ((0.1f + (float)rand.nextInt(10)/100)*GV.Screen.metrics.heightPixels);
			newy = anty - (float)rand.nextInt(20)/100*GV.Screen.metrics.heightPixels;
			x = newx;
			y = newy;
		}
			
		if (rand.nextInt(10) == 10 || rand.nextInt(10) == 5) {
			platafd = BitmapFactory.decodeResource(GV.Instancies.jumpview.getResources(), R.drawable.plataforma, options);
			objectes.add(new Objecte(plataf,x,y,ptx,pty, GV.heightpc(0.06f)));
		}
		//else objectes.add(new Objecte(plataf,x,y,ptx,pty));
		else objectes.add(new Objecte (plataf,x,y,GV.heightpc(0.05f),GV.heightpc(0.05f),0,0,3,1,3));
	}
	
	
	public void actualitza(Jugador j) {
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
	
	public boolean colisio(float px, float py, float tx, float ty, float vy) {
			//PROGRAMAR COLISIONS
		float pecax = px + tx;
		float pecay = py + ty;
		for(int i = 0; i < objectes.size(); ++i) {
			Objecte o = objectes.get(i);
			if(vy > 0) {
				if(pecay >= o.y && py <= o.y+o.ty && pecax >= o.x && px <= o.x+o.tx) {
					GV.posiplataforma.posy = o.y;
					GV.posiplataforma.modplataforma = true;
					o.tocat = true;
					GV.posiplataforma.idplat = i;
//					o.vy = GV.heightpc(0.02f);
					if (o.platafd) o.vy = GV.heightpc(0.05f);
					if(o.moneda == 1) {
						o.moneda = 0;
						GV.puntuacio_jump.coins += o.preu_moneda;
						GV.Activities.jumpgame.handler.sendEmptyMessage(2);
						GV.Activities.jumpgame.handler.sendEmptyMessage(4);
					}
					GV.puntuacio_jump.get_exp += 0.01;
					return true;
				}
			}
		}
		GV.posiplataforma.modplataforma = false;
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
}
