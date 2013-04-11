package com.happybuh;

import java.util.ArrayList;

import android.content.Context;

public class User_Info {
	public String name;
	public int level;
	public int exp;
	public int coins;
	public int color;
	public int glasses;
	public int beard;
	public String num_glasses;
	public String col_glasses;
	public String num_beard;
	public String col_beard;
	
	private VG_Database db;
	
	public User_Info(Context c){
		db = new VG_Database(c);
		db.open();
			ArrayList a = new ArrayList();
	        a = db.info_user();
			level = Integer.parseInt((String) a.get(2));
	        coins = Integer.parseInt((String) a.get(3));
	        color = Integer.parseInt((String) a.get(4));
	        glasses = Integer.parseInt((String) a.get(5));
	        beard = Integer.parseInt((String) a.get(6));
	        
	        //RECOGER DATOS BD CON ID'S OBTENIDOS
		db.close();
	}
}
