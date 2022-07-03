package dto;

public class UserSearchDTO {
	
	public String firstName;
	public String lastName;
	public String username;
	public UserSearchDTO(String firstName, String lastName, String username) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
	}
}
