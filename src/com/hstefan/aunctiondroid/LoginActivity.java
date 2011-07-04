package com.hstefan.aunctiondroid;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		Button b = (Button)findViewById(R.id.login_button);
        b.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				validateLogin(v);
			}
		});
	}
	
	private void validateLogin(View v) {
		EditText login = (EditText)findViewById(R.id.email_text);
		EditText pass = (EditText)findViewById(R.id.pass_text);
		
		if(login.getText().equals("hstefan") && pass.getText().equals("123")) {
			Intent next = new Intent(this, AunctionDroidActivity.class);
			startActivity(next);
		}
	}
}
