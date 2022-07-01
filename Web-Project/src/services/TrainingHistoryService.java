package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import beans.FacilityType;
import beans.SportsFacility;
import beans.TrainingHistories;
import beans.TrainingHistory;
import beans.TrainingType;
import dto.FacilitySearchDTO;
import dto.TrainingSearchDTO;


public class TrainingHistoryService {

private TrainingHistories trainings = new TrainingHistories();
	
	public Collection<TrainingHistory> getAll(){
		return trainings.values();
	}
	public ArrayList<TrainingHistory> getUsersHistories(String username){
		ArrayList<TrainingHistory> users = new ArrayList<TrainingHistory>();
		for(TrainingHistory t : trainings.values()) {
			if(t.getCustomer().equals(username))
				users.add(t);
		}
		return users;
	}
	public ArrayList<TrainingHistory> getManagersHistories(String name){
		ArrayList<TrainingHistory> users = new ArrayList<TrainingHistory>();
		for(TrainingHistory t : trainings.values()) {
			if(t.getTraining().getSportsFacility().getName().equals(name))
				users.add(t);
		}
		return users;
	}
public ArrayList<TrainingHistory> SearchTrainings(TrainingSearchDTO search) {
		
		ArrayList<TrainingHistory> ret = new ArrayList<TrainingHistory>();		
		for(TrainingHistory t : this.trainings.getValues()) {
			if(t.getTraining().getName().toLowerCase().contains(search.name.toLowerCase())){
				if(search.from != null && search.to!=null) {
					if(t.getDateTime().toLocalDate().isBefore(search.to)&& t.getDateTime().toLocalDate().isAfter(search.from)) {
							ret.add(t);
					}
				}else 
					ret.add(t);
			}
		}
		
		return ret;
	}
    public ArrayList<TrainingHistory> getDateAscUsersHistories(String username){
		ArrayList<TrainingHistory> users = new ArrayList<TrainingHistory>();
		for(TrainingHistory t : trainings.values()) {
			if(t.getCustomer().equals(username))
				users.add(t);
		}
		Comparator<TrainingHistory> comparatorAsc = (prod1, prod2) -> prod1.getDateTime()
                .compareTo(prod2.getDateTime());
 
 
        // 2.1 pass above Comparator and sort in ascending order
        Collections.sort(users, comparatorAsc);
		return users;
}
    public ArrayList<TrainingHistory> getDateDiscUsersHistories(String username){
		ArrayList<TrainingHistory> users = new ArrayList<TrainingHistory>();
		for(TrainingHistory t : trainings.values()) {
			if(t.getCustomer().equals(username))
				users.add(t);
		}
		Comparator<TrainingHistory> comparatorDesc = (prod1, prod2) -> prod2.getDateTime()
                .compareTo(prod1.getDateTime());
 
 
        // 2.1 pass above Comparator and sort in ascending order
        Collections.sort(users, comparatorDesc);
		return users;
}
public ArrayList<TrainingHistory> GetGyms() {
		
		ArrayList<TrainingHistory> ret = new ArrayList<TrainingHistory>();		
		for(TrainingHistory th : this.trainings.getValues()) {
			if(th.getTraining().getSportsFacility().getType().equals(FacilityType.GYM))
				ret.add(th);
		}
		return ret;
	}
  public ArrayList<TrainingHistory> GetPools() {
		
	  ArrayList<TrainingHistory> ret = new ArrayList<TrainingHistory>();		
		for(TrainingHistory th : this.trainings.getValues()) {
			if(th.getTraining().getSportsFacility().getType().equals(FacilityType.POOL))
				ret.add(th);
		}
		return ret;
	}
  public ArrayList<TrainingHistory> GetDanceS() {
		
	  ArrayList<TrainingHistory> ret = new ArrayList<TrainingHistory>();		
		for(TrainingHistory th : this.trainings.getValues()) {
			if(th.getTraining().getSportsFacility().getType().equals(FacilityType.DANCESTUDIO))
				ret.add(th);
		}
		return ret;
	}
  public ArrayList<TrainingHistory> GetSportsC() {
		
	  ArrayList<TrainingHistory> ret = new ArrayList<TrainingHistory>();		
		for(TrainingHistory th : this.trainings.getValues()) {
			if(th.getTraining().getSportsFacility().getType().equals(FacilityType.SPORTSCENTER))
				ret.add(th);
		}
		return ret;
	}
  public ArrayList<TrainingHistory> GetGymsT() {
		
	  ArrayList<TrainingHistory> ret = new ArrayList<TrainingHistory>();		
		for(TrainingHistory th : this.trainings.getValues()) {
			if(th.getTraining().getType().equals(TrainingType.GYM))
				ret.add(th);
		}
		return ret;
	}
  public ArrayList<TrainingHistory> GetGroup() {
		
	  ArrayList<TrainingHistory> ret = new ArrayList<TrainingHistory>();		
		for(TrainingHistory th : this.trainings.getValues()) {
			if(th.getTraining().getType().equals(TrainingType.GROUP))
				ret.add(th);
		}
		return ret;
	}
  public ArrayList<TrainingHistory> GetPersonal() {
		
	  ArrayList<TrainingHistory> ret = new ArrayList<TrainingHistory>();		
		for(TrainingHistory th : this.trainings.getValues()) {
			if(th.getTraining().getType().equals(TrainingType.PERSONAL))
				ret.add(th);
		}
		return ret;
	}
}
