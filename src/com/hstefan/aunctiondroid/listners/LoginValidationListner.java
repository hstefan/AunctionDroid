package com.hstefan.aunctiondroid.listners;

import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.hstefan.aunctiondroid.R;
import com.hstefan.aunctiondroid.db.entities.User;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class LoginValidationListner implements OnClickListener {
	
	private Activity parent;
	
	static final int NUM_ROUNDS = 30;
	
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
		try {
			String out = digestPass(pass);
			System.out.println(out);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.i("security", "Unable to get proper MessageDigest.");
		}
		return null;
	}

	public static String digestPass(String pass) throws NoSuchAlgorithmException {
		MessageDigest digester = MessageDigest.getInstance("SHA-512");
		digester.update(pass.getBytes());
		for(int rounds = 0; rounds < NUM_ROUNDS; rounds++) {
			digester.update(digester.digest());
		}
		return new String(digester.digest());
	}

}
