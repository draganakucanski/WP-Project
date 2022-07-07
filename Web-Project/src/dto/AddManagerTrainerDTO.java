package dto;

import beans.Gender;

public class AddManagerTrainerDTO {

	public String username;
	public String password;
	public String firstname;
	public String lastname;
	public Gender gender;
	public String dateOfBirth;
	public String type;
	public AddManagerTrainerDTO(String username, String password, String firstname, String lastname, Gender gender,
			String dateOfBirth, String type) {
		super();
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.type = type;
	}
	
	
}
