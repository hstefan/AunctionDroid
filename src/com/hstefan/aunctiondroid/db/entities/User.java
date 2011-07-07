package com.hstefan.aunctiondroid.db.entities;

public class User {
	private int id;
	private String email;
	private String pass;
	private int money;
	
	static User instance = null;
	
	public User(int id, String email, String pass, int money) {
		super();
		this.id = id;
		this.email = email;
		this.pass = pass;
		this.setMoney(money);
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

	public void setMoney(int money) {
		this.money = money;
	}

	public int getMoney() {
		return money;
	}
}
