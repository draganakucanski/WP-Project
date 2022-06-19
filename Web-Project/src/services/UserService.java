package services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import beans.Role;
import beans.User;
import beans.UserType;
import beans.UserTypeName;
import beans.Users;
import dao.UserTypeDAO;
import dto.UserRegistrationDTO;


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

	public boolean UsernameExists(String username) {
		for (User u : this.users.getValues()) {
			if(u.getUsername().equals(username)) 
				return true;
		}
		return false;
	}

	public void CustomerRegistration(UserRegistrationDTO customerInfo) {
		UserType type = UserTypeDAO.getUserTypeByName(UserTypeName.BRONZE);
		this.users.addUser(new User(customerInfo.username,customerInfo.password,customerInfo.firstname,customerInfo.lastname,customerInfo.dateOfBirth, customerInfo.gender,Role.CUSTOMER,type,false, 0));
	}	
	
	public User login(String username, String password) {
		User user = users.findUser(username);
		System.out.println(user.getUsername());
		if(user != null && user.getPassword().equals(password)) {
			return user;
		}
		else return null;
	}
}
