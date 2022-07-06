package services;

import java.util.ArrayList;
import java.util.Collection;

import beans.Facilities;
import beans.Gender;
import beans.Role;
import beans.SportsFacility;
import beans.User;
import beans.UserType;
import beans.UserTypeName;
import beans.Users;
import dao.UserTypeDAO;
import dto.AddManagerTrainerDTO;
import dto.FacilitySearchDTO;
import dto.UserRegistrationDTO;
import dto.UserSearchDTO;


public class UserService {

	private Users users = new Users();
	private Facilities facilities =new Facilities();
	public Collection<User> getUsers() {
		Collection<User> us = this.users.getValues();
		System.out.println(us);
		return us;
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
	public User editUsersFacility(String username,SportsFacility sportsFacility) {
		System.out.println(username);
		User us = users.getUser(username);
		System.out.println(us);
		us.setSportsFacility(sportsFacility);
		this.users.edit(username, us);
		return us;
		
	}
	public ArrayList<User> getCustomers() {
		
		ArrayList<User> ret = new ArrayList<User>();		
		for(User us : this.users.getValues()) {
			System.out.println( us.getRole());
			if(us.getRole().equals(Role.CUSTOMER))
				ret.add(us);
				
		}
		/*
		 * System.out.println("VRACENI CUSTOMERI :"); System.out.println(ret);
		 */
		return ret;
	}
	public ArrayList<User> getManagers() {
	
	ArrayList<User> ret = new ArrayList<User>();		
	for(User us : this.users.getValues()) {
		System.out.println( us.getRole());
		if(us.getRole().equals(Role.MANAGER))
			ret.add(us);
			
	}
	/*
	 * System.out.println("VRACENI MANAGERI :"); System.out.println(ret);
	 */
	return ret;
}
	public ArrayList<User> getFreeManagers() {
		
		ArrayList<User> ret = new ArrayList<User>();		
		for(User us : this.users.getValues()) {
			System.out.println( us.getRole());
			if(us.getRole().equals(Role.MANAGER) && us.getSportsFacility()==null)
				ret.add(us);
				
		}
		
		  System.out.println("VRACENI free MANAGERI :"); System.out.println(ret);
		 
		return ret;
	}
	public ArrayList<User> getTrainers() {
		
		ArrayList<User> ret = new ArrayList<User>();		
		for(User us : this.users.getValues()) {
			System.out.println( us.getRole());
			if(us.getRole().equals(Role.TRAINER))
				ret.add(us);
				
		}
		/*
		 * System.out.println("VRACENI TRAINERI :"); System.out.println(ret);
		 */
		return ret;
	}
public ArrayList<User> getAdmins() {
		
		ArrayList<User> ret = new ArrayList<User>();		
		for(User us : this.users.getValues()) {
			
			if(us.getRole().equals(Role.ADMIN))
				ret.add(us);
				
		}
		
		return ret;
	}
public ArrayList<User> getGoldUsers() {
	
	ArrayList<User> ret = new ArrayList<User>();		
	for(User us : this.users.getValues()) {
		
		if(us.getRole().equals(Role.CUSTOMER)) {
			if(us.getType().getUserTypeName().equals(UserTypeName.GOLD))
				ret.add(us);
		}
	}
	
	return ret;
}
public ArrayList<User> getSilverUsers() {
	
	ArrayList<User> ret = new ArrayList<User>();		
	for(User us : this.users.getValues()) {
		if(us.getRole().equals(Role.CUSTOMER)) {
			if(us.getType().getUserTypeName().equals(UserTypeName.SILVER))
				ret.add(us);
		}
	}
	
	return ret;
}
public ArrayList<User> getBronzeUsers() {
	
	ArrayList<User> ret = new ArrayList<User>();		
	for(User us : this.users.getValues()) {
		if(us.getRole().equals(Role.CUSTOMER)) {
			if(us.getType().getUserTypeName().equals(UserTypeName.BRONZE))
				ret.add(us);
		}
			
	}
	
	return ret;
}

public ArrayList<User> getAllTypesOfUsers() {
	
	ArrayList<User> ret = new ArrayList<User>();		
	for(User us : this.users.getValues()) {
		if(us.getRole().equals(Role.CUSTOMER)) {
			if(us.getType().getUserTypeName().equals(UserTypeName.BRONZE) || us.getType().getUserTypeName().equals(UserTypeName.SILVER) || us.getType().getUserTypeName().equals(UserTypeName.GOLD) )
				ret.add(us);
		}
			
	}
	
	return ret;
}

public ArrayList<User> SearchUser(UserSearchDTO search) {
	
	ArrayList<User> ret = new ArrayList<User>();		
	for(User us : this.users.getValues()) {
		
			if(us.getFirstName().toLowerCase().contains(search.firstName.toLowerCase())){
				if(us.getLastName().toLowerCase().contains(search.lastName.toLowerCase())) {
					if(us.getUsername().contains(search.username)) {
						ret.add(us);
						}	
					}
				}
			
		}
	
	return ret;
}
public void AddManagerTrainer(AddManagerTrainerDTO userInfo) {
	Role userRole = Role.valueOf(userInfo.type.toUpperCase());
	this.users.addUser(new User(userInfo.username,userInfo.password,userInfo.firstname,userInfo.lastname,userInfo.dateOfBirth, userInfo.gender,userRole,false));
}	
}
