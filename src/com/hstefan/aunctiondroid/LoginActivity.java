package com.hstefan.aunctiondroid;

import com.hstefan.aunctiondroid.db.entities.User;
import com.hstefan.aunctiondroid.listners.LoginValidationListner;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.Button;

public class LoginActivity extends Activity {
	
	private SQLiteOpenHelper helper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		helper = AunctionDroidActivity.getHelper();
		setListners();
	}

	private void setListners() {
		Button b = (Button)findViewById(R.id.login_button);
        b.setOnClickListener(new LoginValidationListner(this, helper.getWritableDatabase()));
	}
	
	public void onAuthentication(User user) {
		Intent intent = new Intent(this, ProfileActivity.class);
		startActivity(intent);
	}
}
