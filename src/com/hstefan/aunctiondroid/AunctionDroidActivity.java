package com.hstefan.aunctiondroid;

import com.hstefan.aunctiondroid.db.DbHelper;
import com.hstefan.aunctiondroid.db.entities.User;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class AunctionDroidActivity extends Activity {
    /** Called when the activity is first created. */
	private boolean is_logged_in;
	private DbHelper dbhelper;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	is_logged_in = false;
    	dbhelper = new DbHelper(this);
        super.onCreate(savedInstanceState);
        if(!is_logged_in) {
	        Intent intent = new Intent(this, LoginActivity.class);
	        startActivity(intent);
	        is_logged_in = true;
        } else {
        	setContentView(R.layout.profile);
        }
    }
}