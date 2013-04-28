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
	Bitmap plataf, platafd;
	BitmapFactory.Options options;
	
	public Plataformes() {
		rand = new Random();
		objectes = new ArrayList<Objecte>();
		carregaplataformes();
	}
	
	private void carregaplataformes() {
		 options = new BitmapFactory.Options();
		options.inPreferredConfig = Bitmap.Config.ARGB_8888;
		plataf = BitmapFactory.decodeResource(GV.Instancies.jumpview.getResources(), R.drawable.nuvesprite, options);
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
//		float limit = j.jugador.y;
//		float dist;
//		if(limit < GV.heightpc(0.4f)) {
//			for(int i = 0; i < objectes.size(); ++i) {
//				Objecte o = objectes.get(i);
//				if (o.y > limit || o.y > GV.Screen.metrics.heightPixels) objectes.remove(o);
//			}
//			dist = GV.heightpc(1f) - objectes.get(objectes.size()-1).y - j.jugador.ty; 
//			for(int i = objectes.size()-1; i >= 0 ; --i) {
//				objectes.get(i).y += dist;
//				j.jugador.y += dist;
//			}
//		}
//		for(int i = 0; i < objectes.size(); ++i) {
//			Objecte o = objectes.get(i);
//			if (o.y > GV.Screen.metrics.heightPixels) objectes.remove(o);
//		}
//		if(objectes.size() < 10) novaplataforma();
//		Log.w("COLISIONO ", "he chocado, la plataforma es la " + GV.posiplataforma.idplat);
//		for(int i = 0; i < objectes.size(); ++i) {
//			Objecte o = objectes.get(i);
//			o.vy = GV.heightpc(0.0f);
//			o.actualitza();
//		}
//		if(objectes.get(GV.posiplataforma.idplat).y >= GV.heightpc(0.90f)) {
//			for(int i = 0; i < objectes.size(); ++i) {
//				Objecte o = objectes.get(i);
//				o.vy = 0;
//				//if (o.y > GV.Screen.metrics.heightPixels) objectes.remove(o);
//			}
//		}
//		if(objectes.size() < 10) novaplataforma();
//		float v;
//		if(j.jugador.y <= GV.heightpc(0.35f)) v = GV.heightpc(0.02f); 
//		else v = 0f;
//		if (j.jugador.y <= GV.heightpc(0.30f) && j.jugador.vy < 0) j.jugador.vy = 0f + (float)(GV.Screen.metrics.heightPixels*0.005);
//		for(int i = 0; i < objectes.size(); ++i) {
//				Objecte o = objectes.get(i);
//				if(o.platafd && o.vy <= GV.heightpc(0.02f)) o.vy = v;
//				else if (!o.platafd) o.vy = v;
//				o.actualitza();
//				if(o.y >= GV.Screen.metrics.heightPixels) objectes.remove(o);
//		}
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
	
	public void actualitza2 () {
		if(objectes.size() > 0 && objectes.get(GV.posiplataforma.idplat).tocat && objectes.get(GV.posiplataforma.idplat).y >= GV.heightpc(0.90f)) {
			for(int i = 0; i < objectes.size(); ++i) {
				Objecte o = objectes.get(i);
				o.tocat = false;
				o.vy = 0;
				if(o.y >= GV.Screen.metrics.heightPixels) objectes.remove(o);
			}
		}
		else if (objectes.size() > 0) {
			for(int i = 0; i < objectes.size(); ++i) {
				Objecte o = objectes.get(i);
				o.actualitza();
			//	if(o.y >= GV.Screen.metrics.heightPixels) objectes.remove(o);
			}
		}
		if(objectes.size() < 10) novaplataforma();
	}
	
	public void draw(Canvas canvas, float dx, float w) {
		
		for(int i=0; i < objectes.size(); i++) {
			Objecte o = objectes.get(i);
			o.actualitza();
			if(o.acabat) o.sfcount=0; 
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
					return true;
				}
			}
		}
		GV.posiplataforma.modplataforma = false;
		return false;
	}
	
	public void muerte(Objecte j) {
		GV.puntuacio_jump.gameover = 1;
		for(int i = 0; i < objectes.size(); ++i) {
			Objecte o = objectes.get(i);
			o.vy = -GV.Screen.metrics.heightPixels*0.06f;
			o.actualitza();
		}
	}
}
