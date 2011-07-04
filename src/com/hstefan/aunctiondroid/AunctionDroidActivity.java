package com.hstefan.aunctiondroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class AunctionDroidActivity extends Activity {
    /** Called when the activity is first created. */
	private boolean is_logged_in;
	
	public AunctionDroidActivity() {
		is_logged_in = false;
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
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