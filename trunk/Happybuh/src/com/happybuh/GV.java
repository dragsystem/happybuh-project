package com.happybuh;



import java.util.ArrayList;



import android.os.PowerManager;
import android.util.DisplayMetrics;


public class GV {
	public static class Screen {
		public static DisplayMetrics metrics;
		public static PowerManager.WakeLock wl;
		public static int Orientation;
	}
	public static class Instancies {
		public static BubbleGameSurfaceView bubbleview;
		public static JumpGameSurfaceView jumpview;
		public static WorldGameSurfaceView worldview;
	}
	public static class Activities {
		public static BubbleGame bubblegame;
		public static JumpGame jumpgame;
		public static Habitacion habitacion;
		public static WorldGame worldgame;
	}
	
	public static class puntuacio_bubble {
		public static int vides;
		public static int coins;
		public static float get_exp;
		public static int gameover;
		public static int pause;
	}
	public static class puntuacio_jump {
		public static int vides;
		public static int coins;
		public static float get_exp;
		public static int gameover;
		public static int pause;
		public static int termina;
		public static int primera_colisio;
	}
	
	public static class puntuacio_world {
		public static int vides;
		public static int coins;
		public static float get_exp;
		public static int gameover;
		public static int pause;
		public static int control;
		public static int csalto;
		public static int salto;
		public static int disparo;
		public static int fast_die;
		public static ArrayList<WorldObjecte> plataformes_mapa;
		public static int cplat;
		public static WorldObjecte prot;
		public static ArrayList<WorldObjecte> fondos;
		public static ArrayList<Monster> monstres;
		public static ArrayList<Lacasito> lacasitos;
		public static ArrayList<Tramps> tramps;
		public static ArrayList<Disparo> galleta_d;
		public static ArrayList<Plataforma> plataformes;
		public static float desplazamiento;
		public static int pos0;
		public static int plataforma_limite;
		public static int plataforma_casa;
		public static int num_monedas;
		public static int pasa_pantalla;
	}
	
	public static class mapa {
		public static int pant;
	}
	public static class Control_mapas {
		public static int parar;
	}
	public static class Plat {
		public static float limder;
		public static float limizq;
	}
	
	public static class posiplataforma {
		public static float posy;
		public static boolean modplataforma;
		public static boolean parats;
		public static int idplat;
	}
	
	public static float widthpc(float pc) {
		return Screen.metrics.widthPixels*pc;
	}
	public static float heightpc(float pc) {
		return Screen.metrics.heightPixels*pc;
	}
	
	public static class World_mov {
		public static int ll_left;
		public static int ll_right;
		public static int ll_top;
		public static int ll_bot;
		public static int izq_right;
		public static int der_left;
		public static int s_left;
		public static int s_right;
		public static int s_top;
		public static int s_bot;
		
		
	}
}
