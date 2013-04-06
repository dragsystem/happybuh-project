package com.happybuh;

import android.content.Context;
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
	
	//VARIABLES USER_COLOR
	public static final String KEY_COLOR_NAME = "name_color";
	public static final String KEY_COLOR_PRICE = "price";
	public static final String KEY_COLOR_LVL = "lvl_req";
	
	//VARIABLES USER_GLASSES
	public static final String KEY_NUM_GLASS = "num_glass";
	public static final String KEY_COLOR_GLASSES = "color_glass";
	public static final String KEY_GLASSES_PRICE = "price";
	public static final String KEY_GLASSES_LVL = "lvl_req";
	
	//VARIABLES USER_BEARD
	public static final String KEY_NUM_BEARD = "num_beard";
	public static final String KEY_COLOR_BEARD = "color_beard";
	public static final String KEY_BEARD_PRICE = "price";
	public static final String KEY_BEARD_LVL = "lvl_req";
	
	
	
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
			db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" +
					KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
					KEY_NAME + " TEXT NOT NULL, " +
					KEY_LVL + " INTEGER NOT NULL, " + 
					KEY_COINS + " INTEGER NOT NULL, " +
					KEY_COLOR + " INTEGER NOT NULL FOREIGN KEY REFERENCES " +  DATABASE_TABLE_COLOR + " (" + KEY_ROWID + "), " +
					KEY_GLASSES + " INTEGER NOT NULL FOREIGN KEY REFERENCES " +  DATABASE_TABLE_GLASSES + " (" + KEY_ROWID + "), " + 
					KEY_BEARD + " INTEGER NOT NULL FOREIGN KEY REFERENCES " +  DATABASE_TABLE_BEARD + " (" + KEY_ROWID + "));"
			);
			
			db.execSQL("CREATE TABLE " + DATABASE_TABLE_COLOR + " (" +
					KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
					KEY_COLOR + " TEXT NOT NULL, " + 
					KEY_COLOR_PRICE + " INTEGER NOT NULL, " + 
					KEY_COLOR_LVL + " INTEGER NOT NULL);"
			);
			
			db.execSQL("CREATE TABLE " + DATABASE_TABLE_GLASSES + " (" +
					KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
					KEY_NUM_GLASS + " INTEGER NOT NULL, " + 
					KEY_COLOR_GLASSES + " INTEGER NOT NULL, " +
					KEY_GLASSES_PRICE + " INTEGER NOT NULL, " +
					KEY_GLASSES_LVL + " INTEGER NOT NULL);"
			);
			
			db.execSQL("CREATE TABLE " + DATABASE_TABLE_BEARD + " (" +
					KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
					KEY_NUM_BEARD + " INTEGER NOT NULL, " + 
					KEY_COLOR_BEARD + " INTEGER NOT NULL, " +
					KEY_BEARD_PRICE + " INTEGER NOT NULL, " +
					KEY_BEARD_LVL + " INTEGER NOT NULL);"
			);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			
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
}
