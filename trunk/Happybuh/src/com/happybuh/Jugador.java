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
	Objecte jugador;
	public float novapy;
	private VG_Database db;
	private Long lc;
	
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
			jugador = new Objecte(personaje,100,GV.Screen.metrics.heightPixels,GV.widthpc(0.10f),GV.widthpc(0.12f),0,0);
		
			//SET GAFAS
			lc = db.getUserGlasses();
			String gafas = "gafas_" + db.getGlassNum(lc) + "_" + db.getGlassColor(lc);
			gafas_img = BitmapFactory.decodeResource(GV.Instancies.jumpview.getResources(), GV.Activities.jumpgame.getResources().getIdentifier("drawable/" + gafas, null, GV.Activities.jumpgame.getPackageName()));
			gafas_img = Bitmap.createScaledBitmap(gafas_img,(int)GV.widthpc(0.10f),(int)GV.widthpc(0.12f),false);

			//SET BARBA
			lc = db.getUserBeard();
			String barba = "barba_" + db.getBeardNum(lc) + "_" + db.getBeardColor(lc);
		    barba_img = BitmapFactory.decodeResource(GV.Instancies.jumpview.getResources(), GV.Activities.jumpgame.getResources().getIdentifier("drawable/" + barba, null, GV.Activities.jumpgame.getPackageName()));
		    barba_img = Bitmap.createScaledBitmap(barba_img,(int)GV.widthpc(0.10f),(int)GV.widthpc(0.12f),false);
	    db.close();
	}
	/*public Jugador(float speed, int que) {
		// TODO inicialitzar Objecte (mirar protagonista)
		options = new BitmapFactory.Options();
		options.inPreferredConfig = Bitmap.Config.ARGB_8888;
		GV.puntuacio_bubble.vides = 3;
		
		db = GV.Activities.jumpgame.db;
		db.open();
		switch(que) {
		case 1:
			lc = db.getUserColor();
	    	if(lc > 0) {
			    String gafas = "buh_" + db.getUserColor();
			   
			    personaje = BitmapFactory.decodeResource(GV.Instancies.jumpview.getResources(), GV.Activities.jumpgame.getResources().getIdentifier("drawable/" + gafas, null, GV.Activities.jumpgame.getPackageName()));
	    	}
			break;
		case 2:
			lc = db.getUserGlasses();
	    	if(lc > 0) {
			    String gafas = "gafas_" + db.getGlassNum(lc) + "_" + db.getGlassColor(lc);
			   
			    personaje = BitmapFactory.decodeResource(GV.Instancies.jumpview.getResources(), GV.Activities.jumpgame.getResources().getIdentifier("drawable/" + gafas, null, GV.Activities.jumpgame.getPackageName()));
	    	}
			break;
		case 3:
			lc = db.getUserBeard();
	    	if(lc > 0) {
			    String barba = "barba_" + db.getBeardNum(lc) + "_" + db.getBeardColor(lc);
			   
			    personaje = BitmapFactory.decodeResource(GV.Instancies.jumpview.getResources(), GV.Activities.jumpgame.getResources().getIdentifier("drawable/" + barba, null, GV.Activities.jumpgame.getPackageName()));
	    	}
			break;
		}
	    db.close();
		personaje = BitmapFactory.decodeResource(GV.Instancies.jumpview.getResources(), R.drawable.buh_blue, options);
		jugador = new Objecte(personaje,100,GV.Screen.metrics.heightPixels,GV.widthpc(0.10f),GV.widthpc(0.12f),0,0);
	}*/
	
	public void actualitza(float yAccel, Plataformes plat) {
		//moviment accelerometre
		jugador.x += yAccel * GV.Screen.metrics.widthPixels*0.1f;
		//moviment de la gravetat
		jugador.vy += GV.Screen.metrics.heightPixels*0.005;
		jugador.actualitza();
		//if(jugador.y > GV.Screen.metrics.heightPixels) salta(GV.Screen.metrics.heightPixels);
		if(jugador.x > GV.Screen.metrics.widthPixels) jugador.x = 0f;
		else if (jugador.x < (0-jugador.tx)) jugador.x = GV.Screen.metrics.widthPixels - (jugador.tx); 
		//Calcular colisions amb plataformes
		if(plat.colisio(jugador.x, jugador.y, jugador.tx, jugador.ty, jugador.vy)) {
			novapy = GV.posiplataforma.posy;
			salta(novapy);
			GV.puntuacio_bubble.get_exp += 0.01;
		}
		else if((jugador.y + jugador.ty) >= GV.Screen.metrics.heightPixels) {
			//FALTA PROGRAMAR MUERTE JUGADOR
			--GV.puntuacio_jump.vides;
			GV.Activities.jumpgame.handler.sendEmptyMessage(1);
			if(GV.puntuacio_jump.vides == 0) {
				GV.puntuacio_jump.gameover = 1;
				GV.Activities.jumpgame.handler.sendEmptyMessage(3);
				plat.muerte(jugador);
			}
			salta(GV.Screen.metrics.heightPixels);
			GV.posiplataforma.modplataforma = false;
		}
		else GV.posiplataforma.modplataforma = false;
	}
	
	public void salta (float newpy) {
		//jugador.y = GV.Screen.metrics.heightPixels - jugador.ty;
		jugador.y = newpy-jugador.ty;
		jugador.vy = -GV.Screen.metrics.heightPixels*0.06f;
	}
	
	
	public void draw(Canvas canvas) {
		canvas.drawBitmap(jugador.img,jugador.x, jugador.y, null);
		canvas.drawBitmap(gafas_img,jugador.x, jugador.y, null);
		canvas.drawBitmap(barba_img,jugador.x, jugador.y, null);
	}
	public float getX() {
		return jugador.x;
	}
	
}