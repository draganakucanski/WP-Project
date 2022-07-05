package services;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;

import javax.imageio.ImageIO;

import beans.Address;
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
public SportsFacility FacilityAdding(FacilityAddingDTO objectInfo) {
	String logo = null;
	Location location = new Location(Double.valueOf(objectInfo.longi),Double.valueOf(objectInfo.lat), new Address(objectInfo.street,objectInfo.number,objectInfo.city,Integer.valueOf(objectInfo.zip)));
	SportsFacility sf = new SportsFacility(objectInfo.name,objectInfo.type,location, logo);
	this.facilities.addFacility(sf);
	this.facilities.AddFacilityLogo(sf, objectInfo.imageFile);
	System.out.println(sf.getName());
	return sf;
}

}

