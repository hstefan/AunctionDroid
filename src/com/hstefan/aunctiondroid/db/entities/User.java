package com.hstefan.aunctiondroid.db.entities;

public class User {
	private int id;
	private String email;
	private String pass;
	
	static User instance = null;
	
	public User(int id, String email, String pass) {
		super();
		this.id = id;
		this.email = email;
		this.pass = pass;
		setActive(this);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}
	
	public static User getActive() {
		return instance;
	}
	
	public static void setActive(User u) {
		instance = u;
	}
}
