package com.hstefan.aunctiondroid;

import com.hstefan.aunctiondroid.listners.LoginValidationListner;

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
		
		setListners();
	}

	private void setListners() {
		Button b = (Button)findViewById(R.id.login_button);
        b.setOnClickListener(new LoginValidationListner());
	}
}
