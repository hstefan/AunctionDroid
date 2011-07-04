package com.hstefan.aunctiondroid.listners;

import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.hstefan.aunctiondroid.R;
import com.hstefan.aunctiondroid.db.PassDigester;
import com.hstefan.aunctiondroid.db.entities.User;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class LoginValidationListner implements OnClickListener {
	
	private Activity parent;
	
	public LoginValidationListner(Activity parent) {
		super();
		this.parent = parent;
	}

	public void onClick(View v) {
		EditText log_etext = (EditText)parent.findViewById(R.id.email_text);
		EditText pass_etext = (EditText)parent.findViewById(R.id.pass_text);
		
		String email = log_etext.getText().toString();
		String pass = pass_etext.getText().toString();
		
		User u = validateUser(email, pass);
		if(u != null) {
			//means credentials are OK
			//TODO
		} else {
			//do something
		}
	}
	
	private User validateUser(String email, String pass) {
		byte[] digest = PassDigester.digest(pass);
		return null;
	}


}
