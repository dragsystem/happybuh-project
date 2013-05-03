package com.happybuh;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;



public class User_Info {
	public static String name;
	public static int level;
	public static float exp; 
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
	        User_Info.exp = Float.parseFloat(a.get(7));
	        
	        Long lc, lg, lb;
	        lc = Long.parseLong((String) a.get(4));
	        User_Info.color_name = db.getColorName(lc);
	        lg = Long.parseLong((String) a.get(5));
	        User_Info.num_glasses = db.getGlassNum(lg);
	        User_Info.col_glasses = db.getGlassColor(lg);
	        lb = Long.parseLong((String) a.get(6));
	        User_Info.num_beard = db.getBeardNum(lb);
	        User_Info.col_beard = db.getBeardColor(lb);
	        
	        Log.v("COLOR NAME+INDEX", User_Info.color_name + " , " + User_Info.color);
	        Log.v("GLASS NUM + COLOR + INDEX", User_Info.num_glasses + " , " + User_Info.col_glasses + " , " + User_Info.glasses);
	        Log.v("BEARD NUM + COLOR + INDEX", User_Info.num_beard + " , " + User_Info.col_beard + " , " + User_Info.beard);
	        Log.v("Experiencia usuario ", ""+exp);
	    db.close();
	}
	
	static void actualizar_user(Context c) {
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
	    db.close();
	}
	
	static void actualizar(Context c, float nexp, int ncoins) {
		Log.v("Exp ganada ", ""+nexp);
		VG_Database db = new VG_Database(c);
	    db.open();
	    	coins += ncoins;
	    	exp += nexp;
	    	if(level >= 0 && level < 5) {
	    		if (exp >= 1) {
	    			++level;
	    			exp = exp-1;
	    		}
	    	}
	    	if(level >= 5 && level < 10) {
	    		if (exp >= 5) {
	    			++level;
	    			exp = exp -5;
	    		}
	    	}
	    	if(level >= 10 && level < 15) {
	    		if (exp >= 10) {
	    			++level;
	    			exp = exp - 10;
	    		}
	    	}
	    	if(level >= 15 && level < 20) {
	    		if (exp >= 15) {
	    			++level;
	    			exp = exp - 15;
	    		}
	    	}
	    	if(level >= 20 && level < 25) {
	    		if (exp >= 20) {
	    			++level;
	    			exp = exp - 20;
	    		}
	    	}
	    	if(level >= 25 && level < 30) {
	    		if (exp >= 25) {
	    			++level;
	    			exp = exp - 25;
	    		}
	    	}
	    	if(level >= 30 && level < 35) {
	    		if (exp >= 30) {
	    			++level;
	    			exp = exp - 30;
	    		}
	    	}
	    	if(level >= 35 && level < 40) {
	    		if (exp >= 35) {
	    			++level;
	    			exp = exp - 35;
	    		}
	    	}
	    	Log.v("Exp final y nivel final ", ""+exp + " "+level);
	    	db.actualiza_user(level, exp, coins);
	    db.close();
	}
	
	static float porcentaje_exp(){
		if(level >= 0 && level < 5) {
			Log.v("level y porcentaje exp", level+"   "+exp*100);
    			return exp*100;
    	}
    	if(level >= 5 && level < 10) {
    			return exp*100/5;
    	}
    	if(level >= 10 && level < 15) {
    			return exp*100/10;
    	}
    	if(level >= 15 && level < 20) {
    			return exp*100/15;
    	}
    	if(level >= 20 && level < 25) {
    			return (float)exp*100/20;
    	}
    	if(level >= 25 && level < 30) {
    			return (float)exp*100/25;
    	}
    	if(level >= 30 && level < 35) {
    			return (float)exp*100.0f/30.0f;
    	}
    	if(level >= 35 && level < 40) {
    			return (float)exp*100/35;
    	}
		return 0;
	}
}
