package beans;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
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
					String typeNameString = st.nextToken().trim(); 
					UserType type = null;
					if(typeNameString.equals("null")) {
						type = null;
					}else {
						UserTypeName typeName = UserTypeName.valueOf(typeNameString);
						UserTypeDAO ut = new UserTypeDAO();
						type  = ut.getUserTypeByName(typeName);
					}
					Role role = Role.valueOf(st.nextToken().trim());
					boolean deleted = Boolean.valueOf(st.nextToken().trim());
					double points = Double.valueOf(st.nextToken().trim()); 
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
					writer.println(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;null", 
							user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName(),
							user.getDateOfBirth(), user.getGender(), user.getType().getUserTypeName(),user.getRole(),user.isDeleted(),user.getPointsCollected()));
				}else if(user.getRole()==Role.ADMIN) {
					writer.println(String.format("%s;%s;%s;%s;%s;%s;null;%s;%s;%s;null", 
							user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName(),
							user.getDateOfBirth(), user.getGender(),user.getRole(),user.isDeleted(),user.getPointsCollected()));
				}else if(user.getRole()==Role.MANAGER) {
					writer.println(String.format("%s;%s;%s;%s;%s;%s;null;%s;%s;%s;%s", 
							user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName(),
							user.getDateOfBirth(), user.getGender(),user.getRole(),user.isDeleted(),user.getPointsCollected(), user.getSportsFacility().getName()));
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
	
}
