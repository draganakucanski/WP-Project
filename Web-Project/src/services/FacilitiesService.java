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
import beans.Training;
import beans.TrainingHistories;
import beans.TrainingHistory;
import beans.Trainings;
import beans.User;
import beans.UserType;
import beans.UserTypeName;
import beans.Users;
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
public boolean NameExists(String name) {
	for (SportsFacility sf : this.facilities.getValues()) {
		if(sf.getName().equals(name)) 
			return true;
	}
	return false;
}
public SportsFacility FacilityAdding(FacilityAddingDTO objectInfo) {
	String logo = null;
	Location location = new Location(Double.valueOf(objectInfo.longi),Double.valueOf(objectInfo.lat), new Address(objectInfo.street,objectInfo.number,objectInfo.city,Integer.valueOf(objectInfo.zip)));
	SportsFacility sf = new SportsFacility(objectInfo.name,objectInfo.type,location, logo);
	this.facilities.addFacility(sf);
	this.facilities.AddFacilityLogo(sf, objectInfo.imageFile);
	return sf;
}
public SportsFacility GetManagersFacility(String username) {
	Users u = new Users();
	User manager = u.getUser(username);
	return manager.getSportsFacility();
}
public ArrayList<User> GetFacilityTrainers(SportsFacility sf) {
	Trainings train = new Trainings();
	Users u = new Users();
	ArrayList<User> ret = new ArrayList<User>();
	for(Training t : train.getValues()) {
		if(t.getSportsFacility().getName().equals(sf.getName()) && !(ret.contains(u.getUser(t.getTrainer())))) {
				ret.add(u.getUser(t.getTrainer()));
		}
	}
	return ret;
}
public ArrayList<User> GetFacilityCustomers(SportsFacility sf) {
	TrainingHistories train = new TrainingHistories();
	Users u = new Users();
	ArrayList<User> ret = new ArrayList<User>();
	for(TrainingHistory th : train.getValues()){
		if(th.getTraining().getSportsFacility() !=null && th.getTraining().getSportsFacility().getName().equals(sf.getName()) && !(ret.contains(u.getUser(th.getCustomer())))) {
				ret.add(u.getUser(th.getCustomer()));
			
		}
	}
	return ret;
}
}

