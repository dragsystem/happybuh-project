package com.happybuh;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;



public class User_Info {
	public static String name;
	public static int level;
	public static int exp; 
	public static int coins;
	public static int color;
	public static int glasses;
	public static int beard;
	public static String color_name;
	public static String num_glasses;
	public static String col_glasses;
	public static String num_beard;
	public static String col_beard;
	static void inicializar(Context c) {
		VG_Database db = new VG_Database(c);
	    db.open();
		    ArrayList<String> a = new ArrayList<String>();
	        a = db.info_user();
	        User_Info.name = a.get(1);
	        User_Info.level = Integer.parseInt(a.get(2));
	        User_Info.coins = Integer.parseInt(a.get(3));
	        User_Info.color = Integer.parseInt(a.get(4));
	        User_Info.glasses = Integer.parseInt(a.get(5));
	        User_Info.beard = Integer.parseInt(a.get(6));
	        
	        Long lc, lg, lb;
	        lc = Long.parseLong((String) a.get(4));
	        Log.v("INDICE COLO", lc.toString());
	        User_Info.color_name = db.getColorName(lc);
	        lg = Long.parseLong((String) a.get(5));
	        User_Info.num_glasses = db.getNumGlasses(lg);
	        User_Info.col_glasses = db.getColGlasses(lg);
	        lb = Long.parseLong((String) a.get(6));
	        User_Info.num_beard = db.getNumBeard(lb);
	        User_Info.col_beard = db.getColBeard(lb);
	    db.close();
	}
	
	static void actualizar(Context c, float nexp, int ncoins) {
		VG_Database db = new VG_Database(c);
	    db.open();
	    	coins += ncoins;
	    	Log.d("COINS TRAS JUGAR", ""+coins);
	    	exp += nexp;
	    	if(level >= 0 && level < 5) {
	    		if (exp >= 1) {
	    			++level;
	    			exp = 1 - exp;
	    		}
	    	}
	    	if(level >= 5 && level < 10) {
	    		if (exp >= 5) {
	    			++level;
	    			exp = 5 - exp;
	    		}
	    	}
	    	if(level >= 10 && level < 15) {
	    		if (exp >= 10) {
	    			++level;
	    			exp = 10 - exp;
	    		}
	    	}
	    	if(level >= 15 && level < 20) {
	    		if (exp >= 15) {
	    			++level;
	    			exp = 15 - exp;
	    		}
	    	}
	    	if(level >= 25 && level < 30) {
	    		if (exp >= 20) {
	    			++level;
	    			exp = 20 - exp;
	    		}
	    	}
	    	db.actualiza_user(level, exp, coins);
	    db.close();
	}
}
