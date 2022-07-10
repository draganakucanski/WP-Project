package services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import beans.Comment;
import beans.FacilityType;
import beans.Role;
import beans.SportsFacility;
import beans.TrainingHistories;
import beans.TrainingHistory;
import beans.TrainingType;
import beans.User;
import beans.Users;
import dto.FacilitySearchDTO;
import dto.TrainingSearchDTO;


public class TrainingHistoryService {

private TrainingHistories trainings = new TrainingHistories();
	
	public Collection<TrainingHistory> getAll(){
		return trainings.values();
	}
	public ArrayList<TrainingHistory> getUsersHistories(String username){
		Users u = new Users();
		User user = u.getUser(username);
		if(user.getRole() == Role.CUSTOMER) {
			ArrayList<TrainingHistory> users = new ArrayList<TrainingHistory>();
			for(TrainingHistory t : trainings.values()) {
				if(t.getCustomer().equals(username) && t.getDateTime().isAfter(LocalDateTime.now().minusMonths(1)))
					users.add(t);
			}
			return users;
		}else {
			ArrayList<TrainingHistory> users = new ArrayList<TrainingHistory>();
			for(TrainingHistory t : trainings.values()) {
				if(t.getTrainer().equals(username))
					users.add(t);
			}
			return users;
		}
	}
	/*public ArrayList<TrainingHistory> getTrainersHistories(String username){
		ArrayList<TrainingHistory> users = new ArrayList<TrainingHistory>();
		for(TrainingHistory t : trainings.values()) {
			if(t.getTrainer().equals(username))
				users.add(t);
		}
		return users;
	} */
	public ArrayList<TrainingHistory> getManagersHistories(String name){
		ArrayList<TrainingHistory> users = new ArrayList<TrainingHistory>();
		for(TrainingHistory t : trainings.values()) {
			if(t.getTraining().getSportsFacility().getName().equals(name))
				users.add(t);
		}
		return users;
	}
public ArrayList<TrainingHistory> SearchTrainings(TrainingSearchDTO search, String username) {
		
		ArrayList<TrainingHistory> ret = new ArrayList<TrainingHistory>();		
		for(TrainingHistory t : getUsersHistories(username)) {
			if(t.getTraining().getSportsFacility().getName().toLowerCase().contains(search.name.toLowerCase())){
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
public ArrayList<TrainingHistory> SearchManagerTrainings(TrainingSearchDTO search, String username) {
	
	ArrayList<TrainingHistory> ret = new ArrayList<TrainingHistory>();		
	for(TrainingHistory t : getManagersHistories(username)) {
		if(t.getTraining().getSportsFacility().getName().toLowerCase().contains(search.name.toLowerCase())){
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
		//ArrayList<TrainingHistory> users = new ArrayList<TrainingHistory>();
		/*for(TrainingHistory t : trainings.values()) {
			if(t.getCustomer().equals(username))
				users.add(t);
		} */
    	ArrayList<TrainingHistory> users = getUsersHistories(username);
		Comparator<TrainingHistory> comparatorAsc = (prod1, prod2) -> prod1.getDateTime()
                .compareTo(prod2.getDateTime());
 
 
        // 2.1 pass above Comparator and sort in ascending order
        Collections.sort(users, comparatorAsc);
		return users;
}
    public ArrayList<TrainingHistory> getDateDiscUsersHistories(String username){
		/*ArrayList<TrainingHistory> users = new ArrayList<TrainingHistory>();
		for(TrainingHistory t : trainings.values()) {
			if(t.getCustomer().equals(username))
				users.add(t);
		} */
    	ArrayList<TrainingHistory> users = getUsersHistories(username);
		Comparator<TrainingHistory> comparatorDesc = (prod1, prod2) -> prod2.getDateTime()
                .compareTo(prod1.getDateTime());
 
 
        // 2.1 pass above Comparator and sort in ascending order
        Collections.sort(users, comparatorDesc);
		return users;
}
    public ArrayList<TrainingHistory> getManagerDateAscUsersHistories(String username){
		//ArrayList<TrainingHistory> users = new ArrayList<TrainingHistory>();
		/*for(TrainingHistory t : trainings.values()) {
			if(t.getCustomer().equals(username))
				users.add(t);
		} */
    	ArrayList<TrainingHistory> users = getManagersHistories(username);
		Comparator<TrainingHistory> comparatorAsc = (prod1, prod2) -> prod1.getDateTime()
                .compareTo(prod2.getDateTime());
 
 
        // 2.1 pass above Comparator and sort in ascending order
        Collections.sort(users, comparatorAsc);
		return users;
}
    public ArrayList<TrainingHistory> getManagerDateDiscUsersHistories(String username){
		/*ArrayList<TrainingHistory> users = new ArrayList<TrainingHistory>();
		for(TrainingHistory t : trainings.values()) {
			if(t.getCustomer().equals(username))
				users.add(t);
		} */
    	ArrayList<TrainingHistory> users = getManagersHistories(username);
		Comparator<TrainingHistory> comparatorDesc = (prod1, prod2) -> prod2.getDateTime()
                .compareTo(prod1.getDateTime());
 
 
        // 2.1 pass above Comparator and sort in ascending order
        Collections.sort(users, comparatorDesc);
		return users;
}
public ArrayList<TrainingHistory> GetGyms(String username) {
		
		ArrayList<TrainingHistory> ret = new ArrayList<TrainingHistory>();		
		for(TrainingHistory th : getUsersHistories(username)) {
			if(th.getTraining().getSportsFacility().getType().equals(FacilityType.GYM))
				ret.add(th);
		}
		return ret;
	}
  public ArrayList<TrainingHistory> GetPools(String username) {
		
	  ArrayList<TrainingHistory> ret = new ArrayList<TrainingHistory>();		
		for(TrainingHistory th : getUsersHistories(username)) {
			if(th.getTraining().getSportsFacility().getType().equals(FacilityType.POOL))
				ret.add(th);
		}
		return ret;
	}
  public ArrayList<TrainingHistory> GetDanceS(String username) {
		
	  ArrayList<TrainingHistory> ret = new ArrayList<TrainingHistory>();		
		for(TrainingHistory th : getUsersHistories(username)) {
			if(th.getTraining().getSportsFacility().getType().equals(FacilityType.DANCESTUDIO))
				ret.add(th);
		}
		return ret;
	}
  public ArrayList<TrainingHistory> GetSportsC(String username) {
		
	  ArrayList<TrainingHistory> ret = new ArrayList<TrainingHistory>();		
		for(TrainingHistory th : getUsersHistories(username)) {
			if(th.getTraining().getSportsFacility().getType().equals(FacilityType.SPORTSCENTER))
				ret.add(th);
		}
		return ret;
	}
  public ArrayList<TrainingHistory> GetGymsT(String username) {
		
	  ArrayList<TrainingHistory> ret = new ArrayList<TrainingHistory>();		
		for(TrainingHistory th : getUsersHistories(username)) {
			if(th.getTraining().getType().equals(TrainingType.GYM))
				ret.add(th);
		}
		return ret;
	}
  public ArrayList<TrainingHistory> GetGroup(String username) {
		
	  ArrayList<TrainingHistory> ret = new ArrayList<TrainingHistory>();		
		for(TrainingHistory th : getUsersHistories(username)) {
			if(th.getTraining().getType().equals(TrainingType.GROUP))
				ret.add(th);
		}
		return ret;
	}
  public ArrayList<TrainingHistory> GetPersonal(String username) {
		
	  ArrayList<TrainingHistory> ret = new ArrayList<TrainingHistory>();		
		for(TrainingHistory th : getUsersHistories(username)) {
			if(th.getTraining().getType().equals(TrainingType.PERSONAL))
				ret.add(th);
		}
		return ret;
	}
  public ArrayList<TrainingHistory> GetManagerGymsT(String username) {
		
	  ArrayList<TrainingHistory> ret = new ArrayList<TrainingHistory>();		
		for(TrainingHistory th : getManagersHistories(username)) {
			if(th.getTraining().getType().equals(TrainingType.GYM))
				ret.add(th);
		}
		return ret;
	}
  public ArrayList<TrainingHistory> GetManagerGroup(String username) {
		
	  ArrayList<TrainingHistory> ret = new ArrayList<TrainingHistory>();		
		for(TrainingHistory th : getManagersHistories(username)) {
			if(th.getTraining().getType().equals(TrainingType.GROUP))
				ret.add(th);
		}
		return ret;
	}
  public ArrayList<TrainingHistory> GetManagerPersonal(String username) {
		
	  ArrayList<TrainingHistory> ret = new ArrayList<TrainingHistory>();		
		for(TrainingHistory th : getManagersHistories(username)) {
			if(th.getTraining().getType().equals(TrainingType.PERSONAL))
				ret.add(th);
		}
		return ret;
	}
  public void Cancel(TrainingHistory th) {
	   if(th.getDateTime().isAfter((LocalDateTime.now().plusDays(2)))) {
		   	th.setCanceled(true);
			this.trainings.Edit(th);
	   }
	}
}
