package com.happybuh;

import java.util.ArrayList;
import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Message;



public class Peces {
	ArrayList<Objecte> objectes;
	ArrayList<Sprite> sprites;
	Random rand;
	ArrayList<Bitmap> bitmaps;
	Bitmap explosio;
	
	public Peces() {
		rand = new Random();
		objectes = new ArrayList<Objecte>();
		sprites = new ArrayList<Sprite>();
		carregaimatges();
	}
	
	private void carregaimatges() {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inPreferredConfig = Bitmap.Config.ARGB_8888;
		bitmaps = new ArrayList<Bitmap>();
		bitmaps.add(BitmapFactory.decodeResource(GV.Instancies.bubbleview.getResources(), R.drawable.burbuja_1, options));
		bitmaps.add(BitmapFactory.decodeResource(GV.Instancies.bubbleview.getResources(), R.drawable.burbuja_2, options));
		bitmaps.add(BitmapFactory.decodeResource(GV.Instancies.bubbleview.getResources(), R.drawable.burbuja_3, options));
		bitmaps.add(BitmapFactory.decodeResource(GV.Instancies.bubbleview.getResources(), R.drawable.burbuja_4, options));
		bitmaps.add(BitmapFactory.decodeResource(GV.Instancies.bubbleview.getResources(), R.drawable.burbuja_5, options));
		bitmaps.add(BitmapFactory.decodeResource(GV.Instancies.bubbleview.getResources(), R.drawable.burbuja_6, options));
		bitmaps.add(BitmapFactory.decodeResource(GV.Instancies.bubbleview.getResources(), R.drawable.burbuja_7, options));
		bitmaps.add(BitmapFactory.decodeResource(GV.Instancies.bubbleview.getResources(), R.drawable.burbuja_8, options));
		bitmaps.add(BitmapFactory.decodeResource(GV.Instancies.bubbleview.getResources(), R.drawable.burbuja_9, options));
		bitmaps.add(BitmapFactory.decodeResource(GV.Instancies.bubbleview.getResources(), R.drawable.burbuja_buh, options));
		explosio = BitmapFactory.decodeResource(GV.Instancies.bubbleview.getResources(), R.drawable.explosionsprite, options);
	}
	public void novapeca() {
		float x,y,tx,ty,vx,vy;
		tx = (0.06f + (float)rand.nextInt(6)/100)*GV.Screen.metrics.widthPixels;
		ty = tx;
		x = (float)rand.nextInt(GV.Screen.metrics.widthPixels-(int)tx);
		y = -ty;
		vx = 0;
		vy = (0.01f+(float)rand.nextInt(20)/1000)*GV.Screen.metrics.heightPixels;
		int num = rand.nextInt(bitmaps.size());
		objectes.add(new Objecte(bitmaps.get(num),num ,x,y,tx,ty,vx,vy));
	}
	
	public void actualitza() {
		if(rand.nextInt(15)==0) novapeca();
		for(Objecte o: objectes) o.actualitza();
		for(int i=sprites.size()-1; i>=0; i--) {
			sprites.get(i).actualitza();
			if(sprites.get(i).acabat)
				sprites.remove(i);
		}
	}
	
	public void draw(Canvas canvas) {
		for(Objecte o: objectes) o.draw(canvas);
		for(Sprite s: sprites) s.draw(canvas);
	}
	
	public void colisions(float x, float y, float tx, float ty) {
		for(int i=objectes.size()-1; i>=0; i--) {
			Objecte o = objectes.get(i);
			if(o.y > GV.Screen.metrics.heightPixels) { //SE ESCAPAN BURBUJAS 
				objectes.remove(i);
				--GV.puntuacio_bubble.vides;
				GV.Activities.bubblegame.handler.sendEmptyMessage(1);
				if(GV.puntuacio_bubble.vides == 0){
					GV.puntuacio_bubble.gameover = 1;
					GV.Activities.bubblegame.handler.sendEmptyMessage(3);
				}
			}
			else if(o.x+o.tx>x && o.x<x+tx && o.y+o.ty>y && o.y<y+ty) {
				sprites.add(new Sprite(explosio, o.x, o.y, o.tx, o.ty, 0, 0, 5, 5, 1));
				int num =objectes.get(i).num_burbuja; 
				switch (num) {
					case 0:
						GV.puntuacio_bubble.get_exp += 0.001;
						break;
					case 1:
						GV.puntuacio_bubble.get_exp += 0.001;
						break;
					case 2:
						GV.puntuacio_bubble.get_exp += 0.001;
						break;
					case 3:
						GV.puntuacio_bubble.get_exp += 0.001;
						break;
					case 4:
						GV.puntuacio_bubble.get_exp += 0.001;
						break;
					case 5:
						GV.puntuacio_bubble.coins += 1;
						GV.puntuacio_bubble.get_exp += 0.001;
						break;
					case 6:
						GV.puntuacio_bubble.coins += 1;
						GV.puntuacio_bubble.get_exp += 0.001;
						break;
					case 7:
						GV.puntuacio_bubble.coins += 1;
						GV.puntuacio_bubble.get_exp += 0.001;
						break;
					case 8:
						GV.puntuacio_bubble.coins -= 2;
						break;
					case 9:
						GV.puntuacio_bubble.coins += 2;
						GV.puntuacio_bubble.get_exp += 0.001;
						break;
				}
				objectes.remove(i);
				GV.Activities.bubblegame.handler.sendEmptyMessage(2);
			}
		}
	}
}
