package beans;

import java.util.ArrayList;

public class SportsFacility {

	private String name;
	private FacilityType type;
	private ArrayList<String> content;
	private boolean works;
	private Location location;
	private String logo;
	private double averageGrade;
	private String workingHours;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public FacilityType getType() {
		return type;
	}
	public void setType(FacilityType type) {
		this.type = type;
	}
	public ArrayList<String> getContent() {
		return content;
	}
	public void setContent(ArrayList<String> content) {
		this.content = content;
	}
	public boolean isWorks() {
		return works;
	}
	public void setWorks(boolean works) {
		this.works = works;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public double getAverageGrade() {
		return averageGrade;
	}
	public void setAverageGrade(double averageGrade) {
		this.averageGrade = averageGrade;
	}
	public String getWorkingHours() {
		return workingHours;
	}
	public void setWorkingHours(String workingHours) {
		this.workingHours = workingHours;
	}
	public SportsFacility(String name, FacilityType type, ArrayList<String> content, boolean works, Location location,
			String logo, double averageGrade, String workingHours) {
		super();
		this.name = name;
		this.type = type;
		this.content = content;
		this.works = works;
		this.location = location;
		this.logo = logo;
		this.averageGrade = averageGrade;
		this.workingHours = workingHours;
	}
	public SportsFacility() {
		super();
	}
	public SportsFacility(String name, Location location, boolean works) {
		this.name= name;
		this.location = location;
		this.works= works;
	}
	public SportsFacility(String name, boolean works) {
		this.name = name;
		this.works = works;
	}
	public SportsFacility(FacilityType type) {
		this.type=type;
			}
	
	public SportsFacility(String name, FacilityType type, boolean works, Location location, double averageGrade, String logo) {
		super();
		this.name = name;
		this.type = type;
		this.works = works;
		this.location = location;
		this.averageGrade = averageGrade;
		this.logo = logo;
	}
	public SportsFacility(String name, FacilityType type, Location location, String logo) {
		super();
		this.name = name;
		this.type = type;
		this.location = location;
		this.logo = logo;
	}
	
	
	
}
