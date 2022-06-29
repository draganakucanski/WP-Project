package services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import beans.Facilities;
import beans.FacilityType;
import beans.Location;
import beans.Role;
import beans.SportsFacility;
import beans.User;
import beans.UserType;
import beans.UserTypeName;
import dao.FacilityTypeDAO;
import dao.UserTypeDAO;
import dto.FacilityAddingDTO;
import dto.FacilitySearchDTO;
import dto.UserRegistrationDTO;

public class FacilitiesService {
	
	private Facilities facilities = new Facilities();
	
	public Collection<SportsFacility> getAll(){
		return facilities.values();
	}
	public ArrayList<SportsFacility> SearchFacility(FacilitySearchDTO search) {
		
		ArrayList<SportsFacility> ret = new ArrayList<SportsFacility>();		
		for(SportsFacility sf : this.facilities.getValues()) {
			if(search.allTypes || sf.getType() == search.type) {
				if(sf.getName().toLowerCase().contains(search.name.toLowerCase())){
					if(sf.getLocation().getAddress().getCity().toLowerCase().contains(search.location.toLowerCase())) {
						if(sf.getAverageGrade() >= search.grade) {
							ret.add(sf);
							}	
						}
					}
				}
			}
		
		return ret;
	}
  public ArrayList<SportsFacility> GetGyms() {
		
		ArrayList<SportsFacility> ret = new ArrayList<SportsFacility>();		
		for(SportsFacility sf : this.facilities.getValues()) {
			if(sf.getType().equals(FacilityType.GYM))
				ret.add(sf);
		}
		return ret;
	}
  public ArrayList<SportsFacility> GetPools() {
		
		ArrayList<SportsFacility> ret = new ArrayList<SportsFacility>();		
		for(SportsFacility sf : this.facilities.getValues()) {
			if(sf.getType().equals(FacilityType.POOL))
				ret.add(sf);
		}
		return ret;
	}
  public ArrayList<SportsFacility> GetDanceS() {
		
		ArrayList<SportsFacility> ret = new ArrayList<SportsFacility>();		
		for(SportsFacility sf : this.facilities.getValues()) {
			if(sf.getType().equals(FacilityType.DANCESTUDIO))
				ret.add(sf);
		}
		return ret;
	}
  public ArrayList<SportsFacility> GetSportsC() {
		
		ArrayList<SportsFacility> ret = new ArrayList<SportsFacility>();		
		for(SportsFacility sf : this.facilities.getValues()) {
			if(sf.getType().equals(FacilityType.SPORTSCENTER))
				ret.add(sf);
		}
		return ret;
	}
  public ArrayList<SportsFacility> GetOpened() {
		
		ArrayList<SportsFacility> ret = new ArrayList<SportsFacility>();		
		for(SportsFacility sf : this.facilities.getValues()) {
			if(sf.isWorks())
				ret.add(sf);
		}
		return ret;
	}
public ArrayList<SportsFacility> GetClosed() {
		
		ArrayList<SportsFacility> ret = new ArrayList<SportsFacility>();		
		for(SportsFacility sf : this.facilities.getValues()) {
			if(!sf.isWorks())
				ret.add(sf);
		}
		return ret;
	}
public void FacilityAdding(FacilityAddingDTO objectInfo) {
	
	this.facilities.addFacility(new SportsFacility(objectInfo.name,objectInfo.type,(boolean)objectInfo.status,objectInfo.location,objectInfo.grade, objectInfo.logo));
}												//String name, FacilityType type, boolean works, Location location, double averageGrade, String logo
}

