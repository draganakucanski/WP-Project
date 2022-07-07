package dto;

import beans.FacilityType;
import beans.Location;

public class FacilityAddingDTO {
	
	public String name;
	public String number;
	public String street;
	public String city;
	public String zip;
	public String longi;
	public String lat;
	public FacilityType type;
	public String logo;
	public String imageFile;
	public String username;
	public FacilityAddingDTO(String name, String number, String street, String city, String zip, String longi,
			String lat, FacilityType type, String logo, String imageFile, String username) {
		super();
		this.name = name;
		this.number = number;
		this.street = street;
		this.city = city;
		this.zip = zip;
		this.longi = longi;
		this.lat = lat;
		this.type = type;
		this.logo = logo;
		this.imageFile = imageFile;
		this.username = username;
	}
	
	
}
