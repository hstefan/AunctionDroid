package com.hstefan.aunctiondroid.listners;

import com.hstefan.aunctiondroid.LoginActivity;
import com.hstefan.aunctiondroid.R;
import com.hstefan.aunctiondroid.db.DbHelper;
import com.hstefan.aunctiondroid.db.PassDigester;
import com.hstefan.aunctiondroid.db.entities.User;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class LoginValidationListner implements OnClickListener {
	
	private Activity parent;
	private SQLiteDatabase database;
	
	public LoginValidationListner(Activity parent, SQLiteDatabase database) {
		super();
		this.parent = parent;
		this.database = database;
	}

	public void onClick(View v) {
		EditText log_etext = (EditText)parent.findViewById(R.id.email_text);
		EditText pass_etext = (EditText)parent.findViewById(R.id.pass_text);
		
		String email = log_etext.getText().toString();
		String pass = pass_etext.getText().toString();
		
		User u = validateUser(email, pass);
		if(u != null) {
			Log.i("Login attempt", "Sucessful");
			((LoginActivity)parent).onAuthentication(u);
			log_etext.setText(null);
			pass_etext.setText(null);
		} else {
			Log.i("Login attempt", "unsucessful");
		}
	}
	
	private User validateUser(String email, String pass) {
		byte[] digest = PassDigester.digest(pass);
		Cursor res = database.query(DbHelper.USER_TABLE, null, 
				"email=? AND password=?",
				new String[]{email, new String(digest)},
				null, null, null);
		if(res.getColumnCount() >= 1) {
			res.moveToFirst();
			return new User(res.getInt(0), res.getString(1), res.getString(2));
		}
		return null;
	}


}
