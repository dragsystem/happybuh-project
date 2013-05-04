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
		public static SearchGame searchgame;
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
	
	public static class puntuacio_search {
		public static int vides;
		public static int coins;
		public static float get_exp;
		public static int gameover;
		public static int pause;
		public static int actual;
		public static int index;
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
	
	public static class ristras {
		public static Integer[] mThumbIds = {
	            R.drawable.gafas1, R.drawable.gafas13,
	            R.drawable.gafas2, R.drawable.gafas14,
	            R.drawable.gafas3, R.drawable.gafas12,
	            R.drawable.gafas4, R.drawable.barba1,
	            R.drawable.gafas5, R.drawable.barba4,
	            R.drawable.gafas6, R.drawable.barba2,
	            R.drawable.gafas7, R.drawable.barba3,
	            R.drawable.gafas10, R.drawable.barba5,
	            R.drawable.gafas9, R.drawable.buh1,
	            R.drawable.gafas11, R.drawable.buh2,
	            R.drawable.gafas8, R.drawable.buh3,
	            R.drawable.buh4, R.drawable.buh5
	    };
		public static Integer[] mThumbIds2 = {
            R.drawable.gafas10, R.drawable.gafas13,
            R.drawable.buh2, R.drawable.gafas1,
            R.drawable.gafas3, R.drawable.gafas12,
            R.drawable.buh5, R.drawable.buh1,
            R.drawable.barba5, R.drawable.barba4,
            R.drawable.gafas6, R.drawable.barba2,
            R.drawable.gafas7, R.drawable.buh3,
            R.drawable.gafas9, R.drawable.gafas5,
            R.drawable.gafas14, R.drawable.barba1,
            R.drawable.gafas11, R.drawable.gafas2,
            R.drawable.gafas8, R.drawable.barba3,
            R.drawable.gafas4, R.drawable.buh4
    };
		public static Integer[] mThumbIds3 = {
            R.drawable.barba5, R.drawable.buh3,
            R.drawable.gafas14, R.drawable.barba2,
            R.drawable.buh5, R.drawable.gafas13,
            R.drawable.gafas8, R.drawable.gafas6,
            R.drawable.buh1, R.drawable.gafas5,
            R.drawable.gafas7, R.drawable.gafas2,
            R.drawable.gafas12, R.drawable.barba3,
            R.drawable.buh2, R.drawable.gafas10,
            R.drawable.gafas9, R.drawable.buh4,
            R.drawable.gafas3, R.drawable.gafas11,
            R.drawable.barba4, R.drawable.gafas4,
            R.drawable.gafas1, R.drawable.barba1,
            };
		public static Integer[] mThumbIds4 = {
			R.drawable.gafas10, R.drawable.gafas8,
            R.drawable.buh4, R.drawable.gafas13,
            R.drawable.gafas9, R.drawable.gafas11,
            R.drawable.gafas2, R.drawable.buh2,
            R.drawable.gafas6, R.drawable.gafas14,
            R.drawable.barba1, R.drawable.gafas3,
            R.drawable.buh1, R.drawable.barba2,
            R.drawable.barba3, R.drawable.gafas7,
            R.drawable.gafas1, R.drawable.barba4,
            R.drawable.gafas5, R.drawable.gafas12,
            R.drawable.barba5, R.drawable.buh3,
            R.drawable.buh5, R.drawable.gafas4,
    };
	}
}
