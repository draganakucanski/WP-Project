package services;

import java.util.Collection;

import beans.User;
import beans.Users;


public class UserService {

	private Users users = new Users();
	
	public Collection<User> getUsers() {
		return this.users.getValues();
	}
	
	public User getUser(String username) {
		return this.users.getUser(username);
	}
	
	public void addUser(User user) {
		this.users.addUser(user);
	}

}
