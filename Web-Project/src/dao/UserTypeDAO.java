package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import beans.UserType;
import beans.UserTypeName;

public class UserTypeDAO {

private static Map<UserTypeName, UserType> userTypes = new HashMap<>();
	
	public UserTypeDAO() {
		loadUserTypes();
	}
	
	public Map<UserTypeName, UserType> getUserTypes() {
		return userTypes;
	}
	private void loadUserTypes() {
		BufferedReader in = null;
		try {
			File file = new File("static/userTypes.txt");
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
					UserTypeName typeName = UserTypeName.valueOf(st.nextToken().trim());
					double discount = Double.valueOf(st.nextToken().trim()); 
					double requiredPoints = Double.valueOf(st.nextToken().trim());
					userTypes.put(typeName, new UserType(typeName, discount, requiredPoints));
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
	public static UserType getUserTypeByName(UserTypeName name) {
		return userTypes.get(name);
	}
}
