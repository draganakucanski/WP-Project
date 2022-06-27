package services;

import java.util.Collection;

import beans.Gender;
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
		UserTypeDAO ut = new UserTypeDAO();
		UserType type = ut.getUserTypeByName(UserTypeName.BRONZE);
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
	
	public User editUser(String username,String firstName, String lastName, String password, String dateOfBirth,Gender gender) {
		User us = users.getUser(username);
		us.setFirstName(firstName);
		us.setLastName(lastName);
		us.setPassword(password);
		us.setDateOfBirth(dateOfBirth);
		us.setGender(gender);
		this.users.edit(username, us);
		return us;
	}
}
