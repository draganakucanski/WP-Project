package services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import beans.Comment;
import beans.FacilityType;
import beans.Membership;
import beans.MembershipType;
import beans.Memberships;
import beans.Role;
import beans.SportsFacility;
import beans.Training;
import beans.TrainingHistories;
import beans.TrainingHistory;
import beans.TrainingType;
import beans.Trainings;
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
	public ArrayList<User> getManagersCustomers(String name){
		ArrayList<User> users = new ArrayList<User>();
		Users u = new Users();
		for(TrainingHistory t : trainings.values()) {
			if(t.getTraining().getSportsFacility().getName().equals(name) && !users.contains(u.getUser(t.getCustomer())))
				users.add(u.getUser(t.getCustomer()));
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
	   if(th.getScheduledFor().isAfter((LocalDateTime.now().plusDays(2)))) {
		   	th.setCanceled(true);
			this.trainings.Edit(th);
	   }
	}
  public boolean SignUp(String dateFor, String customer, String trainingName) {
	  Memberships mems = new Memberships();
	  Membership mem = null;
	  for(Membership m: mems.values()) {
		  if(m.getCustomer().equals(customer) && m.isActive())
			  mem = m;
	  }
	  LocalDateTime scheduledFor = LocalDateTime.parse(dateFor);
	  if(mem==null || mem.isActive()==false || isLimitReached(mem, customer, scheduledFor)) {
		  return false;
	  }
	  LocalDateTime signUpTime = LocalDateTime.now();
	  Trainings trains = new Trainings();
	  Training t = trains.getTraining(trainingName);
	  TrainingHistory th = new TrainingHistory(signUpTime, t, customer, t.getTrainer(), false, scheduledFor);
	  this.trainings.addTrainingHistory(th);
	  return true;
  }
  public boolean isLimitReached(Membership mem, String customer, LocalDateTime sch) {
	   int visits = mem.getDailyVisit();
	   int visitsInMembershipTime = 0;
	   if(mem.getType()==MembershipType.MONTHLY) {
		   visitsInMembershipTime = 16*visits;
	   } else 
		   visitsInMembershipTime = 30*12*visits;
	   int usedToday = 0;
	   for(TrainingHistory th : trainings.values()) {
			   if(th.getCustomer().equals(customer) && th.isCanceled()==false && th.getScheduledFor().toLocalDate().equals(sch.toLocalDate()))
				   usedToday++;
	   }
	   System.out.println(visits);
	   System.out.println(usedToday);
	   if(usedToday>=visits)
		   return true;
	   int usedInMembershipTime = 0;
	   for(TrainingHistory th : trainings.values()) {
			   if(th.getCustomer().equals(customer) && th.isCanceled()==false && th.getScheduledFor().isAfter(mem.getPayDate().atStartOfDay()) && th.getScheduledFor().isBefore(mem.getValidTime()))
				   usedInMembershipTime++;
	  }
	   //provera je l istekla clanarina tad!
	   if(sch.isBefore(mem.getPayDate().atStartOfDay()) || sch.isAfter(mem.getValidTime()))
		   return true;
	   if(usedInMembershipTime>=visitsInMembershipTime)
		   return true;
	   return false;
	}
  public boolean IsFirst(String name, String username) {
	  int count = 0;
	  for(TrainingHistory t : trainings.values()) {
			if(t.getCustomer().equals(username) && t.getTraining().getSportsFacility().getName().equals(name))
				count++;
		}
	  System.out.println(count);
	  if(count == 0)
		  return true;
	  else return false;
  	}
 
}
