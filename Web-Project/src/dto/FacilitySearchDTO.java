package dto;

import beans.FacilityType;

public class FacilitySearchDTO {

	public String name;
	public String location;
	public double grade;
	public FacilityType type;
	public boolean allTypes;
	public FacilitySearchDTO(String name, String location, String grade, String type) {
		super();
		this.name = name;
		this.location = location;
		this.grade = Double.parseDouble(grade);
		
		if(type.equals("GYM")) this.type = FacilityType.GYM;
		else if (type.equals("DANCESTUDIO")) this.type = FacilityType.DANCESTUDIO;
		else if (type.equals("POOL")) this.type = FacilityType.POOL;
		else if (type.equals("SPORTSCENTER")) this.type = FacilityType.SPORTSCENTER;
		else if (type.equals("Types") || type.isBlank()) {
			this.type = null;
			this.allTypes = true;
		}
	}
	
	
}
