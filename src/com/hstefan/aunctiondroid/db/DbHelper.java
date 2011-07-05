package com.hstefan.aunctiondroid.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper {
	
	public static final String DATABASE_NAME= "aunctiondroid.db";
	public static final int DATABASE_VERSION = 2;
	
	public static final String USER_TABLE = "user";
	public static final String ITEM_TABLE = "item";
	public static final String USER_ITEM_TABLE = "user_item";
	
	public DbHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		getWritableDatabase().delete(USER_TABLE, null, null);
		insertExampleData(getWritableDatabase());
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		if(DATABASE_VERSION >= 1) {
			String create_sql = "CREATE TABLE " + USER_TABLE +
					"(id INTEGER PRIMARY KEY AUTOINCREMENT," +
					"email TEXT NOT NULL," +
					"password TEXT NOT NULL)";
			db.execSQL(create_sql);
		} 
		if(DATABASE_VERSION >= 2) {
			String create_sql = "CREATE TABLE " + ITEM_TABLE +
			"(id INTEGER PRIMARY KEY AUTOINCREMENT," +
			"name TEXT NOT NULL)";
			db.execSQL(create_sql);
			
			create_sql = "CREATE TABLE " + USER_ITEM_TABLE +
			"(id_user INTEGER, id_item INTEGER, " +
			"FOREIGN KEY(id_user) REFERENCES " + USER_TABLE + ","  + 
			"FOREIGN KEY(id_item) REFERENCES " + ITEM_TABLE + ")";
			db.execSQL(create_sql);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}
	
	private void insertExampleData(SQLiteDatabase db) {
		if(DATABASE_VERSION >= 1) {
			try {
				ContentValues cv = new ContentValues();
				cv.put("email", "hugopuhlmann@gmail.com");
				cv.put("password", new String(PassDigester.digest("123")));
				db.insert(USER_TABLE, null, cv);
				Log.i("Example data", "inserted");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
