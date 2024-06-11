package com.demoLogin.service;

import java.util.HashMap;

import dto.User;

public class LoginService {
	
HashMap<String,String> users = new HashMap<String,String>();
	//constructor -- in realworld we are going to use a database for this
public LoginService() {
	users.put("jon","Joh Doe");
	users.put("Thao", "ThaoLo");
	// we are going to pull out the name based on the userId
}
public User getUserDetail(String userId) {
	User user = new User();
	user.setUserName(users.get(userId));
	user.setUserId(userId);
	return user; // we need to get this user object to the view
}
public boolean authenticate(String userId, String password) {
	//retrieve userId + password from the database
	// compare with the userId and password provided
	if (password == null || password.trim()=="") {
		return false;
	}
	return true;

}


}
