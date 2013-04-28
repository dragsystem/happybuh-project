package com.happybuh;




import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;

public class Jugador {
	BitmapFactory.Options options;
	public Bitmap personaje;
	Objecte jugador;
	public float novapy;
	public Jugador(float speed) {
		// TODO inicialitzar Objecte (mirar protagonista)
		options = new BitmapFactory.Options();
		options.inPreferredConfig = Bitmap.Config.ARGB_8888;
		GV.puntuacio_bubble.vides = 3;
		personaje = BitmapFactory.decodeResource(GV.Instancies.jumpview.getResources(), R.drawable.buh_blue, options);
		jugador = new Objecte(personaje,100,GV.Screen.metrics.heightPixels,GV.widthpc(0.10f),GV.widthpc(0.10f),0,0);
	}
	
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
		}
		else if((jugador.y + jugador.ty) >= GV.Screen.metrics.heightPixels) {
			//FALTA PROGRAMAR MUERTE JUGADOR
			--GV.puntuacio_jump.vides;
			if(GV.puntuacio_jump.vides == 0) plat.muerte(jugador);
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
	}
	public float getX() {
		return jugador.x;
	}
	
}