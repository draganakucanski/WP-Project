package service;

import com.google.gson.JsonElement;

import beans.User;

public class UserService {

	public User getProduct(String username) {
		return this.users.getUser(username);
	}

	
}
