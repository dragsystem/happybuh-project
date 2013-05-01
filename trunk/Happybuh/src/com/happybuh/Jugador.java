package com.happybuh;




import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class Jugador {
	BitmapFactory.Options options;
	public Bitmap personaje, gafas_img, barba_img;
	private int barba_b, gafas_b;
	Objecte jugador;
	public float novapy;
	private VG_Database db;
	private Long lc;
	private int baja;
	
	public Jugador(float speed) {
		// TODO inicialitzar Objecte (mirar protagonista)
		options = new BitmapFactory.Options();
		options.inPreferredConfig = Bitmap.Config.ARGB_8888;
		GV.puntuacio_bubble.vides = 3;
		db = GV.Activities.jumpgame.db;
		db.open();
			//SET BUH
			String buh = "buh_" + db.getUserColorName().toLowerCase();
		    personaje = BitmapFactory.decodeResource(GV.Instancies.jumpview.getResources(), GV.Activities.jumpgame.getResources().getIdentifier("drawable/" + buh, null, GV.Activities.jumpgame.getPackageName()));
			//personaje = BitmapFactory.decodeResource(GV.Instancies.jumpview.getResources(), R.drawable.buh_blue, options);
			jugador = new Objecte(personaje,GV.widthpc(0.5f)-(GV.widthpc(0.10f)/2),GV.heightpc(0.3f),GV.widthpc(0.10f),GV.widthpc(0.12f),0,GV.Screen.metrics.heightPixels*0.045f);
		db.close();
			//SET GAFAS
		db.open();
			lc = db.getUserGlasses();
			if(lc > 1) {
				String gafas = "gafas_" + db.getGlassNum(lc) + "_" + db.getGlassColor(lc);
				gafas_img = BitmapFactory.decodeResource(GV.Instancies.jumpview.getResources(), GV.Activities.jumpgame.getResources().getIdentifier("drawable/" + gafas, null, GV.Activities.jumpgame.getPackageName()));
				gafas_img = Bitmap.createScaledBitmap(gafas_img,(int)GV.widthpc(0.10f),(int)GV.widthpc(0.12f),false);
				gafas_b = 1;
			}
			else gafas_b = 0;
		db.close();
			//SET BARBA
		db.open();
			lc = db.getUserBeard();
			if(lc > 1) {
				String barba = "barba_" + db.getBeardNum(lc) + "_" + db.getBeardColor(lc);
			    barba_img = BitmapFactory.decodeResource(GV.Instancies.jumpview.getResources(), GV.Activities.jumpgame.getResources().getIdentifier("drawable/" + barba, null, GV.Activities.jumpgame.getPackageName()));
			    barba_img = Bitmap.createScaledBitmap(barba_img,(int)GV.widthpc(0.10f),(int)GV.widthpc(0.12f),false);
			    barba_b = 1;
			}
			else barba_b = 0;
	    db.close();
	}

	
	public void actualitza(float yAccel, Plataformes plat) {
		//moviment accelerometre
		jugador.x += yAccel * GV.Screen.metrics.widthPixels*0.1f;
		//moviment de la gravetat
		if(GV.puntuacio_jump.primera_colisio == 1){
			if(jugador.y < GV.heightpc(0.3f)) {
				jugador.y = GV.heightpc(0.3f);
				jugador.vy = 0f;
				
			}
			jugador.vy += GV.Screen.metrics.heightPixels*0.002f;
		}
		//if(jugador.vy <= 0) jugador.vy = 0;
		jugador.actualitza();
		if(jugador.x > GV.Screen.metrics.widthPixels) jugador.x = 0f;
		else if (jugador.x < (0-jugador.tx)) jugador.x = GV.Screen.metrics.widthPixels - (jugador.tx); 
		//Calcular colisions amb plataformes
		if(plat.colisio(jugador.x, jugador.y, jugador.tx, jugador.ty, jugador.vy)) {
			GV.puntuacio_jump.primera_colisio = 1;
			//jugador.vy = 0;
			novapy = GV.posiplataforma.posy;
			salta(novapy);
			GV.puntuacio_jump.get_exp += 0.01;
		}
		else if((jugador.y + jugador.ty) > GV.Screen.metrics.heightPixels) {
			if(GV.puntuacio_jump.vides == 1) {
				salta(GV.Screen.metrics.heightPixels);
				--GV.puntuacio_jump.vides;
				GV.Activities.jumpgame.handler.sendEmptyMessage(1);
				plat.fugir();
				GV.puntuacio_jump.gameover = 1;
			}
			else {
				--GV.puntuacio_jump.vides;
				GV.Activities.jumpgame.handler.sendEmptyMessage(1);
				salta(GV.Screen.metrics.heightPixels);
				GV.posiplataforma.modplataforma = false;
			}
			
		}
		else GV.posiplataforma.modplataforma = false;
	}
	
	public void salta (float newpy) {
		//jugador.y = GV.Screen.metrics.heightPixels - jugador.ty;
		jugador.y = newpy-jugador.ty;
		jugador.vy = -GV.Screen.metrics.heightPixels*0.035f;
	}
	
	
	public void draw(Canvas canvas) {
		canvas.drawBitmap(jugador.img,jugador.x, jugador.y, null);
		if(gafas_b == 1)canvas.drawBitmap(gafas_img,jugador.x, jugador.y, null);
		if(barba_b == 1)canvas.drawBitmap(barba_img,jugador.x, jugador.y, null);
	}
	public float getX() {
		return jugador.x;
	}
	
}