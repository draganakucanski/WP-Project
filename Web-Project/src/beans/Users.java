package beans;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.StringTokenizer;

import dao.UserTypeDAO;

public class Users {

	private HashMap<String, User> users = new HashMap<String, User>();
	
	public Users() {
		this(".");
	}
	
	public Users(String path) {
		BufferedReader in = null;
		try {
			File file = new File("static/users.txt");
			in = new BufferedReader(new FileReader(file));
			String line;
			StringTokenizer st;
			while ((line = in.readLine()) != null) {
				line = line.trim();
				if (line.equals("") || line.indexOf('#') == 0) {
					continue;
				}
				st = new StringTokenizer(line, ";");
				while (st.hasMoreTokens()) {
					String username = st.nextToken().trim();
					String password = st.nextToken().trim();
					String firstName = st.nextToken().trim();
					String lastName = st.nextToken().trim();
					String dateOfBirth =  st.nextToken().trim();
					Gender gender = Gender.valueOf(st.nextToken().trim());
					//String typeNameString = st.nextToken().trim(); 
					UserType type = null;
					/*if(typeNameString.equals("null")) {
						type = null;
					}else {
						UserTypeName typeName = UserTypeName.valueOf(typeNameString);
						UserTypeDAO ut = new UserTypeDAO();
						type  = ut.getUserTypeByName(typeName);
					} */
					
					Role role = Role.valueOf(st.nextToken().trim());
					boolean deleted = Boolean.valueOf(st.nextToken().trim());
					String pointsString = st.nextToken().trim(); 
					double points = 0;
					if(pointsString.equals("null")) {
						points = 0;
					}else {
						points = Double.valueOf(pointsString);
						type = getUserType(points);
					} 
					//double points = Double.valueOf(st.nextToken().trim()); 
					String facName = st.nextToken().trim();
					SportsFacility sf = null;
					if(facName.equals("null")) {
						sf = null;
					} else {
						Facilities fac = new Facilities();
						sf = fac.getFacility(facName);
					}
					users.put(username, new User(username, password, firstName, lastName, 
												 dateOfBirth, gender, role, type, deleted,sf, points));
				}
				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				}
				catch (Exception e) { }
			}
		}
	}

	

	/** Vraca kolekciju proizvoda. */
	public Collection<User> getValues() {
		return users.values();
	}

	/** Vraca proizvod na osnovu njegovog id-a. */
	public User getUser(String username) {
		return users.get(username);
	}

	public void addUser(User user) {
		if (users.containsKey(user.getUsername())) {
			return;
		} else {
			this.users.put(user.getUsername(), user);
			saveData();
		}
	}

	public void edit(String username, User u) {
		this.users.put(username, u);
		saveData();
	}

	public void delete(String id) {
		this.users.remove(id);
	}
	public void saveData(){
		
		PrintWriter writer;
		try {
			
			writer = new PrintWriter("static/users.txt", "UTF-8");
			
			for (User user : users.values()) {
				/*
				 * String localDate =user.getDateOfBirth();//For reference DateTimeFormatter
				 * formatter =
				 * DateTimeFormatter.ofPattern("yyyy-MM-dd").withLocale(Locale.ENGLISH); String
				 * date = localDate.format(formatter);
				 */
				if(user.getRole()==Role.CUSTOMER) {
					writer.println(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;null", 
							user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName(),
							user.getDateOfBirth(), user.getGender(),user.getRole(),user.isDeleted(),user.getPointsCollected()));
				}else if(user.getRole()==Role.ADMIN) {
					writer.println(String.format("%s;%s;%s;%s;%s;%s;%s;%s;null;null", 
							user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName(),
							user.getDateOfBirth(), user.getGender(),user.getRole(),user.isDeleted()));
				}else if(user.getRole()==Role.MANAGER) {
					if(user.getSportsFacility()==null) {
						writer.println(String.format("%s;%s;%s;%s;%s;%s;%s;%s;null;null", 
								user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName(),
								user.getDateOfBirth(), user.getGender(),user.getRole(),user.isDeleted()));
					} else {
							writer.println(String.format("%s;%s;%s;%s;%s;%s;%s;%s;null;%s", 
							user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName(),
							user.getDateOfBirth(), user.getGender(),user.getRole(),user.isDeleted(), user.getSportsFacility().getName()));
							}
				}
				else if(user.getRole()==Role.TRAINER) {
					writer.println(String.format("%s;%s;%s;%s;%s;%s;%s;%s;null;null", 
							user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName(),
							user.getDateOfBirth(), user.getGender(),user.getRole(),user.isDeleted()));
				}
				/*
				writer.println(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s", 
						user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName(),
						user.getDateOfBirth(), user.getGender(), user.getType().getUserTypeName(),user.getRole(),user.isDeleted(),user.getPointsCollected(), user.getSportsFacility().getName()));
				*/
			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
	}
	public User findUser(String username) {
		for(User u:users.values()) {
			if(u.getUsername().equals(username))
				return u;
		}
		return null;
	}
	public void updatePoints(String username, Membership m) {
		User customer = findUser(username);
		ArrayList<TrainingHistory> visitsInMembershipTime = new ArrayList<TrainingHistory>();
		TrainingHistories ths = new TrainingHistories();
		for (TrainingHistory th : ths.values()) {
			if(th.getCustomer().equals(username) && th.getDateTime().isAfter(m.getPayDate().atStartOfDay()) && th.getDateTime().isBefore(m.getValidTime()))
				visitsInMembershipTime.add(th);
		}
		double price = m.getPrice();
		int visits = m.getDailyVisit();
		int sumVisits = 0;
		double points = customer.getPointsCollected();
		if(m.getType()==MembershipType.MONTHLY) {
			sumVisits = visits*16;
		}else 
			sumVisits = visits*30*12;
		if( visitsInMembershipTime.size() >= sumVisits/3 ) {
			points+= 133*price/1000*customer.getVisitedFacility().size();
			customer.setPointsCollected(points);
			this.users.put(username, customer);
		}else {
			points -= price/1000*133*4;
			if(points<0) {
				points = 0;
			}
			customer.setPointsCollected(points);
			this.users.put(username, customer);
		} 
	}
	public void setMembership(String username, Membership m) {
		User u = getUser(username);
		u.setMembership(m);
		this.users.put(username, u);
		saveData();
	}
	public UserType getUserType(Double points) {
		UserTypeDAO ut = new UserTypeDAO();
		UserType ret = ut.getUserTypeByName(UserTypeName.BRONZE);
		UserType silver  = ut.getUserTypeByName(UserTypeName.SILVER);
		UserType gold  = ut.getUserTypeByName(UserTypeName.GOLD);
		if(points>= silver.getRequiredPoints()) {
			ret = silver;
		} else if(points >= gold.getRequiredPoints()) {
			ret = gold;
		}
		return ret;
	}
	public void addTrainingHistory(String username, TrainingHistory th) {
		User u = getUser(username);
		ArrayList<TrainingHistory> history = new ArrayList<TrainingHistory>();
		if( u.getHistory() == null) {
			history.add(th);
		} else {
			history = u.getHistory();
			history.add(th);
		}
		u.setHistory(history);
		this.users.put(username, u);
		saveData();
	}
}
