package com.happybuh;



import android.os.PowerManager;
import android.util.DisplayMetrics;


public class GV {
	public static class Screen {
		public static DisplayMetrics metrics;
		public static PowerManager.WakeLock wl;
	}
	public static class Instancies {
		public static BubbleGameSurfaceView bubbleview;
	}
	public static class Activities {
		public static BubbleGame bubblegame;
	}
	
	public static class puntuacio_bubble {
		public static int vides;
		public static int coins;
		public static float get_exp;
	}
}
