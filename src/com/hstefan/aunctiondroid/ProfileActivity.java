package com.hstefan.aunctiondroid;

import com.hstefan.aunctiondroid.db.entities.User;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class ProfileActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile);
		
		Log.i("Session started:", User.getActive().getEmail() + ":" + User.getActive().getPass());
	}
	
}
