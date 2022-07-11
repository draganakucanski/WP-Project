package services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Random;

import beans.Membership;
import beans.MembershipType;
import beans.Memberships;
import beans.UserType;
import beans.Users;

public class MembershipService {

private Memberships memberships = new Memberships();
	
	public Collection<Membership> getAll(){
		return memberships.values();
	}
	public void CreateMembership(String username, String type) {
		MembershipType mt = MembershipType.valueOf(type.toUpperCase());
		LocalDate date = LocalDate.now();
		LocalDateTime valid = null;
		Users users = new Users();
		UserType userType = users.getUser(username).getType();
		if(mt == MembershipType.MONTHLY) {
			valid = LocalDateTime.now().plusMonths(1);
		} else 
			valid = LocalDateTime.now().plusYears(1);
		double price = 0; 
		if(mt == MembershipType.MONTHLY) {
			price = 2500;
		} else 
			price = 26000;
		int visits = 0;
		if(mt == MembershipType.MONTHLY) {
			visits = 1;
		} else 
			visits = 3;
		price = price - price*userType.getDiscount()/100;
		addMembership(new Membership(generateNewID(), mt, date, valid, price, username, true, visits, false)); 
	}	
	public void addMembership(Membership m) {
		for(Membership mem: this.memberships.values()) {
			if(mem.getCustomer().equals(m.getCustomer()) && mem.isActive()) {
				this.memberships.ChangeToInactive(mem);
				Users us = new Users();
				us.updatePoints(m.getCustomer(), m);
				this.memberships.ChangeToGotPoints(mem);
			}
		}
		this.memberships.addMembership(m);
	}
	public String generateNewID() {
	    int leftLimit = 48; // numeral '0'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 10;
	    Random random = new Random();

	    String generatedString = random.ints(leftLimit, rightLimit + 1)
	      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
	      .limit(targetStringLength)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();

	    return generatedString;
	}
}
