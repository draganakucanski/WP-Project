package beans;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class User {
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String dateOfBirth;
	private Gender gender;
	private Role role;
	private UserType type;
	private boolean deleted;
	
	private TrainingHistory history;
	private Membership membership;
	private SportsFacility sportsFacility;
	private ArrayList<SportsFacility> visitedFacility;
	private double pointsCollected;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public UserType getType() {
		return type;
	}
	public void setType(UserType type) {
		this.type = type;
	}
	public TrainingHistory getHistory() {
		return history;
	}
	public void setHistory(TrainingHistory history) {
		this.history = history;
	}
	public Membership getMembership() {
		return membership;
	}
	public void setMembership(Membership membership) {
		this.membership = membership;
	}
	public SportsFacility getSportsFacility() {
		return sportsFacility;
	}
	public void setSportsFacility(SportsFacility sportsFacility) {
		this.sportsFacility = sportsFacility;
	}
	public ArrayList<SportsFacility> getVisitedFacility() {
		return visitedFacility;
	}
	public void setVisitedFacility(ArrayList<SportsFacility> visitedFacility) {
		this.visitedFacility = visitedFacility;
	}
	public double getPointsCollected() {
		return pointsCollected;
	}
	public void setPointsCollected(double pointsCollected) {
		this.pointsCollected = pointsCollected;
	}
	public User(String username, String password, String firstName, String lastName, String dateOfBirth, Gender gender,
			Role role, UserType type, TrainingHistory history, Membership membership, SportsFacility sportsFacility,
			ArrayList<SportsFacility> visitedFacility, double pointsCollected) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.role = role;
		this.type = type;
		this.history = history;
		this.membership = membership;
		this.sportsFacility = sportsFacility;
		this.visitedFacility = visitedFacility;
		this.pointsCollected = pointsCollected;
	}
	public User() {
		super();
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	public User(String username, String password, String firstName, String lastName, String dateOfBirth, Gender gender,
			Role role, UserType type, boolean deleted, double pointsCollected) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.role = role;
		this.type = type;
		this.deleted = deleted;
		this.pointsCollected = pointsCollected;
	}
	
	
}
