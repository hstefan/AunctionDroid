package com.hstefan.aunctiondroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		Button b = (Button)findViewById(R.id.login_button);
        b.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(null, AunctionDroidActivity.class);
				startActivity(intent);
			}
		});
	}
	
	private void validateLogin() {
		
	}
}
