package com.happybuh;



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
	}
	public static class Activities {
		public static BubbleGame bubblegame;
		public static JumpGame jumpgame;
		public static Habitacion habitacion;
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
}
