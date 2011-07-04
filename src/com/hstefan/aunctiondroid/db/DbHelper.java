package com.hstefan.aunctiondroid.db;

import java.security.NoSuchAlgorithmException;

import com.hstefan.aunctiondroid.listners.LoginValidationListner;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
	
	static final String DATABASE_NAME= "aunctiondroid.db";
	static final int DATABASE_VERSION = 1;
	
	static final String USER_TABLE = "user";
	
	public DbHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		if(DATABASE_VERSION >= 1) {
			String create_sql = "CREATE TABLE ? (?, ?, ?)";
			db.execSQL(create_sql, new String[]{
					USER_TABLE,
					"id INTEGER PRIMARY KEY AUTOINCREMENT,",
					"email TEXT NOT NULL", 
					"password BLOB NOT NULL"});
		}
		insertExampleData(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}
	
	private void insertExampleData(SQLiteDatabase db) {
		if(DATABASE_VERSION >= 1) {
			String users_sql = "INSERT INTO ? VALUES(?,?)";
			try {
				db.execSQL(users_sql, new String[] {USER_TABLE, 
						"hugopuhlmann@gmail.com", 
						new String(PassDigester.digest("123"))});
				db.execSQL(users_sql, new String[] {USER_TABLE, 
						"puhlmann@inf.ufsm.br", 
						new String(PassDigester.digest("123"))});
				db.execSQL(users_sql, new String[] {USER_TABLE, 
						"fiabane@inf.ufsm.br", 
						new String(PassDigester.digest("123"))});
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
