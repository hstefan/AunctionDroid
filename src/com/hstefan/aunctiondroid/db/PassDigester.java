package com.hstefan.aunctiondroid.db;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PassDigester {
	
	public static final int NUM_ROUNDS = 30;
	
	public static byte[] digest(String pass) {
		MessageDigest digester = null;
		try {
			digester = MessageDigest.getInstance("SHA-512");
			digester.update(pass.getBytes());
			for(int rounds = 0; rounds < NUM_ROUNDS; rounds++) {
				digester.update(digester.digest());
			}
			return digester.digest();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
