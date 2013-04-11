package com.happybuh;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class VG_Database {
	//VARIABLES USER_INFO
	public static final String KEY_ROWID = "_id";
	public static final String KEY_NAME = "user_name";
	public static final String KEY_LVL = "user_level";
	public static final String KEY_COINS = "user_coins";
	public static final String KEY_COLOR = "user_color";
	public static final String KEY_GLASSES = "user_glasses";
	public static final String KEY_BEARD = "user_beard";
	public static final String KEY_EXP = "user_exp";
	
	//VARIABLES USER_COLOR
	public static final String KEY_COLOR_NAME = "name_color";
	public static final String KEY_COLOR_PRICE = "price";
	public static final String KEY_COLOR_LVL = "lvl_req";
	public static final String KEY_COLOR_BOUGHT = "bought";
	
	//VARIABLES USER_GLASSES
	public static final String KEY_NUM_GLASS = "num_glass";
	public static final String KEY_COLOR_GLASSES = "color_glass";
	public static final String KEY_GLASSES_PRICE = "price";
	public static final String KEY_GLASSES_LVL = "lvl_req";
	public static final String KEY_GLASSES_BOUGHT = "bought";
	
	//VARIABLES USER_BEARD
	public static final String KEY_NUM_BEARD = "num_beard";
	public static final String KEY_COLOR_BEARD = "color_beard";
	public static final String KEY_BEARD_PRICE = "price";
	public static final String KEY_BEARD_LVL = "lvl_req";
	public static final String KEY_BEARD_BOUGHT = "bought";
	
	
	
	private static final String DATABASE_NAME ="Happybuh_db";
	private static final String DATABASE_TABLE ="User_info";
	private static final String DATABASE_TABLE_COLOR = "User_color";
	private static final String DATABASE_TABLE_GLASSES = "User_glasses";
	private static final String DATABASE_TABLE_BEARD = "User_beard";
	private static final int DATABASE_VERSION = 1;
	
	private DbHelper ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;
	
	private static class DbHelper extends SQLiteOpenHelper {

		public DbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("PRAGMA foreign_keys=ON;");
			
			db.execSQL("CREATE TABLE " + DATABASE_TABLE_COLOR + " (" +
					KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
					KEY_COLOR_NAME + " TEXT NOT NULL, " + 
					KEY_COLOR_PRICE + " INTEGER NOT NULL, " + 
					KEY_COLOR_LVL + " INTEGER NOT NULL, " +
					KEY_COLOR_BOUGHT + " INTEGER NOT NULL);"
			);
			
			db.execSQL("CREATE TABLE " + DATABASE_TABLE_GLASSES + " (" +
					KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
					KEY_NUM_GLASS + " INTEGER NOT NULL, " + 
					KEY_COLOR_GLASSES + " INTEGER NOT NULL, " +
					KEY_GLASSES_PRICE + " INTEGER NOT NULL, " +
					KEY_GLASSES_LVL + " INTEGER NOT NULL, " +
					KEY_GLASSES_BOUGHT + " INTEGER NOT NULL);"
			);
			
			db.execSQL("CREATE TABLE " + DATABASE_TABLE_BEARD + " (" +
					KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
					KEY_NUM_BEARD + " INTEGER NOT NULL, " + 
					KEY_COLOR_BEARD + " INTEGER NOT NULL, " +
					KEY_BEARD_PRICE + " INTEGER NOT NULL, " +
					KEY_BEARD_LVL + " INTEGER NOT NULL, " +
					KEY_BEARD_BOUGHT + " INTEGER NOT NULL);"
			);
			
			db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" +
					KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
					KEY_NAME + " TEXT NOT NULL, " +
					KEY_LVL + " INTEGER NOT NULL, " + 
					KEY_COINS + " INTEGER NOT NULL, " +
					KEY_COLOR + " INTEGER NOT NULL, " +
					KEY_GLASSES + " INTEGER NOT NULL, " + 
					KEY_BEARD + " INTEGER NOT NULL, " +
					KEY_EXP + " INTEGER NOT NULL, " +
					"FOREIGN KEY (" + KEY_COLOR + ") REFERENCES " + DATABASE_TABLE_COLOR + " (" + KEY_ROWID + "), " +
					"FOREIGN KEY (" + KEY_GLASSES + ") REFERENCES " + DATABASE_TABLE_GLASSES + " (" + KEY_ROWID + "), " +
					"FOREIGN KEY (" + KEY_BEARD + ") REFERENCES " + DATABASE_TABLE_BEARD + " (" + KEY_ROWID + ")); "
			);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(db);
		}
		
	}
	
	public VG_Database(Context c) {
		ourContext = c;
	}
	
	public VG_Database open() {
		ourHelper = new DbHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
	}
	
	public void close() {
		ourHelper.close();
	}
	
	public long createEntry_userinfo(String name) {
		ContentValues cv = new ContentValues();
		cv.put(KEY_NAME, name);
		cv.put(KEY_LVL, 1);
		cv.put(KEY_COINS, 100);
		cv.put(KEY_COLOR, 0);
		cv.put(KEY_GLASSES, 0);
		cv.put(KEY_BEARD, 0);
		cv.put(KEY_EXP, 0);
		return ourDatabase.insert(DATABASE_TABLE, null, cv);
	}
	
	public ArrayList info_user() {
		String[] columns = new String[]{KEY_ROWID, KEY_NAME, KEY_LVL, KEY_COINS, KEY_COLOR, KEY_GLASSES, KEY_BEARD, KEY_EXP};
		Cursor c = ourDatabase.query(DATABASE_TABLE,columns, null, null, null, null, null);
		
		ArrayList a = new ArrayList();
		
		int iRow = c.getColumnIndex(KEY_ROWID);
		int iName = c.getColumnIndex(KEY_NAME);
		int iLvl = c.getColumnIndex(KEY_LVL);
		int iCoins = c.getColumnIndex(KEY_COINS);
		int iColor = c.getColumnIndex(KEY_COLOR);
		int iGlasses = c.getColumnIndex(KEY_GLASSES);
		int iBeard = c.getColumnIndex(KEY_BEARD);
		int iExp = c.getColumnIndex(KEY_EXP);
		
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			a.add(c.getString(iRow));
			a.add(c.getString(iName));
			a.add(c.getString(iLvl));
			a.add(c.getString(iCoins));
			a.add(c.getString(iColor));
			a.add(c.getString(iGlasses));
			a.add(c.getString(iBeard));
			a.add(c.getString(iExp));
		}
		c.close();
		
		return a;
	}
	
	public void Create_color_glass_beard(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE " + DATABASE_TABLE_COLOR + " (" +
				KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
				KEY_COLOR_NAME + " TEXT NOT NULL, " + 
				KEY_COLOR_PRICE + " INTEGER NOT NULL, " + 
				KEY_COLOR_LVL + " INTEGER NOT NULL, " +
				KEY_COLOR_BOUGHT + " INTEGER NOT NULL);"
		);
		
		db.execSQL("CREATE TABLE " + DATABASE_TABLE_GLASSES + " (" +
				KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
				KEY_NUM_GLASS + " INTEGER NOT NULL, " + 
				KEY_COLOR_GLASSES + " INTEGER NOT NULL, " +
				KEY_GLASSES_PRICE + " INTEGER NOT NULL, " +
				KEY_GLASSES_LVL + " INTEGER NOT NULL, " +
				KEY_GLASSES_BOUGHT + " INTEGER NOT NULL);"
		);
		
		db.execSQL("CREATE TABLE " + DATABASE_TABLE_BEARD + " (" +
				KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
				KEY_NUM_BEARD + " INTEGER NOT NULL, " + 
				KEY_COLOR_BEARD + " INTEGER NOT NULL, " +
				KEY_BEARD_PRICE + " INTEGER NOT NULL, " +
				KEY_BEARD_LVL + " INTEGER NOT NULL, " +
				KEY_BEARD_BOUGHT + " INTEGER NOT NULL);"
		);
	}
	
	public void Upgrade_color_glass_beard (SQLiteDatabase db) {
		db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
		Create_color_glass_beard(db);
	}

	
	//INSERTAMOS LOS DATOS DE BEARD
	public long Insert_beard(int num, int color, int price, int lvl, int comprado) {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		
		cv.put(KEY_NUM_BEARD, num);
		cv.put(KEY_COLOR_BEARD, color);
		cv.put(KEY_BEARD_PRICE, price);
		cv.put(KEY_BEARD_LVL, lvl);
		cv.put(KEY_BEARD_BOUGHT, comprado);
		
		return ourDatabase.insert(DATABASE_TABLE_BEARD, null, cv);
	}
	//INSERTAMOS LOS DATOS DE GLASSES
	public long Insert_glass(int num_glass, int color, int price, int lvl, int comprado) {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		cv.put(KEY_NUM_GLASS, num_glass);
		cv.put(KEY_COLOR_GLASSES, color);
		cv.put(KEY_GLASSES_PRICE, price);
		cv.put(KEY_GLASSES_LVL, lvl);
		cv.put(KEY_GLASSES_BOUGHT, comprado);
		return ourDatabase.insert(DATABASE_TABLE_GLASSES, null, cv);
	}

	//INSERTAMOS LOS DATOS DE COLOR
	public long Insert_color(String color, int price, int lvl, int comprado) {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		cv.put(KEY_COLOR_NAME, color);
		cv.put(KEY_COLOR_PRICE, price);
		cv.put(KEY_COLOR_LVL, lvl);
		cv.put(KEY_COLOR_BOUGHT, comprado);
		return ourDatabase.insert(DATABASE_TABLE_COLOR, null, cv);
	}
	
	//METODOS GET
	
	public String getUserName() {
		String[] columns = new String[]{KEY_ROWID, KEY_NAME, KEY_LVL, KEY_COINS, KEY_COLOR, KEY_GLASSES, KEY_BEARD, KEY_EXP};
		Cursor c = ourDatabase.query(DATABASE_TABLE,columns, null, null, null, null, null);
		
		int iName = c.getColumnIndex(KEY_NAME);
		c.moveToFirst();
		
		String name = c.getString(iName);
		c.close();
		return name;
	}
	
	/*public int getColorLevel() {
		String[] columns = new String[]{KEY_ROWID, KEY_COLOR_NAME, KEY_COLOR_PRICE, KEY_COLOR_LVL, KEY_COLOR_BOUGHT};
		Cursor c = ourDatabase.query(DATABASE_TABLE_COLOR,columns, null, null, null, null, null);
		
		int iLvlReq = c.getColumnIndex(KEY_COLOR_LVL);
		c.moveToFirst();
		
		int level = Integer.parseInt(c.getString(iLvlReq));
		c.close();
		return level;
	}
	
	public int getColorCoins() {
		String[] columns = new String[]{KEY_ROWID, KEY_COLOR_NAME, KEY_COLOR_PRICE, KEY_COLOR_LVL, KEY_COLOR_BOUGHT};
		Cursor c = ourDatabase.query(DATABASE_TABLE_COLOR,columns, null, null, null, null, null);
		
		int iPrice = c.getColumnIndex(KEY_COLOR_PRICE);
		c.moveToFirst();
		
		int price = Integer.parseInt(c.getString(iPrice));
		c.close();
		return price;
	}*/


	
	//METODOS SET
	public void setUser(String user, String new_user) {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		cv.put(KEY_NAME, new_user);
		ourDatabase.update(DATABASE_TABLE, cv, KEY_ROWID + "= 0", null);
	}

	public String getColorById(int u_color) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
