package com.happybuh;





import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Base {
	BitmapFactory.Options options;
	//mida
	float tx,ty;
	//posis
	float px,py;
	public Bitmap bowser;
	public Base() {
		tx = ((float)GV.Screen.metrics.widthPixels*0.2f);
		ty = ((float)GV.Screen.metrics.heightPixels*0.05f);
		options = new BitmapFactory.Options();
		options.inPreferredConfig = Bitmap.Config.ARGB_8888;
		bowser = BitmapFactory.decodeResource(GV.Instancies.bubbleview.getResources(), R.drawable.caparazonp, options);
		px = ((float)GV.Screen.metrics.widthPixels/2) - (tx/2);
		py = (float)GV.Screen.metrics.heightPixels - (bowser.getHeight()/2);
	}
	
	public void draw(Canvas canvas) {
		canvas.drawBitmap(bowser, px, py, null);
	}
	
	public void movex(float x) {
		//px += x;
		if((px + x) > ((float)GV.Screen.metrics.widthPixels-tx)) px = ((float)GV.Screen.metrics.widthPixels-tx);
		else if ((px + x) < 0) px = 0;
		else px += x;
	}
}
