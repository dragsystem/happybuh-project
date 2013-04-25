package com.happybuh;

import java.util.ArrayList;
import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;



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
		objectes.add(new Objecte(bitmaps.get(rand.nextInt(bitmaps.size())),x,y,tx,ty,vx,vy));
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
			if(o.y>GV.Screen.metrics.heightPixels) {
				objectes.remove(i);
			}
			else if(o.x+o.tx>x && o.x<x+tx && o.y+o.ty>y && o.y<y+ty) {
				sprites.add(new Sprite(explosio, o.x, o.y, o.tx, o.ty, 0, 0, 5, 5, 1));
				objectes.remove(i);
			}
		}
	}
}
