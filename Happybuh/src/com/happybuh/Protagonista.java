package com.happybuh;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;


public class Protagonista {
	BitmapFactory.Options options;
	public Bitmap personaje, gafas_img, barba_img;
	public float suelo;
	WorldObjecte jugador;
	Resources resources;
	private int cau;
	private VG_Database db;
	private Long lc;
	private int gafas_b, barba_b;
	
	
	public Protagonista () {
		options = new BitmapFactory.Options();
		options.inPreferredConfig = Bitmap.Config.ARGB_8888;
		resources = GV.Activities.worldgame.getResources();
		
		GV.puntuacio_world.csalto = 0;
		GV.puntuacio_world.cplat = 0;
		
		float ty = GV.heightpc(0.13f);
	    float tx = GV.heightpc(0.10f);
	    //Limite de choque con el suelo
	    suelo = (GV.Screen.metrics.heightPixels)-(ty*2.3f);
	    
		float x  = (float)(GV.Screen.metrics.widthPixels/2)-tx;
		float y = (float)(GV.Screen.metrics.heightPixels)-(ty*2f);
		//ty = (float)((GV.Screen.metrics.heightPixels*0.2));
		//suelo -= ty;
		//y -= ty;
		cau = 1;
		
		db = GV.Activities.worldgame.db;
		db.open();
			//SET BUH
			String buh = "buh_" + db.getUserColorName().toLowerCase();
		    personaje = BitmapFactory.decodeResource(GV.Instancies.worldview.getResources(), GV.Activities.worldgame.getResources().getIdentifier("drawable/" + buh, null, GV.Activities.worldgame.getPackageName()));
			//personaje = BitmapFactory.decodeResource(GV.Instancies.worldview.getResources(), R.drawable.buh_blue, options);
			jugador = new WorldObjecte(x, y, 0, 0, tx, ty, 0, 3, personaje);
		db.close();
			//SET GAFAS
		db.open();
			lc = db.getUserGlasses();
			if(lc > 1) {
				String gafas = "gafas_" + db.getGlassNum(lc) + "_" + db.getGlassColor(lc);
				gafas_img = BitmapFactory.decodeResource(GV.Instancies.worldview.getResources(), GV.Activities.worldgame.getResources().getIdentifier("drawable/" + gafas, null, GV.Activities.worldgame.getPackageName()));
				gafas_img = Bitmap.createScaledBitmap(gafas_img,(int)tx,(int)ty,false);
				gafas_b = 1;
			}
			else gafas_b = 0;
		db.close();
			//SET BARBA
		db.open();
			lc = db.getUserBeard();
			if(lc > 1) {
				String barba = "barba_" + db.getBeardNum(lc) + "_" + db.getBeardColor(lc);
			    barba_img = BitmapFactory.decodeResource(GV.Instancies.worldview.getResources(), GV.Activities.worldgame.getResources().getIdentifier("drawable/" + barba, null, GV.Activities.worldgame.getPackageName()));
			    barba_img = Bitmap.createScaledBitmap(barba_img,(int)tx,(int)ty,false);
			    barba_b = 1;
			}
			else barba_b = 0;
	    db.close();
	    GV.puntuacio_world.prot = jugador;
	}
	
	public void actualiza() {
		if(GV.puntuacio_world.vides == 0) {
			GV.puntuacio_world.fast_die = 1;
		}
		else {
			if(GV.puntuacio_world.salto == 1 && jugador.vy == 0) {
				GV.puntuacio_world.salto = 0;
				jugador.vy = -GV.Screen.metrics.heightPixels*0.07f;
				GV.puntuacio_world.csalto = 1;
				GV.puntuacio_world.cplat = 0;
			}
			else if (GV.puntuacio_world.salto == 0){
				jugador.vy += GV.Screen.metrics.heightPixels*0.007;
			}
			if(jugador.y + jugador.vy > suelo) {
				jugador.y = suelo;
				jugador.vy = 0;
				GV.puntuacio_world.salto = 3;
				GV.puntuacio_world.csalto = 0;
			}
			if(GV.puntuacio_world.cplat == 1) {
				GV.puntuacio_world.csalto = 0;
			}
		}
	}
	
	
	
	public void draw(Canvas canvas){
			jugador.actualitza();
			jugador.draw(canvas);
			if(gafas_b == 1)canvas.drawBitmap(gafas_img,jugador.x, jugador.y, null);
			if(barba_b == 1)canvas.drawBitmap(barba_img,jugador.x, jugador.y, null);
	}	
	
	public void actualitza_die() {
		if(cau == 1) {
			jugador.vy = -GV.Screen.metrics.heightPixels*0.06f;
			cau = 0;
		}
		else jugador.vy += GV.Screen.metrics.heightPixels*0.005;
		
		if(jugador.y > GV.Screen.metrics.heightPixels){
			GV.puntuacio_world.gameover = 1;
		}
	}
}