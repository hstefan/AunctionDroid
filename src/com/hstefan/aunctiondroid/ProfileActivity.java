package com.hstefan.aunctiondroid;

import com.hstefan.aunctiondroid.db.entities.User;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ProfileActivity extends Activity {
	SQLiteDatabase helper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile);
		
		Log.i("Session started:", User.getActive().getEmail() + ":" + User.getActive().getPass());
		helper = AunctionDroidActivity.getHelper().getWritableDatabase();
		
		setListners();
	}

	private void setListners() {
		Button reg_button = (Button)findViewById(R.id.register_button_id);
		reg_button.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent intent = new Intent(ProfileActivity.this, RegisterItemActivity.class);
				startActivity(intent);
			}
		});
	}
	
}
